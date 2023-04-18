package com.tuling.mall.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author Fox
 *
 */
@Configuration
@MapperScan("com.jinbiao.cloud.mbg.*")
public class MybatisConfig {
    
    /**
     * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
     * 原生datasource前缀取"spring.datasource"
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/**/*-mapper.xml"));
        
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        //使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseGeneratedKeys(true);
        //使用列别名替换列名
        configuration.setUseColumnLabel(true);
        //自动使用驼峰命名属性映射字段，如userId ---> user_id
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        
        return factoryBean.getObject();
    }
    
}

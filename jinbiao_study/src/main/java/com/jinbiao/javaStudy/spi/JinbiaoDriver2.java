package com.jinbiao.javaStudy.spi;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Author：Jinbiao
 * @Date：2023/5/26 21:49
 * @Desc：
 */
@Slf4j
public class JinbiaoDriver2 implements Driver {

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        log.info("Jinbiao2 驱动获取数据库连接成功...");
        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}

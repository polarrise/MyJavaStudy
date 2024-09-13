package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.Account;
import com.jinbiao.cloud.mbg.model.AccountExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {
    long countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);


    /**
     * 查询账户
     * @param userId
     * @return
     */
    @Select("select id, user_id, money from account WHERE user_id = #{userId}")
    Account selectByUserId(@Param("userId") String userId);

    /**
     * 扣减余额
     * @param userId 用户id
     * @param money 要扣减的金额
     * @return
     */
    @Update("update account set money=money-#{money} where user_id = #{userId}")
    int reduceBalance(@Param("userId") Integer userId, @Param("money") BigDecimal money);

    /**
     * 冻结金额  Try: 账户余额-支出余额，冻结余额+支出余额
     * @param userId 用户id
     * @param money 要扣减的金额
     * @return
     */
    @Update("update account set money=money-#{money},freeze_money=freeze_money+#{money} where user_id = #{userId}")
    int freezeBalance(@Param("userId") String userId, @Param("money") BigDecimal money);

    /**
     * 扣减冻结金额  Confirm: 冻结余额-支出余额 （真正的扣减余额）
     * @param userId 用户id
     * @param money 要扣减的金额
     * @return
     */
    @Update("update account set freeze_money=freeze_money-#{money} where user_id = #{userId}")
    int reduceFreezeBalance(@Param("userId") String userId, @Param("money") Integer money);

    /**
     * 解冻金额  Cancel: 账户余额+支出余额，冻结余额-支出余额
     * @param userId 用户id
     * @param money 要扣减的金额
     * @return
     */
    @Update("update account set money=money+#{money},freeze_money=freeze_money-#{money} where user_id = #{userId}")
    int unfreezeBalance(@Param("userId") String userId, @Param("money") Integer money);
}
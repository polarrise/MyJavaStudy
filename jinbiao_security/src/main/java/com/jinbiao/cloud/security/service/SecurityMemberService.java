package com.jinbiao.cloud.security.service;

import com.jinbiao.cloud.mbg.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author jinbiao
 */
public interface SecurityMemberService {

    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 获取当前用户机构ID
     */
    Long getCurrentEventId();

    /**
     * 删除用户缓存
     */
    void delMember(Long memberId);

    void delMember(String username);

}

package com.jinbiao.cloud.security.service.impl;

import com.jinbiao.cloud.common.service.RedisService;
import com.jinbiao.cloud.mbg.mapper.UmsMemberMapper;
import com.jinbiao.cloud.mbg.model.UmsMember;
import com.jinbiao.cloud.mbg.model.UmsMemberExample;
import com.jinbiao.cloud.security.domain.MemberDetails;
import com.jinbiao.cloud.security.service.SecurityMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author jinbiao
 */
@Service
@Slf4j
public class SecurityMemberServiceImpl implements SecurityMemberService {

    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private RedisService redisService;
    @Value("${redis.database:mall}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common:86400}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.member:ums:member}")
    private String REDIS_KEY_MEMBER;

    @Override
    public UmsMember getByUsername(String username) {
        return getMember(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if (member != null) {
            return new MemberDetails(member);
        } else {
            return null;
        }
    }

    @Override
    public UmsMember getCurrentMember() {
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication auth = ctx.getAuthentication();
            if (auth == null) {
                return new UmsMember();
            }
            MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
            return memberDetails.getUmsMember();
        } catch (Exception e) {
            log.error("获取当前用户异常:{},{}", e.getMessage(), e);
            return new UmsMember();
        }
    }

    @Override
    public Long getCurrentEventId() {
        UmsMember member = getCurrentMember();
        if (member == null) return null;
        return member.getEventId();
    }

    @Override
    public void delMember(Long memberId) {
        UmsMember umsMember = memberMapper.selectByPrimaryKey(memberId);
        if (umsMember != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + umsMember.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delMember(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + username;
        redisService.del(key);
    }

    private UmsMember getMember(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + username;
        UmsMember redisMember = null;
        try {
            redisMember = (UmsMember) redisService.get(key);
        } catch (Exception ignored) {
        }
        if (redisMember == null) {
            UmsMemberExample example = new UmsMemberExample();
            example.createCriteria().andUsernameEqualTo(username).andStatusEqualTo(1);
            List<UmsMember> memberList = memberMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(memberList)) {
                UmsMember member = memberList.get(0);
                setMember(member);
                return member;
            }
        } else {
            UmsMember umsMember = new UmsMember();
            BeanUtils.copyProperties(redisMember, umsMember);
            return umsMember;
        }
        return null;
    }

    private void setMember(UmsMember member) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getUsername();
        UmsMember redisMember = new UmsMember();
        BeanUtils.copyProperties(member, redisMember);
        redisService.set(key, redisMember, REDIS_EXPIRE);
    }
}

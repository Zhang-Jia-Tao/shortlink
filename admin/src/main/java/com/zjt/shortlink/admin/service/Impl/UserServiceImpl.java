package com.zjt.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.shortlink.admin.common.convention.exception.ClientException;
import com.zjt.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.zjt.shortlink.admin.dao.entity.UserDO;
import com.zjt.shortlink.admin.dao.mapper.UserMapper;
import com.zjt.shortlink.admin.dto.resp.UserRespDTO;
import com.zjt.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现层
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        UserRespDTO result = new UserRespDTO();

        if(userDO != null){
            BeanUtils.copyProperties(userDO, result);       // 此方法需要判空才可以，否则会报错
            return result;
        } else {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
            //return null;
        }

    }
}
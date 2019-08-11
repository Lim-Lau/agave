package com.ansimpleasy.agave.ans.mapper.user;

import com.ansimpleasy.agave.ans.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liucan
 * @since 2019-08-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User fetchUserByName(@Param("name") String name);

}


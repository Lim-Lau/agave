package com.ansimpleasy.agave.ans.entity;

import com.ansimpleasy.agave.ans.entity.base.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */
@Data
public class UserRole extends BaseEntity {

    private long userId;

    private long roleId;

}

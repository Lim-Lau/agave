package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.entity.Log;
import com.ansimpleasy.ans.mapper.log.LogMapper;
import com.ansimpleasy.ans.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucan
 * @since 2019-12-17
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}

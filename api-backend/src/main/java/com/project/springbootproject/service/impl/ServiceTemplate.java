package com.project.springbootproject.service.impl;

import com.google.common.base.Stopwatch;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author frank.xu
 * @createDate 2023/11/1 20:29
 */
@Log
public abstract class ServiceTemplate<T, R> {
    /**
     * 模板方法,统一暴露执行入口
     *
     * @param request
     * @return
     */
    public R process(T request) {
        //1.打印入口日志
        log.info("start invoke,request= " + request);
        //开始计时,用于日志打印记录耗时
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            //2.校验参数
            checkParam(request);
            //3.具体业务实现逻辑
            R response = doProcess(request);
            //4.打印出口日志
            long timeCost = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            log.info("end invoke,response=" + response + "costTime=" + timeCost);
            return response;
        } catch (Exception e) {
            //打印错误日志
            log.info("error invoke,exception=" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * 参数校验，交给具体实现
     *
     * @param request
     */
    protected abstract void checkParam(T request);

    /**
     * 业务逻辑方法，交给具体实现
     *
     * @param request
     * @return
     */
    protected abstract R doProcess(T request);
}

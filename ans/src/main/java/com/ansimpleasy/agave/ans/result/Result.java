package com.ansimpleasy.agave.ans.result;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.util.NutMap;

import java.util.Iterator;
import java.util.Map;

/**
 * @author LiuCan
 * @date 2019/8/9 14:59
 */
public class Result {
        public enum OperationState {
            /**
             * 成功
             */
            SUCCESS("成功", 0),
            /**
             * 失败
             */
            FAIL("失败", 1),
            /**
             * 异常
             */
            EXCEPTION("异常发生", -1);

            String mgs;

            int code;

            /**
             * @param mgs
             * @param code
             */
            private OperationState(String mgs, int code) {
                this.mgs = mgs;
                this.code = code;
            }

            /**
             * @return the mgs
             */
            public String getMgs() {
                return mgs;
            }

            /**
             * @return the code
             */
            public int getCode() {
                return code;
            }

        }

        /**
         * 创建一个异常结果
         *
         * @return 一个异常结果实例,不携带异常信息
         */
        public static Result exception() {
            return Result.me().setOperationState(Result.OperationState.EXCEPTION);
        }

        /**
         * 创建一个异常结果
         *
         * @param e 异常
         * @return 一个异常结果实例,包含参数异常的信息
         */
        public static Result exception(Exception e) {
            return Result.exception(e.getMessage());
        }

        /**
         * 创建一个异常结果
         *
         * @param msg 异常信息
         * @return 一个异常结果实例,不携带异常信息
         */
        public static Result exception(String msg) {
            return Result.exception().setErrors(msg);
        }

        /**
         * 创建一个带失败信息的result
         *
         * @param reason 失败原因
         * @return result实例
         */
        public static Result fail(Object... reason) {
            return Result.me().setOperationState(Result.OperationState.FAIL).setErrors(reason);
        }

        /**
         * 获取一个result实例
         *
         * @return 一个不携带任何信息的result实例
         */
        public static Result me() {
            return new Result();
        }

        /**
         * 创建一个成功结果
         *
         * @return result实例状态为成功无数据携带
         */
        public static Result success() {
            return Result.me().setOperationState(Result.OperationState.SUCCESS);
        }

        /**
         * 创建一个成功结果
         *
         * @param data 需要携带的数据
         * @return result实例状态为成功数据位传入参数
         */
        public static Result success(Map data) {
            return Result.success().setData(data);
        }

        /**
         * 操作结果数据 假设一个操作要返回很多的数据 一个用户名 一个产品 一个相关产品列表 一个产品的评论信息列表 我们以key
         * value形式进行保存，页面获取data对象读取其对于的value即可
         */
        private NutMap data = new NutMap();

        /**
         * 带状态的操作 比如登录有成功和失败
         */
        private Result.OperationState operationState = Result.OperationState.SUCCESS;

        private Object[] errors;

        /**
         * @return the errors
         */
        public Object[] getErrors() {
            return errors;
        }

        public Result setErrors(Object... errors) {
            this.errors = errors;
            return this;
        }

        public Result() {
            super();
        }

        public Result(Result.OperationState operationState, Map data) {
            super();
            this.operationState = operationState;
            this.data = NutMap.WRAP(data);
        }

        /**
         * 添加更多的数据
         *
         * @param data 待添加的数据
         * @return 结果实例
         */
        public Result addData(Map<String, Object> data) {
            Iterator iterator = data.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                this.data.put(key, data.get(key));
            }
            return this;
        }

        public Result addData(String key, Object object) {
            if (this.data == null) {
                data = new NutMap();
            }
            data.put(key, object);
            return this;
        }

        public Result clear() {
            this.operationState = Result.OperationState.SUCCESS;
            if (data != null) {
                this.data.clear();
            }
            return this;
        }

        public NutMap getData() {
            return data;
        }

        public OperationState getOperationState() {
            return operationState;
        }

        public boolean isSuccess() {
            return getOperationState() == Result.OperationState.SUCCESS;
        }

        public Result setData(Map<String, Object> data) {
            this.data = NutMap.WRAP(data);
            return this;
        }

        public Result setOperationState(OperationState operationState) {
            this.operationState = operationState;
            return this;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return Json.toJson(this, JsonFormat.forLook());
        }
    }



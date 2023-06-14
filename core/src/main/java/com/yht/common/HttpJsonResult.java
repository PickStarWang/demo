package com.yht.common;


import com.alibaba.fastjson.JSONObject;
import com.yht.page.PageResult;

import java.io.Serializable;

/**
 * 返回结果
 * @param <T>
 */
public class HttpJsonResult<T> implements Serializable {


    private static final long serialVersionUID = 2460821510392836839L;
    public static final String PARAM_ERROR = "参数错误";
    public static final String USER_IS_NULL = "用户不存在";
    public static final String PARAM_LACK = "缺少请求参数";
    public static final String SYS_ERROR = "系统错误";
    public static final String ACCOUNT_IS_NULL = "请重新登录";
    public static final String FREQUENT_OPERATION = "操作频繁,请稍后重试";
    public static final String ACCOUNT_IS_DISABLED = "账号被禁用";
    public static final String TOO_MANY_OPERATORS = "操作人数过多,请稍后重试";
    public static final String REQUIRED_CANNOT_BE_NULL  = "必填项不能为空";
    public static final String OPERATION_FAILED = "操作失败";

    /**
     * 通用失败码
     */
    public static final Integer DEFAULT_FAIL_CODE = 1001;
    /**
     * app未实名状态
     */
    public static final Integer NO_APP_AUTH_CODE = 1002;
    /**
     * app未实名状态
     */
    public static final Integer NO_REGISTER_CODE = 1009;
    /**
     * 验证码错误code
     */
    public static final Integer NO_QR_CODE_ERROR_CODE = 1003;
    /**
     * token无效
     */
    public static final Integer NO_USER_TOKEN = 1010;




    public HttpJsonResult(){

    }

    public HttpJsonResult(Boolean success, String msg){
        this.success=success;
        this.msg=msg;
    }
    public HttpJsonResult(Boolean success, String msg, Integer code){
        this.success=success;
        this.msg=msg;
        this.code=code;
    }
    public HttpJsonResult(Boolean success, String msg, Integer code, T data, Long total){
        this.success=success;
        this.msg=msg;
        this.code=code;
        this.data=data;
        this.total=total;
    }

    public static HttpJsonResult success(){
        return new HttpJsonResult(true,null,0);
    }
    public static HttpJsonResult success(Object data){
        return new HttpJsonResult(true,null,0,JSONObject.toJSON(data),null);
    }
    public static HttpJsonResult success(Integer code, String mes){
        return new HttpJsonResult(true,mes,code);
    }
    public static HttpJsonResult fail(){
        return new HttpJsonResult(false,null);
    }
    public static HttpJsonResult fail(String mes){
        return new HttpJsonResult(false,mes,DEFAULT_FAIL_CODE);
    }
    public static HttpJsonResult fail(Integer code, String mes){
        return new HttpJsonResult(false,mes,code);
    }
    public static HttpJsonResult fail(Integer code, String mes, Object data){
        return new HttpJsonResult(false,mes,code,JSONObject.toJSON(data),null);
    }

    public static HttpJsonResult failLogin(){
        //重新登陆。
        return new HttpJsonResult(false,"请重新登录",NO_USER_TOKEN);
    }
    public static HttpJsonResult notRegister(String mes){
        //重新登陆。
        return new HttpJsonResult(false,mes,NO_REGISTER_CODE);
    }
    public static HttpJsonResult successPage(Object data, Long total){
        return new HttpJsonResult(true,null,0, JSONObject.toJSON(data),total);
    }

    public static HttpJsonResult success(PageResult data){
        return new HttpJsonResult(true,null,0, JSONObject.toJSON(data.getResults()),data.getTotal());
    }
    private Boolean success=true;

    private T data;

    private String msg;

    private Long total;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}

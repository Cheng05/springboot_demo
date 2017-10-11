package com.test.web.comm;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by shadongjun on 2016/9/29.
 */
public class ReturnValue<T> {

    public final static int STATUS_SUCCESS=100;
    public final static int STATUS_ERROR=-100;

    public final static String  MSG_BUSINESS_NAME_EXIST="企业名称已经存在";
    public final static String MSG_MERCHANT_NAME_EXIST="商户名称已经存在";
    public final static String MSG_COMMUNITY_EXIST="社区已经存在";
    public final static String MSG_COMMUNITY_NOT_EXIST="社区不存在";
    public final static String MSG_BUSINESS_CAR_EXIST="企业车辆已经存在";
    public final static String MSG_BUSINESS_CAR_NOT_EXIST="企业车辆不存在";
    public final static String MSG_BUSINESS_DRIVER_EXIST="企业司机已经存在";
    public final static String MSG_BUSINESS_DRIVER_NOT_EXIST="企业司机不存在";

    public final static String MSG_ERR_NOEXISTUSER = "用户不存在";
    public final static String MSG_ERR_PASSWORD = "账号和密码不匹配";

    public final static String MSG_PHONE_NOTEXIST = "手机号不存在";
    public final static String MSG_PHONE_CODE_ERROR = "验证码错误";
    public final static String MSG_PHONE_CODE_FREQUENCY = "发送验证码过于频繁";
    public final static String MSG_PASS_ERROR= "密码错误";
    public final static String MSG_ACCOUNT_NOTEXIST = "账号不存在";
    public final static String MSG_PASS_NULL = "密码为空";
    public final static String MSG_CUSTOMER_EXIST = "乘客已存在";
    public final static String MSG_CUSTOMER_NOT_EXIST = "乘客不存在";
    public final static String MSG_MERCHANT_EXIST = "商户已存在";
    public final static String MSG_MERCHANT_NOT_EXIST = "商户不存在";

    public final static String MSG_BUSINESS_EXIST = "企业已存在";
    public final static String MSG_BUSINESS_NOT_EXIST = "企业不存在";

    public final static String MSG_SUCCESS = "操作成功";
    public final static String MSG_ERR_SYSTEM = "系统出错";
    public final static String MSG_PARAM_ERROR = "参数错误";
    public final static String RUTES_EXISTS= "线路已经存在";
    public final static String RUTES_NOT_EXISTS= "线路不存在";
    public final static String MSG_NO_DATA= "没有数据";


    public ReturnValue() {

    }

    public ReturnValue(String msg) {
        this.status = STATUS_ERROR;
        this.msg = msg;

    }




    public void setErrMsg(String msg){
        this.status = STATUS_ERROR;
        this.msg = msg;
    }



    public ReturnValue(List<T> list, int currpage, int totalrecords, int totalpages) {
        this.list = list;
        this.currpage = currpage;
        this.totalrecords = totalrecords;
        this.totalpages = totalpages;
    }

    @ApiModelProperty(value = "状态码：100 成功 -100 失败 ", required = true)
    private int status = STATUS_SUCCESS;//状态码
    @ApiModelProperty(value = "返回结果状态描述", required = true)
    String msg = MSG_SUCCESS;//描述


    @ApiModelProperty(value = "返回数组信息", required = true)
    List<T> list;//返回数组信息
    @ApiModelProperty(value = "系统报错信息", required = true)
    String systemerrormsg;//系统报错信息
    @ApiModelProperty(value = "当前页数", required = true)
    private int currpage = 0;	//当前页数
    @ApiModelProperty(value = "全记录数", required = true)
    private int totalrecords = 0;	//
    @ApiModelProperty(value = "全页数", required = true)
    private int totalpages = 0;  //


    @ApiModelProperty(value = "返回结果对象", required = true)
    private T data;
    @ApiModelProperty(value = "返回结果对象", required = true)
    private T object;

    @ApiModelProperty(value = "token", required = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }


    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getSystemerrormsg() {
        return systemerrormsg;
    }

    public void setSystemerrormsg(String systemerrormsg) {
        this.systemerrormsg = systemerrormsg;
    }

    public int getCurrpage() {
        return currpage;
    }

    public void setCurrpage(int currpage) {
        this.currpage = currpage;
    }

    public int getTotalrecords() {
        return totalrecords;
    }

    public void setTotalrecords(int totalrecords) {
        this.totalrecords = totalrecords;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }


}

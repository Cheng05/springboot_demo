package com.test.web.comm;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * Created by shadongjun on 2016/9/29.
 */
public class InputValue<T> {

    @ApiModelProperty(value = "用户ID")
    private int userid;
    private String token;
    @ApiModelProperty(value = "参数")
    private T reqdata;
    private int carorderid;
    private int modifytype;
    private String sidx;
    private String sord;
    @ApiModelProperty(value = "当前页数")
    private int pageNumber = 1;
    @ApiModelProperty(value = "每页显示数量")
    private int pageSize =20;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    List<T> list;

    public T getReqdata() {
        return reqdata;
    }

    public void setReqdata(T reqdata) {
        this.reqdata = reqdata;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public int getCarorderid() {
        return carorderid;
    }

    public void setCarorderid(int carorderid) {
        this.carorderid = carorderid;
    }

    public int getModifytype() {
        return modifytype;
    }

    public void setModifytype(int modifytype) {
        this.modifytype = modifytype;
    }
}

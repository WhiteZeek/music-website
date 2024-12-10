package com.domain;
//管理员

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import java.io.Serializable;
//接入Serializable接口以序列化数据，便于数据传输
public class admin implements Serializable
{
    private Integer id;//管理员id
    private String name;//用户名
    private String password;//密码

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getid()
    {
        return id;
    }
    public void setid(Integer id)
    {
        this.id=id;
    }
    
}

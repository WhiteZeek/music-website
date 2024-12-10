package com.service;

import java.util.List;

import com.domain.consumer;

// 前端用户服务接口
public interface consumerService {
    public boolean insert(consumer c);// 增加

    public boolean update(consumer c);// 修改

    public boolean delete(Integer id);// 删除

    public consumer selectByPrimaryKey(Integer id);// 根据id查找

    public List<consumer> allconsumer();// 查询所有用户

    public int verifyPassword(String username, String password);// 验证密码

    public consumer getByUsername(String username);// 根据username查找
}

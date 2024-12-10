package com.service.implement;
//用户服务实现类

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.consumer;
import com.service.consumerService;
import com.dao.consumerMapper;

@Service
public class consumerServiceI implements consumerService
{
    @Autowired// 将类成员变量、方法及构造函数进行标注，实现自动装配
    private consumerMapper cm;
    @Override
    public boolean insert(consumer c) {
        // TODO Auto-generated method stub
        return cm.insert(c)>0;
    }

    @Override
    public boolean update(consumer c) {
        // TODO Auto-generated method stub
        return cm.update(c)>0;
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        return cm.delete(id)>0;
    }

    @Override
    public consumer selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return cm.selectByPrimaryKey(id);
    }

    @Override
    public List<consumer> allconsumer() {
        // TODO Auto-generated method stub
        return cm.allConsumer();
    }

    @Override
    public int verifyPassword(String username, String password) {
        // TODO Auto-generated method stub
        return cm.verifyPassword(username, password);
    }

    @Override
    public consumer getByUsername(String username) {
        // TODO Auto-generated method stub
        return cm.getByUsername(username);
    }

}

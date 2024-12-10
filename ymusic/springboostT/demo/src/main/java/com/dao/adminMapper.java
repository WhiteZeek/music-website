package com.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface adminMapper {
    public int verify(String username,String password);//登录验证
}

package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.domain.recommend;
@Repository
public interface recommendMapper {
    public int insert(recommend r);// 增加
    public List<String> getRecommend(String userid);//获取推荐
}

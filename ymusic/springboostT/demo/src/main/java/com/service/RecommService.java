package com.service;
import java.util.List;
import com.domain.Recommender;
public interface RecommService{
    public List<Integer> recommmendByid(Integer id);//通过用户矩阵推荐
    public boolean setRecommendList(String userid,String songid);//推荐更新
    public List<String> getRecommend(String userid);//获取推荐歌曲 
}
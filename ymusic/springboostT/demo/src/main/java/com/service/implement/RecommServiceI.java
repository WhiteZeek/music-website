package com.service.implement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.collectMapper;
import com.dao.recommendMapper;
import com.domain.Recommender;
import com.domain.collect;
import com.domain.recommend;
import com.service.RecommService;
@Service
public class RecommServiceI implements RecommService {
    @Autowired
    private collectMapper cm;

    @Autowired
    private recommendMapper rm;

    @Override
    public List<Integer> recommmendByid(Integer id){
        List<collect> collects = cm.allCollect();//获取收藏
        List<Map<String, Object>> collectMaps = new ArrayList<>();
        for (collect c : collects) {
            Map<String, Object> collectMap = new HashMap<>();
            collectMap.put("id", c.getId());
            collectMap.put("userId", c.getUserId());
            collectMap.put("type", c.getType());
            collectMap.put("songId", c.getsongId());
            collectMap.put("style", c.getStyle());
            collectMaps.add(collectMap);
        }
        Recommender recommend = new Recommender(collectMaps);
        List<Integer> recommendedSongs = recommend.recommend(id); // 推荐列表
        return recommendedSongs;
    }

    @Override
    public boolean setRecommendList(String userid,String songid){
        recommend r=new recommend();
        r.setUserId(userid);
        r.setsongId(songid);
        return rm.insert(r)>0;
    }

    @Override
    public List<String> getRecommend(String userid)
    {
        List<String> t=rm.getRecommend(userid);
        System.out.println(t);
        return t;
    }
}

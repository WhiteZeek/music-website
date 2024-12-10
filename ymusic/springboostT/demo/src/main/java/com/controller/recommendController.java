package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.service.RecommService;

@RestController
@RequestMapping("/recommend")
public class recommendController 
{
    @Autowired
    private RecommService rs;
    //推荐
    @RequestMapping(value = "/recommendById", method = RequestMethod.GET)
    public Object recommand(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("userId").trim());
        // List<Integer> recommmendByid
        List<Integer> l =rs.recommmendByid(id);
        return l;
    }
    //更新推荐列表
    @RequestMapping(value = "/setRecommend", method = RequestMethod.POST)
    public Object setrecommend(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userid=request.getParameter("userId").trim();
        String songid=request.getParameter("songId").trim();
        if(rs.setRecommendList(userid,songid))
        {
            jsonObject.put("code", 1);
        }
        else
        {
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getRecommend", method = RequestMethod.GET)
    public Object getrecommend(int userId){
        String userid=String.valueOf(userId);
        return rs.getRecommend(userid);
    }
}

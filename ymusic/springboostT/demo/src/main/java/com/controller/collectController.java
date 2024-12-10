package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.service.CollectService;
import com.domain.collect;

@RestController
@RequestMapping("/collect")
public class collectController {
    @Autowired
    private CollectService CollectService;

    //添加收藏
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String userId = request.getParameter("userId");     //用户id
        String type = request.getParameter("type");         //收藏类型（0歌曲1歌单）
        String songId = request.getParameter("songId");     //歌 id
        String style = request.getParameter("style");       //歌曲风格
        if(songId==null||songId.equals("")){
            json.put("code",0);
            json.put("message","收藏歌曲不存在");
            return json;
        }
        if(CollectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId),Integer.parseInt(type))){
            json.put("code",2);
            json.put("message","已收藏");
            return json;
        }
        // 保存到收藏的对象中
        collect col = new collect();
        col.setUserId(Integer.parseInt(userId));
        col.setType(new Byte(type));
        col.setsongId(Integer.parseInt(songId));
        col.setStyle(style);
        
        boolean flag = CollectService.insert(col);
        if (flag) { // 保存成功
            json.put("code", 1);
            json.put("message", "收藏成功");
            return json;
        }
        json.put("code", 0);
        json.put("message", "收藏失败");
        return json;
    }

    /*删除收藏*/
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request) {
        String userId = request.getParameter("userId"); // 用户id
        String songId = request.getParameter("songId"); // 歌曲id
        String Type = request.getParameter("type");     // 类型
        boolean flag = CollectService.deleteByUserIdSongId(Integer.parseInt(userId), Integer.parseInt(songId), Integer.parseInt(Type));
        return flag;
    }

    /*所有收藏*/
    @RequestMapping(value = "/allCollect", method = RequestMethod.GET)
    public Object allCollect(HttpServletRequest request) {
        return CollectService.allCollect();
    }

    /*单个用户的收藏*/
    @RequestMapping(value = "/collectOfUserId", method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId"); // 用户id
        return CollectService.collectOfUserId(Integer.parseInt(userId));
    }

    /* 是否已收藏 */
    @RequestMapping(value = "/existSongId", method = RequestMethod.POST)
    public Object existSongId(HttpServletRequest request) {
        String userId = request.getParameter("userId"); // 用户id
        String songId = request.getParameter("songId");
        String Type = request.getParameter("type");
        // System.out.println(userId+' '+songId+' '+Type);
        boolean flag = CollectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId),
                Integer.parseInt(Type));
        JSONObject json = new JSONObject();
        json.put("code", 0);
        if(flag){
            json.put("code", 1);
        }
        return json;
    }

    @RequestMapping(value = "/numOfSong", method = RequestMethod.GET)
    public Object numOfSong(String songid) {
        Integer songId = Integer.parseInt(songid); // 用户id
        return CollectService.numOfSong(songId);
    }

}

package com.service.implement;

import com.dao.collectMapper;
import com.domain.collect;
import com.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceI implements CollectService{
    @Autowired
    private collectMapper cm;

    /**
     * 增加
     *
     * @param col
     */
    @Override
    public boolean insert(collect col) {
        return cm.insert(col) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return cm.delete(id) > 0;
    }

    /**
     * 根据用户id和歌曲id删除
     *
     * @param userId
     * @param songId
     * @param Type
     */
    @Override
    public boolean deleteByUserIdSongId(Integer userId, Integer songId, Integer Type) {
        return cm.deleteByUserIdSongId(userId, songId, Type) > 0;
    }

    /**
     * 查询所有收藏
     */
    @Override
    public List<collect> allCollect() {
        return cm.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     *
     * @param userId
     */
    @Override
    public List<collect> collectOfUserId(Integer userId) {
        return cm.collectOfUserId(userId);
    }

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     *
     * @param userId
     * @param songId
     * @param Type
     */
    @Override
    public boolean existSongId(Integer userId, Integer songId, Integer Type) {
        return cm.existSongId(userId, songId, Type) > 0;
    }

    @Override
    public int numOfSong(Integer songId){
        return cm.numOfSong(songId);
    }
}

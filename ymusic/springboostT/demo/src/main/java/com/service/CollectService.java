package com.service;

import com.domain.collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {

    public boolean insert(collect col);

    public boolean delete(Integer id);

    public boolean deleteByUserIdSongId(Integer userId, Integer songId, Integer Type);

    public List<collect> allCollect();

    public List<collect> collectOfUserId(Integer userId);

    public boolean existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId, @Param("Type") Integer Type);

    public int numOfSong(Integer songId);// 歌曲的收藏人数
}

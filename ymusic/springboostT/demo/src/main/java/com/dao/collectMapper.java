package com.dao;
import com.domain.collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface collectMapper {
    public int insert(collect col);// 增加

    public int delete(Integer id);// 删除
    /* 根据用户id删除 */

    public int deleteByUserIdSongId(@Param("userId") Integer userId, @Param("songId") Integer songId,
            @Param("Type") Integer Type);

    public List<collect> allCollect();// 所有收藏

    public List<collect> collectOfUserId(Integer userId);// 特定用户收藏

    public int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId,
            @Param("Type") Integer Type);// 是否已收藏
    
    public int numOfSong(Integer songId);//歌曲的收藏人数

}

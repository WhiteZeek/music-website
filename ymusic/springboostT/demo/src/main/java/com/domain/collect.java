package com.domain;
//收藏信息
import java.io.Serializable;

public class collect implements Serializable {
    private Integer id;     //主键
    private Integer userId; //用户id
    private Byte type;      //收藏类型 0歌曲1歌单
    private Integer songId; //歌曲id
    private String style;   //歌曲风格
    
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getsongId() {
        return songId;
    }

    public void setsongId(Integer songId) {
        this.songId = songId;
    }

}

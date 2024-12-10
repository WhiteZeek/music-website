package com.domain;

public class recommend {
    private String songId;//歌曲id
    private String userId;//用户id
    public String getsongId() {
        return songId;
    }
    public void setsongId(String songId) {
        this.songId = songId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}

package com.mlj.customviews.userEnter;

public class User {

    private String nick;
    private String avatarUrl;
    private int avatarId;

    private Long createTime;

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public User(String nick, int avatarId) {
        this.nick = nick;
        this.avatarId = avatarId;
        this.createTime = System.currentTimeMillis();
    }

    public User(String nick, String avatarUrl) {
        this.nick = nick;
        this.avatarUrl = avatarUrl;
        this.createTime = System.currentTimeMillis();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}

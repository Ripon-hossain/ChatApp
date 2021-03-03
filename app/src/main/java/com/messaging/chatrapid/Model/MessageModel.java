package com.messaging.chatrapid.Model;

public class MessageModel {
    String uId, message, ProfileIcon;
    Long timesTemp;

    public MessageModel(String uId, String message, String profileIcon, Long timesTemp) {
        this.uId = uId;
        this.message = message;
        ProfileIcon = profileIcon;
        this.timesTemp = timesTemp;
    }

    public MessageModel(){}

    public MessageModel(String uId, String message, String profileIcon) {
        this.uId = uId;
        this.message = message;
//        ProfileIcon = profileIcon;
    }
    public MessageModel(String senderId, String message){}

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfileIcon() {
        return ProfileIcon;
    }

    public void setProfileIcon(String profileIcon) {
        ProfileIcon = profileIcon;
    }

    public Long getTimesTemp() {
        return timesTemp;
    }

    public void setTimesTemp(Long timesTemp) {
        this.timesTemp = timesTemp;
    }
}

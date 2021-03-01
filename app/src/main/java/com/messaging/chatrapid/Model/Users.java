package com.messaging.chatrapid.Model;

public class Users {

    String profilePic, userName, mail, pasword, userId, lastMessage;

    public Users(String profilePic, String userName, String mail, String pasword, String userId, String lastMessage) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mail = mail;
        this.pasword = pasword;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }
    public Users(){}

    public String getUserId() {
        return userId;
    }

    //SignUp constructor
    public Users(String userName, String mail, String pasword) {

        this.userName = userName;
        this.mail = mail;
        this.pasword = pasword;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}

package com.example.moodTracker;

public class User {
    public String Dob;
    public String Phone;
    public String UserName;
    public String Email;
    public String photoUri;
    public String Provider;

    User(){
    }

    /*public User(String userName, String email, String photoUri, String provider) {
        UserName = userName;
        Email = email;
        this.photoUri = photoUri;
        Provider = provider;
    }*/

    public User(String userName, String email, String dob, String phone, String photoUri, String provider) {
        Dob = dob;
        Phone = phone;
        UserName = userName;
        Email = email;
        this.photoUri = photoUri;
        Provider = provider;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }
}

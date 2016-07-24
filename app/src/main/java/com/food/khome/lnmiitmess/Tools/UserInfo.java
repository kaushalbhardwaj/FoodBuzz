package com.food.khome.lnmiitmess.Tools;

/**
 * Created by khome on 21/1/16.
 */
public class UserInfo {
    public String uname,rollno,uid,password,email;
    public UserInfo()
    {
        super();
    }
    public UserInfo(String un,String r,String u,String ps,String em)
    {
        uname=un;
        rollno=r;
        uid=u;
        password=ps;
        email=em;

    }
    public void setDetails(String un,String r,String u,String ps,String em) {
        uname = un;
        rollno = r;
        uid = u;
        password = ps;
        email = em;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

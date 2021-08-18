package sg.edu.rp.c346.id20020036.contactlist;

import java.io.Serializable;

public class Contacts implements Serializable {

    private int id;
    private String name;
    private String mobile;
    private String home;
    private String email;
    private String address;
    private String gender;
    private String info;

    public Contacts(int id, String name, String mobile, String home, String email, String address, String gender, String info) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.home = home;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

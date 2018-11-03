package com.example.sande.walmart_onlineshopping.data;

public class OrderData {

    private String mobile;
    private String pid;
    private String pname;
    private int quantity;
    private String prize;
    private String image;

    public OrderData()
    {

    }


    public OrderData(String mobile, String pid, String pname, int quantity, String prize, String image) {
        this.mobile = mobile;
        this.pid = pid;
        this.pname = pname;
        this.quantity = quantity;
        this.prize = prize;
        this.image = image;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

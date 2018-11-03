package com.example.sande.walmart_onlineshopping.data;

public class PurchaseHistoryData {

    String orderId;
    String orderStatus;
    String userName;
    String billing;
    String mailing;
    String mobile;
    String email;
    String itemId;
    String itemName;
    String itemQuantity;
    String totalPrice;
    String paidPrice;
    String placedOn;

    public PurchaseHistoryData(String orderId, String orderStatus, String userName, String billing, String mailing, String mobile, String email, String itemId, String itemName, String itemQuantity, String totalPrice, String paidPrice, String placedOn) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.userName = userName;
        this.billing = billing;
        this.mailing = mailing;
        this.mobile = mobile;
        this.email = email;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.totalPrice = totalPrice;
        this.paidPrice = paidPrice;
        this.placedOn = placedOn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    public String getMailing() {
        return mailing;
    }

    public void setMailing(String mailing) {
        this.mailing = mailing;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(String paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getPlacedOn() {
        return placedOn;
    }

    public void setPlacedOn(String placedOn) {
        this.placedOn = placedOn;
    }
}

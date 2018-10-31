package com.example.sande.walmart_onlineshopping;

public class ProductListData {

    String image_pl;
    String name_pl;
    String price;
    String shippingDetails;

    public ProductListData(String image_pl, String name_pl, String price, String shippingDetails) {
        this.image_pl = image_pl;
        this.name_pl = name_pl;
        this.price = price;
        this.shippingDetails = shippingDetails;
    }

    public String getImage_pl() {
        return image_pl;
    }

    public void setImage_pl(String image_pl) {
        this.image_pl = image_pl;
    }

    public String getName_pl() {
        return name_pl;
    }

    public void setName_pl(String name_pl) {
        this.name_pl = name_pl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }
}

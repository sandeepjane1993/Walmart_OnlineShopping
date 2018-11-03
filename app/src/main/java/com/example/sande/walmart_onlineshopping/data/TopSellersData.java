package com.example.sande.walmart_onlineshopping.data;

public class TopSellersData {

    String sellerName;
    String sellerDeal;
    float sellerRating;
    String sellerLogo;

    public TopSellersData(String sellerName, String sellerDeal, float sellerRating, String sellerLogo) {
        this.sellerName = sellerName;
        this.sellerDeal = sellerDeal;
        this.sellerRating = sellerRating;
        this.sellerLogo = sellerLogo;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerDeal() {
        return sellerDeal;
    }

    public void setSellerDeal(String sellerDeal) {
        this.sellerDeal = sellerDeal;
    }

    public float getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(float sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }
}

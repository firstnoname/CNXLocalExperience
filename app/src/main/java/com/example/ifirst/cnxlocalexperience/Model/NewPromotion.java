package com.example.ifirst.cnxlocalexperience.Model;

public class NewPromotion {

    /*promoCode is the code to identify the promotion.
    promoType is the used to identify the type of this code are for guesthouse, restaurant, gift shop;*/
    private String id;
    private String promoCode;
    private String promoType;
    private String promoExpireDate;
    private String promoImgPercent;
    private String promoDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getPromoExpireDate() {
        return promoExpireDate;
    }

    public void setPromoExpireDate(String promoExpireDate) {
        this.promoExpireDate = promoExpireDate;
    }

    public String getPromoImgPercent() {
        return promoImgPercent;
    }

    public void setPromoImgPercent(String promoImgPercent) {
        this.promoImgPercent = promoImgPercent;
    }

    public String getPromoDesc() {
        return promoDesc;
    }

    public void setPromoDesc(String promoDesc) {
        this.promoDesc = promoDesc;
    }
}
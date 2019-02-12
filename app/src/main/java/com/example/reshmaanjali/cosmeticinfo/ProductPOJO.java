package com.example.reshmaanjali.cosmeticinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductPOJO implements Parcelable {
    public static final Creator<ProductPOJO> CREATOR = new Creator<ProductPOJO>() {
        @Override
        public ProductPOJO createFromParcel(Parcel in) {
            return new ProductPOJO(in);
        }

        @Override
        public ProductPOJO[] newArray(int size) {
            return new ProductPOJO[size];
        }
    };
    private String id;
    private String brand;
    private String name;
    private String price;
    private String price_sign;
    private String currency;
    private String image_link;
    private String product_link;
    private String description;
    private String rating;
    private String product_type;

    protected ProductPOJO(Parcel in) {
        id = in.readString();
        brand = in.readString();
        name = in.readString();
        price = in.readString();
        price_sign = in.readString();
        currency = in.readString();
        image_link = in.readString();
        product_link = in.readString();
        description = in.readString();
        rating = in.readString();
        product_type = in.readString();
    }

    ProductPOJO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_sign() {
        return price_sign;
    }

    public void setPrice_sign(String price_sign) {
        this.price_sign = price_sign;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getProduct_link() {
        return product_link;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getId());
        parcel.writeString(getBrand());
        parcel.writeString(getName());
        parcel.writeString(getPrice());
        parcel.writeString(getPrice_sign());
        parcel.writeString(getCurrency());
        parcel.writeString(getImage_link());
        parcel.writeString(getProduct_link());
        parcel.writeString(getDescription());
        parcel.writeString(getRating());
        parcel.writeString(getProduct_type());
    }
}

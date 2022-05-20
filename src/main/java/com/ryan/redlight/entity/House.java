package com.ryan.redlight.entity;

public class House {
    private Integer houseId;

    private Byte isSold;

    private Integer click;

    private String name;

    private String address;

    private Integer size;

    private String division;

    @Override
    public String toString() {
        return "House{" +
                "houseId=" + houseId +
                ", isSold=" + isSold +
                ", click=" + click +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", size=" + size +
                ", division='" + division + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    private Integer price;

    private String type;

    private String description;

    private String picture;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Byte getIsSold() {
        return isSold;
    }

    public void setIsSold(Byte isSold) {
        this.isSold = isSold;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division == null ? null : division.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}
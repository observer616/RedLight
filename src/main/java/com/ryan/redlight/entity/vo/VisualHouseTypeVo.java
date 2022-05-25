package com.ryan.redlight.entity.vo;

/**
 * @author Ryan
 */
public class VisualHouseTypeVo {
    private String houseType;
    private Integer count;

    public VisualHouseTypeVo(){

    }

    @Override
    public String toString() {
        return "VisualHouseTypeVo{" +
                "houseType='" + houseType + '\'' +
                ", count=" + count +
                '}';
    }

    public VisualHouseTypeVo(String houseType, Integer count) {
        this.houseType = houseType;
        this.count = count;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

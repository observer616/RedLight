package com.ryan.redlight.entity.vo;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.ViewAppointment;
import com.sun.istack.internal.NotNull;

/**
 * 预约VO对象，包含房名、创建者名、回复者名
 *
 * @author Ryan
 */
public class AppointmentVo extends ViewAppointment {
    private String houseName;
    private String creatorName;
    private String replierName;

    public AppointmentVo() {
        super();
    }

    public AppointmentVo(ViewAppointment appointment, House house, Client client) {
        this();
        this.setViewAppointmentId(appointment.getViewAppointmentId());
        this.setHouseId(appointment.getHouseId());
        this.setCreatorId(appointment.getCreatorId());
        this.setCreateTime(appointment.getCreateTime());
        this.setViewTime(appointment.getViewTime());
        this.setIsReplied(appointment.getIsReplied());
        this.setReplyerId(appointment.getReplyerId());
        this.setReplyTime(appointment.getReplyTime());
        this.setIsViewed(appointment.getIsViewed());

        houseName = house.getName();
        creatorName = client.getNickname();
        replierName = null;
    }

    public AppointmentVo(ViewAppointment appointment, House house, Client client, @NotNull Admin admin) {
        this(appointment, house, client);
        replierName = admin.getNickname();
    }

    @Override
    public String toString() {
        return "AppointmentVo{" + super.toString() +
                "houseName='" + houseName + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", replierName='" + replierName + '\'' +
                '}';
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getReplierName() {
        return replierName;
    }

    public void setReplierName(String replierName) {
        this.replierName = replierName;
    }
}

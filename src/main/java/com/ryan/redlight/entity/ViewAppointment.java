package com.ryan.redlight.entity;

import com.ryan.redlight.entity.vo.AppointmentVo;

import java.util.Date;

public class ViewAppointment {
    private Integer viewAppointmentId;

    private Integer houseId;

    private Integer creatorId;


    private Date createTime;

    private Date viewTime;

    private Byte isReplied;

    private Integer replyerId;

    private Date replyTime;

    private Byte isViewed;

    public ViewAppointment(){}

    public ViewAppointment(AppointmentVo appointmentVo){
         viewAppointmentId=appointmentVo.getViewAppointmentId();
         houseId=appointmentVo.getHouseId();
         creatorId=appointmentVo.getCreatorId();
         createTime=appointmentVo.getCreateTime();
         viewTime=appointmentVo.getViewTime();
         isReplied=appointmentVo.getIsReplied();
         replyerId=appointmentVo.getReplyerId();
         replyTime=appointmentVo.getReplyTime();
         isViewed=appointmentVo.getIsViewed();
    }

    @Override
    public String toString() {
        return "ViewAppointment{" +
                "viewAppointmentId=" + viewAppointmentId +
                ", houseId=" + houseId +
                ", creatorId=" + creatorId +
                ", createTime=" + createTime +
                ", viewTime=" + viewTime +
                ", isReplied=" + isReplied +
                ", replyerId=" + replyerId +
                ", replyTime=" + replyTime +
                ", isViewed=" + isViewed +
                '}';
    }

    public Integer getViewAppointmentId() {
        return viewAppointmentId;
    }

    public void setViewAppointmentId(Integer viewAppointmentId) {
        this.viewAppointmentId = viewAppointmentId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    public Byte getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(Byte isReplied) {
        this.isReplied = isReplied;
    }

    public Integer getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Byte getIsViewed() {
        return isViewed;
    }

    public void setIsViewed(Byte isViewed) {
        this.isViewed = isViewed;
    }
}
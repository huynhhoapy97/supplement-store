package com.huynhhoapy97.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account implements Cloneable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "is_activated")
    private Integer isActivated;
    @Column(name = "is_vip")
    private Integer isVip;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "created_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDay;
    @Column(name = "updated_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDay;
    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @Transient
    private String confirmPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Integer isActivated) {
        this.isActivated = isActivated;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(Date createdDay) {
        this.createdDay = createdDay;
    }

    public Date getUpdatedDay() {
        return updatedDay;
    }

    public void setUpdatedDay(Date updatedDay) {
        this.updatedDay = updatedDay;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public Account clone() {
        try {
            return (Account) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

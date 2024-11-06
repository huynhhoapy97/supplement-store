package com.huynhhoapy97.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birth_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "is_active")
    private Integer isActive;
    @Column(name = "created_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDay;
    @Column(name = "updated_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDay;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

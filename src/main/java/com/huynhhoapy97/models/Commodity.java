package com.huynhhoapy97.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "commodity")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @Column(name = "created_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDay;
    @Column(name = "updated_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDay;
    @Column(name = "deleted_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDay;
    @OneToMany(mappedBy = "commodity")
    @JsonIgnore
    private Collection<Category> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public Date getDeletedDay() {
        return deletedDay;
    }

    public void setDeletedDay(Date deletedDay) {
        this.deletedDay = deletedDay;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }
}

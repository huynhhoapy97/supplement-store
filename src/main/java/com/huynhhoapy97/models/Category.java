package com.huynhhoapy97.models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "cover_photo")
    private String coverPhoto;
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
    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
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

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}

package com.itcast.manager.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass//用来标注父类的注解
@Getter
@Setter
public abstract class BaseEntity  {
    @JsonIgnore//表示返回值不会出现此字段
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

    /**
     * 供Override
     */
    protected void prePersist(){}

    /**
     * 供Override
     */
    protected void preUpdate(){}

    @PrePersist//创建的时候会执行此方法
    private void _prePersist() {
        createTime = new Date();
        prePersist();
    }

    @PreUpdate//更新的时候会执行此方法
    private void _preUpdate(){
        updateTime = new Date();
        preUpdate();
    }
}

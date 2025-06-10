package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *医生介绍：(DoctorIntroduction)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DoctorIntroduction")
public class DoctorIntroduction implements Serializable {

    //DoctorIntroduction编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_introduction_id")
    private Integer doctor_introduction_id;
   // 医生工号
   @Basic
    private Integer doctor_job_number;
   // 医生姓名
   @Basic
    private String name_of_doctor;
   // 性别
   @Basic
    private String gender;
   // 年龄
   @Basic
    private String age;
   // 照片
   @Basic
    private String photo;
   // 科室
   @Basic
    private String department;
   // 挂号费
   @Basic
    private Integer registration_fee;
   // 擅长领域
   @Basic
    private String areas_of_expertise;
   // 排班时间
   @Basic
    private String scheduling_time;
   // 医生介绍
   @Basic
    private String doctor_introduction;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}

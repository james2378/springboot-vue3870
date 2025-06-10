package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *在线咨询：(OnlineConsultation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OnlineConsultation")
public class OnlineConsultation implements Serializable {

    //OnlineConsultation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_consultation_id")
    private Integer online_consultation_id;
   // 医生工号
   @Basic
    private Integer doctor_job_number;
   // 医生姓名
   @Basic
    private String name_of_doctor;
   // 科室
   @Basic
    private String department;
   // 用户名
   @Basic
    private Integer user_name;
   // 宠物品种
   @Basic
    private String pet_variety;
   // 咨询问题
   @Basic
    private String consulting_questions;
   // 医生回复
   @Basic
    private String doctor_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}

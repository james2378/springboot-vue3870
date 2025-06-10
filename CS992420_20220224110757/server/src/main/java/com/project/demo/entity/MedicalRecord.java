package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *病历记录：(MedicalRecord)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MedicalRecord")
public class MedicalRecord implements Serializable {

    //MedicalRecord编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record__id")
    private Integer medical_record__id;
   // 订单号
   @Basic
    private String order_number;
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
   // 宠物年龄
   @Basic
    private String pet_age;
   // 症状描述
   @Basic
    private String symptom_description;
   // 临床诊断
   @Basic
    private String clinical_diagnosis;
   // 药物处方
   @Basic
    private String drug_prescription;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}

package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *预约挂号：(HaveAnAppointmentWithADoctor)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HaveAnAppointmentWithADoctor")
public class HaveAnAppointmentWithADoctor implements Serializable {

    //HaveAnAppointmentWithADoctor编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "have_an_appointment_with_a_doctor_id")
    private Integer have_an_appointment_with_a_doctor_id;
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
   // 挂号费
   @Basic
    private String registration_fee;
   // 用户名
   @Basic
    private Integer user_name;
   // 宠物品种
   @Basic
    private String pet_variety;
   // 宠物年龄
   @Basic
    private String pet_age;
   // 宠物性别
   @Basic
    private String pet_sex;
   // 联系电话
   @Basic
    private String contact_number;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}

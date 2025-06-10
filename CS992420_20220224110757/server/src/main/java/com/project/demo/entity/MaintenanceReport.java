package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *维修上报：(MaintenanceReport)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MaintenanceReport")
public class MaintenanceReport implements Serializable {

    //MaintenanceReport编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_report_id")
    private Integer maintenance_report_id;
   // 器械编号
   @Basic
    private String device_number;
   // 器械名称
   @Basic
    private String device_name;
   // 相关图片
   @Basic
    private String related_pictures;
   // 医生工号
   @Basic
    private Integer doctor_job_number;
   // 故障描述
   @Basic
    private String fault_description;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}

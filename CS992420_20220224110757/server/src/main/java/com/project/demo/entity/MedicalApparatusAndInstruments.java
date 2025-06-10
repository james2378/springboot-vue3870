package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *医疗器械：(MedicalApparatusAndInstruments)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MedicalApparatusAndInstruments")
public class MedicalApparatusAndInstruments implements Serializable {

    //MedicalApparatusAndInstruments编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_apparatus_and_instruments_id")
    private Integer medical_apparatus_and_instruments_id;
   // 器械编号
   @Basic
    private String device_number;
   // 器械名称
   @Basic
    private String device_name;
   // 购入日期
   @Basic
    private Timestamp purchase_date;
   // 厂家
   @Basic
    private String manufactor;
   // 状态
   @Basic
    private String state;
   // 维修记录
   @Basic
    private String maintenance_record;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}

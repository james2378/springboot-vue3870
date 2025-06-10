package com.project.demo.controller;

import com.project.demo.entity.MedicalRecord;
import com.project.demo.service.MedicalRecordService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *病历记录：(MedicalRecord)表控制层
 *
 */
@RestController
@RequestMapping("/medical_record_")
public class MedicalRecordController extends BaseController<MedicalRecord,MedicalRecordService> {

    /**
     *病历记录对象
     */
    @Autowired
    public MedicalRecordController(MedicalRecordService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}

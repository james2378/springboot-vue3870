package com.project.demo.controller;

import com.project.demo.entity.DoctorIntroduction;
import com.project.demo.service.DoctorIntroductionService;
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
 *医生介绍：(DoctorIntroduction)表控制层
 *
 */
@RestController
@RequestMapping("/doctor_introduction")
public class DoctorIntroductionController extends BaseController<DoctorIntroduction,DoctorIntroductionService> {

    /**
     *医生介绍对象
     */
    @Autowired
    public DoctorIntroductionController(DoctorIntroductionService service) {
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

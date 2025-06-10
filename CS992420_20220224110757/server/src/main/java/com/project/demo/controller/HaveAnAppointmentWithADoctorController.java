package com.project.demo.controller;

import com.project.demo.entity.HaveAnAppointmentWithADoctor;
import com.project.demo.service.HaveAnAppointmentWithADoctorService;
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
 *预约挂号：(HaveAnAppointmentWithADoctor)表控制层
 *
 */
@RestController
@RequestMapping("/have_an_appointment_with_a_doctor")
public class HaveAnAppointmentWithADoctorController extends BaseController<HaveAnAppointmentWithADoctor,HaveAnAppointmentWithADoctorService> {

    /**
     *预约挂号对象
     */
    @Autowired
    public HaveAnAppointmentWithADoctorController(HaveAnAppointmentWithADoctorService service) {
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

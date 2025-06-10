package com.project.demo.controller;

import com.project.demo.entity.MedicalApparatusAndInstruments;
import com.project.demo.service.MedicalApparatusAndInstrumentsService;
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
 *医疗器械：(MedicalApparatusAndInstruments)表控制层
 *
 */
@RestController
@RequestMapping("/medical_apparatus_and_instruments")
public class MedicalApparatusAndInstrumentsController extends BaseController<MedicalApparatusAndInstruments,MedicalApparatusAndInstrumentsService> {

    /**
     *医疗器械对象
     */
    @Autowired
    public MedicalApparatusAndInstrumentsController(MedicalApparatusAndInstrumentsService service) {
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

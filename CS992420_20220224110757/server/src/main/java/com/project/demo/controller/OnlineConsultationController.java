package com.project.demo.controller;

import com.project.demo.entity.OnlineConsultation;
import com.project.demo.service.OnlineConsultationService;
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
 *在线咨询：(OnlineConsultation)表控制层
 *
 */
@RestController
@RequestMapping("/online_consultation")
public class OnlineConsultationController extends BaseController<OnlineConsultation,OnlineConsultationService> {

    /**
     *在线咨询对象
     */
    @Autowired
    public OnlineConsultationController(OnlineConsultationService service) {
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

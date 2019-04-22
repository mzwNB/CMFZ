package com.baizhi.cmfz_mzw.controller;

import com.baizhi.cmfz_mzw.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("bar")
public class BarController {
    @Autowired
    private ViewService viewService;

    @ResponseBody
    @RequestMapping("regDetails")
    public Map regDetails(){
        return  viewService.selectUserRegist();
    }
    @ResponseBody
    @RequestMapping("provinceDetails")
    public Map provinceDetails(){
        return  viewService.selectProvinceBysex();
    }
}

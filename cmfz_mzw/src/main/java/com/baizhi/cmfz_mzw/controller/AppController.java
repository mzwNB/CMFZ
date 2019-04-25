package com.baizhi.cmfz_mzw.controller;

import com.baizhi.cmfz_mzw.interfaceObj.UserView;
import com.baizhi.cmfz_mzw.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @Autowired
    private AppService appService;

    @RequestMapping(value = "/first_page", method = RequestMethod.GET)
    public Object first(Integer uid, String type, String sub_type) {
        return appService.getFirstPageData(uid, type, sub_type);
    }

    @RequestMapping(value = "/detail/si", method = RequestMethod.GET)
    public Object si(Integer uid, Integer id) {
        return appService.getSi(uid, id);
    }

    @RequestMapping(value = "/detail/wen", method = RequestMethod.GET)
    public Object wen(Integer uid, Integer id) {
        return appService.getWen(uid, id);
    }

    @RequestMapping(value = "/account/login", method = RequestMethod.POST)
    public Object login(String phone, String password) {
        return appService.getLogin(phone, password);
    }

    @RequestMapping(value = "/account/regist", method = RequestMethod.POST)
    public Object regist(String phone, String password) {
        return appService.getRegister(phone, password);
    }

    @RequestMapping(value = "/account/modify", method = RequestMethod.POST)
    public Object modify(UserView userView) {
        return appService.getModify(userView);
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public Object member(Integer uid) {
        return appService.getUserList(uid);
    }


}

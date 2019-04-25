package com.baizhi.cmfz_mzw.service;

import com.baizhi.cmfz_mzw.interfaceObj.UserView;

import java.util.Map;

public interface AppService {

    Map getFirstPageData(Integer uid, String type, String subType);

    Object getSi(Integer uid, Integer id);

    Map getWen(Integer uid, Integer id);

    Object getLogin(String phone, String password);

    Map getRegister(String phone, String password);

    Object getModify(UserView user);

    Map getIdentifyCode(String phone);

    Map checkIdentifyCode(String code, String phone);

    Object getUserList(Integer uid);
}

package com.baizhi.cmfz_mzw.service.impl;

import com.baizhi.cmfz_mzw.dao.UserDao;
import com.baizhi.cmfz_mzw.service.ViewService;
import com.baizhi.cmfz_mzw.viewObject.ProvinceVIewObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ViewServiceImpl implements ViewService {
    @Autowired
    private UserDao userDao;

    @Override
    public Map selectUserRegist() {
        Map map = new HashMap();
        map.put("x", Arrays.asList("近六月内","近五个月内","近四个月内","近三个月内","近二个月内","近一个月内"));
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 6; i >0; i--) {
            int i1 = userDao.selectUserRegist(i * 30);
            y.add(i1);
        }
        System.out.println(y);
        map.put("y",y);
        return map;
    }

    @Override
    public Map selectProvinceBysex() {
        Map map =new HashMap();
        List<ProvinceVIewObj> male = userDao.selectProBysex(1);
        List<ProvinceVIewObj> famale = userDao.selectProBysex(0);
        map.put("male",male);
        map.put("famale",famale);
        return map;
    }
}

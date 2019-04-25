package com.baizhi.cmfz_mzw.service.impl;

import com.alibaba.fastjson.JSON;
import com.baizhi.cmfz_mzw.dao.*;
import com.baizhi.cmfz_mzw.interfaceObj.ChapterView;
import com.baizhi.cmfz_mzw.interfaceObj.UserView;
import com.baizhi.cmfz_mzw.service.AppService;
import com.baizhi.cmfz_mzw.service.ViewService;
import com.baizhi.cmfz_mzw.utils.MD5utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AppServiceImpl implements AppService {
    @Autowired
    BannerViewDao bannerViewDao;
    @Autowired
    ChapterViewDao chapterViewDao;
    @Autowired
    SiDetailDao siDetailDao;
    @Autowired
    SiViewDao siViewDao;
    @Autowired
    UserViewDao userViewDao;
    @Autowired
    WenDetaileDao wenDetaileDao;
    @Autowired
    WenViewDao wenViewDao;
    @Autowired
    ViewService viewService;

    @Override
    public Map getFirstPageData(Integer uid, String type, String sub_type) {
        Map map = new HashMap();
        if ("all".equals(type)) {
            System.out.println(bannerViewDao.selectList(null));
            map.put("header", bannerViewDao.selectList(null));
        }
        if ("si".equals(type)) {
            //根据id查询上师文章并返回
        }
        if ("wen".equals(type)) {
            System.out.println(wenViewDao.selectList(null));
            map.put("body", wenViewDao.selectList(null));
        }
        return map;
    }

    @Override
    public Object getSi(Integer uid, Integer id) {
        return siDetailDao.selectById(id);
    }

    @Override
    public Map getWen(Integer uid, Integer id) {
        Map map = new HashMap();
        map.put("introduction", wenDetaileDao.selectById(id));
        QueryWrapper<ChapterView> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ChapterView::getAlbumId, id);
        map.put("list", chapterViewDao.selectList(queryWrapper));
        return map;
    }

    @Override
    public Object getLogin(String phone, String password) {

        QueryWrapper<UserView> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserView::getPhone, phone);
        queryWrapper.lambda().eq(UserView::getPassword, MD5utils.toMD5(password));
        UserView userView = userViewDao.selectOne(queryWrapper);
        if (userView != null) {
            return userView;
        } else {
            Map map = new HashMap();
            map.put("error", "-200");
            map.put("errmsg", "登录失败");
            return map;
        }

    }

    @Override
    public Map getRegister(String phone, String password) {
        QueryWrapper<UserView> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserView::getPhone, phone);
        UserView userView = userViewDao.selectOne(queryWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        if (userView != null) {
            map.put("error", "-200");
            map.put("errmsg", "改手机号已被注册");
            return map;
        } else {
            UserView userView1 = new UserView();
            userView1.setPhone(phone);
            userView1.setPassword(MD5utils.toMD5(password));
            userViewDao.insertOne(userView1);
            map.put("password", MD5utils.toMD5(password));
            map.put("uid", userView1.getId());
            map.put("phone", phone);
            String message = JSON.toJSONString(viewService.selectUserRegist());
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-07dde9196acf4107ab17c1745697110f");
            goEasy.publish("mzw", message);
            String message1 = JSON.toJSONString(viewService.selectProvinceBysex());
            GoEasy goEasy1 = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-07dde9196acf4107ab17c1745697110f");
            goEasy1.publish("mzw1", message1);
            return map;
        }

    }

    @Override
    public Object getModify(UserView user) {
        int i = userViewDao.updateById(user);
        Map map = new HashMap();
        if (i == 1) {
            return userViewDao.selectById(user.getId());
        } else {
            map.put("error", "-200");
            map.put("errmsg", "修改失败");
            return map;
        }
    }

    @Override
    public Map getIdentifyCode(String phone) {
        //调用接口发短信并储存code到Redis

        return null;
    }

    @Override
    public Map checkIdentifyCode(String code, String phone) {
        Map map = new HashMap();
        //从redis 根据phone取值 如果对
        if (true) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    @Override
    public Object getUserList(Integer uid) {
        return userViewDao.selectList(null);

    }
}

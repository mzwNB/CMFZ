package com.baizhi.cmfz_mzw.dao;

import com.baizhi.cmfz_mzw.enetity.User;
import com.baizhi.cmfz_mzw.viewObject.ProvinceVIewObj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

    //查询一个月内 二个月内 三个月内的用户注册量；
    int selectUserRegist(Integer tianshu);

    //查询用户的省份分布情况,
    List<ProvinceVIewObj> selectProBysex(Integer sex);
}

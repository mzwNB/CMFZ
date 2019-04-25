package com.baizhi.cmfz_mzw.dao;

import com.baizhi.cmfz_mzw.interfaceObj.UserView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserViewDao extends BaseMapper<UserView> {
    void insertOne(UserView user);
}

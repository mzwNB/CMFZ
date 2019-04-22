package com.baizhi.cmfz_mzw.dao;

import com.baizhi.cmfz_mzw.enetity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MenuDao extends BaseMapper<Menu> {
    List<Menu> selectAllMenu();
}

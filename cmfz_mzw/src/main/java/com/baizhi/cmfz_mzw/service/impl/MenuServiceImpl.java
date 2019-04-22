package com.baizhi.cmfz_mzw.service.impl;

import com.baizhi.cmfz_mzw.dao.MenuDao;
import com.baizhi.cmfz_mzw.enetity.Menu;
import com.baizhi.cmfz_mzw.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> selectAll() {
        List<Menu> list = menuDao.selectAllMenu();
        return list;
    }
}

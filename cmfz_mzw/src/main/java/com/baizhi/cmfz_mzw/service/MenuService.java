package com.baizhi.cmfz_mzw.service;

import com.baizhi.cmfz_mzw.enetity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MenuService {

    List<Menu> selectAll();

}

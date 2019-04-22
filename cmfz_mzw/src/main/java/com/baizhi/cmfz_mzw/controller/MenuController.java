package com.baizhi.cmfz_mzw.controller;

import com.baizhi.cmfz_mzw.enetity.Menu;
import com.baizhi.cmfz_mzw.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequestMapping("menu")
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("selectAll")

    public List<Menu> selectAll(HttpSession session){
       return menuService.selectAll();
    }
}

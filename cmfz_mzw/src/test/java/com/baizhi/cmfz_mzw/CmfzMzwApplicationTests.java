package com.baizhi.cmfz_mzw;

import com.alibaba.fastjson.JSON;
import com.baizhi.cmfz_mzw.controller.AlbumController;
import com.baizhi.cmfz_mzw.service.AlbumService;
import com.baizhi.cmfz_mzw.service.AppService;
import com.baizhi.cmfz_mzw.service.BannerService;
import com.baizhi.cmfz_mzw.service.ViewService;
import com.baizhi.cmfz_mzw.viewObject.AlbumDto;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzMzwApplicationTests {
@Autowired
private BannerService bannerService;
@Autowired
private AlbumService albumService;
    @Autowired
    private ViewService viewService;
    @Autowired
    private AppService appService;
    @Autowired
    private AlbumController albumController;
    @Test
    public void contextLoads() {
        Map map = bannerService.selectAllBanner(1, 10);
        List rwos = (List)map.get("rows");
        for (Object rwo : rwos) {
            System.out.println(rwo);
        }
        System.out.println(map);
    }
    @Test
    public void ss1(){
        List<AlbumDto> list = albumService.selectAllAlbum();
        list.forEach(System.out::println);
    }

    @Test
    public void Register() {
        String message = JSON.toJSONString(viewService.selectUserRegist());
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-07dde9196acf4107ab17c1745697110f");
        goEasy.publish("mzw", message);
    }

    @Test
    public void ss3() {
        Object register = appService.getRegister("17877888888", "123456");
        String s = JSON.toJSONString(register);
        System.out.println(s);
    }
}

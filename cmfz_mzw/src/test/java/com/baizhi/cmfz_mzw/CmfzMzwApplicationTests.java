package com.baizhi.cmfz_mzw;

import com.baizhi.cmfz_mzw.service.AlbumService;
import com.baizhi.cmfz_mzw.service.BannerService;
import com.baizhi.cmfz_mzw.viewObject.AlbumDto;
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

}

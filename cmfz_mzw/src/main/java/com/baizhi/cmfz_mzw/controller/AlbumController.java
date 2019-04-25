package com.baizhi.cmfz_mzw.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmfz_mzw.enetity.Album;
import com.baizhi.cmfz_mzw.service.AlbumService;
import com.baizhi.cmfz_mzw.viewObject.AlbumDto;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("selectAll")
    public List<AlbumDto> selectAll(){
        return albumService.selectAllAlbum();

    }

    @RequestMapping("downloadAblum")
    public void download(HttpServletResponse response) {
        List<AlbumDto> list = albumService.selectAllAlbum();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑列表", "album"), AlbumDto.class, list);
        try {
            String s1 = URLEncoder.encode("专辑表.xls", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + s1);
            response.setContentType("application/x-xls");
            OutputStream stream = response.getOutputStream();
            workbook.write(stream);
        } catch (IOException e) {
            try {
                workbook.close();
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    @RequestMapping("selectOne")
    public Map selectOne(Integer id){
        Map map =new HashMap();
        try {
            Album  album= albumService.selectOne(id);
            if(album==null){
                map.put("flag",false);
            }else {
                if(album.getAmount()==null){
                    album.setAmount(0);
                }
                map.put("flag",true);
                map.put("Album",album);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
        }
       return map;
    }
    @RequestMapping("insert")
    public Map insert(Album album, MultipartFile imgFile){
        Map map = new HashMap<>();
        System.out.println(album);
        try {
            if(imgFile!=null){
                String name = imgFile.getOriginalFilename();
                imgFile.transferTo(new File("D:\\CMFZ\\cmfz_mzw\\src\\main\\webapp\\img\\audioCollection\\"+name));
                album.setImgPath("/img/audioCollection/"+name);
                albumService.insertAblum(album);
                map.put("flag",true);
            }

        } catch (Exception e) {
            map.put("flag",false);
            e.printStackTrace();
        }
        return map;
    }
}

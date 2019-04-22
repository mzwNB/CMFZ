package com.baizhi.cmfz_mzw.controller;

import com.baizhi.cmfz_mzw.dao.ChapterDao;
import com.baizhi.cmfz_mzw.enetity.Album;
import com.baizhi.cmfz_mzw.enetity.Chapter;
import com.baizhi.cmfz_mzw.service.AlbumService;
import com.baizhi.cmfz_mzw.service.ChapterService;
import com.baizhi.cmfz_mzw.utils.MusicUtils;
import com.baizhi.cmfz_mzw.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterDao chapterDao;

    @RequestMapping("insert")
    public Map insert(MultipartFile chapterFile, Chapter chapter){
        Map map =new HashMap();
        try {
            String printSize = MyFileUtils.getPrintSize(chapterFile.getSize());
            chapter.setSize(printSize);
            String filename = chapterFile.getOriginalFilename();
            String saveFileName="D:\\CMFZ\\cmfz_mzw\\src\\main\\webapp\\Music\\"+filename;
            File file = new File(saveFileName);
            chapterFile.transferTo(file);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String duration =MusicUtils.getDuration(file);
            chapter.setDuration(duration);
            chapter.setId(uuid);
            chapter.setChapterPath(saveFileName);
            System.out.println(chapter);
            chapterService.insertChapter(chapter);
            Album album = albumService.selectOne(chapter.getAlbumId());
               album.setAmount(album.getAmount()+1);
            albumService.updateAblum(album);
            map.put("flag",true);
        } catch (Exception e) {
            map.put("flag",false);
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("download")
    public void download(String  id, HttpServletResponse resp)throws Exception{
        Chapter chapter = chapterDao.selectById(id);
        String mp3=chapter.getChapterPath().substring(chapter.getChapterPath().lastIndexOf("."));
        //1.读取要下载的文件
        InputStream is =new FileInputStream(chapter.getChapterPath());
        String s1 = URLEncoder.encode(chapter.getTitle()+mp3,"UTF-8");
        resp.setHeader("content-disposition","attachment;filename="+s1);
        ServletOutputStream os = resp.getOutputStream();
        while (true){
            int i = is.read();
            if(i==-1){ break;}
            os.write(i);
        }
        os.close();
        is.close();

    }
}

package com.baizhi.cmfz_mzw.controller;

import com.baizhi.cmfz_mzw.dao.BannerDao;
import com.baizhi.cmfz_mzw.enetity.Banner;
import com.baizhi.cmfz_mzw.service.BannerService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerDao bannerDao;


    @RequestMapping("selectAll")
    public Map selectAll(Integer page, Integer rows){
        return  bannerService.selectAllBanner(page,rows);
    }
    @RequestMapping("insert")
    public Map insert(Banner banner, MultipartFile imgFile, HttpServletRequest request) {
        Map map =new HashMap();
        try {
            if(imgFile!=null){
                String name = imgFile.getOriginalFilename();
                String realPath = request.getSession().getServletContext().getRealPath("/");
                System.out.println(realPath);
                imgFile.transferTo(new File("D:\\CMFZ\\cmfz_mzw\\src\\main\\webapp\\img\\shouye\\"+name));
                banner.setImgPath("/img/shouye/"+name);
                bannerService.insertBanner(banner);
                map.put("flag",true);
            }
        } catch (Exception e) {
            map.put("flag",false);
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("update")
    public Map update(Banner banner,MultipartFile imgFile){
        Map map =new HashMap();
        System.out.println(imgFile);
        try {
            if(imgFile!=null){
                String filename = imgFile.getOriginalFilename();
                imgFile.transferTo(new File("D:\\CMFZ\\cmfz_mzw\\src\\main\\webapp\\img\\shouye\\"+filename));
                banner.setImgPath("/img/shouye/"+filename);
            }
            bannerService.updateBanner(banner);
            map.put("flag",true);
        } catch (Exception e) {
            map.put("flag",false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("delete")
    public Map update(Integer id){
        Map map =new HashMap();
        try {
            bannerService.deleteBanner(id);
            map.put("flag",true);
        } catch (Exception e) {
            map.put("flag",false);
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) {
        List<Banner> list = bannerDao.selectAllBanner();
        String[] column ={"编号","标题","图片地址","创建时间","播放状态"};
        //1.创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建sheet
        HSSFSheet banner = workbook.createSheet("banner");
        //3.创建colum标题行
        HSSFRow row = banner.createRow(0);
        //4.将row标题插入
        //5.处理date格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
        for (int i = 0; i < column.length; i++) {
            row.createCell(i).setCellValue(column[i]);
        }

        //6.将数据写到聂蓉中
        for (int i = 0; i< list.size(); i++) {
            HSSFRow bannerRow = banner.createRow(i + 1);
            bannerRow.createCell(0).setCellValue(list.get(i).getId());
            bannerRow.createCell(1).setCellValue(list.get(i).getTitle());
            bannerRow.createCell(2).setCellValue(list.get(i).getImgPath());
            //设置单元格日期格式
            HSSFCell cellDate = bannerRow.createCell(3);
            cellDate.setCellStyle(cellStyle);
            cellDate.setCellValue(list.get(i).getCreateTime());

            if(list.get(i).getStatus()==1) {
                bannerRow.createCell(4).setCellValue("播放");
            }else {
                bannerRow.createCell(4).setCellValue("不播放");
            }
        }
        try {
            String s1 = URLEncoder.encode("轮播图.xls","UTF-8");
            response.setHeader("content-disposition","attachment;filename="+s1);
            response.setContentType("application/x-xls");
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

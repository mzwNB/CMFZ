package com.baizhi.cmfz_mzw.viewObject;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.baizhi.cmfz_mzw.enetity.Chapter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AlbumDto implements Serializable {
    @ExcelCollection(name = "章节")
    List<Chapter> children;
    @Excel(name = "专辑编号", height = 20, width = 30, needMerge = true)
    private  Integer id;
    @Excel(name = "专辑标题", height = 20, width = 30, needMerge = true)
    private  String title;
}

package com.baizhi.cmfz_mzw.enetity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("chapter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {

    private String id;
    @Excel(name = "章节标题")
    private String title;
    @Excel(name = "章节大小")
    private String size;
    @Excel(name = "播放时长")
    private String  duration;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "publish_date")
    @Excel(name = "出版时间", format = "yyyy-MM-dd", width = 30)
    private Date publishDate;
    @TableField("chapter_path")
    @Excel(name = "章节路径", width = 50)
    private String chapterPath;
    @TableField("album_id")
    private Integer albumId;

}

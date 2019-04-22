package com.baizhi.cmfz_mzw.enetity;

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
    private String title;
    private String size;
    private String  duration;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("publish_date")
    private Date publishDate;
    @TableField("chapter_path")
    private String chapterPath;
    @TableField("album_id")
    private Integer albumId;

}

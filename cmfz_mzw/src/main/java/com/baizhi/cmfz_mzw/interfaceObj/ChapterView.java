package com.baizhi.cmfz_mzw.interfaceObj;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("chapter")
public class ChapterView implements Serializable {

    String title;
    @JSONField(name = "download_url")
    @TableField("chapter_path")
    String downloadURL;

    String size;

    String duration;


    @TableField("album_id")
    @JSONField(serialize = false)
    Integer albumId;
}

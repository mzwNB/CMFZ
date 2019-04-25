package com.baizhi.cmfz_mzw.interfaceObj;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("album")
public class WenDetailView implements Serializable {
    String score;
    @JSONField(serialize = false)
    private Integer id;
    @TableField("img_path")
    private String thumbnail;
    @TableField("title")
    private String title;
    @TableField("author")
    private String author;
    @TableField("boardcast")
    private String boardcast;
    @TableField("brief")
    private String brief;
    @TableField("amount")
    @JSONField(name = "set_count")
    private Integer count;

    @TableField("publish_time")
    @JSONField(name = "create_date", format = "yyyy-MM-dd")
    private Date createDate;


}

package com.baizhi.cmfz_mzw.interfaceObj;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("album")
public class WenView implements Serializable {
    @JSONField(serialize = false)
    private Integer id;
    @TableField("img_path")
    private String thumbnail;
    @TableField("title")
    private String title;
    @TableField("author")
    private String author;
    @TableField(exist = false)
    private Integer type = 0;

    @TableField("amount")
    @JSONField(name = "set_count")
    private Integer count;
    @TableField("publish_time")
    @JSONField(name = "create_date", format = "yyyy-MM-dd")
    private Date createDate;


}

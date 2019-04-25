package com.baizhi.cmfz_mzw.interfaceObj;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("article")
public class SiView implements Serializable {
    @TableField(exist = false)
    private String thumbnail;

    private String title;

    private String author;
    @TableField(exist = false)
    private Integer type = 1;

    @TableField(exist = false)
    @JSONField(name = "set_count")
    private Integer count;

    @JSONField(name = "create_date")
    private Integer createDate;
}

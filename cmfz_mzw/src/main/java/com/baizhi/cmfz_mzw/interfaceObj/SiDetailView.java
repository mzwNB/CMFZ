package com.baizhi.cmfz_mzw.interfaceObj;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("article")
public class SiDetailView implements Serializable {
    @TableField("img_path")
    String link;
    @TableField("id")
    Integer id;
    @TableField(exist = false)
    String ext;
}

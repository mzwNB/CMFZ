package com.baizhi.cmfz_mzw.interfaceObj;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("banner")
public class BannerView implements Serializable {
    @TableField("img_path")
    private String thumbnail;
    @TableField("title")
    private String desc;
    private Integer id;

}

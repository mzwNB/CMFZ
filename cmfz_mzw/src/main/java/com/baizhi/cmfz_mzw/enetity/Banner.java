package com.baizhi.cmfz_mzw.enetity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("banner")
public class Banner implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    @TableField(value = "img_path")
    private String imgPath;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date")
    private Date createTime;

    private Integer status;

}

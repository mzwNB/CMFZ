package com.baizhi.cmfz_mzw.enetity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("album")
public class Album implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private Integer amount;
    @TableField("img_path")
    private String  imgPath;
    private String author;
    private String boardcast;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("publish_time")
    private Date publishTime;
    private String brief;
    private String  score;

    @TableField(exist = false)
    private List<Chapter> children;



}

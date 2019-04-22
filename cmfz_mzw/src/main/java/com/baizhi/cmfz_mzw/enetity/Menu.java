package com.baizhi.cmfz_mzw.enetity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName(value = "menu")
public class Menu implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    @TableField("icon_cls")
    private String iconCls;
    private  Integer parent_id;
    @TableField("jsp_url")
    private String url;
     @TableField(exist = false)
    private List<Menu> list ;

}

package com.baizhi.cmfz_mzw.interfaceObj;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class UserView implements Serializable {
    @JSONField(name = "uid")
    @TableId(type = IdType.AUTO)
    Integer id;
    String password;
    @JSONField(name = "farmington")
    String dharma;
    @JSONField(name = "nickname")
    String name;
    @JSONField(name = "gender")
    Integer sex;
    @JSONField(name = "photo")
    @TableField("head_img")
    String headImg;
    String province;
    String city;
    @JSONField(name = "description")
    String sign;
    String phone;

}

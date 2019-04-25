package com.baizhi.cmfz_mzw.enetity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    String dharma;
    Integer sex;
    String province;
    String city;
    String sign;
    Integer status;
    String phone;
    String password;
    Integer salt;
    String headImg;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    Date createDate;
    Integer masterId;

}

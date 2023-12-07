package com.hniu.yi.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.util.List;






@Data
@TableName(value = "病人信息")
public class HLW患者信息 {


    private String 病人id;
    @TableField(exist = false)
    private String yljgdm;
    @TableField(exist = false)
    private String hlwyljgdm;
    @TableField(exist = false)
    private String zjlx = "01";
    private String 身份证号;
    private String 手机号;
    private String 姓名;
    private String 性别;
    private String 出生日期;
    @TableField(exist = false)
    private String gmbz;
    @TableField(exist = false)
    private String gmxw;
    @TableField(exist = false)
    private String gzdwmc;
    @TableField(exist = false)
    private String gzdwdz;
    @TableField(exist = false)
    private String jzdz;
    private String 联系人姓名;
    private String 联系人关系;
    private String 联系人地址;
    private String 联系人电话;
    @TableField(exist = false)
    private String sfsmrz = "1";
    @TableField(exist = false)
    private String smrzfs = "身份证";
    @TableField(exist = false)
    private List<Card> cardList;






}

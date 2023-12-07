package com.hniu.yi.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "HLW_参数")
public class HLW参数 {
    private String 参数名;
    private String 参数值;
}

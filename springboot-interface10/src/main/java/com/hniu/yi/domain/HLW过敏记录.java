package com.hniu.yi.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "病人过敏记录")
public class HLW过敏记录 {

    private String 病人id;
    private String 药物名;
}

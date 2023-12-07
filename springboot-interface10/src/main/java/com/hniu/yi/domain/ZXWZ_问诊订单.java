package com.hniu.yi.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "ZXWZ_问诊订单")
public class ZXWZ_问诊订单 {

    private String 病人id;

}

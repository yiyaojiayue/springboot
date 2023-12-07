package com.hniu.yi.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniu.yi.domain.ZXWZ_问诊订单;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZXWZ_问诊订单Dao extends BaseMapper<ZXWZ_问诊订单> {

    // 获取在互联网挂号过的病人id
    @Select("select distinct 病人id from ZXWZ_问诊订单")
    public List<ZXWZ_问诊订单> getIdAll();

}

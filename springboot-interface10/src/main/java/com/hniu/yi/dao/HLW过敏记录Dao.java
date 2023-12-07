package com.hniu.yi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniu.yi.domain.HLW过敏记录;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HLW过敏记录Dao extends BaseMapper<HLW过敏记录> {

    @Select("select count(*) from 病人过敏记录 where 病人id = #{病人id}")
    public int count(String 病人id);

    // 获取过敏数据
    @Select("select  listagg(药物名, ';') within group(order by 病人id)  from 病人过敏记录 where 病人id = #{病人id}")
    public String getData(String 病人id);

}

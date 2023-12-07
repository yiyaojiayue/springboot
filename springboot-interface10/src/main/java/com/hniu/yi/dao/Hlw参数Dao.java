package com.hniu.yi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniu.yi.domain.HLW参数;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Hlw参数Dao extends BaseMapper<HLW参数> {

    @Select("select 参数值 from HLW_参数 where 参数名 = #{参数名}")
    public String getyljgdm(String 参数名);

    @Select("select 参数值 from HLW_参数 where 参数名 = #{参数名}")
    public String gethlwyljgdm(String 参数名);

}

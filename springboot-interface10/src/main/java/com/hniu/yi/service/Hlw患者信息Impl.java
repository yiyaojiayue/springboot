package com.hniu.yi.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hniu.yi.dao.HLW患者信息Dao;
import com.hniu.yi.dao.HLW过敏记录Dao;
import com.hniu.yi.dao.Hlw参数Dao;
import com.hniu.yi.dao.ZXWZ_问诊订单Dao;
import com.hniu.yi.domain.Card;
import com.hniu.yi.domain.HLW患者信息;
import com.hniu.yi.domain.ZXWZ_问诊订单;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Hlw患者信息Impl implements IHlw患者信息Service{


    @Autowired
    private HLW患者信息Dao HLWDao;

    @Autowired
    private HLW过敏记录Dao hlw过敏记录Dao;

    @Autowired
    private Hlw参数Dao hlw参数Dao;

    @Autowired
    private ZXWZ_问诊订单Dao zxwz_问诊订单Dao;

    @Override
    public List<HLW患者信息> getList() {

        // 获取在线问诊中下单的所有对象
        List<ZXWZ_问诊订单> idAll = zxwz_问诊订单Dao.getIdAll();

        ArrayList<String> Idlist = new ArrayList<>();

        // 将病人id放入到集合中
        for (ZXWZ_问诊订单 zxwz_问诊订单 : idAll) {
            Idlist.add(zxwz_问诊订单.get病人id());
        }


        List<HLW患者信息> hlw患者信息s = HLWDao.selectList(new LambdaQueryWrapper<HLW患者信息>()
                .in(HLW患者信息::get病人id, Idlist));

        hlw患者信息s.forEach(data->{
            String 病人id = data.get病人id();
            String yljgdm = hlw参数Dao.getyljgdm("yljgdm");
            String hlwyljgdm = hlw参数Dao.gethlwyljgdm("hlwyljgdm");
            data.setYljgdm(yljgdm);
            data.setHlwyljgdm(hlwyljgdm);

            ArrayList<Card> cards = new ArrayList<>();
            Card card = new Card(data.get身份证号(),"1");
            cards.add(card);
            data.setCardList(cards);
            // 有记录更新过敏标志为1
            int count = hlw过敏记录Dao.count(病人id);
            if (count == 0){
                data.setGmbz("0");
            }else{
                data.setGmbz("1");
                String 过敏数据 = hlw过敏记录Dao.getData(病人id);
                data.setGmxw(过敏数据);
            }
        });
        return hlw患者信息s;
    }
}

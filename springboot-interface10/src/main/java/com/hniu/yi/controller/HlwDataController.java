package com.hniu.yi.controller;


import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hniu.yi.dao.HLW过敏记录Dao;
import com.hniu.yi.dao.Hlw参数Dao;
import com.hniu.yi.domain.Card;
import com.hniu.yi.domain.HLW患者信息;
import com.hniu.yi.result.Result;
import com.hniu.yi.service.IHlw患者信息Service;
import com.hniu.yi.util.Sm3Util;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class HlwDataController {

    @Autowired
    private IHlw患者信息Service HlwService;

    @Autowired
    private Hlw参数Dao hlw参数Dao;

    @Autowired
    private HLW过敏记录Dao hlw过敏记录Dao;

    private static final String ENCODING = "UTF-8";

    private static String msg;
    private static String code;

    @GetMapping("index")
    public Result uploadPatientInfo() {

        List<HLW患者信息> list = HlwService.getList();

        JSONObject jsonObject = new JSONObject();

        list.forEach(data -> {
            String 病人id = data.get病人id();
            String yljgdm = hlw参数Dao.getyljgdm("yljgdm");
            String hlwyljgdm = hlw参数Dao.gethlwyljgdm("hlwyljgdm");
            data.setYljgdm(yljgdm);
            data.setHlwyljgdm(hlwyljgdm);

            ArrayList<Card> cards = new ArrayList<>();
            Card card = new Card(data.get身份证号(), "1");
            cards.add(card);

            data.setCardList(cards);

            // 有记录更新过敏标志为1
            int count = hlw过敏记录Dao.count(病人id);

            if (count == 0) {
                data.setGmbz("0");
            } else {
                data.setGmbz("1");
                String 过敏数据 = hlw过敏记录Dao.getData(病人id);
                data.setGmxw(过敏数据);
            }
            // 使用 Bouncy Castle 的 SM3Digest 进行计算
            SM3Digest digest = new SM3Digest();
            try {
                byte[] res = data.toString().getBytes("UTF-8");
                digest.update(res, 0, res.length);
                // 完成计算并获取结果
                byte[] result = new byte[digest.getDigestSize()];
                digest.doFinal(result, 0);
                // 将结果转换成十六进制字符串
                String sm3Hex = new String(Hex.encode(result));

                UUID uuid = UUID.randomUUID();

                String requestid = uuid.toString().replace("-", "");

                String signStr = "appCode=HNEHOS&requestid=" + requestid + "&hlwyljgdm=" + data.getHlwyljgdm() + "&method=uploadHospitalInfo&timestamp=" + System.currentTimeMillis() + "&req=" + sm3Hex;

                // 签名
                String resultHexString = "";
                try {
                    byte[] srcData = signStr.getBytes(ENCODING);
                    byte[] hash = Sm3Util.hash(srcData);

                    resultHexString = ByteUtils.toHexString(hash);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }


                jsonObject.put("appCode", "HNEHOS");
                jsonObject.put("hlwyljgdm", data.getHlwyljgdm());
                jsonObject.put("requestid", requestid);
                jsonObject.put("method", "uploadPatientInfo");

                jsonObject.put("req", data);
                jsonObject.put("digest", resultHexString);
                jsonObject.put("timestamp", System.currentTimeMillis());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            //写日志。记录请求记录

            //请求
            String resp = HttpRequest.post("")
                    .body(jsonObject.toJSONString())
                    .execute()
                    .body();
            JSONObject result = JSON.parseObject(resp);

            //解析结果
            String ResultCode = result.getString("code");

            if (ResultCode.equals("0000")) {
                //更新请求记录
                code = ResultCode;
            } else {
                // 请求失败，更新错误日志
                code = ResultCode;
                if (code.equals("0001")) {
                    code = ResultCode;
                    msg = "未查询到符号条件的记录";
                } else if (code.equals("2000")) {
                    code = ResultCode;
                    msg = "无效JSON";
                } else if (code.equals("2001")) {
                    code = ResultCode;
                    msg = "无效appCode";
                } else if (code.equals("2002")) {
                    code = ResultCode;
                    msg = "无效的医疗机构编码";
                } else if (code.equals("2003")) {
                    code = ResultCode;
                    msg = "签名错误";
                } else if (code.equals("3001")) {
                    code = ResultCode;
                    msg = "参数字段为空（msg 会提示那些字段为空，多个字段用| 分隔）";
                } else if (code.equals("3002")) {
                    code = ResultCode;
                    msg = "加密错误";
                } else if (code.equals("5000")) {
                    code = ResultCode;
                    msg = "接口程序异常";
                }
            }


        });

        return new Result("0000", "成功");

    }

}

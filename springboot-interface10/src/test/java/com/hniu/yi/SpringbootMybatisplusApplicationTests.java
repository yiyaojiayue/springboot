package com.hniu.yi;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hniu.yi.dao.HLW患者信息Dao;
import com.hniu.yi.dao.HLW过敏记录Dao;
import com.hniu.yi.dao.Hlw参数Dao;
import com.hniu.yi.dao.ZXWZ_问诊订单Dao;
import com.hniu.yi.domain.Card;
import com.hniu.yi.domain.HLW患者信息;
import com.hniu.yi.domain.ZXWZ_问诊订单;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

	@Autowired
	private HLW患者信息Dao HLWDao;

	@Autowired
	private HLW过敏记录Dao hlw过敏记录Dao;

	@Autowired
	private Hlw参数Dao hlw参数Dao;

	@Autowired
	private ZXWZ_问诊订单Dao zxwz_问诊订单Dao;

	private static final String ENCODING = "UTF-8";

	@Test
	void contextLoads() {

		List<ZXWZ_问诊订单> idAll = zxwz_问诊订单Dao.getIdAll();

		ArrayList<String> idlist = new ArrayList<>();

		for (ZXWZ_问诊订单 zxwz_问诊订单 : idAll) {
			idlist.add(zxwz_问诊订单.get病人id());
		}

		JSONObject jsonObject = new JSONObject();




	HLWDao.selectList(new LambdaQueryWrapper<HLW患者信息>().in(HLW患者信息::get病人id,idlist)).forEach(data->{

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

				String requestid =uuid.toString().replace("-","");

				String signStr="appCode=HNEHOS&requestid="+requestid+"&hlwyljgdm="+data.getHlwyljgdm()+"&method=uploadHospitalInfo&timestamp="+System.currentTimeMillis()+"&req="+sm3Hex;

                // 签名
				String resultHexString = "";
				try {
					byte[] srcData = signStr.getBytes(ENCODING);
					byte[] hash = hash(srcData);

					resultHexString = ByteUtils.toHexString(hash);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}


				jsonObject.put("appCode", "HNEHOS");
				jsonObject.put("hlwyljgdm", data.getHlwyljgdm());
				jsonObject.put("requestid", requestid);
				jsonObject.put("method", "uploadPatientInfo");

				jsonObject.put("req",data);
				jsonObject.put("digest", resultHexString);
				jsonObject.put("timestamp", System.currentTimeMillis());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}

			//写日志。记录请求记录

			//请求
//			String resp = HttpRequest.post("")
//					.body(jsonObject.toJSONString())
//					.execute()
//					.body();
//
//			JSONObject j = JSON.parseObject(resp);

			//解析结果

			//更新请求记录
		});

	}
	@Test
	void Test(){

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("appCode", "HNEHOS");
		jsonObject.put("hlwyljgdm", "444885014");
		jsonObject.put("requestid", "1234567");
		jsonObject.put("method", "uploadPatientInfo");

		jsonObject.put("req","");
		jsonObject.put("digest", "54AD63018DD8B61488A8D960794974D5FF9A47C11E97EC3FED4AC9C02589C86D");
		jsonObject.put("timestamp", System.currentTimeMillis());

		System.out.println(jsonObject);

	}

	public static byte[] hash(byte[] srcData){
		SM3Digest sm3Digest = new SM3Digest();
		sm3Digest.update(srcData,0,srcData.length);
		byte[] bytes = new byte[sm3Digest.getDigestSize()];
		sm3Digest.doFinal(bytes,0);
		return bytes;
	}

}

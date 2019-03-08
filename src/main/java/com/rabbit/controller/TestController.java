package com.rabbit.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbit.entity.TradeDetailParamDTO;

@RestController
public class TestController {
	@Autowired
	private RestTemplate RestTemplate;
	@RequestMapping("/testRPC")
  public void testRPC(){
		TradeDetailParamDTO request = new TradeDetailParamDTO();
		request.setContractAddr("");
		request.setCrtUserId("");
		request.setDes("");
		request.setFromAddr("123");
		request.setGas(new BigDecimal(0.1));
		request.setHash("123");
		request.setToAddr("456");
		request.setTokenName("KMOT");
		request.setTokenNum(new BigDecimal(1));
		request.setTokenType("MBC");
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("data", request);
		 HttpHeaders headers = new HttpHeaders();
		 MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		 headers.setContentType(type);
		 headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		          
		 JSONObject jsonObj = (JSONObject) JSONObject.toJSON(hashMap);
		 String url = new String("http://192.168.0.77:7073/api/wallet/mbc/v1/insertTradeDetail");
		 HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
		 String postForObject = RestTemplate.postForObject(url, formEntity, String.class);
		 System.out.println(postForObject);
  }
}

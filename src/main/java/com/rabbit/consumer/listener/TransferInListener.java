package com.rabbit.consumer.listener;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.rabbit.entity.DepositCoinAssetStatus;
import com.rabbit.entity.TransferInDto;
import com.rabbit.exception.AppException;

@Component("transferInListener")
@Slf4j
public class TransferInListener extends BaseListener {

//	@Autowired
//	private DepositCoinOrderService depositCoinOrderService;

	@Override
	public void onMessage(JSONObject json) {

		log.info("receive message : {}", json.toJSONString());
		TransferInDto dto = JSONObject.toJavaObject(json, TransferInDto.class);

		String assetCode = dto.getAssetCode();
		String fromAddress = dto.getFromWallet();
		String toAddress = dto.getToWallet();
		BigDecimal amount = dto.getAmount();
		String outerOrder = dto.getOrderId();
		String sendMessage = dto.getSendMessage();
		DepositCoinAssetStatus status = dto.getAssetStatus();
		if (null == status || Strings.isNullOrEmpty(outerOrder) || Strings.isNullOrEmpty(assetCode)
				|| Strings.isNullOrEmpty(toAddress) || null == amount) {
			log.info("订单字段异常状�?异常，状态字段无法解�?");
			throw new AppException("1","订单字段异常状�?异常，状态字段无法解�?");
		}

		if (null == fromAddress) {
			fromAddress = "";
		}

		switch (status) {
		case CONFIRM:
			//已经发送转账了
			System.out.println("已经发送转账了");
//			depositCoinOrderService.coinDepositOrder(assetCode, toAddress, amount, outerOrder, sendMessage);
			break;
		case SUCCESS:
			//已经转账成功了
			System.out.println("已经转账成功了");
//			depositCoinOrderService.depositConfirm(assetCode, toAddress, amount, outerOrder, sendMessage);
			break;
		case FAILURE:
			//转账失败
			System.out.println("转账失败");
//			depositCoinOrderService.depositCancel(assetCode, toAddress, amount, outerOrder, sendMessage);
			break;
		default:
			log.info("无法识别的订单状�?资产{}订单�?{},状�?:", assetCode, outerOrder, status);
				throw new AppException("1","订单字段异常状�?异常，状态字段无法解�?");
		}
	}

}

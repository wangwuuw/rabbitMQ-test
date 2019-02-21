package com.rabbit.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferInDto {
	
	String assetCode;
	
	String orderId;
	
	String toWallet;
	
	String fromWallet;
	
	String sendMessage;
	
	BigDecimal amount;
	
	DepositCoinAssetStatus assetStatus;
	
}

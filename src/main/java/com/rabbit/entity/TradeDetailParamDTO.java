package com.rabbit.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 交易所 转账
 * @author xinbin.chen
 *
 */
public class TradeDetailParamDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 链上交易hash
	 */
	private String hash;
	/**
	 * 钱包转账地址
	 */
	private String fromAddr;
	/**
	 * 钱包转入地址
	 */
	private String toAddr;
	/**
	 * 通证类型
	 */
	private String tokenType;
	/**
	 * 合约地址
	 */
	private String contractAddr;
	/**
	 * 通证数量
	 */
	private BigDecimal tokenNum;
	/**
	 * 燃油费
	 */
	private BigDecimal gas;
	/**
	 * 交易备注
	 */
	private String des;
	/**
	 * 操纵人id
	 */
	private String crtUserId;
	/**
	 * 通证名称
	 */
	private String tokenName;
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getContractAddr() {
		return contractAddr;
	}
	public void setContractAddr(String contractAddr) {
		this.contractAddr = contractAddr;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getCrtUserId() {
		return crtUserId;
	}
	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}
	public String getTokenName() {
		return tokenName;
	}
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	public BigDecimal getTokenNum() {
		return tokenNum;
	}
	public void setTokenNum(BigDecimal tokenNum) {
		this.tokenNum = tokenNum;
	}
	public BigDecimal getGas() {
		return gas;
	}
	public void setGas(BigDecimal gas) {
		this.gas = gas;
	}
	public String getDes() {
		return des;
	}
	
}

package com.stockAPI.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyTranctionStockData {

	private Integer id;
	private Timestamp create_time;

	@JsonProperty(value = "Code")
	private String code;

	@JsonProperty(value = "Name")
	private String name;

	@JsonProperty(value = "TradeVolume")
	private Integer trade_volume;

	@JsonProperty(value = "TradeValue")
	private BigInteger trade_value;

	@JsonProperty(value = "OpeningPrice")
	private BigDecimal opening_price;

	@JsonProperty(value = "HighestPrice")
	private BigDecimal highest_price;

	@JsonProperty(value = "LowestPrice")
	private BigDecimal lowest_price;

	@JsonProperty(value = "ClosingPrice")
	private BigDecimal closing_price;

	@JsonProperty(value = "Change")
	private BigDecimal change_gap;

	@JsonProperty(value = "Transaction")
	private Integer transaction_count;

	public DailyTranctionStockData() {

	}

	public DailyTranctionStockData(Integer id, Timestamp create_time, String code, String name, Integer trade_volume,
			BigInteger trade_value, BigDecimal opening_price, BigDecimal highest_price, BigDecimal lowest_price,
			BigDecimal closing_price, BigDecimal change_gap, Integer transaction_count) {
		super();
		this.id = id;
		this.create_time = create_time;
		this.code = code;
		this.name = name;
		this.trade_volume = trade_volume;
		this.trade_value = trade_value;
		this.opening_price = opening_price;
		this.highest_price = highest_price;
		this.lowest_price = lowest_price;
		this.closing_price = closing_price;
		this.change_gap = change_gap;
		this.transaction_count = transaction_count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTrade_volume() {
		return trade_volume;
	}

	public void setTrade_volume(Integer trade_volume) {
		this.trade_volume = trade_volume;
	}

	public BigInteger getTrade_value() {
		return trade_value;
	}

	public void setTrade_value(BigInteger trade_value) {
		this.trade_value = trade_value;
	}

	public BigDecimal getOpening_price() {
		return opening_price;
	}

	public void setOpening_price(BigDecimal opening_price) {
		this.opening_price = opening_price;
	}

	public BigDecimal getHighest_price() {
		return highest_price;
	}

	public void setHighest_price(BigDecimal highest_price) {
		this.highest_price = highest_price;
	}

	public BigDecimal getLowest_price() {
		return lowest_price;
	}

	public void setLowest_price(BigDecimal lowest_price) {
		this.lowest_price = lowest_price;
	}

	public BigDecimal getClosing_price() {
		return closing_price;
	}

	public void setClosing_price(BigDecimal closing_price) {
		this.closing_price = closing_price;
	}

	public BigDecimal getChange_gap() {
		return change_gap;
	}

	public void setChange_gap(BigDecimal change_gap) {
		this.change_gap = change_gap;
	}

	public Integer getTransaction_count() {
		return transaction_count;
	}

	public void setTransaction_count(Integer transaction_count) {
		this.transaction_count = transaction_count;
	}

}

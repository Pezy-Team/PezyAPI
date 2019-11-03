package com.pezy.pezy_api.enumerate;

public enum OrderStatusEnum {
	WAIT("wait"),
	PAID_CONF_BY_CUST("paid_conf_by_cust"),
	PAID_CHECKED_BY_SELLER("paid_checked_by_seller"),
	CANCELLED_BY_CUST("cancelled_by_cust"),
	CANCELLED_BY_SELLER("cancelled_by_seller"),
	PACKED("packed"),
	TRANSPORTED("transported"),
	ARRIVED("arrived");
	
	private String code;
	
	public static OrderStatusEnum fromCode(String code) {
		for(OrderStatusEnum be : OrderStatusEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}
	
	private OrderStatusEnum(String code) {
		this.code = code;
	}
	

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}

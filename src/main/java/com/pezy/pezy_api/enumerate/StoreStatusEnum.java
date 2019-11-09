package com.pezy.pezy_api.enumerate;

public enum StoreStatusEnum {
	WAIT("wait"),
	PAID("paid"),
	UNPAID("unpaid"),
	APPROVED("approved"),
	SUSPEND("suspend"),
	BANNED("banned"),
	UNAVAILABLE("unavailable"),
	INACTIVE("inactive");
	

	
	private String code;
	
	public static StoreStatusEnum fromCode(String code) {
		for(StoreStatusEnum be : StoreStatusEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}
	
	private StoreStatusEnum(String code) {
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

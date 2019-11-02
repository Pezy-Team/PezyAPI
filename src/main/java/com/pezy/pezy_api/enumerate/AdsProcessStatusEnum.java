package com.pezy.pezy_api.enumerate;

public enum AdsProcessStatusEnum {
	WAIT("wait"),
	APPROVED("approved"),
	RUNNING("running"),
	SUSPEND("suspend"),
	INACTIVE("inactive");
	

	
	private String code;
	
	public static AdsProcessStatusEnum fromCode(String code) {
		for(AdsProcessStatusEnum be : AdsProcessStatusEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}
	
	private AdsProcessStatusEnum(String code) {
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

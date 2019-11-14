package com.pezy.pezy_api.enumerate;

public enum RegisterByEnum {

	FACEBOOK("facebook"),
	ETC("etc"),
	WEBSITE("website");
	
	private String code;
	
	public static RegisterByEnum fromCode(String code) {
		for(RegisterByEnum be : RegisterByEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}

	private RegisterByEnum(String code) {
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

package com.pezy.pezy_api.enumerate;

public enum GenderEnum {

	MALE("m"),
	FEMAIL("f");

	
	private String code;
	
	public static GenderEnum fromCode(String code) {
		for(GenderEnum be : GenderEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}
	
	private GenderEnum(String code) {
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

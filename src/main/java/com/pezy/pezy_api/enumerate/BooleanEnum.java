package com.pezy.pezy_api.enumerate;

public enum BooleanEnum {
	TRUE("t"),
	FALSE("f");
	
	private String code;
	
	public static BooleanEnum fromCode(String code) {
		for(BooleanEnum be : BooleanEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}

	private BooleanEnum(String code) {
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

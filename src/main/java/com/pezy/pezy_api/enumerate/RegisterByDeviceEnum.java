package com.pezy.pezy_api.enumerate;

public enum RegisterByDeviceEnum {


	ANDROID("android"),
	IOS("ios"),
	WEBSITE("website"),
	ETC("etc");
	
	private String code;
	
	public static RegisterByDeviceEnum fromCode(String code) {
		for(RegisterByDeviceEnum be : RegisterByDeviceEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}

	private RegisterByDeviceEnum(String code) {
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

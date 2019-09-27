package com.pezy.pezy_api.enumerate;

public enum UserTypeEnum {
	SUPERADMIN("superadmin"),
	ADMIN("admin"),
	MERCHANT("merchant"),
	CUSTOMER("customer");
	
	

	private String code;
	
	public static UserTypeEnum fromCode(String code) {
		for(UserTypeEnum en : UserTypeEnum.values()) {
			if(en.getCode().equals(code)) {
				return en;
			}
		}
		return null;
	}

	private UserTypeEnum(String code) {
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

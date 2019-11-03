package com.pezy.pezy_api.enumerate;

import javax.print.attribute.standard.MediaSize.Other;

public enum PezyArticleTypeEnum {
	OTHER("other"),
	HELP_CENTER("help_center"),
	ABOUT_PEZY("about_pezy");
	
	private String code;
	
	public static PezyArticleTypeEnum fromCode(String code) {
		for(PezyArticleTypeEnum be : PezyArticleTypeEnum.values()) {
			if(be.getCode().equals(code)) {
				return be;
			}
		}
		return null;
	}

	private PezyArticleTypeEnum(String code) {
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

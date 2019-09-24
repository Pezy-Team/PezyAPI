package com.pezy.pezy_api.pojo;

import lombok.Data;

@Data
public class UploadFileResponse {
	
	private String fileName;
	private String fileUploadUri;
	private String fileType;
	private long size;
	
	public UploadFileResponse(String cFileName, String cFileUploadUri, String cFileType, long cSize) {
		this.fileName = cFileName;
		this.fileUploadUri = cFileUploadUri;
		this.fileType = cFileType;
		this.size = cSize;
	}
	
}

package com.kh.semi.review.model.vo;

import java.util.Date;

public class ReviewAttachment implements java.io.Serializable {

	private String fileNo;
	private String originName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private String reviewNo;
	
	public ReviewAttachment() {}

	public ReviewAttachment(String fileNo, String originName, String changeName, String filePath, Date uploadDate,
			String reviewNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.reviewNo = reviewNo;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(String reviewNo) {
		this.reviewNo = reviewNo;
	}

	@Override
	public String toString() {
		return "ReviewAttachment [fileNo=" + fileNo + ", originName=" + originName + ", changeName=" + changeName
				+ ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", reviewNo=" + reviewNo + "]";
	}
	
	
	
}

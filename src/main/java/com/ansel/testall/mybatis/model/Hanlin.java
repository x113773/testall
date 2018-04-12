package com.ansel.testall.mybatis.model;

import java.util.Date;

public class Hanlin {

	private String id;

	private String title;

	private String url;

	private Integer thumbUp;

	private Date createDate;

	private String createDateStr;

	private Date updateDate;

	private String updateDateStr;

	private Integer first;

	private Integer cfirst;

	private Integer cthumb;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getThumbUp() {
		return thumbUp;
	}

	public void setThumbUp(Integer thumbUp) {
		this.thumbUp = thumbUp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getCfirst() {
		return cfirst;
	}

	public void setCfirst(Integer cfirst) {
		this.cfirst = cfirst;
	}

	public Integer getCthumb() {
		return cthumb;
	}

	public void setCthumb(Integer cthumb) {
		this.cthumb = cthumb;
	}

}
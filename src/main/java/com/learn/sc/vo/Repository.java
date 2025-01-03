package com.learn.sc.vo;

public class Repository {
	private String html_url;
	private String clone_url;
	
	public Repository() {}
	
	public String getHtml_url(){
		return this.html_url;
	}
	
	public void setHtml_url(String url) {
		this.html_url = url;
	}
	
	public String getClone_url(){
		return this.clone_url;
	}
	
	public void setClone_url(String url) {
		this.clone_url = url;
	}
	
	
}

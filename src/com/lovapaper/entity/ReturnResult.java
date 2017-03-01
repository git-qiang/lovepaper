package com.lovapaper.entity;
/**
 * 返回结果类
 * @author my love
 *
 */
public class ReturnResult {
	private Object data;
	private String msg;
	private int code;
	
	public ReturnResult(){
		
	}
	public ReturnResult(int code,String msg,Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
}

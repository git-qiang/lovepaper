package com.lovapaper.entity;

import java.util.List;
/**
 * 分页组件
 * @author Li Yongqiang
 * @param <T> 实体对象
 */
public class PageModel {
	private int totalRecords;//总记录数
	private List list;//结果集
	private List list1;//结果集
	private int pageNo;//当前页
	private int pageSize;//每页显示多少条 
	private List list2;//结果集
	/**
	 * 取得第一页
	 * @return 第一页
	 */
	public int getTopPageNo() {
		return 1;
	}
	/**
	 * 取得上一页
	 * @return 上一页
	 */
	public int getPreviousPageNo() {
		if (pageNo <= 1) {
			return 1;
		}
		return pageNo -1;
	}
	/**
	 * 取得下一页
	 * @return 下一页
	 */
	public int getNextPageNo() {
		if (pageNo >= getTotalPages()) {
			return getTotalPages() == 0 ? 1 : getTotalPages();
		}
		return pageNo + 1;
	}
	/**
	 * 取得最后一页
	 * @return 最后一页
	 */
	public int getBottomPageNo() {
		return getTotalPages() == 0 ? 1 : getTotalPages();
	}
	/**
	 * 取得总页数
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List getList1() {
		return list1;
	}
	public void setList1(List list1) {
		this.list1 = list1;
	}
	public List getList2() {
		return list2;
	}
	public void setList2(List list2) {
		this.list2 = list2;
	}
	
}

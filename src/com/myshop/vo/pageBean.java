package com.myshop.vo;

import java.util.ArrayList;
import java.util.List;

public class pageBean<T> {
	private String cid;
	private String pname;
	
	public String getPname() {
	return pname;
}

public void setPname(String pname) {
	this.pname = pname;
}

	// 当前页
	private int currentPage;
	// 当前页面显示总数
	private int currentCount;
	// 商品总数
	private int totalCount;
	// 总页数
	private int totalPage;
	// 页面数据
	private List<T> productList = new ArrayList<T>();

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getProductList() {
		return productList;
	}

	public void setProductList(List<T> productList) {
		this.productList = productList;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}

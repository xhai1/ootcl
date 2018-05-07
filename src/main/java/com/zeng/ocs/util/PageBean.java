package com.zeng.ocs.util;

import java.math.BigDecimal;
import java.util.List;
/**
 * 分页实体类
 * @author cxs
 *
 * @param <T>
 */
public class PageBean<T> {
	private int page;	// 当前页数
	private int totalCount; // 总记录数
	private int totalPage; // 总页数
	private int limit;	// 每页显示的记录数
	private List<T> list; // 每页显示数据的集合.
	private int begin;
	
	private Double subtotal;//小计
	private BigDecimal total;//总计
	
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public int getBegin(){
		return (page - 1) * limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
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
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}

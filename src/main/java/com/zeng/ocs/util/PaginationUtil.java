package com.zeng.ocs.util;

import java.util.List;

import com.zeng.ocs.configuration.Configuration;

/**
 * @date:2017年12月8日 下午3:47:25
 * @author Jianghai Yang
 * @version :
 */
public class PaginationUtil
{

	private Integer currentPage;//当前页
	private Integer totalPage;//总共多少页
	private Integer nextPage;//下一页
	private Integer perPageNum;//当前页显示数量
	/**
	 * 
	 */
	private Integer totalRecord;//总共记录数
	
	/**
	 * 存储多个相同条件
	 */
	private List<String> condition;
	

	private String condition1;//根据实际情况使用
	private String condition2;
	private String condition3;
	private String condition4;
	private String condition5;
	private String condition6;
	private Integer begin; //数据库索引
	private Integer end;
	
	public List<String> getCondition() {
		return condition;
	}
	public void setCondition(List<String> condition) {
		this.condition = condition;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public String getCondition5() {
		return condition5;
	}
	public void setCondition5(String condition5) {
		this.condition5 = condition5 == null ? null : condition5.trim();
	}
	public String getCondition6() {
		return condition6;
	}
	public void setCondition6(String condition6) {
		this.condition6 = condition6 == null ? null : condition6.trim();
	}
	
	
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	
	
	public String getCondition1() {
		return condition1;
	}
	public void setCondition1(String condition1) {
		this.condition1 =  condition1 == null ? null : condition1.trim();
	}
	public String getCondition2() {
		return condition2;
	}
	public void setCondition2(String condition2) {
		this.condition2 =  condition2 == null ? null : condition2.trim();
	}
	public String getCondition3() {
		return condition3;
	}
	public void setCondition3(String condition3) {
		this.condition3 =  condition3 == null ? null : condition3.trim();
	}
	public String getCondition4() {
		return condition4;
	}
	public void setCondition4(String condition4) {
		this.condition4 =  condition4 == null ? null : condition4.trim();
	}
	public PaginationUtil(){
		this.perPageNum = new Configuration().getPerPageNum();
	}
	public Integer getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage)
	{
		this.currentPage = currentPage;
	}
	public Integer getTotalPage()
	{
		return totalPage;
	}
	public void setTotalPage(Integer totalPage)
	{
		this.totalPage = totalPage;
	}
	public Integer getNextPage()
	{
		return nextPage;
	}
	public void setNextPage(Integer nextPage)
	{
		this.nextPage = nextPage;
	}
	public Integer getPerPageNum()
	{
		return perPageNum;
	}
	public void setPerPageNum(Integer perPageNum)
	{
		this.perPageNum = perPageNum;
	}

	public static String Prompt(String mes , String pageAction){//null时关闭子窗口
		if(pageAction == null)
		return "<script>alert('"+mes+"');window.opener.location.href = window.opener.location.href;window.close();</script>";
		else
			return "<script>alert('"+mes+"');location.href='"+pageAction+"';</script>";
	}
}

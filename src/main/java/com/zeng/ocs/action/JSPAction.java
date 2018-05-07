package com.zeng.ocs.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * jsp直接跳转，也即由菜单调用JSP页面的接口
 * @author cxs
 *
 */
@Controller
@RequestMapping("/jsp")
public class JSPAction {
	@RequestMapping("/topJsp")
	public String topJsp(Model model,String isroot,String username,String cusid){
		//引用TOP页面
		model.addAttribute("isroot", isroot);
		model.addAttribute("username", username);
		model.addAttribute("cusid", cusid);
		return "/WEB-INF/jsp/top.jsp";
	}
	@RequestMapping("/leftJsp")
	public String leftJsp(Model model,String isroot,String cusid){
		//引用左侧菜单栏页面
		model.addAttribute("isroot", isroot);
		model.addAttribute("cusid", cusid);
		return "/WEB-INF/jsp/left.jsp";
	}
	@RequestMapping("/placeJsp")
	public String placeJsp(){
		//引用位置标示页面
		return "/WEB-INF/jsp/place.jsp";
	}
	@RequestMapping("/indexJsp")
	public String indexJsp(Model model,String username){
		//引用欢迎页面
		model.addAttribute("username", username);
		return "/WEB-INF/jsp/index.jsp";
	}
	@RequestMapping("/loginJsp")
	public String loginJsp(){
		//打开登入界面
		return "/WEB-INF/jsp/login.jsp";
	}
//	@RequestMapping("/demosJsp")
//	public String demosJsp(){
//		//
//		return "/WEB-INF/jsp/demos.jsp";
//	}
//	@RequestMapping("/demoJsp")
//	public String demoJsp(){
//		return "/WEB-INF/jsp/demo.jsp";
//	}
	@RequestMapping("/RightUpdateOrderDetailJsp")
	public String RightUpdateOrderDetailJsp(){
		//打开改单明细查询页面
		return "/WEB-INF/jsp/RightUpdateOrderDetail.jsp";
	}
    
    @RequestMapping("/RightAddOrderDetailJsp")
	public String RightAddOrderDetailJsp(){
    	//打开补单明细查询页面
		return "/WEB-INF/jsp/RightAddOrderDetail.jsp";
	}
	@RequestMapping("/RightMonAccountJsp")
	public String RightMonAccountJsp(){
		//打开计费总量月统计表页面
		return "/WEB-INF/jsp/RightMonAccount.jsp";
	}
	@RequestMapping("/RightTotalAccountJsp")
	public String RightTotalAccountJsp(){
		//打开计费总量表页面
		return "/WEB-INF/jsp/RightTotalAccount.jsp";
	}
	@RequestMapping("/RightProBigClassJsp")
	public String RightProBigClassJsp(){
		//打开产品大类管理页面
		return "/WEB-INF/jsp/RightProBigClass.jsp";
	}
	@RequestMapping("/RightProComDetailJsp")
	public String RightProComDetailJsp(){
		//打开公司管理页面
		return "/WEB-INF/jsp/RightProComDetail.jsp";
	}
	@RequestMapping("/RightPhoneticDetailJsp")
	public String RightPhoneticDetailJsp(){
		//打开语音清单查询页面
		return "/WEB-INF/jsp/RightPhoneticDetail.jsp";
	}
	@RequestMapping("/RightOnlineServiceDetailJsp")
	public String RightOnlineServiceDetailJsp(){
		return "/WEB-INF/jsp/RightOnlineServiceDetail.jsp";
	}
	@RequestMapping("/RightUserInfoJsp")
	public String RightUserInfoJsp(){
		//打开用户信息页面
		return "/WEB-INF/jsp/RightUserInfo.jsp";
	}
	@RequestMapping("/CusTimeRecordJsp")
	public String CusTimeRecordJsp(){
		//打开改单明细查询页面
		return "/WEB-INF/jsp/CusTimeRecord.jsp";
	}
	@RequestMapping("/multimddtJsp")
	public String multimddtJsp(){
		//打开多媒体语音明细查询页面
		return "/WEB-INF/jsp/multimddt.jsp";
	}
	@RequestMapping("/errorpageJsp")
	public String errorpageJsp(){
		//打开错误页面
		return "/WEB-INF/jsp/errorpage.jsp";
	}
	@RequestMapping("/sysparamJsp")
	public String sysparamJsp(){
		//打开系统参数页面
		return "/WEB-INF/jsp/sysparam.jsp";
	}
	@RequestMapping("/useraddJsp")
	public String useraddJsp(){
		//打开用户添加页面
		return "/WEB-INF/jsp/useradd.jsp";
	}
	@RequestMapping("/userbrowseJsp")
	public String userbrowseJsp(){
		//打开用户浏览页面
		return "/WEB-INF/jsp/userbrowse.jsp";
	}
	@RequestMapping("/userupdateJsp")
	public String userupdateJsp(){
		//打开用户修改页面
		return "/WEB-INF/jsp/userupdate.jsp";
	}
	@RequestMapping("/hotlineaddJsp")
	public String hotlineaddJsp(){
		return "/WEB-INF/jsp/hotlineadd.jsp";
	}
	@RequestMapping("/hotlineupdateJsp")
	public String hotlineupdateJsp(){
		return "/WEB-INF/jsp/hotlineupdate.jsp";
	}
	@RequestMapping("/summarydetailJsp")
	public String summarydetailJsp(){
		return "/WEB-INF/jsp/summarydetail.jsp";
	}
	@RequestMapping("/addclassJsp")
	public String addclassJsp(){
		return "/WEB-INF/jsp/addclass.jsp";
	}
	@RequestMapping("/databackupJsp")
	public String databackupJsp(){
		//打开备份页面
		return "/WEB-INF/jsp/databackup.jsp";
	}
	@RequestMapping("/hotlineimportJsp")
	public String hotlineimportJsp(){
		return "/WEB-INF/jsp/hotlineimport.jsp";
	}
	
	@RequestMapping("/multi981Jsp")
	public String multi981Jsp(){
		//打开多媒体租户分机号添加页面
		return "/WEB-INF/jsp/multi981add.jsp";
	}
	
	
	@RequestMapping("/multi981ImportJsp")
	public String multi981ImportJsp(){
		//打开多媒体租户分机号导入页面
		return "/WEB-INF/jsp/multi981import.jsp";
	}
	
	@RequestMapping("/othersetJsp")
	public String othersetJsp(){
		//打开其它设置页面
		return "/WEB-INF/jsp/otherset.jsp";
	}
}

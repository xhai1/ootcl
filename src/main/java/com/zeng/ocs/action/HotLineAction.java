package com.zeng.ocs.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.exception.CustomException;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.SmsPort;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.HotLineService;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PaginationUtil;

/**
 * @date:2017年12月8日 上午8:55:28
 * @author Jianghai Yang
 * @version :
 */
@Controller
@RequestMapping("/hotline")
public class HotLineAction
{

	@Autowired
	private HotLineService hotLineService;
	
	@Autowired
	private CustomerService customerService;
	/**
	 * 头部与左部链接默认显示，查询所有数据，分页
	 * @author Jianghai Yang
	 * @FileName HotLineControl.java
	 * @return
	 */
	@RequestMapping("/show")
	public ModelAndView show(HttpSession session,PaginationUtil pageCondition)
	{
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/RightProComDetail.jsp");
		PaginationUtil page = new PaginationUtil();
		Configuration conf  =  new Configuration();
		
		if((pageCondition.getCondition1() != null) || (pageCondition.getCondition2() != null) || (pageCondition.getCondition3() != null) 
				|| (pageCondition.getCondition4() != null) || (pageCondition.getCondition5() != null) 
				){
			Long total = hotLineService.getCountByCondition(pageCondition);
			page.setTotalRecord(new Long(total).intValue());
			page.setTotalPage((int)Math.ceil(1.0 * total / conf.getPerPageNum()));
			pageCondition.setBegin(0);
			pageCondition.setEnd(conf.getPerPageNum());
			List<HotLine>  hotLines = hotLineService.showHotLineByContidion(pageCondition);
			
			page.setCurrentPage(1);
			page.setPerPageNum(hotLines.size());
			
			page.setCondition1(pageCondition.getCondition1() );
			page.setCondition2(pageCondition.getCondition2() );
			page.setCondition3(pageCondition.getCondition3() );
			page.setCondition4(pageCondition.getCondition4() );
			page.setCondition5(pageCondition.getCondition5() );
			mv.addObject("page", page);
			mv.addObject("hotlines", hotLines);
			return mv;
		}
		Long total = hotLineService.getCountByCondition(pageCondition);
		page.setTotalRecord(new Long(total).intValue());
		
		page.setTotalPage((int)Math.ceil(1.0 * total / conf.getPerPageNum()));
		pageCondition.setBegin(0);
		pageCondition.setEnd(conf.getPerPageNum());
		List<HotLine>  hotLines = hotLineService.showHotLineByContidion(pageCondition);
		
		page.setCurrentPage(1);
		page.setPerPageNum(hotLines.size());
		
		
		
		mv.addObject("page", page);
		mv.addObject("hotlines", hotLines);
		
		return mv;
	}

	@RequestMapping("/shownext")
	public ModelAndView showNext(PaginationUtil page)
	{
		Configuration conf  =  new Configuration();
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/RightProComDetail.jsp");
		if((page.getCondition1() != null) || (page.getCondition2() != null) || (page.getCondition3() != null)
				|| (page.getCondition4() != null) || (page.getCondition5() != null) 
				){
			if(page.getNextPage() == 1){
				page.setBegin(0);
				page.setEnd(conf.getPerPageNum());
				page.setCurrentPage(1);
			}else if((page.getNextPage() > 1 )&& (page.getNextPage() <= page.getTotalPage()) ){
				page.setBegin((page.getNextPage() - 1) * conf.getPerPageNum());
				page.setEnd(page.getNextPage()  * conf.getPerPageNum());
				page.setCurrentPage(page.getNextPage());
			}else if(page.getNextPage() > page.getTotalPage()){
				page.setBegin(0);
				page.setEnd(conf.getPerPageNum());
				page.setCurrentPage(1);
			}
			List<HotLine> hotLines = hotLineService.showHotLineByContidion(page);
			if(hotLines.size() < conf.getPerPageNum())page.setPerPageNum(hotLines.size());
			mv.addObject("page", page);
			mv.addObject("hotlines", hotLines);
			return mv;
		}
		
		if(page.getNextPage() == 1){
			page.setBegin(0);
			page.setEnd(conf.getPerPageNum());
			page.setCurrentPage(1);
		}else if((page.getNextPage() > 1 )&& (page.getNextPage() <= page.getTotalPage()) ){
			page.setBegin((page.getNextPage() - 1) * conf.getPerPageNum());
			page.setEnd(page.getNextPage()  * conf.getPerPageNum());
			page.setCurrentPage(page.getNextPage());
		}else if(page.getNextPage() > page.getTotalPage()){
			page.setBegin(0);
			page.setEnd(conf.getPerPageNum());
			page.setCurrentPage(1);
		}
		List<HotLine> hotLines = hotLineService.showHotLineByContidion(page);
		if(hotLines.size() < conf.getPerPageNum())page.setPerPageNum(hotLines.size());
		mv.addObject("page", page);
		mv.addObject("hotlines", hotLines);
		
		return mv;

	}
	
	@RequestMapping("/showBusiness")
	public ModelAndView showBusiness(@RequestParam String  business,@RequestParam String  cusTelephone,@RequestParam String telephone,@RequestParam String bigType)
	{
		HotLine hotLine = new HotLine();
		hotLine.setBusiness(business);
		hotLine.setCusTelephone(cusTelephone);
		hotLine.setTelephone(telephone);
		hotLine.setBigType(bigType);
		
		Integer limit = new Configuration().getPerPageNum();
		List<HotLine> HotLines = hotLineService.showHotlineBusinessAndOtheresByLike(hotLine);
		PaginationUtil page = new PaginationUtil();
		page.setCurrentPage(1);
		page.setTotalPage((int) (Math.ceil(HotLines.size() * 1.0 / limit)));
		page.setPerPageNum(HotLines.size() < limit ? HotLines.size() : limit);
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/RightProComDetail.jsp");
		mv.addObject("hotlines", HotLines);
		mv.addObject("page", page);
		return mv;

	}
	
	/**
	 * 添加热线号落地号
	 * @author Jianghai Yang
	 * @FileName HotLineAction.java
	 * @param business
	 * @param cusTelephone
	 * @param telephone
	 * @param bigType
	 * @return
	 */
	@RequestMapping(value="/addHotLine",method ={ RequestMethod.GET})
	@ResponseBody
	public  String addHotLine( HotLine hotLine)
	{
		//Integer limit = new Configuration().getPerPageNum();
		
		String OK = hotLineService.addHotLine(hotLine);
		return PaginationUtil.Prompt("添加成功!", null);
		/*ModelAndView mv = new ModelAndView("/WEB-INF/jsp/hotlineadd.jsp");
		mv.addObject("err", OK);
		return mv;*/
		
	/*	if(OK){
			List<HotLine> HotLines = hotLineService.showHotlineAll();
			PaginationUtil page = new PaginationUtil();
			page.setCurrentPage(1);
			page.setTotalPage((int) (Math.ceil(HotLines.size() * 1.0 / limit)));
			page.setPerPageNum(HotLines.size() < limit ? HotLines.size() : limit);
			ModelAndView mv = new ModelAndView("/WEB-INF/jsp/RightProComDetail.jsp");
			mv.addObject("hotlines", HotLines);
			mv.addObject("page", page);
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("/WEB-INF/jsp/hotlineadd.jsp");
			mv.addObject("err", "添加失败，没有这个客户。");
			return mv;
		}*/

	}
	
	@RequestMapping(value="/showUpdateHotLine",method ={ RequestMethod.GET})
	public @ResponseBody ModelAndView showUpdateHotLine( HttpSession session,HotLine hotLine,PaginationUtil page)
	{
		
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()==0){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		HotLine hotLineu = hotLineService.showHotlineByIdAndCusid(hotLine);
		
//		System.out.println(hotLineu);
		if(hotLineu != null){
			ModelAndView mv = new ModelAndView("/WEB-INF/jsp/hotlineupdate.jsp");
			mv.addObject("page", page);
			mv.addObject("hotline", hotLineu);
			return mv;
		}
		else {
			return null;
		}

	}
	
	
	@RequestMapping(value="/renewHotLine",method ={ RequestMethod.GET})
	public @ResponseBody String renewHotLine( HotLine hotLine,PaginationUtil page)
	{
		try
		{
			hotLineService.renewHotline(hotLine);
			return PaginationUtil.Prompt("更新成功!", "shownext.action?nextPage="+page.getCurrentPage()+"&totalPage="+page.getTotalPage()+"&totalRecord="+page.getTotalRecord()+"&condition1="+page.getCondition1()+"&condition2="+page.getCondition2()+"&condition3="+page.getCondition3()+"&condition4="+page.getCondition4()+"&condition5="+page.getCondition5());
//			ModelAndView mv = new ModelAndView("/WEB-INF/jsp/hotlineupdate.jsp");
//			HotLine hotLineu = hotLineService.showHotlineByIdAndCusid(hotLine);
//			mv.addObject("hotline", hotLineu);
//			mv.addObject("flag",1);
//			return mv;
		} catch (SQLException e)
		{
			return PaginationUtil.Prompt("更新失败!", "shownext.action?nextPage="+page.getCurrentPage()+"&totalPage="+page.getTotalPage()+"&totalRecord="+page.getTotalRecord()+"&condition1="+page.getCondition1()+"&condition2="+page.getCondition2()+"&condition3="+page.getCondition3()+"&condition4="+page.getCondition4()+"&condition5="+page.getCondition5());
//			ModelAndView mv = new ModelAndView("/WEB-INF/jsp/hotlineupdate.jsp");
//			mv.addObject("hotline", hotLine);
//			mv.addObject("flag",0);
//			return mv;
		}

	}
	
	
	@RequestMapping(value="/deleteHotLine",method ={ RequestMethod.GET})
	public  @ResponseBody ModelAndView deleteHotLine( Integer id,PaginationUtil pageCondition) throws RuntimeException
	{
		try
		{
			hotLineService.removeHotlineById(id);
//			return new ModelAndView("redirect:/hotline/show.action","pageCondition",pageCondition);
			

			Configuration conf  =  new Configuration();
			PaginationUtil page =  pageCondition;
			ModelAndView mv = new ModelAndView("/WEB-INF/jsp/RightProComDetail.jsp");
			if((page.getCondition1() != null) || (page.getCondition2() != null) || (page.getCondition3() != null)
					|| (page.getCondition4() != null) || (page.getCondition5() != null) 
					){
				if(page.getNextPage() == 1){
					page.setBegin(0);
					page.setEnd(conf.getPerPageNum());
					page.setCurrentPage(1);
				}else if((page.getNextPage() > 1 )&& (page.getNextPage() <= page.getTotalPage()) ){
					page.setBegin((page.getNextPage() - 1) * conf.getPerPageNum());
					page.setEnd(page.getNextPage()  * conf.getPerPageNum());
					page.setCurrentPage(page.getNextPage());
				}else if(page.getNextPage() > page.getTotalPage()){
					page.setBegin(0);
					page.setEnd(conf.getPerPageNum());
					page.setCurrentPage(1);
				}
				List<HotLine> hotLines = hotLineService.showHotLineByContidion(page);
				if(hotLines.size() < conf.getPerPageNum())page.setPerPageNum(hotLines.size());
				mv.addObject("page", page);
				mv.addObject("hotlines", hotLines);
				return mv;
			}
			
			if(page.getNextPage() == 1){
				page.setBegin(0);
				page.setEnd(conf.getPerPageNum());
				page.setCurrentPage(1);
			}else if((page.getNextPage() > 1 )&& (page.getNextPage() <= page.getTotalPage()) ){
				page.setBegin((page.getNextPage() - 1) * conf.getPerPageNum());
				page.setEnd(page.getNextPage()  * conf.getPerPageNum());
				page.setCurrentPage(page.getNextPage());
			}else if(page.getNextPage() > page.getTotalPage()){
				page.setBegin(0);
				page.setEnd(conf.getPerPageNum());
				page.setCurrentPage(1);
			}
			List<HotLine> hotLines = hotLineService.showHotLineByContidion(page);
			if(hotLines.size() < conf.getPerPageNum())page.setPerPageNum(hotLines.size());
			mv.addObject("page", page);
			mv.addObject("hotlines", hotLines);
			
			return mv;
			
		} catch (SQLException e)
		{
			throw new RuntimeException("删除失败！");
		}
	}
	
	@RequestMapping(value="/batchDel",method ={ RequestMethod.POST})
	public  @ResponseBody ModelAndView deleteHotLine(HttpServletRequest request, HttpServletResponse response) throws RuntimeException
	{
		try
		{
			String items = request.getParameter("delitems");
			String[] strs = items.split(",");  
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < strs.length; i++) {  
				
					int ID = Integer.parseInt(strs[i]);  
					ids.add(ID);  
				
			} 
			hotLineService.batchDelByIds(ids);
			return new ModelAndView("redirect:/hotline/show.action");
		} catch (SQLException e)
		{
			throw new RuntimeException("删除失败！");
		}
	}
	
	
	/**
	 * 导出全部到excel
	 * @author Jianghai Yang
	 * @FileName HotLineAction.java
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportHotLine")
	public void exportHotLine(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		List<HotLine> hotLineList=hotLineService.exportHotlineAll();
		for(HotLine hotLine : hotLineList)System.out.println(hotLine);
		response.setHeader("Content-Disposition", "attachment;filename=" +NetUtil.getFileName(request, "热线号落地号.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
         //具体导出hotline
        hotLineService.exportHotlineAllDetail(hotLineList, outputStream);
        
        if(outputStream != null){
			 outputStream.close();
		}
	}
    
	/**
	 * 查询数据库判断是否修改数据，修改数据则更新，无数据则增加
	 * @author Jianghai Yang
	 * @FileName HotLineAction.java
	 * @param request
	 * @throws CustomException 
	 * @throws IOException
	 * @throws SQLException 
	 */
	
	@RequestMapping(value="/importHotLine",method ={ RequestMethod.POST})
	@ResponseBody
	public String importHotLine(HttpServletRequest request) throws CustomException{
		
		/*
			<!-- 多部分文件上传 -->
			<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
			     <property name="maxUploadSize" value="104857600" />
			     <property name="maxInMemorySize" value="4096" />
			     <property name="defaultEncoding" value="UTF-8"></property>
			</bean>
		*/
		File dist = null;
		String result = "";
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request))
		{
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext())
			{
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null)
				{
					File dir = new File("./upload/");
					if (!dir.exists())
						dir.mkdirs();
					// 上传
					dist = new File(dir, file.getOriginalFilename());
					if( !file.getOriginalFilename().contains("热线号落地号")){
						result += "<script>alert('操作失败,需要上传热线号落地号。xlsx文件！');history.go(-1)</script>";
						
					}
					try {
						file.transferTo(dist);
					} catch (IllegalStateException | IOException e) {
						throw new CustomException("服务器传输错误!");
					}
				}

			}

		}
		
		List<HotLine> hots  =  hotLineService.readHotLine(dist);
		try {
			 hotLineService.importHotLineToDb(hots);
		} catch (SQLException e) {
			throw new CustomException("服务器传输错误!");
		}
		
		result += "<script>alert('导入成功!');location.href='show.action'</script>";
		return result;
	}
	
}

package com.zeng.ocs.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.MutilMedia981;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.MutilMedia981Service;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PaginationUtil;

/**
 * @date:2017年12月17日 下午8:02:33
 * @author Jianghai Yang
 * @version :
 */
@Controller
@RequestMapping(value="/multi981")
public class MultiMedia981Action
{
	@Autowired
	MutilMedia981Service  mutilMedia981Service; 
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/showAllMTulti981")
	public ModelAndView showAllTulti981(HttpSession session,PaginationUtil pageCondition){
		//这里使用session
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()==0){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}

		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/multimd981.jsp");
		PaginationUtil page = new PaginationUtil();
		Configuration conf  =  new Configuration();
		
		if((pageCondition.getCondition1() != null) || (pageCondition.getCondition2() != null) || (pageCondition.getCondition3() != null)		
				){
			Integer total = new Long(mutilMedia981Service.getCountByCondition(pageCondition)).intValue();
			page.setTotalRecord(total);
			
			page.setTotalPage((int)Math.ceil(1.0 * total / conf.getPerPageNum()));
			pageCondition.setBegin(0);
			pageCondition.setEnd(conf.getPerPageNum());
			List<MutilMedia981>  mutilMedia981s = mutilMedia981Service.showMultiMedia981ByCondition(pageCondition);
			
			page.setCurrentPage(1);
			page.setPerPageNum(mutilMedia981s.size());
			
			page.setCondition1(pageCondition.getCondition1() );
			page.setCondition2(pageCondition.getCondition2() );
			page.setCondition3(pageCondition.getCondition3() );			
			mv.addObject("page", page);
			mv.addObject("mutilMedia981s", mutilMedia981s);
			
			return mv;
		}
		Integer total = new Long(mutilMedia981Service.getCountByCondition(pageCondition)).intValue();
		page.setTotalRecord(total);
		page.setTotalPage((int)Math.ceil(1.0 * total / conf.getPerPageNum()));
		pageCondition.setBegin(0);
		pageCondition.setEnd(conf.getPerPageNum());
		List<MutilMedia981>  mutilMedia981s = mutilMedia981Service.showMultiMedia981ByCondition(pageCondition);
		
		page.setCurrentPage(1);
		page.setPerPageNum(mutilMedia981s.size());
		
		
		
		mv.addObject("page", page);
		mv.addObject("mutilMedia981s", mutilMedia981s);
		
		return mv;
		
		
		
	}
	
	
	
	/**
	 * 分页，显示下一页
	 * @return
	 */
	@RequestMapping("/shownext")
	public ModelAndView shownext(PaginationUtil page){
		
		
		Configuration conf  =  new Configuration();
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/multimd981.jsp");
		if((page.getCondition1() != null) || (page.getCondition2() != null) || (page.getCondition3() != null)				
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
			List<MutilMedia981> mutilMedia981s = mutilMedia981Service.showMultiMedia981ByCondition(page);
			if(mutilMedia981s.size() < conf.getPerPageNum())page.setPerPageNum(mutilMedia981s.size());
			mv.addObject("page", page);
			mv.addObject("mutilMedia981s", mutilMedia981s);
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
		List<MutilMedia981> mutilMedia981s = mutilMedia981Service.showMultiMedia981ByCondition(page);
		if(mutilMedia981s.size() < conf.getPerPageNum())page.setPerPageNum(mutilMedia981s.size());
		mv.addObject("page", page);
		mv.addObject("mutilMedia981s", mutilMedia981s);
		
		return mv;
		
	
	}
	
	@RequestMapping("/showMTulti981BySearch")
	public ModelAndView showMTulti981BySearch( MutilMedia981 mutilMedia981){
		List<MutilMedia981> mutilMedia981s = mutilMedia981Service.showMTulti981BySearch(mutilMedia981);
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/multimd981.jsp");
		mv.addObject("mutilMedia981s", mutilMedia981s);
		
		PaginationUtil page = new PaginationUtil();
		
		Configuration conf = new Configuration();
		
		page.setCurrentPage(1);
		page.setTotalPage((int) (Math.ceil(mutilMedia981s.size() * 1.0 / conf.getPerPageNum())));
		page.setPerPageNum(mutilMedia981s.size() < conf.getPerPageNum() ? mutilMedia981s.size() : conf.getPerPageNum());
		mv.addObject("page", page);
		
		return mv;		
	}
	
	@RequestMapping("/exportAllTulti981")
	public void exportAllTulti981(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<MutilMedia981> mutilMedia981s = mutilMedia981Service.exportMutilMedia981All();
		response.setHeader("Content-Disposition", "attachment;filename=" +  NetUtil.getFileName(request, "多媒体981租户分机号.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
         //具体导出多媒体981租户分机号
        mutilMedia981Service.exportAllTulti981Detail(mutilMedia981s, outputStream);
        
        if(outputStream != null){
			 outputStream.close();
		}
		
	}
	
	@RequestMapping("/showUpdateMutilMedia981")
	public ModelAndView showUpdateMutilMedia981(MutilMedia981 mutilMedia981,PaginationUtil page) throws IOException{
		
         //具体导出多媒体981租户分机号
		if(mutilMedia981 != null){
			ModelAndView mv = new ModelAndView("/WEB-INF/jsp/mutil981update.jsp");
			mv.addObject("mutilMedia", mutilMedia981);
			mv.addObject("page", page);
			return mv;
		}
		else {
			return null;
		}
		
	}
	
	@RequestMapping(value="/UpdateMutilMedia981",method={RequestMethod.POST})
	@ResponseBody
	public 	String UpdateMutilMedia981(MutilMedia981 mutilMedia981,PaginationUtil page) throws IOException{
		String result = mutilMedia981Service.UpdateMulti981(mutilMedia981);
	
		return  "<script>alert('"+result+"');location.href='shownext.action?nextPage="+page.getNextPage()+"&totalPage="+page.getTotalPage()+"+&totalRecord="+page.getTotalRecord()+"&condition1="+page.getCondition1()+"&condition2="+page.getCondition2()+"&condition3="+page.getCondition3()+"';</script>";
	}
	
	/**
	 * 添加控制器
	 * @author Jianghai Yang
	 * @FileName MultiMedia981Action.java
	 * @param mutilMedia981
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/AddMultiMedia981",method={RequestMethod.POST})
	@ResponseBody
	public String AddMultiMedia981(MutilMedia981 mutilMedia981) throws IOException{
		mutilMedia981Service.AddMulti981(mutilMedia981);
		return PaginationUtil.Prompt("添加成功!", null);
		 
		
	}
//	,produces={"application/json;","text/html;charset=UTF-8;"}
	@RequestMapping(value="/autoShowAddMultiMedia981",method={RequestMethod.GET})
	public void autoShowAddMultiMedia981(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String telephone = request.getParameter("telephone");
		String bigAndCus =  mutilMedia981Service.autoShowAddMultiMedia981(telephone);
		response.setContentType("text/text;charset=GBK");
		PrintWriter out = response.getWriter();
		out.write(bigAndCus);
		out.flush();
		out.close();
		
	}
	
	
	@RequestMapping(value="/importMultiMedia981",method={RequestMethod.POST})
	@ResponseBody
	public String importMultiMedia981(HttpServletRequest request) throws IOException{
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
					if (!file.getOriginalFilename().contains("多媒体981租户分机号")){						
						
						 result = "<script>alert('请添加多媒体981租户分机号!');"
									+ "window.location.href='showAllMTulti981.action'"
									+ "</script>";
							return  result;
					}
					file.transferTo(dist);
				}

			}

		}

		List<MutilMedia981> mutilMedia981s = mutilMedia981Service.readMultiMedia981(dist);
		List<MutilMedia981> errList = mutilMedia981Service.importMutilMedia981ToDb(mutilMedia981s);
		
		if(errList.size() > 0){
			result += "以下数据导入失败，系统无公司记录，需先导入相应公司的热线号落地号完整信息。之后再次尝试\\n";
			for(MutilMedia981 mutilMedia981 : errList){
				result += "\t 租户:"+ mutilMedia981.getItenantId()+"\t 分机号:"+ mutilMedia981.getExtNumber()
				+"\t 落地号:"+ mutilMedia981.getTelephone()
				+"\t 计时产品大类:"+ mutilMedia981.getBigType()+"\t 计时所属公司:"+ mutilMedia981.getCusName()+"\\n";
				
			}
			
			 result = "<script>alert('"+result+"');"
						+ "window.location.href='showAllMTulti981.action';"
						+ "</script>";
			return  result;
		}
		 result = "<script>alert('导入成功!');"
				+ "window.location.href='showAllMTulti981.action';"
				+ "</script>";
		return  result;
		
	}
	
	@RequestMapping(value="/delMultiMedia981")
	@ResponseBody
	public ModelAndView delMultiMedia981(MutilMedia981 mutilMedia981,PaginationUtil page){
		mutilMedia981Service.delMultiMedia981(mutilMedia981);			
		Configuration conf  =  new Configuration();
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/multimd981.jsp");
		if((page.getCondition1() != null) || (page.getCondition2() != null) || (page.getCondition3() != null)				
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
			List<MutilMedia981> mutilMedia981s = mutilMedia981Service.showMultiMedia981ByCondition(page);
			if(mutilMedia981s.size() < conf.getPerPageNum())page.setPerPageNum(mutilMedia981s.size());
			mv.addObject("page", page);
			mv.addObject("mutilMedia981s", mutilMedia981s);
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
		List<MutilMedia981> mutilMedia981s = mutilMedia981Service.showMultiMedia981ByCondition(page);
		if(mutilMedia981s.size() < conf.getPerPageNum())page.setPerPageNum(mutilMedia981s.size());
		mv.addObject("page", page);
		mv.addObject("mutilMedia981s", mutilMedia981s);
		
		return mv;
	}
}

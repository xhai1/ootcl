package com.zeng.ocs.action;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.exception.CustomException;
import com.zeng.ocs.exception.CustomExceptionResolver;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.MutilMedia981;
import com.zeng.ocs.po.SmsPort;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.SmsPortService;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PaginationUtil;

@Controller
@RequestMapping("/SmsPort")
public class SmsPortAction {

	@Autowired
	SmsPortService smsPortService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/searchSmsPort")
	public ModelAndView searchSmsPort(HttpSession session,PaginationUtil pageCondition){
		
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/smsport.jsp");
		PaginationUtil page = new PaginationUtil();
		Configuration conf  =  new Configuration();
		
		if((pageCondition.getCondition1() != null) || (pageCondition.getCondition2() != null) || (pageCondition.getCondition3() != null)){
			
			Integer total = new Long(smsPortService.getCountByCondition(pageCondition)).intValue();
			page.setTotalRecord(total);
			
			page.setTotalPage((int)Math.ceil(1.0 * total / conf.getPerPageNum()));
			pageCondition.setBegin(0);
			pageCondition.setEnd(conf.getPerPageNum());
			List<SmsPort>  smsPorts = smsPortService.showSmsPortByContidion(pageCondition);
			
			page.setCurrentPage(1);
			page.setPerPageNum(smsPorts.size());
			
			page.setCondition1(pageCondition.getCondition1() );
			page.setCondition2(pageCondition.getCondition2() );
			page.setCondition3(pageCondition.getCondition3() );
			
			mv.addObject("page", page);
			mv.addObject("smsPorts", smsPorts);
			return mv;
		}
		
		Integer total = new Long(smsPortService.getCountByCondition(pageCondition)).intValue();
		page.setTotalRecord(total);
		
		page.setTotalPage((int)Math.ceil(1.0 * total / conf.getPerPageNum()));
		pageCondition.setBegin(0);
		pageCondition.setEnd(conf.getPerPageNum());
		List<SmsPort>  smsPorts = smsPortService.showSmsPortByContidion(pageCondition);
		
		page.setCurrentPage(1);
		page.setPerPageNum(smsPorts.size());
		
		
		
		mv.addObject("page", page);
		mv.addObject("smsPorts", smsPorts);
		
		return mv;
		
	}
	
	
	/**
	 * 按条件搜索
	 * @return
	 */
	@RequestMapping("/searchSmsPortByCondition")
	public ModelAndView searchSmsPortByCondition(PaginationUtil page ){
		Configuration conf  =  new Configuration();
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/smsport.jsp");
		if((page.getCondition1() != null) || (page.getCondition2() != null) || (page.getCondition3() != null)){
			if(page.getNextPage() == 1){
				page.setBegin(0);
				page.setEnd(conf.getPerPageNum());
				page.setCurrentPage(1);
			}else if((page.getNextPage() > 1 )&& (page.getNextPage() <= page.getTotalPage()) ){
				page.setBegin((page.getNextPage() - 1) * conf.getPerPageNum());
				page.setEnd(conf.getPerPageNum());
				page.setCurrentPage(page.getNextPage());
			}else if(page.getNextPage() > page.getTotalPage()){
				page.setBegin(0);
				page.setEnd(conf.getPerPageNum());
				page.setCurrentPage(1);
			}
			List<SmsPort> smsPorts = smsPortService.showSmsPortByContidion(page);
			if(smsPorts.size() < conf.getPerPageNum())page.setPerPageNum(smsPorts.size());
			mv.addObject("page", page);
			mv.addObject("smsPorts", smsPorts);
			return mv;
		}
		
		if(page.getNextPage() == 1){
			page.setBegin(0);
			page.setEnd(conf.getPerPageNum());
			page.setCurrentPage(1);
		}else if((page.getNextPage() > 1 )&& (page.getNextPage() <= page.getTotalPage()) ){
			page.setBegin((page.getNextPage() - 1) * conf.getPerPageNum());
			page.setEnd(conf.getPerPageNum());
			page.setCurrentPage(page.getNextPage());
		}else if(page.getNextPage() > page.getTotalPage()){
			page.setBegin(0);
			page.setEnd(conf.getPerPageNum());
			page.setCurrentPage(1);
		}
		List<SmsPort> smsPorts = smsPortService.showSmsPortByContidion(page);
		if(smsPorts.size() < conf.getPerPageNum())page.setPerPageNum(smsPorts.size());
		mv.addObject("page", page);
		mv.addObject("smsPorts", smsPorts);
		
		return mv;
		
	}	
	
	
	
	@RequestMapping(value = "/addSmsPort", method = {RequestMethod.POST})
	 @ResponseBody
	public String addSmsPort(SmsPort smsPort){
		/*String result ="";
		result+="<script>alert('"+smsPortService.addSmsPort(smsPort)+"');history.go(-1)</script>";
		return result;*/
		return PaginationUtil.Prompt(smsPortService.addSmsPort(smsPort),null);
	}
	
	
	@RequestMapping("/updateSearchSmsPort")
	@ResponseBody
	public ModelAndView updateSearchSmsPort(SmsPort smsPort,PaginationUtil page){
		
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/smsportupdate.jsp");
		mv.addObject("smsPort", smsPort);
		mv.addObject("page", page);
		return mv;
	}
	@RequestMapping(value ="/updateSmsPort", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView   updateSmsPort(SmsPort smsPort,PaginationUtil page){
//		return PaginationUtil.Prompt(smsPortService.updateSmsPort(smsPort), null);
		smsPortService.updateSmsPort(smsPort);
		
		Configuration conf  =  new Configuration();
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/smsport.jsp");
		if((page.getCondition1() != null) || (page.getCondition2() != null) || (page.getCondition3() != null)){
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
			List<SmsPort> smsPorts = smsPortService.showSmsPortByContidion(page);
			if(smsPorts.size() < conf.getPerPageNum())page.setPerPageNum(smsPorts.size());
			mv.addObject("page", page);
			mv.addObject("smsPorts", smsPorts);
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
		List<SmsPort> smsPorts = smsPortService.showSmsPortByContidion(page);
		if(smsPorts.size() < conf.getPerPageNum())page.setPerPageNum(smsPorts.size());
		mv.addObject("page", page);
		mv.addObject("smsPorts", smsPorts);
		
		return mv;	
	}
	
	/**
	 * 导出excel，全部导出
	 * @param smsPort
	 * @return
	 * @throws CustomException 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	@RequestMapping(value ="/exportSmsPort", method = {RequestMethod.GET})
	@ResponseBody
	public void exportSmsPort(HttpServletRequest request,HttpServletResponse response) throws CustomException, UnsupportedEncodingException{
		response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "短信端口归属.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
		} catch (IOException e1) {
			throw new CustomException("网络出错，期待再次导出！");
		}
        
         //具体导出SmsPort
        smsPortService.exportAllSmsPort(outputStream);
        
        if(outputStream != null){
			 try {
				outputStream.close();
			} catch (IOException e) {
				throw new CustomException("导出出错，期待再次导出！");
			}
		}
	}
	
	@RequestMapping(value ="/importSmsPort", method = {RequestMethod.POST})
	@ResponseBody
	public String importSmsPort(HttpServletRequest request,HttpServletResponse response) throws CustomException {
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
					if( !file.getOriginalFilename().contains("短信端口归属")){
						result += "<script>alert(操作失败,需要上传短信端口归属。xlsx文件！);history.go(-1)</script>";
						return result;
					}
					try {
						file.transferTo(dist);
					} catch (IllegalStateException | IOException e) {
						throw new CustomException("文件上传出错，请检查网络环境。");
					} 
				}

			}

		}
		
		List<SmsPort> errList = smsPortService.importAllSmsPort(dist);
		if(errList.size() >= 1){
			String errLog = "正确数据导入成功，以下公司导入失败，请在系统中添加以下公司的详细信息，或导入公司的热线号落地号信息.<br/>";
			for(SmsPort smsPort : errList){
				errLog += "短信端口："+smsPort.getPort()+"\t 短信用途："+smsPort.getPurpose()+"\t 计时所属公司:"+smsPort.getCusname()+"\t 运营商:"+smsPort.getCaroprator()+"<br/>";
			}		
//			errLog = "<script>alert('"+errLog+"');location.href='searchSmsPort.action';</script>";
			return errLog;
			
		}
		result += "<script>alert('短信端口归属.xlsx文件导入成功！');location.href='searchSmsPort.action';</script>";
		return result;
	}
	
	public void responseLogFile( File file , HttpServletResponse response) throws CustomException{
		String downloadFilename;
		try {
			downloadFilename = URLEncoder.encode("短信端口归属日志", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			throw new CustomException("服务器操作错误");
		}  
        // 对默认下载的文件名编码。不编码的结果就是，在客户端下载时文件名乱码  
        if (file.exists()) {  
            // 写明要下载的文件的大小  
            response.setContentLength((int) file.length());  
            response.setHeader("Content-Disposition", "attachment;filename="  
                    + downloadFilename);// 设置在下载框默认显示的文件名  
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流  
            // 读出文件到response  
            // 这里是先需要把要把文件内容先读到缓冲区  
            // 再把缓冲区的内容写到response的输出流供用户下载  
            FileInputStream fileInputStream;
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new CustomException("服务器操作错误");
			}  
            BufferedInputStream bufferedInputStream = new BufferedInputStream(  
                    fileInputStream);  
            byte[] b;
			try {
				b = new byte[bufferedInputStream.available()];
				bufferedInputStream.read(b);
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(b);
				bufferedInputStream.close();
				outputStream.flush();
		        outputStream.close();  
			} catch (IOException e) {
				throw new CustomException("服务器操作错误");
			}              
        }  
	}
	
	@RequestMapping("/smsPortimportJsp")
	public String smsPortimportJsp(){
		return "/WEB-INF/jsp/smsportimport.jsp";
	}
	
	@RequestMapping("/smsportadd")
	public String smsportadd(){
		return "/WEB-INF/jsp/smsportadd.jsp";
	}
	
	
	@RequestMapping(value="/delSmsport")
	@ResponseBody
	public ModelAndView  delSmsport(SmsPort smsPort,PaginationUtil page){
		smsPortService.delSmsport(smsPort);
		
		Configuration conf  =  new Configuration();
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/smsport.jsp");
		if((page.getCondition1() != null) || (page.getCondition2() != null) || (page.getCondition3() != null)){
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
			List<SmsPort> smsPorts = smsPortService.showSmsPortByContidion(page);
			if(smsPorts.size() < conf.getPerPageNum())page.setPerPageNum(smsPorts.size());
			mv.addObject("page", page);
			mv.addObject("smsPorts", smsPorts);
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
		List<SmsPort> smsPorts = smsPortService.showSmsPortByContidion(page);
		if(smsPorts.size() < conf.getPerPageNum())page.setPerPageNum(smsPorts.size());
		mv.addObject("page", page);
		mv.addObject("smsPorts", smsPorts);
		
		return mv;		
	}
}

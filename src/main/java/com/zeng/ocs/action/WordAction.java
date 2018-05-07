package com.zeng.ocs.action;


import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试月报表导出word
 * @author cxs
 *
 */
@Controller
public class WordAction {
	@RequestMapping("/exportWord")
	public void exportWord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Disposition", "attachment;filename=" + new String("月报表.docx".getBytes("utf-8"), "ISO-8859-1"));
		response.setContentType("application/msword"); // word格式  
        ServletOutputStream outputStream = response.getOutputStream();
        /*WPSUtil.exportWord(outputStream);*/
        if(outputStream != null){
			 outputStream.close();
		}
	}
}
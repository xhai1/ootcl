package com.zeng.ocs.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download")
public class DownloadAction {
	@RequestMapping("/downloadHotLine")
	public void downloadHotLine(HttpServletResponse response,HttpServletRequest request) throws IOException{
		/*System.out.println(request.getRequestURL());
		System.out.println(System.getProperty("evan.webapp"));*/
		String filePath=System.getProperty("evan.webapp");
		File file=new File(filePath+"/download/热线号落地号 .xlsx");
		if (file == null || !file.exists()) {
            return;
        }
        OutputStream out = null;
        try {
            response.reset();
            /*response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());*/
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("热线号落地号.xlsx".getBytes("utf-8"), "ISO-8859-1"));
            response.setContentType("application/ynd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	@RequestMapping("/download981")
	public void download981(HttpServletResponse response,HttpServletRequest request) throws IOException{
		/*System.out.println(request.getRequestURL());
		System.out.println(System.getProperty("evan.webapp"));*/
		String filePath=System.getProperty("evan.webapp");
		File file=new File(filePath+"/download/多媒体981租户分机号.xlsx");
		if (file == null || !file.exists()) {
            return;
        }
        OutputStream out = null;
        try {
            response.reset();
            /*response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());*/
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("多媒体981租户分机号.xlsx".getBytes("utf-8"), "ISO-8859-1"));
            response.setContentType("application/ynd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	@RequestMapping("/downloadMessage")
	public void downloadMessage(HttpServletResponse response,HttpServletRequest request) throws IOException{
		/*System.out.println(request.getRequestURL());
		System.out.println(System.getProperty("evan.webapp"));*/
		String filePath=System.getProperty("evan.webapp");
		File file=new File(filePath+"/download/短信端口归属 .xlsx");
		if (file == null || !file.exists()) {
            return;
        }
        OutputStream out = null;
        try {
            response.reset();
            /*response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());*/
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("短信端口归属  .xlsx".getBytes("utf-8"), "ISO-8859-1"));
            response.setContentType("application/ynd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	@RequestMapping("/downloadMoneyStandard")
	public void downloadMoneyStandard(HttpServletResponse response,HttpServletRequest request) throws IOException{
		/*System.out.println(request.getRequestURL());
		System.out.println(System.getProperty("evan.webapp"));*/
		String filePath=System.getProperty("evan.webapp");
		File file=new File(filePath+"/download/计费标准.xlsx");
		if (file == null || !file.exists()) {
            return;
        }
        OutputStream out = null;
        try {
            response.reset();
            /*response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());*/
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("计费标准.xlsx".getBytes("utf-8"), "ISO-8859-1"));
            response.setContentType("application/ynd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}

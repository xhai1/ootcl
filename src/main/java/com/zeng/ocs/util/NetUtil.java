package com.zeng.ocs.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.zeng.ocs.exception.CustomException;
import com.zeng.ocs.shiro.CustomRealm;

import sun.misc.BASE64Encoder;

/*import java.util.Base64.Encoder;*/

public class NetUtil {

	/**
	 * 根据客户端信息生成相应编码的文件名，防止文件明乱码
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String getFileName(HttpServletRequest request , String fileName){
		//判断是否为IE浏览器
		String agent = request.getHeader("User-Agent").toUpperCase(); //获得浏览器信息并转换为大写
		try {
			if (agent.indexOf("MSIE") > 0 || (agent.indexOf("GECKO") > 0 && agent.indexOf("RV:11") > 0)) { // IE浏览器和Edge浏览器
				fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			} else if (agent.contains("FIREFOX")) {
				BASE64Encoder base64Encoder = new BASE64Encoder();

				fileName = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")).replaceAll("\\+", "%20")
						+ "?=";

			} else { // 其他浏览器

				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1").replaceAll("\\+", "%20");

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public static String  fileNameEncoding(HttpServletRequest request,String fileName) throws CustomException  {  
        String agent = request.getHeader("User-Agent").toUpperCase();  
		try {
			if (agent.contains("FIREFOX")) {
				BASE64Encoder base64Encoder = new BASE64Encoder();

				fileName = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")).replaceAll("\\+", "%20")
						+ "?=";

			} else {
				fileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
			}
		} catch (UnsupportedEncodingException e) {
			throw new CustomException("编码不被支持，请联系开发人员.");
		}
        return fileName;  
    }  
}

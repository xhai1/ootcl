package com.zeng.ocs.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * @date:2017年12月17日 上午8:36:14
 * @author Jianghai Yang
 * @version :
 */
public interface DataService
{

	public String backup();
	public String recorvery();
	public void download(HttpServletResponse response) throws IOException;
	
}

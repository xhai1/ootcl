package com.zeng.ocs.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.dao.ParaDao;
import com.zeng.ocs.po.Para;
import com.zeng.ocs.service.DataService;
import com.zeng.ocs.util.DbUtiL;

/**
 * @date:2017年12月17日 上午8:36:31
 * @author Jianghai Yang
 * @version :
 */
@Service("dataService")
public class DataServiceImpl implements DataService
{
	@Autowired
	private ParaDao paraDao;
	
	Configuration  conf = new Configuration();

	@SuppressWarnings("null")
	@Override
	public String backup()
	{
		String path = DbUtiL.backUp(conf.getUserName(), conf.getPassword(),conf.getDatabase());
		Para para =paraDao.selectByPkey("dataRecorveryPath");
		if(para == null)
		{
			para = new Para();
			para.setPkey("dataRecorveryPath");
			para.setPvalue(path);
			paraDao.insertSelective(para);
		}else{
			para.setPvalue(path);
			paraDao.updateByPrimaryKeySelective(para);
		}
		return "备份成功！";
	}

	@Override
	public String recorvery()
	{
		Para para = paraDao.selectByPkey("dataRecorveryPath");
		if(para != null){
			
			DbUtiL.restore(conf.getUserName(), conf.getPassword(),conf.getDatabase(),para.getPvalue());
			return "恢复成功";
		}
		return "恢复失败,请先备份！";
	}

	@Override
	public void download(HttpServletResponse response) throws IOException
	{
		Para para = paraDao.selectByPkey("dataRecorveryPath");
		if(para == null){
			
			return;
			
		}
		    BufferedInputStream bis = null;  
		    BufferedOutputStream bos = null;  
		  
		    
		    //获取下载文件
		    String downLoadPath = para.getPvalue();  
		  
		    //获取文件的长度
		    long fileLength = new File(downLoadPath).length();  

		    //设置文件输出类型
		    response.setContentType("application/octet-stream");  
		    response.setHeader("Content-disposition", "attachment; filename="  
		        + new String(new File(downLoadPath).getName().getBytes("utf-8"), "ISO8859-1")); 
		    //设置输出长度
		    response.setHeader("Content-Length", String.valueOf(fileLength));  
		    //获取输入流
		    bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
		    //输出流
		    bos = new BufferedOutputStream(response.getOutputStream());  
		    byte[] buff = new byte[2048];  
		    int bytesRead;  
		    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		      bos.write(buff, 0, bytesRead);  
		    }  
		    //关闭流
		    bos.close();  
		    bis.close();  
		 
	}  
		   
		
	
	

}

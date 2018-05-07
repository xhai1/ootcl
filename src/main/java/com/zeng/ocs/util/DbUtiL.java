package com.zeng.ocs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @date:2017年12月16日 下午10:14:02
 * @author Jianghai Yang
 * @version :
 */
public class DbUtiL
{

	/**
	 * 备份数据库，返回数据库本文件路径
	 * @author Jianghai Yang
	 * @FileName DbUtiL.java
	 * @param username
	 * @param password
	 * @param database
	 * @return
	 */
	public static String backUp(String username, String password, String database)
	{

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String currentTime = dateFormat.format(calendar.getTime());
		
        try {
            Runtime rt = Runtime.getRuntime();
 
            // 调用 调用mysql的安装目录的命令
//            Process child = rt.exec("mysqldump -h localhost -uroot -p642496 ocs");
            Process child = rt.exec("mysqldump -h localhost -u"+username+" -p"+password+" "+database);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
 
            InputStreamReader isr = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
 
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(isr);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(new File(".")+"\\"+currentTime+".sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            writer.close();
            fout.close();
            br.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

		return new File(".").getAbsolutePath() + "\\" + currentTime + ".sql";
	}
	
	
	public static void restore(String username, String password, String database,String filePath)
	{
		try
		{
			Runtime runtime = Runtime.getRuntime();
			// mysql -u root -p [dbname] < backup.sq
			// Process process = runtime.exec("mysql -hlocalhost -uroot -p642496
			// --default-character-set=utf8 "+ databaseName);
			Process process = runtime
					.exec("mysql -hlocalhost -u"+username+" -p"+password+" --default-character-set=utf8 " + database);
			OutputStream outputStream = process.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "utf-8"));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null)
			{
				sb.append(str + "\r\n");
			}
			str = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
			writer.write(str);
			writer.flush();
			writer.close();
			br.close();
			outputStream.close();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
//		System.out.println(DbUtiL.backUp("root", "642496", "ocs"));
//		D:\\MyEclipse 2015\\workspace\\ocs\\.\\20171216-232057.sql
//		DbUtiL.restore("root", "642496", "ocs" ,"C:\\Users\\admin\\Desktop\\文档\\ocschenxiaosheng.sql");
	}
}

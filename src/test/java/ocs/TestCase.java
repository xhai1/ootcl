package ocs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCase {
	public static void main(String[] args){
		//当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String endTime = df.format(new Date());// new Date()为获取当前系统时间
		System.out.println(endTime.substring(0,endTime.lastIndexOf("-")));
	}

}

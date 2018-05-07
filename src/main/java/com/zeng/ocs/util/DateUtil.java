package com.zeng.ocs.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	// 获取上一个月份
	public static String getLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		return mon;
	}
	 /** 
     * 获取某月的最后一天 
     * @Title:getLastDayOfMonth 
     * @Description: 
     * @param:@param year 
     * @param:@param month 
     * @param:@return 
     * @return:String 
     * @throws 
     */  
    public static String getLastDayOfMonth(int year,int month)  
    {  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
//        cal.set(Calendar.YEAR,year);  
        //设置月份  
//        cal.set(Calendar.MONTH, month-1);  
        cal.set(year,month-1,1);
        //获取某月最大天数  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String lastDayOfMonth = sdf.format(cal.getTime());  
          
        return lastDayOfMonth;  
    }  
    /** 
     * 获取某月的第一天 
     * getFirstDayOfMonth 
     * @Description: 
     * @param:@param year 
     * @param:@param month 
     * @param:@return 
     * @return:String 
     * @throws 
     */  
    public static String getFirstDayOfMonth(int year,int month)  
    {  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR,year);  
        //设置月份  
        cal.set(Calendar.MONTH, month-1);  
        //获取某月最大天数  
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, firstDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String firsDayOfMonth = sdf.format(cal.getTime());  
          
        return firsDayOfMonth;  
    }  
    
    /** 
     * @Title:main 
     * @Description: 
     * @param:@param args 
     * @return: void 
     * @throws 
     */  
    public static void main(String[] args)   
    {  
        String lastDay = getLastDayOfMonth(2020,2);  
        System.out.println("获取当前月的最后一天：" + lastDay);
        String firstDay = getFirstDayOfMonth(2020,2);  
        System.out.println("获取当前月的第一天：" + firstDay);
    } 
}
package ocs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//java 获取上月、一个月最后一天、周一、周日、本月日期
public class JavaDate {

	// 获取上月：
	public static void getToMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String time = format.format(c.getTime());

		// 得到一个月最后一天日期(31/30/29/28)
		int MaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 按你的要求设置时间
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), MaxDay, 23, 59, 59);
		// 按格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String gtime = sdf.format(c.getTime()); // 上月最后一天

	}

	/**
	 * 获取本日日期
	 * 
	 * @return
	 */
	public static String getToDay() {
		Calendar c = Calendar.getInstance();
		String time = c.getTime().toString();
		System.out.println(time);
		return time;
	}

	/**
	 * 获取本周一日期
	 * 
	 * @return
	 */
	public static String getWeekA() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// System.out.println(cal.getTime());
		String time = cal.getTime() + " 00:00:00";
		System.out.println(time);
		return time;
	}

	/**
	 * 获取本周日日期
	 * 
	 * @return
	 */
	public static String getWeekB() {
		Calendar cal = Calendar.getInstance();
		// System.out.println(cal.getTime());
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// System.out.println(cal.getTime());

		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		// System.out.println(cal.getTime()); //本周日
		String time = cal.getTime() + " 23:59:59";
		System.out.println(time);
		return time;
	}

	/**
	 * 获取本月日期
	 * 
	 * @return
	 */
	public static String getMonth() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String time = format.format(c.getTime());
		System.out.println(time);
		return time;
	}

	// java获取当前时间前一周、前一月、前一年的时间
	public static void getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();

		// 过去七天
		c.setTime(new Date());
		c.add(Calendar.DATE, -7);
		Date d = c.getTime();
		String day = format.format(d);
		System.out.println("过去七天：" + day);

		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		System.out.println("过去一个月：" + mon);

		// 过去三个月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -3);
		Date m3 = c.getTime();
		String mon3 = format.format(m3);
		System.out.println("过去三个月：" + mon3);

		// 过去一年
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date y = c.getTime();
		String year = format.format(y);
		System.out.println("过去一年：" + year);
	}

	public static void main(String[] args) {
		getDate();
	}

}
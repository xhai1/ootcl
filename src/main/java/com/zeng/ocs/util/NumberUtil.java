package com.zeng.ocs.util;

/**
 * @date:2017年12月16日 上午11:45:32
 * @author Jianghai Yang
 * @version :
 */
import java.text.DecimalFormat;

public class NumberUtil
{
	public static String ToChinese(int Number)
	{
		String ans = "";
		String source = String.valueOf(Number);

		return ans;
	}

	public static String ToBig(int num)
	{
		String str[] ={ "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾" };
		return str[num - 1];
	}

	public static String Test2(double x)
	{
		DecimalFormat format = new DecimalFormat("#.00");
		String str = format.format(x);
		System.out.println(str);
		String s[] = str.split("\\.");
		String temp = "";
		int ling = 0;
		int shu = 0;
		int pos = 0;
		for (int j = 0; j < s[0].length(); ++j)
		{
			int num = s[0].charAt(j) - '0';
			if (num == 0)
			{
				ling++;
				if (ling == s[0].length())
				{
					temp = "零";
				} else if (s[0].length() - j - 1 == 4)
				{
					if (shu == 1 && (s[0].length() - pos - 1) >= 5 && (s[0].length() - pos - 1) <= 7)
					{
						temp += "万";
					}
				} else if (s[0].length() - j - 1 == 8)
				{
					if (shu == 1 && (s[0].length() - pos - 1) >= 9 && (s[0].length() - pos - 1) <= 11)
					{
						temp += "亿";
					}
				}
			} else
			{
				shu++;
				int flag = 0;
				if (shu == 1)
				{
					ling = 0;
					pos = j;
				}
				if (shu == 2)
				{
					flag = 1;
					if (ling > 0)
					{
						temp += "零";
					}
					shu = 1;
					pos = j;
					ling = 0;
				}
				if (s[0].length() - j - 1 == 11)
				{
					temp += ToBig(num) + "仟";
				} else if (s[0].length() - j - 1 == 10)
				{
					temp += ToBig(num) + "佰";
				} else if (s[0].length() - j - 1 == 9)
				{
					if (num == 1 && flag != 1)
						temp += "拾";
					else
						temp += ToBig(num) + "拾";
				} else if (s[0].length() - j - 1 == 8)
				{
					temp += ToBig(num) + "亿";
				} else if (s[0].length() - j - 1 == 7)
				{
					temp += ToBig(num) + "仟";
				} else if (s[0].length() - j - 1 == 6)
				{
					temp += ToBig(num) + "佰";
				} else if (s[0].length() - j - 1 == 5)
				{
					if (num == 1 && flag != 1)
						temp += "拾";
					else
						temp += ToBig(num) + "拾";
				} else if (s[0].length() - j - 1 == 4)
				{
					temp += ToBig(num) + "万";
				} else if (s[0].length() - j - 1 == 3)
				{
					temp += ToBig(num) + "仟";
				} else if (s[0].length() - j - 1 == 2)
				{
					temp += ToBig(num) + "佰";
				} else if (s[0].length() - j - 1 == 1)
				{
					if (num == 1 && flag != 1)
						temp += "拾";
					else
						temp += ToBig(num) + "拾";
				} else
				{
					temp += ToBig(num);
				}
			}
			// System.out.println(temp);
		}
		temp += "元";
		for (int j = 0; j < s[1].length(); ++j)
		{
			int num = s[1].charAt(j) - '0';
			if (j == 0)
			{
				if (num != 0)
					temp += ToBig(num) + "角";
				else if (num == 0 && 1 < s[1].length() && s[1].charAt(1) != '0')
				{
					temp += "零";
				}
			} else if (j == 1)
			{
				if (num != 0)
					temp += ToBig(num) + "分";
			}
		}
		System.out.println(temp);
		return temp;
	}

	public static void main(String[] args)
	{

		Test2(800230001.23);
		Test2(800230001.00);
		Test2(800230001.01);
	}

}
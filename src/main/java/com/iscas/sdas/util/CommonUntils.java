package com.iscas.sdas.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 通用工具类
 * @author Administrator
 *
 */
public class CommonUntils {
	/**
	 * 判断字符串是否为null或者""
	 * Administrator
	 * 2017年9月27日下午8:41:36
	 * @param str
	 * @return
	 */
	public static boolean isempty(String str) {
		if (str==null) {
			return true;
		}else if (str=="") {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 生成导入文件路径
	 * Administrator
	 * 2017年9月27日下午8:42:05
	 * @return
	 */
	public static String generatePath() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		int date = c.get(Calendar.DATE); 

		
		String path = "E:\\load\\" + year + "\\" + month + "-" + date;
		
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		path += "\\";

		return path;
	}
	
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String DateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	/**
	 * 字符串转换成时间戳
	 * Administrator
	 * 2017年9月27日下午8:38:38
	 * @param str
	 * @return
	 */
	public static Timestamp strToTimestap(String str) {
		Timestamp ts = null;
	    try {
			ts =  Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return ts;
	}

}

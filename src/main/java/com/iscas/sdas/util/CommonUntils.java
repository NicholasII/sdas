package com.iscas.sdas.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
/**
 * 通用工具类
 * @author Administrator
 *
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
	
	/**
     * 根据用户名的不同长度，来进行替换 ，达到保密效果
     *
     * @param userName 用户名
     * @return 替换后的用户名
     */
    public static String userNameReplaceWithStar(String userName) {
        String userNameAfterReplaced = "";

        if (userName == null){
            userName = "";
        }

        int nameLength = userName.length();

        if (nameLength <= 1) {
            userNameAfterReplaced = "*";
        } else if (nameLength == 2) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{0})\\d(?=\\d{1})");
        } else if (nameLength >= 3 && nameLength <= 6) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{1})\\d(?=\\d{1})");
        } else if (nameLength == 7) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{1})\\d(?=\\d{2})");
        } else if (nameLength == 8) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{2})\\d(?=\\d{2})");
        } else if (nameLength == 9) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{2})\\d(?=\\d{3})");
        } else if (nameLength == 10) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{3})\\d(?=\\d{3})");
        } else if (nameLength >= 11) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{3})\\d(?=\\d{4})");
        }

        return userNameAfterReplaced;

    }

    /**
     * 实际替换动作
     *
     * @param username username
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");
    }

    /**
     * 身份证号替换，保留前四位和后四位
     *
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */
    public static String idCardReplaceWithStar(String idCard) {

        if (idCard.isEmpty() || idCard == null) {
            return null;
        } else {
            return replaceAction(idCard, "(?<=\\d{4})\\d(?=\\d{4})");
        }
    }

    /**
     * 银行卡替换，保留后四位
     *
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param bankCard 银行卡号
     * @return
     */
    public static String bankCardReplaceWithStar(String bankCard) {

        if (bankCard.isEmpty() || bankCard == null) {
            return null;
        } else {
            return replaceAction(bankCard, "(?<=\\d{0})\\d(?=\\d{4})");
        }
    }
    /**
     * 多文件上传
     * @param request
     * @return
     */
	public static List<String> MultipleFilesUpload(HttpServletRequest request) {
		List<String> filepaths = new ArrayList<>();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest mutiRequest = (MultipartHttpServletRequest) request;
			Iterator it = mutiRequest.getFileNames();
			while (it.hasNext()) {
				String filename = (String) it.next();
				System.out.println(filename);
				MultipartFile file = mutiRequest.getFile(filename);
				if (file != null) {
					String targetfile = request.getServletContext().getRealPath("/WEB-INF/order/") + file.getOriginalFilename();
					//System.out.println(request.getServletContext().getRealPath("/WEB-INF/order/"));
					try {
						file.transferTo(new File(targetfile));
						filepaths.add(targetfile);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return filepaths;
	}

}

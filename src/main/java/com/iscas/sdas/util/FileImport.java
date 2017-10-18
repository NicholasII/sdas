package com.iscas.sdas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.iscas.sdas.dto.TableInfoDto;
import com.mysql.fabric.xmlrpc.base.Data;

/**
 * 文件导入1、上传到指定目录；2、利用POI逐页行列对取存到bean中3、存到数据库
 * @author dq
 */
public class FileImport {
	/**
	 * 遍历excel存到list<T>中
	 * Administrator
	 * 2017年9月28日下午9:41:06
	 * @param path
	 * @param list
	 * @param tableindex
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static <T> List<T> importwork(String path,List<T> list,List<TableInfoDto> tableindex) throws Exception {
		File file = new File(path);
		List<T> result = list;
		Workbook workbook;
		try {
			InputStream is = new FileInputStream(file);
			String filetype = path.substring(path.lastIndexOf(".")+1);
			if ("xls".equals(filetype)) {
				workbook = new HSSFWorkbook(is);
			}else if ("xlsx".equals(filetype)) {
				workbook = new XSSFWorkbook(is);
			}else {
				throw new Exception("上传文件不是EXCEL文件");
			}
			//workbook.getNumberOfSheets()
			for (int page = 0; page < 1 ; page++) {//去第一页的数据
				Sheet sheet = workbook.getSheetAt(page);
				if (sheet == null) {
					continue;
				}
				int rowNum = sheet.getLastRowNum();
				for (int i = 1; i <= 10; i++) {
					T t = result.get(i-1);
					Row titlerow = sheet.getRow(0);
					Row row = sheet.getRow(i);
					int minCol = row.getFirstCellNum();
					int maxCol = row.getLastCellNum();
					for (int col = minCol; col < maxCol; col++) {
						Cell cell = row.getCell(col);
						Cell cellname = titlerow.getCell(col);
						String titlevalue = cellname.getStringCellValue();//excel中表头的字段值
						for (TableInfoDto column : tableindex) {
							if (column.getColumnComment().equals(titlevalue)) {
								String type = column.getColumnType();
								String fieldname = column.getColumnName();
								String  methodname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
								methodname = "set" + methodname;
								Class[] classes = new Class[1];																
								if ("int".equals(type)) {
									classes[0] = Integer.class;
									Method method = t.getClass().getMethod(methodname, classes);
									Integer value = (Integer)getTypeValue(type, cell);
									method.invoke(t, value);
								}else if ("varchar".equals(type) || "text".equals(type)) {
									classes[0] = String.class;
									Method method = t.getClass().getMethod(methodname, classes);
									String value = (String)getTypeValue(type, cell);
									method.invoke(t, value);
								}else if ("datetime".equals(type)) {
									classes[0] = Date.class;
									Method method = t.getClass().getMethod(methodname, classes);
									Date value = (Date)getTypeValue(type, cell);
									method.invoke(t, value);
								}
								
								break;
							}
						}								
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Test
	public void test(){
		String path = "C:\\Users\\liuyufeng\\Desktop\\work2.xls";
		Workbook workbook = null;
		File file = new File(path);
		try {
			InputStream is = new FileInputStream(file);
			String filetype = path.substring(path.lastIndexOf(".")+1);
			if ("xls".equals(filetype)) {
				workbook = new HSSFWorkbook(is);
			}else if ("xlsx".equals(filetype)) {
				workbook = new XSSFWorkbook(is);
			}else {
				//throw new Exception("上传文件不是EXCEL文件");
			}
			for (int page = 0; page < workbook.getNumberOfSheets(); page++) {
				Sheet sheet = workbook.getSheetAt(page);
				if (sheet == null) {
					continue;
				}
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					//T t = result.get(i-1);
					Row titlerow = sheet.getRow(0);
					Row row = sheet.getRow(i);
					int minCol = row.getFirstCellNum();
					int maxCol = row.getLastCellNum();
					for (int col = minCol; col < maxCol; col++) {
						Cell cell = row.getCell(col);
						Cell cellname = titlerow.getCell(col);
						String titlevalue = cellname.getStringCellValue();
						System.out.println("--标题："+titlevalue+"--内容："+getStringValue(cell));
						/*for (TableInfoDto column : tableindex) {
							if (column.getColumnComment().equals(titlerow)) {
								String type = column.getColumnType();
								titlevalue = cellname.getStringCellValue();
								String methodname = "set" +titlevalue.replace(titlevalue.substring(0, 1), titlevalue.substring(0, 1).toUpperCase());
								Class[] classes = new Class[1];
								classes[0] = String.class;
								Method method = t.getClass().getMethod(methodname, classes);
								method.invoke(t, cell.getStringCellValue());
								break;
							}
						}	*/							
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将各种类型的cell的值输出指定类型数据
	 * @param type 输出数据类型
	 * @param t 输出数据类实例
	 * @param tempvalue 输入字符串数据
	 * @return 数据库表中字段类型
	 */
	public static Object getTypeValue(String type, Cell cell) {
		
		if (cell != null) {
			if ("datetime".equals(type)) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_FORMULA:
					return cell.getCellFormula();
				case Cell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell) && "datetime".equals(type)) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						return date;
					} else {
						return cell.getNumericCellValue();
					}
				case Cell.CELL_TYPE_STRING:
					return cell.getStringCellValue();
				default:
					return null;
				}
			}else if ("varchar".equals(type)|| "text".equals(type)) {
				
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					return cell.getBooleanCellValue() ? "true" : "false";
				case Cell.CELL_TYPE_FORMULA:
					return cell.getCellFormula();
				case Cell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell) && "datetime".equals(type)) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						return format.format(date);
					} else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						return cell.getStringCellValue();
					}
				case Cell.CELL_TYPE_STRING:
					return cell.getStringCellValue();
				default:
					return null;
				}
			}else if ("int".equals(type)) {
				
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					return cell.getBooleanCellValue() ? 1 : 0;
				case Cell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell) && "datetime".equals(type)) {
						return null;
					} else {
						return (int)cell.getNumericCellValue();
					}
				case Cell.CELL_TYPE_STRING:
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					return cell.getNumericCellValue();
				default:
					return null;
				}
			}
			
		}
			return null;
	}
	
	
	
	/**
	 * 将各种类型的cell的值转换成string
	 * Administrator
	 * 2017年9月28日下午9:42:42
	 * @param cell
	 * @return
	 */
	public static Object getStringValue(Cell cell) {
		if (cell!=null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() ? true : false;
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			case Cell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					
					return format.format(date);
				}else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					return cell.getStringCellValue();
				}		
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return "EMPTY";
			}
		}
		return "EMPTY";
		
	}
	/**
	 * 第一页里有几行(除去表头)
	 * @param table
	 * @return
	 */
	public static int tablerows(String table){
		File file = new File(table);
		Workbook workbook;
		int result = 0;
		try {
			InputStream is = new FileInputStream(file);
			String filetype = table.substring(table.lastIndexOf(".")+1);
			if ("xls".equals(filetype)) {
				workbook = new HSSFWorkbook(is);
			}else if ("xlsx".equals(filetype)) {
				workbook = new XSSFWorkbook(is);
			}else {
				throw new Exception("上传文件不是EXCEL文件");
			}
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet!=null) {
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					result++;
				}
			}
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}
		return result;
	}

}

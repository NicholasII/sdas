package com.iscas.sdas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.iscas.sdas.dto.TableInfoDto;

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
	public static <T> List<T> name(String path,List<T> list,List<TableInfoDto> tableindex) throws Exception {
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
			for (int page = 0; page < workbook.getNumberOfSheets(); page++) {
				Sheet sheet = workbook.getSheetAt(page);
				if (sheet == null) {
					continue;
				}
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					T t = result.get(i-1);
					Row titlerow = sheet.getRow(0);
					Row row = sheet.getRow(i);
					int minCol = row.getFirstCellNum();
					int maxCol = row.getLastCellNum();
					for (int col = minCol; col < maxCol; col++) {
						Cell cell = row.getCell(col);
						Cell cellname = titlerow.getCell(col);
						String titlevalue = cellname.getStringCellValue();
						for (TableInfoDto column : tableindex) {
							if (column.getColumnComment().equals(titlerow)) {
								titlevalue = cellname.getStringCellValue();
								String methodname = "set" +titlevalue.replace(titlevalue.substring(0, 1), titlevalue.substring(0, 1).toUpperCase());
								Class[] classes = new Class[1];
								classes[0] = String.class;
								Method method = t.getClass().getMethod(methodname, classes);
								method.invoke(t, cell.getStringCellValue());
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
	
	/**
	 * 将各种类型的cell的值转换成string
	 * Administrator
	 * 2017年9月28日下午9:42:42
	 * @param cell
	 * @return
	 */
	public static String getStringValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return "EMPTY";
		}
	}

}

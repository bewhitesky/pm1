package com.cszx.common.excel;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cszx.util.CacheUtil;

public class ExcelUtil {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	private static CacheUtil cacheUtil = new CacheUtil();
	private static Map<String, String> dataMap;

	/**
	 * Excel导入
	 */
	public static List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
		List<List<Object>> list = null;
		// 创建Excel工作薄
		Workbook work = getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		list = new ArrayList<List<Object>>();
		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 遍历当前sheet中的所有行
			// 包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				// 读取一行
				row = sheet.getRow(j);
				// 去掉空行和表头
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				// 遍历所有的列
				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < sheet.getRow(0).getLastCellNum(); y++) {
					try {
						cell = row.getCell(y);
						li.add(getCellValue(cell));
					} catch (NullPointerException e) {
						if (cell == null) {
							li.add("");
							continue;
						}
					}
				}
				list.add(li);
			}
		}
		return list;
	}

	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 */
	public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化字符类型的数字
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}

	/**
	 * 导入Excel表结束 导出Excel表开始
	 * 
	 * @param sheetName
	 *            工作簿名称
	 * @param clazz
	 *            数据源model类型
	 * @param objs
	 *            excel标题列以及对应model字段名
	 * @param map
	 *            标题列行数以及cell字体样式
	 */
	public static XSSFWorkbook createExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map,
			String sheetName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			ClassNotFoundException, IntrospectionException, ParseException {
		// 创建新的Excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
		XSSFSheet sheet = workbook.createSheet(sheetName);
		// 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
		dataMap = new HashMap<String, String>();
		dataMap.put("pDepartment", "project_department");
		dataMap.put("pType", "project_type");
		dataMap.put("pLevel", "project_level");
		dataMap.put("testType", "test_type");
		dataMap.put("currStateOne", "current_state");
		dataMap.put("currStateReport", "current_state_report");
		dataMap.put("currStateTwo", "current_state");
		dataMap.put("currState", "curr_state");
		dataMap.put("internalTestState", "test_state");// 内部测试状态
		dataMap.put("testPrincipal", "test_principal");// 测试负责人
		dataMap.put("thirdTest", "third_test");// 第三方测试
		dataMap.put("riskLevel", "risk_level");// 风险等级
		dataMap.put("taskState", "task_state");// 当前任务状态
		dataMap.put("testStage", "test_stage");// 测试阶段

		dataMap.put("imptask_state","imptask_state");//重要任务状态
		dataMap.put("imptask_taskcls","imptask_taskcls");//重要任务分类
		dataMap.put("p_charge", "test_principal");// 测试负责人

		dataMap.put("w_type", "w_type");// 测试负责人
		createFont(workbook); // 字体样式
		createTableHeader(sheet, map); // 创建标题（头）
		createTableRows(sheet, map, objs, clazz, workbook); // 创建内容
		return workbook;
	}

	private static XSSFCellStyle fontStyle;
	private static XSSFCellStyle fontStyle2;

	public static void createFont(XSSFWorkbook workbook) {
		// 表头
		fontStyle = workbook.createCellStyle();
		XSSFFont font1 = workbook.createFont();
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font1.setFontName("微软雅黑");
		font1.setFontHeightInPoints((short) 10);// 设置字体大小
		font1.setBold(true);

		fontStyle.setFont(font1);
		fontStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		fontStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		fontStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		fontStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		fontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		fontStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
		fontStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		fontStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		// 内容
		fontStyle2 = workbook.createCellStyle();
		XSSFFont font2 = workbook.createFont();
		font2.setFontName("微软雅黑");
		font2.setFontHeightInPoints((short) 10);// 设置字体大小
		fontStyle2.setFont(font2);
		fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
		fontStyle2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
	}

	/**
	 * 根据ExcelMapping 生成列头（多行列头）
	 * 
	 * @param sheet
	 *            工作簿
	 * @param map
	 *            每行每个单元格对应的列头信息
	 */
	public static final void createTableHeader(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) {
		int startIndex = 0;// cell起始位置
		int endIndex = 0;// cell终止位置
		for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
			XSSFRow row = sheet.createRow(entry.getKey());
			List<ExcelBean> excels = entry.getValue();
			int flag = 0;
			int start = 0;
			int end = 0;
			for (int x = 0; x < excels.size(); x++) {
				// 合并单元格
				if (excels.get(x).getCols() > 1) {
					XSSFCell cell = null;
					if (excels.size() > 1) {// 标题为多行
						cell = row.createCell(excels.get(x).getCols());
						cell.setCellStyle(fontStyle);
						cell.setCellValue(excels.get(x).getHeadTextName());
					} else {// 标题为一行
						if (x == 0) {
							endIndex += excels.get(x).getCols() - 1;
							CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
							sheet.addMergedRegion(range);
							startIndex += excels.get(x).getCols();
						} else {
							endIndex += excels.get(x).getCols();
							CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
							sheet.addMergedRegion(range);
							startIndex += excels.get(x).getCols();
						}
						cell = row.createCell(startIndex - excels.get(x).getCols());
					}

					cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容
					if (excels.get(x).getCellStyle() != null) {
						cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式
					}
					cell.setCellStyle(fontStyle);
				} else {
					XSSFCell cell = row.createCell(x);
					if ((!excels.get(x).getIsCombine()) && entry.getKey() > 0) {
						sheet.addMergedRegion(new CellRangeAddress(0, 1, x, x));
					}
					cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容
					if (excels.get(x).getCellStyle() != null) {
						cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式

					}
					cell.setCellStyle(fontStyle);

					if (flag == 0 && entry.getKey() > 0) {
						sheet.addMergedRegion(new CellRangeAddress(0, 0, 14, 21));
						sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 29));
						flag++;
					}

				}
			}
		}
	}

	public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz,
			XSSFWorkbook workbook) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			IntrospectionException, ClassNotFoundException, ParseException {
		int rowindex = map.size();
		int maxKey = 0;
		List<ExcelBean> ems = new ArrayList<>();
		for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
			if (entry.getKey() > maxKey) {
				maxKey = entry.getKey();
			}
		}
		ems = map.get(maxKey);
		List<Integer> widths = new ArrayList<Integer>(ems.size());
		for (int i = 0; i < ems.size(); i++) {
			widths.add(ems.get(i).getHeadTextName().getBytes().length * 300);
		}
		int num = 0;
		String oldId = "";
		Boolean isCombine = false;
		int count = 0;
		int lastcol = 0;
		Boolean last = false;
		Boolean isChange = false;
		int isHide = -1;
		for (Object obj : objs) {
			XSSFRow row = sheet.createRow(rowindex);
			for (int i = 0; i < ems.size(); i++) {
				ExcelBean em = (ExcelBean) ems.get(i);
				// 获得get方法

				PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
				Method getMethod = pd.getReadMethod();
				Object rtn = getMethod.invoke(obj);
				String value = "";
				Double numDouble = null;
				// 如果是日期类型进行转换

				if ("num".equals(pd.getName())) {
					value = String.valueOf(++num);
				}
				if (rtn != null) {
					if (rtn instanceof Date) {
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						value = format.format(rtn);
					} else if (rtn instanceof BigDecimal) {
						NumberFormat nf = new DecimalFormat("#,##0.00");
						value = nf.format((BigDecimal) rtn).toString();
					} else if ((rtn instanceof Integer) && (Integer.valueOf(rtn.toString()) < 0)) {
						value = "--";
					} else if ((rtn instanceof Double) && (Double.valueOf(rtn.toString()) >= 0)) {
						numDouble = Double.parseDouble(rtn.toString());
					} else if (rtn instanceof Integer && (Integer.valueOf(rtn.toString()) >= 0)) {
						if (dataMap.get(pd.getName()) == null) {
							value = rtn.toString();
						} else {
							@SuppressWarnings("unchecked")
							Map<String, String> itemMap = (Map<String, String>) cacheUtil
									.getCache(dataMap.get(pd.getName()), "datadicMapCache");
							if (itemMap != null) {
								value = itemMap.get(rtn.toString());
							}
						}

					}else if ("p_charge".equals(pd.getName())){
							Map<String,String> itemMap = (Map<String,String>)cacheUtil
									.getCache(dataMap.get(pd.getName()),"datadicMapCache");
							value = rtn.toString();
							String[] values = value.split(",");
							value ="";
							for (String str : values){
								value = value +itemMap.get(str) +",";
							}

					} else {
						if ("currState".equals(pd.getName())) {
							@SuppressWarnings("unchecked")
							Map<String, String> itemMap = (Map<String, String>) cacheUtil
									.getCache(dataMap.get(pd.getName()), "datadicMapCache");
							value = rtn.toString();
							String[] values = value.split(",");
							value = "";
							for (String s : values) {
								value = value + itemMap.get(s) + ",";
							}
						} else {
							value = rtn.toString();
						}

					}
				}

				if ("id".equals(pd.getName())) {
					if (oldId.equals(rtn)) {
						isCombine = true;
						value = String.valueOf(num);
						count++;
						if (rowindex == objs.size()) {
							last = true;
							lastcol = 0;
						}
					} else {
						if (isCombine) {
							last = true;
							lastcol = 1;
						}
						isCombine = false;
						oldId = rtn.toString();
						value = String.valueOf(++num);
					}

				}
				if ("level".equals(pd.getName())) {
					if (rtn.toString().equals("1")) {
						isChange = true;
					} else {
						isChange = false;
					}
					isHide = i;
				}
				XSSFCell cell = row.createCell(i);
				if (last && i < 15) {
					sheet.addMergedRegion(new CellRangeAddress(rowindex - count - lastcol, rowindex - lastcol, i, i));

					if (i == 14) {
						count = 0;
						last = false;
					}

				}
				if (numDouble != null) {
					cell.setCellValue(numDouble);
					cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
					value = numDouble.toString();
				} else {
					cell.setCellValue(value);
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				}
				cell.setCellStyle(fontStyle2);
				// 不直接使用getCellStyle()，用cloneStyleFrom就能实现保持原有样式

				if ("taskName".equals(pd.getName())) {
					XSSFCellStyle cStyle = workbook.createCellStyle();
					cStyle.cloneStyleFrom(cell.getCellStyle());
					cStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT); // 居中
					cell.setCellStyle(cStyle);
				}
				if (isChange) {
					XSSFCellStyle cStyle = workbook.createCellStyle();
					cStyle.cloneStyleFrom(cell.getCellStyle());
					cStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
					cStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					cell.setCellStyle(cStyle);
					// row.setZeroHeight(true);
				}

				// 获得最大列宽
				int width = 0;
				if (value != null) {
					width = value.getBytes().length * 300;
				}
				// 还未设置，设置当前
				if (widths.size() <= i) {
					widths.add(width);
					continue;
				}
				// 比原来大，更新数据
				if (width > widths.get(i)) {
					widths.set(i, width);
				}
			}
			rowindex++;
		}
		if (!(isHide < 0)) {
			sheet.setColumnHidden(isHide, true);
		}

		// 设置列宽
		for (int index = 0; index < widths.size(); index++) {
			Integer width = widths.get(index);
			width = width < 2500 ? 2500 : width + 100;
			width = width > 10000 ? 10000 + 100 : width + 100;
			sheet.setColumnWidth(index, width);
		}
	}
}
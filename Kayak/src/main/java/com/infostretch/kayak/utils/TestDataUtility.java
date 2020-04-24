package com.infostretch.kayak.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataUtility {

	public static Logger log = LogManager.getLogger(TestDataUtility.class.getName());

	/**
	 * Reads the test data file
	 * 
	 * @return
	 * @return Array list
	 * @return HashMap with test data values in excel file
	 * @throws IOException
	 */

	public static ArrayList<ArrayList<Object>> getTestDataFromExcel() throws IOException {

		ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list = null;
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/com/infostretch/kayak/testdata/KayakTestData.xlsx");

		log.info("Test data file loaded");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheetIndex = workbook.getSheetIndex("Home");

		XSSFSheet homeSheet = workbook.getSheetAt(sheetIndex);
		Iterator<Row> rows = homeSheet.iterator();
		// skip header row
		rows.next();

		while (rows.hasNext()) {
			Row next = rows.next();
			Iterator<Cell> cellIterator = next.cellIterator();
			list = new ArrayList<Object>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getCellType().equals(CellType.STRING)) {
					String datastr = cell.getStringCellValue();
					// dataList.put("origin", datastr);
					list.add(datastr);
				} else if (cell.getCellType().equals(CellType.NUMERIC)) {
					Date datanum = (Date) cell.getDateCellValue();
					// dataList.put("fromdate", datanum);
					list.add(datanum);
				} else if (cell.getCellType().equals(CellType.BOOLEAN)) {
					boolean isOriginNearBy = cell.getBooleanCellValue();
					// dataList.put("originNearBy", isOriginNearBy);
					list.add(isOriginNearBy);

				}

			}
			dataList.add(list);

		}


		log.info("Test data loaded in collection");
		return dataList;

	}

	public static Date getDate(String date) {

		if (date != null)
			try {
				return new SimpleDateFormat("MM/dd/yyyy").parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// logger
			}
		return null;

	}
}

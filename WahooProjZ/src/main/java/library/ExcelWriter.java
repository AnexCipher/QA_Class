package library;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	
	Sheet sheet;
	Workbook WB;
	
	public ExcelWriter() throws IOException {
//		String filename = "data/DolibarrNewData.xlsx";
//		FileOutputStream fos = new FileOutputStream(filename);
		
		// Excel Concept:
		WB = new XSSFWorkbook();
		sheet = WB.createSheet("NewData");
//		WB.write(fos);
//		WB.close();
//		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		
	}
	
	public void inputData(int i, String data) throws IOException {
		
		String filename = "data/DolibarrNewData.xlsx";
		FileOutputStream fos = new FileOutputStream(filename);
		
		Row row = sheet.createRow(i);
//		int lastColumn = sheet.getRow(i).getPhysicalNumberOfCells() + 1;
		Cell cell = row.createCell(0);
		cell.setCellValue(data);
		
		int columns = sheet.getRow(i).getPhysicalNumberOfCells();
		for(int j = 0; j < columns; j++) {
			sheet.autoSizeColumn(j);
		}
		
		WB.write(fos);
	}

}

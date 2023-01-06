package library;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	// Global element Sheet : sheet
	Sheet sheet;

	// Create Excel
	public ExcelReader(String filename, String sheetname) throws IOException {
		// InputStream because data is being inputed
		FileInputStream fis = new FileInputStream(filename);
		// Create a WorkBook
		Workbook WB = new XSSFWorkbook(fis);
		// Get the Sheet
		sheet = WB.getSheet(sheetname);
		WB.close();
	}

	public Object[][] excelToArray() {
		Object[][] t;
		// Get the number of Row and Columns
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		// Initialise the size of the Array : t
		t = new Object[totalRows - 1][totalColumns];

		// Read each value from cell in Excel and store it in Array : t
		for (int row = 1; row < totalRows; row += 1) { // ROWS
			for (int column = 0; column < totalColumns; column += 1) { // COLUMNS
				// Using getCellData METHOD to store in Array : t[][]
				t[row - 1][column] = getCellData(row, column);
			}
		}
		return t;
	}

	// Method to grab each cell data
	public String getCellData(int row, int column) {
		String value; // Store the data
		Cell cell = sheet.getRow(row).getCell(column); // Obtaining the cell data
		DataFormatter formatter = new DataFormatter();
		
		value = formatter.formatCellValue(cell);

//		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
//			value = cell.getStringCellValue();
//		} else {
//			if (cell.getNumericCellValue() % 1 == 0) {
//				value = "" + (int) cell.getNumericCellValue();
//			} else {
//				value = "" + cell.getNumericCellValue();
//			}
//		}
		return value;
	}

}

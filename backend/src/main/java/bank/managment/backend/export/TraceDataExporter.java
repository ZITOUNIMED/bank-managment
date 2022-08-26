package bank.managment.backend.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bank.managment.backend.entities.TraceData;

public class TraceDataExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public TraceDataExporter() {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Trace Data");
	}
	
	private void prepareHeaders() {
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        
        createCell(row, 0, "Login", style);
        createCell(row, 1, "Method", style);
        createCell(row, 2, "Date", style);
        createCell(row, 3, "Args", style);
	}
	
	private void createCell(Row row, int index, String value, CellStyle style) {
        sheet.autoSizeColumn(index);
        Cell cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
	
	public void export(List<TraceData> tracesData, OutputStream output) {
		prepareHeaders();
		if(tracesData != null) {
			CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(false);
	        font.setFontHeight(14);
	        style.setFont(font);
	        int index = 1;
	        
	        for(TraceData traceData: tracesData) {
	        	writeLine(traceData, index++, style);
	        }
		}
		
		try {
			workbook.write(output);
			workbook.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeLine(TraceData traceData, int rowIndex, CellStyle style) {
		Row row = sheet.createRow(rowIndex);
		createCell(row, 0, traceData.getLogin(), style);
        createCell(row, 1,traceData.getMethod(), style);
        createCell(row, 2, traceData.getDate().toLocalDate().toString(), style);
        createCell(row, 3, traceData.getArgs(), style);
	}
}

package ee.auto24.parsing;

import ee.auto24.parsing.objects.Car;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateExcel {

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void main(String[] args) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Auto24");

        List<Car> makeList = new ArrayList<>();
        //List<Employee> list = EmployeeDAO.listEmployees();

        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // Mark
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mark");
        cell.setCellStyle(style);
        // Model
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Model");
        cell.setCellStyle(style);
        // Color
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Color");
        cell.setCellStyle(style);
        // Privod
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Privod");
        cell.setCellStyle(style);
        // Korobka
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Korobka");
        cell.setCellStyle(style);
        // Year
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("God");
        cell.setCellStyle(style);
        // Toplivo
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Toplivo");
        cell.setCellStyle(style);
        // Objom
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Objom");
        cell.setCellStyle(style);
        // Toplivo
        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Toplivo");
        cell.setCellStyle(style);
        // Probeg
        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Probeg");
        cell.setCellStyle(style);
        // Cena
        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("Cena2");
        cell.setCellStyle(style);
        // Cena2
        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Cena2");
        cell.setCellStyle(style);
        // url
        cell = row.createCell(12, CellType.STRING);
        cell.setCellValue("url");
        cell.setCellStyle(style);

        // Data
        for (Car car : makeList) {
            rownum++;
            row = sheet.createRow(rownum);

            // Make
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(car.getCarMake());
            // Model
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(car.getMakeModel());
            // Color
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(car.getCarColor());
            // Privod
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(car.getPrivod());
            // Korobka
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(car.getKorobka());
            // Year
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(car.getCarYear());
            // Toplivo
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(car.getTipTopliva());
            // Objom
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(car.getObjom());
            // Toplivo
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(car.getTipTopliva());
            // Probeg
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue(car.getProbeg());
            // Cena
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue(car.getCena());
            // Cena2
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue(car.getCena2());
            // url
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue(car.getCarUrl());

        }
        File file = new File("auto24.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }

}

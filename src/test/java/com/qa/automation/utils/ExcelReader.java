
package com.qa.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] getTestData(
            String fileName, String sheetName) {

        List<Object[]> data = new ArrayList<>();

        try (InputStream input =
                     ExcelReader.class.getResourceAsStream(
                             "/testdata/" + fileName)) {

            if (input == null) {
                throw new IllegalArgumentException(
                        "Excel file not found: " + fileName);
            }

            try (Workbook workbook = WorkbookFactory.create(input)) {
                Sheet sheet = workbook.getSheet(sheetName);

                if (sheet == null) {
                    throw new IllegalArgumentException(
                            "Sheet not found: " + sheetName);
                }

                DataFormatter formatter = new DataFormatter();

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);

                    if (row == null) {
                        continue;
                    }

                    String postId = formatter.formatCellValue(
                            row.getCell(0)).trim();
                    String userId = formatter.formatCellValue(
                            row.getCell(1)).trim();
                    String status = formatter.formatCellValue(
                            row.getCell(2)).trim();

                    if (postId.isEmpty() || userId.isEmpty()
                            || status.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Missing data at Excel row " + (i + 1));
                    }

                    data.add(new Object[]{
                            postId, userId, status
                    });
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read Excel test data", e);
        }

        return data.toArray(new Object[0][]);
    }
}

package com.wxj.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 * <p>Title: ExcelUtil</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-26 18:08
 */
public class ExcelUtil {

    public static boolean isRowEmpty(Row row) throws Exception {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            if (row.getCell(c).getCellTypeEnum() == CellType.STRING) {
                String cell = row.getCell(c).getStringCellValue();
                if (cell != null && !"".equals(cell) && !"null".equals(cell))
                    return false;
            }
            if (row.getCell(c).getCellTypeEnum() == CellType.NUMERIC) {
                Double cell = row.getCell(c).getNumericCellValue();
                if (cell != null)
                    return false;
            }
        }
        return true;
    }
}
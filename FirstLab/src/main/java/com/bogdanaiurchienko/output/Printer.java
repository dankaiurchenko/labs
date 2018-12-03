package com.bogdanaiurchienko.output;

import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Bogdana Iurchienko
 */
public class Printer {
  private ArrayList<String> sorterNames;
  private int[] arrayLength;
  private HashMap<String, HashMap<String, HashMap<String, Long>>> results;
  private final int NUMBER_OF_ROWS;
  private final int NUMBER_OF_COLS;

  public Printer(HashMap<String, HashMap<String, HashMap<String, Long>>> results, ArrayList<String> sorterNames, int[] arrayLengths) {
    this.results = results;
    this.arrayLength = arrayLengths;
    this.sorterNames = sorterNames;
    this.NUMBER_OF_COLS = sorterNames.size();
    this.NUMBER_OF_ROWS = arrayLengths.length;
  }

  public void print() throws PrinterException {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet spreadsheet;
    for(Map.Entry<String, HashMap<String, HashMap<String, Long>>> type : results.entrySet()){
      spreadsheet = workbook.createSheet(type.getKey());
      fillTheSheet(spreadsheet, type);
      drawTheGraph(spreadsheet);
    }
    try(FileOutputStream out = new FileOutputStream(new File(getName("Results")))){
      workbook.write(out);
    } catch (IOException e) {
      throw new PrinterException(e);
    }
  }

  private void fillTheSheet(XSSFSheet spreadsheet, Map.Entry<String, HashMap<String, HashMap<String, Long>>> type){
    XSSFRow row;
    int rowId = 0;
    int cellId = 0;
    row = spreadsheet.createRow(rowId++);
    row.createCell(cellId++).setCellValue("Array Length");
    for (String sorterName : sorterNames) {
      row.createCell(cellId++).setCellValue(sorterName.trim());
    }
    fillResultsRows(spreadsheet, rowId, type.getValue());
    for(int i = 0; i < sorterNames.size(); i++){
      spreadsheet.autoSizeColumn(i);
    }

  }

  private void fillResultsRows(XSSFSheet spreadsheet, int rowId, HashMap<String, HashMap<String, Long>> lengths){
    for (int anArrayLength : arrayLength) {
      XSSFRow row = spreadsheet.createRow(rowId++);
      int cellId2 = 0;
      row.createCell(cellId2++).setCellValue(anArrayLength);
      for (String sorterTime : sorterNames) {
        row.createCell(cellId2++).setCellValue(lengths.get(String.valueOf(anArrayLength)).get(sorterTime));
      }
    }
  }

  private void drawTheGraph(XSSFSheet sheet){
    XSSFDrawing drawing = sheet.createDrawingPatriarch();
    XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, NUMBER_OF_ROWS+2,
            NUMBER_OF_COLS+2, NUMBER_OF_ROWS+20);
    XSSFChart chart = drawing.createChart(anchor);
    XSSFChartLegend legend = chart.getOrCreateLegend();
    legend.setPosition(LegendPosition.BOTTOM);
    LineChartData data = chart.getChartDataFactory().createLineChartData();

    ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
    ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
    leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

    ChartDataSource<Number> sorters  = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 1, NUMBER_OF_COLS - 1));
    for(int i = 1; i < NUMBER_OF_ROWS; i++){
      LineChartSeries series1 = data.addSeries(sorters, DataSources.fromNumericCellRange(sheet, new CellRangeAddress(i, i, 1, NUMBER_OF_COLS - 1)));
      series1.setTitle(String.valueOf(sheet.getRow(i).getCell(0).getNumericCellValue()));
    }
    chart.plot(data, bottomAxis, leftAxis);
  }

  /**
   * Prints results of sort time measurements for each filler method type in individual .txt file.
   * Name of file {@code arrayType + ".txt"}
   * @param arrayType String name of filler method, set as value of FillerMethod annotation
   * @see com.bogdanaiurchienko.fillers.FillerMethod
   * @param allArrayTypesSortTime all results of analyzing one single array type,
   *                              returned by <b>analyzeAllSorters</b>
   * @param arrayLength lengths of sorted arrays
   * @see com.bogdanaiurchienko.analyzer.Analyzer#analyzeAllSorters(int[], HashMap)
   */
  @SuppressWarnings("unused")
  public void print(String arrayType, HashMap<String, HashMap<Integer, Long>> allArrayTypesSortTime, int[] arrayLength) {
    try(Formatter formatter = new Formatter(new FileOutputStream(arrayType+".txt"))) {
      formatter.format("%-35s", "Sorter");
      for (int anArrayLength : arrayLength) {
        formatter.format("%10s", anArrayLength);
      }
      formatter.format("    With arrayType = %s \n", arrayType);
      Iterator<java.util.Map.Entry<String, HashMap<Integer, Long>>> typesIterator = allArrayTypesSortTime.entrySet().iterator();
      while (typesIterator.hasNext()) {
        java.util.Map.Entry<String, HashMap<Integer, Long>> pair = typesIterator.next();
        formatter.format("%-35s",pair.getKey());
        HashMap<Integer, Long> arraySizes = pair.getValue();
        for (int anArrayLength : arrayLength) {
          formatter.format("%10s", arraySizes.get(anArrayLength));
        }
        formatter.format("\n");
        typesIterator.remove();
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }


  @SuppressWarnings("SameParameterValue")
  private String getName(String name){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH.mm.ss");
    LocalDateTime now = LocalDateTime.now();
//    System.out.println(dtf.format(now));
    return name + "." + dtf.format(now) + ".xlsx";
  }
}

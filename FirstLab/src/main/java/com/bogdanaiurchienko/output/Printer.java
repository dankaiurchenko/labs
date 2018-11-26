package com.bogdanaiurchienko.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;

public class Printer {

  public static void print(String arrayType, int[] arrayLength, HashMap<String, HashMap<Integer, Long>> allArrayTypesSortTime) {
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


}

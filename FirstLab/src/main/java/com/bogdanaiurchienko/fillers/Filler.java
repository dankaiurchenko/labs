package com.bogdanaiurchienko.fillers;

import java.util.Random;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Filler {
  private static Random randomNum = new Random();

  @FillerMethod
  static public int[] getRandomArray(int length, int max){
    int[] array = new int[length];
    for (int i = 0; i < length; i++){
      array[i] = randomNum.nextInt(max);
    }
    return array;
  }


  @FillerMethod
  static public int[] getSortedArray(int length, int max) {
    int[] array = new int[length];
    int step = max / length;
    for (int i = 0; i < length; i++) {
      array[i] = step * i;
    }
    return array;
  }


  @FillerMethod
  static public int[] getSortedArrayWithRandomEnd(int lenght, int max){
    int[] array = new int[lenght];
    int[] sortedPart = Filler.getSortedArray(lenght-1, max);
    System.arraycopy(sortedPart, 0, array, 0, lenght - 1);
    array[lenght - 1] =  max / 2;
    return array;
  }


  @FillerMethod
  static public int[] getReverseSortedArray(int length, int max){
    int[] array = new int[length];
    int step = max / length;
    for (int i = 0; i < length; i++) {
      array[i] = step * (length - i);
    }
    return array;
  }
}

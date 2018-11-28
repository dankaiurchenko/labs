package com.bogdanaiurchienko.fillers;

import java.util.Random;
/**
 * @author Bogdana Iurchienko
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Filler {
  private static Random randomNum = new Random();

  /**
   * Generates array of set size with every element generated randomly between 0 and max.
   * @param length length of array to generate
   * @param max maximum value of an element
   * @return generated array
   */
  @FillerMethod("Random array")
  static public int[] getRandomArray(int length, int max) throws FillerException {
    if(validate(length, max)){
      throw new FillerException("Either array length or max value is invalid");
    }
    int[] array = new int[length];
    for (int i = 0; i < length; i++){
      array[i] = randomNum.nextInt(max);
    }
    return array;
  }


  /**
   * Generates array of set size, where first element is 0 and the last goes to max.
   * @param length length of array to generate
   * @param max maximum value of an element
   * @throws FillerException in case of invalid argument
   * @return generated array
   */
  @FillerMethod("Sorted array")
  static public int[] getSortedArray(int length, int max) throws FillerException {
    if(validate(length, max)){
      throw new FillerException("Either array length or max value is invalid");
    }
    int[] array = new int[length];
    int step = max / length;
    for (int i = 0; i < length; i++) {
      array[i] = step * i;
    }
    return array;
  }


  /**
   * Generates array of set size, where first element is 0 and the second last goes max.<br>
   *   The last element is the average between 0 and max.
   * @param length length of array to generate
   * @param max maximum value of an element
   * @throws FillerException in case of invalid argument
   * @return generated array
   */
  @FillerMethod("Sorted array with random end")
  static public int[] getSortedArrayWithRandomEnd(int length, int max) throws FillerException {
    if(validate(length, max)){
      throw new FillerException("Either array length or max value is invalid");
    }
    int[] array = new int[length];
    int[] sortedPart = Filler.getSortedArray(length-1, max);
    System.arraycopy(sortedPart, 0, array, 0, length - 1);
    array[length - 1] =  max / 2;
    return array;
  }


  /**
   * Generates array of set size, where first element is the closest to  max and the last goes to 0.
   * @param length length of array to generate
   * @param max maximum value of an element
   * @throws FillerException in case of invalid argument
   * @return generated array
   */
  @FillerMethod("Reverse sorted array")
  static public int[] getReverseSortedArray(int length, int max) throws FillerException {
    if(validate(length, max)){
      throw new FillerException("Either array length or max value is invalid");
    }
    int[] array = new int[length];
    int step = max / length;
    for (int i = 0; i < length; i++) {
      array[i] = step * (length - i);
    }
    return array;
  }

  private static boolean validate(int length, int max){
    return (length <= 1 || max <= 1);
  }
}

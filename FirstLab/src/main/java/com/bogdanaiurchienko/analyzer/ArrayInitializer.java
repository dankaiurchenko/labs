package com.bogdanaiurchienko.analyzer;


import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class that invokes filler methods found with <b>ClassScanner</b>
 * in {@link com.bogdanaiurchienko.fillers.Filler}
 * and converts the result into int[][]
 * @see ClassScanner#getFillerMethodsWithAnnotation()
 */
class ArrayInitializer{

  /**
   * Calls <b>method</b> for every value in {@link Analyzer#arrayLength} and adds to return 2-dimensional array.
   *
   * @param method filler method from {@link com.bogdanaiurchienko.fillers.Filler}
   * @return 2-dimensional array of int with arrays of each size
   */
  int[][] initArrays(Method method, int[] arrayLength) throws AnalyzerException {
    int[][] arrays = new int[arrayLength.length][];
    for(int i = 0; i < arrayLength.length; i++){
      try {
        arrays[i] = toPrimitive(toInteger(unpack(method.invoke(null, arrayLength[i], arrayLength[i] * 2))));
      } catch (IllegalAccessException | InvocationTargetException e) {
        throw new AnalyzerException(e);
      }
    }
    return arrays;
  }

  /**
   * Converts Integer[] into int[]
   * @see ArrayInitializer#toInteger(Object[])
   * @param IntegerArray array of Integer
   * @return {@link @param IntegerArray} converted into int[]
   */
  private int[] toPrimitive(Integer[] IntegerArray) {

    int[] result = new int[IntegerArray.length];
    for (int i = 0; i < IntegerArray.length; i++) {
      result[i] = IntegerArray[i];
    }
    return result;
  }

  /**
   * Converts array of objects into Integer array
   * @see ArrayInitializer#unpack(Object)
   * @see ArrayInitializer#toPrimitive(Integer[])
   * @param array array of Objects to be converted
   * @return resulting Integer array
   */
  private Integer[] toInteger(Object[] array) {
    Integer[] integerArray = new Integer[array.length];
    int i = 0;
    for (Object a : array) {
      integerArray[i++] = (Integer) a;
    }
    return integerArray;
  }

  /**
   * Unpacks array of numbers represented as Object returned by reflective filler method invocation
   * @see ArrayInitializer#initArrays(Method, int[])
   * @see ArrayInitializer#toInteger(Object[])
   * @param array array of numbers represented as an instance of Object
   * @return array of Objects
   */
  private Object[] unpack(Object array) {
    Object[] array2 = new Object[Array.getLength(array)];
    for (int i = 0; i < array2.length; i++)
      array2[i] = Array.get(array, i);
    return toInteger(array2);
  }

}

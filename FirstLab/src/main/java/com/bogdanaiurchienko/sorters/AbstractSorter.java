package com.bogdanaiurchienko.sorters;

/**
 * Parent class to all classes that can be analyzed by utility.
 * @author Bogdana Iurchienko
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractSorter {

  public AbstractSorter(){

  }


  /**
   * Abstract method that is being called by Analyzer.
   * @see com.bogdanaiurchienko.analyzer.Analyzer#analyzeSort(AbstractSorter, int[])
   * @param arrayToSort array of int to be sorted
   * @return sorted array
   */
  public abstract int[] sort(int[] arrayToSort) throws SorterException;

  /**
   * Swaps two elements in <b>array</b> with indexes <b>firstElement</b> and <b>secondElement</b>
   * @param array array where two elements are to bew swapped
   * @param firstElement first element
   * @param secondElement second element
   */
  protected void swap(int[] array, int firstElement, int secondElement){
    int temp = array[firstElement];
    array[firstElement] = array[secondElement];
    array[secondElement] = temp;
  }

}

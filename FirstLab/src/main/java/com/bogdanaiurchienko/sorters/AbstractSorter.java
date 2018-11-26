package com.bogdanaiurchienko.sorters;


@SuppressWarnings("WeakerAccess")
abstract public class AbstractSorter {

  public AbstractSorter(){

  }

  public int[] sort(int[] arrayToSort){
    if (arrayToSort.length == 0){
      return new int[]{};
    }

    else
      return sortArray(arrayToSort);
  }

  abstract int[] sortArray(int[] arrayToSort);

  void swap(int[] array, int firstElement, int secondElement){
    int temp = array[firstElement];
    array[firstElement] = array[secondElement];
    array[secondElement] = temp;
  }

}

package com.bogdanaiurchienko.sorters;

import java.util.Arrays;

/**
 * Parent class to all merge sorters with method of half partition that can be analyzed by utility.
 * @author Bogdana Iurchienko
 */
public abstract class AbstractMergeSorter extends AbstractSorter implements Runnable{
  protected int[] subArrayToSort;
  protected Thread thread;


  protected AbstractMergeSorter() {
  }

  public AbstractMergeSorter(int[] subArrayToSort) {
    this.subArrayToSort = subArrayToSort;
  }

  public int[] sort(int[] arrayToSort) throws SorterException {
    return this.mergeSort(arrayToSort);
  }

  /**
   * Divides <b>arrayToSort</b> into n parts, sorts each in another thread. <br>
   *   Then merges parts into one array.
   * @param arrayToSort array to sort
   */
  private int[] mergeSort(int[] arrayToSort) throws SorterException {
    int [][] arrayChunks = splitArray(arrayToSort, Runtime.getRuntime().availableProcessors());
    Thread[] threads = new Thread[arrayChunks.length];
      for(int i = 0; i < arrayChunks.length; i++){
        threads[i] = this.getNewThread(arrayChunks[i]);
      }
      for(int i = 0; i < arrayChunks.length; i++){
        try {
          threads[i].join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      int[] sortedArray = new int[]{};
      for (int[] arrayChunk : arrayChunks) {
        sortedArray = mergeTwoArrays(sortedArray, arrayChunk);
      }
      return sortedArray;
  }

  private int[] mergeTwoArrays(int[] firstOne, int[] secondOne){
    int newLength = firstOne.length + secondOne.length;
    int[] mergedArray = new int[newLength];
    int i = 0, j = 0;
    for (int k = 0; k < newLength; k++) {
      if (i > firstOne.length - 1) {
        mergedArray[k] = secondOne[j];
        j++;
      } else if (j > secondOne.length - 1) {
        mergedArray[k] = firstOne[i];
        i++;
      } else if (secondOne[j] < firstOne[i]) {
        mergedArray[k] = secondOne[j];
        j++;
      } else {
        mergedArray[k] = firstOne[i];
        i++;
      }
    }
    return mergedArray;
  }

  private int[][] splitArray(int[] arrayToSplit, int chunks) throws SorterException{
    if(arrayToSplit.length < chunks){
      throw new SorterException();
    }
    int chunkSize = (int)(arrayToSplit.length / chunks + 0.5);
    int[][] arrays = new int[chunks][];
    for(int i = 0; i < chunks; i++){
      arrays[i] = Arrays.copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
    }
    return arrays;
  }

  protected abstract Thread getNewThread(int[] subArrayToSort);

  public Thread getThread() {
    return thread;
  }
}

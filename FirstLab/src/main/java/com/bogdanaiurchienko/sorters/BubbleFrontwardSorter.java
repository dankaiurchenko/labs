package com.bogdanaiurchienko.sorters;
/**
 * Sorter class that sorts array with bubble method.
 * Each iteration begins from the first element and goes to the last one.
 * @author Bogdana Iurchienko
 */
@SorterAnnotation("Bubble frontward sort")
public class BubbleFrontwardSorter extends AbstractBubbleSorter {

  @Override
  protected int getFirstOuterIndex() {
    return 0;
  }

  @Override
  protected int getFirstInnerIndex(int i) {
    return 0;
  }

  @Override
  protected boolean isLastOuterIndex(int i) {
    return i < arrayToSort.length -1;
  }

  @Override
  protected boolean isLastInnerIndex(int i, int j) {
    return j < arrayToSort.length - i - 1;
  }

  @Override
  protected boolean elementsNotInOrder(int j) {
    return arrayToSort[j] > arrayToSort[j + 1];
  }

  @Override
  protected void swapElements(int j) {
    swap(arrayToSort, j,j+1);
  }

  @Override
  protected int getNextIndex(int i) {
    return i+1;
  }
}

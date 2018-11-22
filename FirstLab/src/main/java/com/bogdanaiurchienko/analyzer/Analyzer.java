package com.bogdanaiurchienko.analyzer;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.output.Printer;
import com.bogdanaiurchienko.sorters.Sorter;
import com.sun.javafx.collections.ObservableSetWrapper;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Analyzer {

  private int[] arrayLength;
  private LinkedList<Method> fillerMethods;

  public Analyzer(int[] arrayLength, Class<? extends Annotation> fillerType) {
    this.arrayLength = arrayLength;
    Class fillerClass = Filler.class;
    fillerMethods = getFillerMethodsWithAnnotation(fillerClass, fillerType);
  }

  public void analyzeAllSorters(String pack){
    ObservableSetWrapper<Class<? extends Sorter>> sorterClasses = getAllSorters(pack);
    for(Class sorterClass: sorterClasses){
      try {
        Sorter sorter = (Sorter) sorterClass.newInstance();
        Printer.print(sorterClass.getName(), arrayLength, analyzeAllArrayTypes(sorter));
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  private HashMap<Integer, Long> analyzeAllArrayLength(Sorter sorter, int[][] allArrays) {
    HashMap<Integer, Long> allArrayLengthSortTimeMS = new HashMap<>();
    for (int j = 0; j < allArrays.length; j++) {
      allArrayLengthSortTimeMS.put(arrayLength[j], analyzeSort(sorter, Arrays.copyOf(allArrays[j], arrayLength[j])));
    }
    return allArrayLengthSortTimeMS;
  }

  private HashMap<String, HashMap<Integer, Long>> analyzeAllArrayTypes(Sorter sorter) {
    HashMap<String, HashMap<Integer, Long>> allArrayTypesSortTimeMS = new HashMap<>();
    for(Method method: fillerMethods){
      int[][] arrays = initArrays(method);
      allArrayTypesSortTimeMS.put(method.getName(), analyzeAllArrayLength(sorter, arrays));
    }
    return allArrayTypesSortTimeMS;
  }

  private int[][] initArrays(Method method) {
    int[][] arrays = new int[arrayLength.length][];
    for(int i = 0; i < arrayLength.length; i++){
      try {
        arrays[i] = toPrimitive(toInteger(unpack(method.invoke(null, arrayLength[i], arrayLength[i] * 2))));
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return arrays;
  }

  private long analyzeSort(Sorter sorter, int[] arrayToSort) {
    long before = System.currentTimeMillis();
    sorter.sort(arrayToSort);
    long after = System.currentTimeMillis();
    return after - before;
  }

  private static int[] toPrimitive(Integer[] IntegerArray) {

    int[] result = new int[IntegerArray.length];
    for (int i = 0; i < IntegerArray.length; i++) {
      result[i] = IntegerArray[i];
    }
    return result;
  }

  private static Integer[] toInteger(Object[] array) {
    Integer[] integerArray = new Integer[array.length];
    int i = 0;
    for (Object a : array) {
      integerArray[i++] = (Integer) a;
    }
    return integerArray;
  }

  private static Object[] unpack(Object array) {
    Object[] array2 = new Object[Array.getLength(array)];
    for (int i = 0; i < array2.length; i++)
      array2[i] = Array.get(array, i);
    return toInteger(array2);
  }

  @SuppressWarnings("Convert2Lambda")
  private static LinkedList<Method> getFillerMethodsWithAnnotation(Class fillerClass, Class<? extends Annotation> fillerType) {
    LinkedList<Method> methodsWithAnnotation = new LinkedList<>();
    for (Method method : fillerClass.getDeclaredMethods())
      if (method.isAnnotationPresent(fillerType)) {
        methodsWithAnnotation.add(method);
      }
    methodsWithAnnotation.sort(new Comparator<Method>(){
      @Override
      public int compare(Method o1, Method o2){
        return o1.getName().compareTo(o2.getName());
      }
    });
    return methodsWithAnnotation;
  }

  private static ObservableSetWrapper<Class<? extends Sorter>> getAllSorters(String pack){
    Reflections reflections = new Reflections(pack);
    Set<Class<? extends Sorter>> sorters = new HashSet<>();
    Set<Class<? extends Sorter>> classes = reflections.getSubTypesOf(Sorter.class);
    for (Class<? extends Sorter> sorterClass: classes){
      if(!Modifier.isAbstract(sorterClass.getModifiers())){
        sorters.add(sorterClass);
      }
    }
    return new ObservableSetWrapper<>(sorters);
  }
}

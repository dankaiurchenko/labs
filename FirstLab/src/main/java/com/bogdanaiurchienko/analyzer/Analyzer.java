package com.bogdanaiurchienko.analyzer;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.fillers.FillerMethod;
import com.bogdanaiurchienko.output.Printer;
import com.bogdanaiurchienko.sorters.AbstractSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;
import org.reflections.Reflections;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

public class Analyzer {

  private int[] arrayLength;
  private LinkedList<Method> fillerMethods;

  public Analyzer(int[] arrayLength, Class<? extends Annotation> fillerType) {
    this.arrayLength = arrayLength;
    Class<Filler> fillerClass = Filler.class;
    fillerMethods = getFillerMethodsWithAnnotation(fillerClass, fillerType);
  }

  public void analyzeAllArrayTypes(String pack) {
    //LinkedList<Class<? extends AbstractSorter>> sorterClasses = getAllSortersUsingReflections(pack);
    LinkedList<Class<? extends AbstractSorter>> sorterClasses = getSorters(Thread.currentThread().getContextClassLoader(),pack);
    for(Method method: fillerMethods){
      int[][] arrays = initArrays(method);
      String methodName =  method.getAnnotation(FillerMethod.class).value();
      Printer.print(methodName, arrayLength, analyzeAllSorters(arrays, sorterClasses));
    }
  }

  private HashMap<String, HashMap<Integer, Long>> analyzeAllSorters(int[][] allArrays, LinkedList<Class<? extends AbstractSorter>> sorterClasses){
    LinkedHashMap<String, HashMap<Integer, Long>> allSortersSortTimeMS = new LinkedHashMap<>();
    for(Class<? extends AbstractSorter> sorterClass: sorterClasses){
      try {
        AbstractSorter sorter = sorterClass.newInstance();
        String sorterName = (sorterClass.getAnnotation(SorterAnnotation.class) != null) ?
                sorterClass.getAnnotation(SorterAnnotation.class).value() : sorterClass.getSimpleName();
        allSortersSortTimeMS.put(sorterName, analyzeAllArrayLength(sorter, allArrays));
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return allSortersSortTimeMS;
  }

  private HashMap<Integer, Long> analyzeAllArrayLength(AbstractSorter sorter, int[][] allArrays) {
    HashMap<Integer, Long> allArrayLengthSortTimeMS = new HashMap<>();
    for (int j = 0; j < allArrays.length; j++) {
      allArrayLengthSortTimeMS.put(arrayLength[j], analyzeSort(sorter, Arrays.copyOf(allArrays[j], arrayLength[j])));
    }
    return allArrayLengthSortTimeMS;
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

  private long analyzeSort(AbstractSorter sorter, int[] arrayToSort) {
    long before = System.currentTimeMillis();
    sorter.sort(arrayToSort);
    long after = System.currentTimeMillis();
    return after - before;
  }

  private int[] toPrimitive(Integer[] IntegerArray) {

    int[] result = new int[IntegerArray.length];
    for (int i = 0; i < IntegerArray.length; i++) {
      result[i] = IntegerArray[i];
    }
    return result;
  }

  private Integer[] toInteger(Object[] array) {
    Integer[] integerArray = new Integer[array.length];
    int i = 0;
    for (Object a : array) {
      integerArray[i++] = (Integer) a;
    }
    return integerArray;
  }

  private Object[] unpack(Object array) {
    Object[] array2 = new Object[Array.getLength(array)];
    for (int i = 0; i < array2.length; i++)
      array2[i] = Array.get(array, i);
    return toInteger(array2);
  }

  private LinkedList<Method> getFillerMethodsWithAnnotation(Class<Filler> fillerClass, Class<? extends Annotation> fillerType) {
    LinkedList<Method> methodsWithAnnotation = new LinkedList<>();
    for (Method method : fillerClass.getDeclaredMethods())
      if (method.isAnnotationPresent(fillerType)) {
        methodsWithAnnotation.add(method);
      }
    return methodsWithAnnotation;
  }

  @SuppressWarnings({"Convert2Lambda", "unused"})
  private LinkedList<Class<? extends AbstractSorter>> getAllSortersUsingReflections(String pack){
    Reflections reflections = new Reflections(pack);
    Set<Class<? extends AbstractSorter>> classes = reflections.getSubTypesOf(AbstractSorter.class);
    LinkedList<Class<? extends AbstractSorter>> sorters = new LinkedList<>();
    for (Class<? extends AbstractSorter> sorterClass: classes){
      if(!Modifier.isAbstract(sorterClass.getModifiers())){
        sorters.add(sorterClass);
      }
    }
    sorters.sort(new Comparator<Class<? extends AbstractSorter>>() {
      @Override
      public int compare(Class<? extends AbstractSorter> o1, Class<? extends AbstractSorter> o2) {
        return o1.getSimpleName().compareTo(o2.getSimpleName());
      }
    });
    return sorters;
  }

  private LinkedList<Class<? extends AbstractSorter>> getSorters(ClassLoader loader, String pack){
    List<Class> classes = new ArrayList<>();
    getClassesInPackage(loader, pack, classes);
    return filterSorters(classes);
  }

  private void getClassesInPackage(ClassLoader loader, String pack, List<Class> classes){
    URL uPackage = loader.getResource(pack.replace('.', '/'));
    if (uPackage != null) {
      File[] files = new File(uPackage.getPath()).listFiles();
      if(files != null){
        for(File f: files){
          if(f.isDirectory()){
            getClassesInPackage(loader, pack+"."+f.getName(), classes);
          }
          if(f.getName().endsWith(".class")) {
            try {
              classes.add(Class.forName(pack+"."+f.getName().substring(0,f.getName().lastIndexOf('.'))));
            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

  @SuppressWarnings({"Convert2Lambda", "unchecked"})
  private LinkedList<Class<? extends AbstractSorter>> filterSorters(List<Class> classes){
    LinkedList<Class<? extends AbstractSorter>> sorters = new LinkedList<>();
    for (Class<?> clazz: classes){
      if(isSorterClass(clazz) && (!Modifier.isAbstract(clazz.getModifiers()))){
        sorters.add((Class<? extends AbstractSorter>) clazz);
      }
    }
    sorters.sort(new Comparator<Class<? extends AbstractSorter>>() {
      @Override
      public int compare(Class<? extends AbstractSorter> o1, Class<? extends AbstractSorter> o2) {
        return o1.getSimpleName().compareTo(o2.getSimpleName());
      }
    });
    return sorters;
  }

  private boolean isSorterClass(Class clazz){
    if(clazz.isAnnotation()) return false;
    if(clazz.getSuperclass() == Object.class)
      return false;
    else if(clazz.getSuperclass() == AbstractSorter.class)
      return true;
    else return isSorterClass(clazz.getSuperclass());
  }
}

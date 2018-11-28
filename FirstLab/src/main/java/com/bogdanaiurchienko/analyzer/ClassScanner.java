package com.bogdanaiurchienko.analyzer;

import com.bogdanaiurchienko.fillers.Filler;
import com.bogdanaiurchienko.fillers.FillerMethod;
import com.bogdanaiurchienko.sorters.AbstractSorter;
import com.bogdanaiurchienko.sorters.SorterAnnotation;
import org.reflections.Reflections;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;
/**
 * The main class that works with reflection
 * @author Bogdana Iurchienko
 */
@SuppressWarnings("WeakerAccess")
public class ClassScanner {

  /**
   * Scans the {@link com.bogdanaiurchienko.fillers.Filler} in search of public methods with particular annotation
   * Puts all found methods in {@code LinkedHashMap<String, Method> methods} where
   * the value of annotation (which is to be name of the filler type) is the key.
   * @see FillerMethod
   * @return entrySet of methods and their names
   */
  public Set<Map.Entry<String, Method>> getFillerMethodsWithAnnotation() {
    LinkedHashMap<String, Method> methods = new LinkedHashMap<>();
    for (Method method : Filler.class.getDeclaredMethods())
      if (method.isAnnotationPresent(FillerMethod.class)) {
        String methodName =  method.getAnnotation(FillerMethod.class).value();
        methods.put(methodName, method);
      }
    return methods.entrySet();
  }

  /**
   * Initialises instances of each sorter class found with {@link ClassScanner#getAllSortersUsingReflections(String)}
   * in pack. Puts all sorter objects into LinkedHashMap, where the key is the name of sort method.
   * Name of sort method is the value of {@link SorterAnnotation},
   * or (if an annotation has not been set) the simple name of the class.
   * @param pack package, which contains sorters to be analyzed
   * @return Map of sorter objects and their names
   */
  public LinkedHashMap<String, AbstractSorter> initSorters(String pack) throws AnalyzerException {
//    LinkedList<Class<? extends AbstractSorter>> sorterClasses = getSorters(pack);
    LinkedList<Class<? extends AbstractSorter>> sorterClasses = getAllSortersUsingReflections(pack);
    LinkedHashMap<String, AbstractSorter> sorters = new LinkedHashMap<>();
    for(Class<? extends AbstractSorter> sorterClass: sorterClasses){
      try {
        String sorterName = (sorterClass.getAnnotation(SorterAnnotation.class) != null) ?
                sorterClass.getAnnotation(SorterAnnotation.class).value() : sorterClass.getSimpleName();
        sorters.put(sorterName, sorterClass.newInstance());
      } catch (InstantiationException | IllegalAccessException e) {
        throw new AnalyzerException(e);
      }
    }
    return sorters;
  }

  /**
   * Finds all sorter classes in given package that extend {@link com.bogdanaiurchienko.sorters.AbstractSorter}
   * and that are not abstract.
   * {@link org.reflections.Reflections}
   * @param pack package where child classes of AbstractSorter are to be found
   * @return LinkedList of Class objects of all found classes
   */
  @SuppressWarnings({"Convert2Lambda", "unused"})
  public LinkedList<Class<? extends AbstractSorter>> getAllSortersUsingReflections(String pack){
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

  /**
   * Finds all sorter classes in given package that extend {@link com.bogdanaiurchienko.sorters.AbstractSorter}
   * and that are not abstract.<br>
   * Does't work from jar file.
   * <b>Use ClassScanner#getAllSortersUsingReflections(String)</b>
   * @see ClassScanner#getClassesInPackage(ClassLoader, String, List)
   * @see ClassScanner#getAllSortersUsingReflections(String)
   * @param pack package where child classes of AbstractSorter are to be found
   * @return List of Class objects of all found classes
   */
  @Deprecated
  @SuppressWarnings("unused")
  public LinkedList<Class<? extends AbstractSorter>> getSorters(String pack) throws ClassNotFoundException {
    List<Class> classes = new ArrayList<>();
    getClassesInPackage(Thread.currentThread().getContextClassLoader(), pack, classes);
    return filterSorters(classes);
  }

  /**
   * Recursively scans all directories and finds all the classes in pack.
   * <b>Use ClassScanner#getAllSortersUsingReflections(String)</b>
   * @see ClassScanner#filterSorters(List)
   * @see ClassScanner#getAllSortersUsingReflections(String)
   * @param loader classLoader of current Thread
   * @param pack package to scan
   * @param classes all classes in package and subpackages
   */
  @Deprecated
  private void getClassesInPackage(ClassLoader loader, String pack, List<Class> classes) throws ClassNotFoundException {
    URL uPackage = loader.getResource(pack.replace('.', '/'));
    if (uPackage != null) {
      File[] files = new File(uPackage.getPath()).listFiles();
      if(files != null){
        for(File f: files){
          if(f.isDirectory()){
            getClassesInPackage(loader, pack+"."+f.getName(), classes);
          }
          if(f.getName().endsWith(".class")) {
            classes.add(Class.forName(pack+"."+f.getName().substring(0,f.getName().lastIndexOf('.'))));
          }
        }
      }
    }
  }

  /**
   * Checks for every class in <b>classes</b> if it extends <b>AbstractSorter</b> and whether it is abstract or not.
   * <b>Use ClassScanner#getAllSortersUsingReflections(String)</b>
   * @see ClassScanner#getAllSortersUsingReflections(String)
   * @see ClassScanner#getClassesInPackage(ClassLoader, String, List)
   * @see ClassScanner#extendsSorter(Class)
   * @param classes List of all found classes in package
   * @return LinkedList of non abstract child classes of <b>AbstractSorter</b>
   */
  @SuppressWarnings({"Convert2Lambda", "unchecked"})
  @Deprecated
  private LinkedList<Class<? extends AbstractSorter>> filterSorters(List<Class> classes){
    LinkedList<Class<? extends AbstractSorter>> sorters = new LinkedList<>();
    for (Class<?> clazz: classes){
      if(extendsSorter(clazz) && (!Modifier.isAbstract(clazz.getModifiers()))){
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

  /**
   * Recursively checks if <b>AbstractSorter</b> is among super classes of argument class.<br>
   *   Returns 'false' if the class is an Annotation.<br>
   * <b>Use ClassScanner#getAllSortersUsingReflections(String)</b>
   * @see ClassScanner#getAllSortersUsingReflections(String)
   * @param clazz class to inspect
   * @return boolean value
   */
  @Deprecated
  private boolean extendsSorter(Class clazz){
    if(clazz.isAnnotation()) return false;
    if(clazz.getSuperclass() == Object.class)
      return false;
    else if(clazz.getSuperclass() == AbstractSorter.class)
      return true;
    else return extendsSorter(clazz.getSuperclass());
  }
}

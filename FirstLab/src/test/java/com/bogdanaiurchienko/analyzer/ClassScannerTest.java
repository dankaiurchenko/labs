package com.bogdanaiurchienko.analyzer;

import com.bogdanaiurchienko.fillers.Filler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class ClassScannerTest {
  private ClassScanner classScanner;

  @Before
  public void setUp() {
    classScanner = new ClassScanner();
  }

  @After
  public void tearDown() {
    classScanner = null;
  }

  @Test(timeout = 100)
  public void getFillerMethodsWithAnnotation() {
    for(Map.Entry<String, Method> method: classScanner.getFillerMethodsWithAnnotation()){
      Assert.assertEquals(method.getValue().getDeclaringClass().getName(), Filler.class.getName());
    }
  }

  @Test(timeout = 1000)
  public void initSorters() throws AnalyzerException {
    Assert.assertTrue(classScanner.initSorters("some.ransom.pack.that.doesnt.exist").isEmpty());
  }

  @Test
  public void getAllSortersUsingReflections() throws AnalyzerException {
    Assert.assertTrue(classScanner.initSorters("some.ransom.pack.that.doesnt.exist").isEmpty());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void getSorters() throws ClassNotFoundException {
    classScanner.getSorters("some.ransom.pack.that.doesnt.exist");
  }
}
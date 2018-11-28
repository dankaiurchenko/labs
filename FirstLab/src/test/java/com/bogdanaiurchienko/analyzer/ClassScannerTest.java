package com.bogdanaiurchienko.analyzer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    System.out.println(classScanner.getFillerMethodsWithAnnotation());
  }

  @Test(timeout = 1000)
  public void initSorters() {
    classScanner.initSorters("some.ransom.pack.that.doesnt.exist");
  }

  @Test
  public void getAllSortersUsingReflections() {
    classScanner.initSorters("some.ransom.pack.that.doesnt.exist");
  }

  @SuppressWarnings("deprecation")
  @Test
  public void getSorters() {
    classScanner.getSorters("some.ransom.pack.that.doesnt.exist");
  }
}
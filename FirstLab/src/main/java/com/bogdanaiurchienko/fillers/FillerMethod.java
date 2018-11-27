package com.bogdanaiurchienko.fillers;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Indicates methods that can be used as a fillers for sorter analysis.
 * Is used in the Runtime to find all the methods.
 * @author Bogdana Iurchienko
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FillerMethod {
  /**
   * Contains the name of method set by user.
   * @return the name of the filler method
   */
  String value();
}

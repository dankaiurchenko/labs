package com.bogdanaiurchienko.sorters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Is used in the Runtime to find out simple name of the sorter.
 * @author Bogdana Iurchienko
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface SorterAnnotation {
  /**
   * Contains the name of sorter method set by user.
   * @return the name of the sorter method
   */
  String value();
}

#!/bin/bash


while [[ $# -gt 0 ]]; do
  exceptionPath="src/main/java/com/doklad/api/customers/utility/exception/$1.java"
  exceptionName=$(basename "$1")
  shift
  package=$(dirname "$(realpath -m --relative-to=src/main/java/com/doklad/api/customers/utility/exception "$exceptionPath")" | tr / .  | sed 's/\.$//')
  if [[ ${package:0:1} != '.' ]]; then
    package=.$package
  fi
  mkdir -p "$(dirname $exceptionPath)"
  echo "\
  package com.doklad.api.customers.utility.exception$package;\
  \
  public class $exceptionName extends RuntimeException {\
      public $exceptionName(String message) {\
          super(message);\
      }\
  }\
  " > "$exceptionPath"
done
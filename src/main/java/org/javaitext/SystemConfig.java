package org.javaitext;

import java.io.File;

public class SystemConfig {

  public static String getPath() {
    String currentDir = new File(".").getAbsolutePath();
    currentDir = currentDir.replace(".", "") + "files/";
    return currentDir;
  }


}
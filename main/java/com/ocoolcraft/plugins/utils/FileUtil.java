package com.ocoolcraft.plugins.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;

public class FileUtil {

    public  static void writeStringToFile(String data,String filePath) {
        try {
            FileUtils.writeStringToFile(new File(filePath),data,Charset.defaultCharset());
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String readStringFromFile(String configFile) {
        try {
            File file = new File(configFile);
            return FileUtils.readFileToString(file,Charset.defaultCharset());
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

package com.ocoolcraft.plugins.utils;


import java.io.*;

public class FileUtils {

    public  static void writeStringToFile(String data,String filePath) {
        BufferedWriter writer = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            writer = new BufferedWriter( new FileWriter( filePath));
            writer.write( data);
        }catch ( IOException e)  {
            throw new RuntimeException(e);
        }
        finally {
            try  {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e) {

            }
        }
    }

    public static String readStringFromFile(String configFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(configFile));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

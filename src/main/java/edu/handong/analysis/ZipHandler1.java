package edu.handong.analysis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipHandler1 {

  private static final int BUFFER_SIZE = 2048;

  private static final String ZIP_EXTENSION = ".zip";
  public static final Integer FOLDER = 1;
  public static final Integer ZIP = 2;
  public static final Integer FILE = 3;


  public static Deque<Map<Integer, Object[]>> unzip(ByteArrayOutputStream zippedOutputFile) {

    try {

      ZipInputStream inputStream = new ZipInputStream(
          new BufferedInputStream(new ByteArrayInputStream(
              zippedOutputFile.toByteArray())));

      ZipEntry entry;

      Deque<Map<Integer, Object[]>> result = new ArrayDeque<Map<Integer, Object[]>>();

      while ((entry = inputStream.getNextEntry()) != null) {

        LinkedHashMap<Integer, Object[]> map = new LinkedHashMap<Integer, Object[]>();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.out.println("\tExtracting entry: " + entry);
        int count;
        byte[] data = new byte[BUFFER_SIZE];

        
        if (!entry.isDirectory()) {
       
          BufferedOutputStream out = new BufferedOutputStream(
        		  outputStream, BUFFER_SIZE);

          while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1) {
            out.write(data, 0, count);
          }

          out.flush();
          out.close();

          //  recursively unzip files
          if (entry.getName().toUpperCase().endsWith(ZIP_EXTENSION.toUpperCase())) {
            map.put(ZIP, new Object[] {entry.getName(), unzip(outputStream)});
            result.add(map);
            //result.addAll();
          } else { 
            map.put(FILE, new Object[] {entry.getName(), outputStream});
            result.add(map);
          }
        } else {
          map.put(FOLDER, new Object[] {entry.getName(), unzip(outputStream)});
          result.add(map);
        }
      }

      inputStream.close();

      return result;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
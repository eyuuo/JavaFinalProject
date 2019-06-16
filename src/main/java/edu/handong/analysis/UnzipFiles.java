package edu.handong.analysis;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Deque;
import java.util.Map;

public class UnzipFiles {

private static final int  BUFFER_SIZE = 2048;

    public void unzipFiles(String input) {
        
    
    	try {
            File file = new File(input);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos);
            byte[] buffer = new byte[BUFFER_SIZE];
            while (bis.read(buffer, 0, BUFFER_SIZE) != -1) {
               bos.write(buffer);
            }
            bos.flush();
            bos.close();
            bis.close();

            //This STACK has the output byte array information 
            Deque<Map<Integer, Object[]>> outputDataStack = ZipHandler1.unzip(baos);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}    
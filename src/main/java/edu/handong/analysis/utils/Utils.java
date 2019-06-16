
package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;

public class Utils {
	

	/**
	 * getLines method reads csv file from the given file path and returns ArrayList<String> object. One element has a string of one line. The second argument is the option to avoid including first line to ArrayList when true.
	 * @param file
	 * @param removeHeader
	 * @return 
	 */
	public static ArrayList<String> getLines(String file,boolean removeHeader){
		
		ArrayList<String> lines = new ArrayList<String>();
		
		File locationFile = new File(file);
		String line = null;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(locationFile));
			while( (line = in.readLine()) != null) {
				lines.add(line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(removeHeader==true) lines.remove(0);
		

		return lines;
	}
	
	/**
	 * writeAFile saves the result information in the ArrayList to a file in the second argument. If a file does not exist in the directory, FileNotFoundException may occur, to prevent, a logic to create directory is required. There is a hint at TIPS below.
	 * @param lines
	 * @param targetFileName
	 */
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
		String content="StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester";
		 for (int count = 0 ; count < lines.size() ; count++) {
			  content=content+"\n"+lines.get(count);
	        }
		try {
			//그냥 파일이름만 적었을때를 위해.
			if(targetFileName.contains("/")==false) targetFileName = "./"+targetFileName;
           
			Path path = Paths.get(targetFileName);
            
			if(Files.exists(path)==false)
				System.out.println("output path does not exist. so I made!");
				
            Files.createDirectories(path.getParent());

            Files.write(path, content.getBytes());
            //System.out.println(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
	}
	
		public static void writeAFile2(ArrayList<String> lines, String targetFileName) {
		
		String content="Year,Semester,CouseCode, CourseName,TotalStudents,StudentsTaken,Rate";
		//System.out.println(content);
		 for (int count = 0 ; count < lines.size() ; count++) {
			  content=content+"\n"+lines.get(count);
	        }
		try {
			//그냥 파일이름만 적었을때를 위해.
			if(targetFileName.contains("/")==false) targetFileName = "./"+targetFileName;
           
			Path path = Paths.get(targetFileName);
            
			if(Files.exists(path)==false)
				System.out.println("output path does not exist. so I made!");
				
            Files.createDirectories(path.getParent());

            Files.write(path, content.getBytes());
            //System.out.println(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
	}

}

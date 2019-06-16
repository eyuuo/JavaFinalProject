
package edu.handong.analysis.utils;

import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.handong.analysis.Runner;
//사용하지 않을 예정.
//TODO: 파일을 cvs apache로 읽는다. 같이 넘겨 준다. path를 확인해서 없으면 에러를 내준다.

public class Utils2 {
	// "data/hw5data2.csv";
	
//		public static void main(String[] args) throws IOException {
//			ArrayList<String> words = new ArrayList<String>();
//			words = getWord("data/hw5data2.csv",true);
//			for(int i=0;i<9;i++)
//				System.out.println(words.get(i));
//		}
	 
	
	public static ArrayList<String> getWord(String file,boolean removeHeader) throws IOException {
    	ArrayList<String> words = new ArrayList<String>();
    	String content="StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester";
	
        try (
            Reader reader = Files.newBufferedReader(Paths.get(file));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
            	
            	words.add(csvRecord.get(0).trim());
            	words.add(csvRecord.get(1).trim());
            	words.add(csvRecord.get(2).trim());
            	words.add(csvRecord.get(3).trim());
            	words.add(csvRecord.get(4).trim());
            	words.add(csvRecord.get(5).trim());
            	words.add(csvRecord.get(6).trim());
            	words.add(csvRecord.get(7).trim());
            	words.add(csvRecord.get(8).trim());
            	

            }
        }
        for(int i=0;i<9;i++) { 
        	words.remove(0);} 
        return words;
    }
	
	
	


}

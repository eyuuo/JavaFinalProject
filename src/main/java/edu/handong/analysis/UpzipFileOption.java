package edu.handong.analysis;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class UpzipFileOption {
	
	String input;
	String output;
	boolean help;
	
	public void unzipOption(String[] args) throws IOException {
		
		//0. 옵션만들기 (완료)
		Options options = createOptions();
		if(parseOptions(options, args)){

				if (help){
					printHelp(options);
					return;
				}
				
				//zip 파일 위치가 없는 위치 일때 경고.
				Path path = Paths.get(input);
				boolean Exist =Files.exists(path);
			       if(Exist==false) {
			    	   System.out.println("The file path does not exist. Please check your CLI argument!");
			    	   System.exit(0);
			            }

	    System.out.println(input);
		//1.파일 압축 풀기 (보류)
		//문제1 : 저장된 파일 위치를 모른다. 문제2 옵션에 파일이름을 넣지 않으면 에러가 생긴다.
		UnzipFiles unzip = new UnzipFiles();
		unzip.unzipFiles(input);
		//2.파일 읽기 
					
		
		//3.파일 통합하기 
		
		//4.파일 내보내기 
  
			}//parseOptions
		
	}
	
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);	
			input= cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

			
		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	private Options createOptions() {
		
		Options options = new Options();
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("path to data.zip or directory path having files like 0001.zip ")
				.hasArg()
				.argName("path to input file")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("results csv or exce file that merged all excel files.")
				.hasArg()
				.argName("path to output file")
				.required()
				.build());
				
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.argName("Help")
				.build());				
					 
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Unzip file";
		String footer =""; // Leave this empty.
		formatter.printHelp("Unzip file : input and output", header, options, footer, true);
	}

}

/*
 * 목표 - 2개의 옵션을 구현한다. 
$ JavaFinalProject -i [path to data.zip or directory path having files like 0001.zip ] -o [results csv or exce file that merged all excel files.]
Acutal example
$ JavaFinalProject -i data.zip -o results.csv
$ JavaFinalProject -i data -o results.csv

 */

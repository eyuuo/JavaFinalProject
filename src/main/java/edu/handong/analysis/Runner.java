package edu.handong.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;



//-fullpath -v -p /Users/eyuuo/Documents/

public class Runner {
	
	String input;
	String output;
	String analysis;
	String endyear;
	String startyear;
	String coursecode;
	boolean help;

	public void runOption(String[] args) {
		
	Options options = createOptions();
	if(parseOptions(options, args)){
			
			
		if(analysis.equals("1")){
		
				if (help){
					printHelp(options);
					return;
				}
				//파일 위치가 없는 위치 일때 경고.
				Path path = Paths.get(input);
				//버리는 값 
	            boolean Exist =Files.exists(path);
	            //System.out.println(Exist);
	            if(Exist==false) {
	            	System.out.println("The file path does not exist. Please check your CLI argument!");
					System.exit(0);
	            }
   
				
				String[] arg = new String[6];
				arg[0]= input;
				arg[1]= output;
				arg[2]= startyear;
				arg[3]= endyear;
				arg[4]= analysis;
				arg[5]= coursecode;
	
				HGUCoursePatternAnalyzer analyzer = new HGUCoursePatternAnalyzer();
				analyzer.run(arg);
			
				
			}
			else if(analysis.equals("2")){
				
				Options options2 = createOptions2();
				if(parseOptions(options2, args)){
					if (help){
						printHelp(options2);
						return;
					}
					
				String[] arg = new String[6];
				arg[0]= input;
				arg[1]= output;
				arg[2]= startyear;
				arg[3]= endyear;
				arg[4]= analysis;
				arg[5]= coursecode;
				
				HGUCoursePatternAnalyzer2 analyzer2 = new HGUCoursePatternAnalyzer2();
				analyzer2.run(arg);
				
				}
				
			}
		}
	
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			
			analysis= cmd.getOptionValue("a");
			input= cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			coursecode= cmd.getOptionValue("c");
			startyear= cmd.getOptionValue("s");
			endyear = cmd.getOptionValue("e");
			help = cmd.hasOption("h");

			
		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, \n2: Count per course name and year")
				.hasArg()
				.argName("Analysis option")
				.required()
				.build());


		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
	
				
		// add options by using OptionBuilder
			options.addOption(Option.builder("c").longOpt("coursecode")
					.desc("Course code for '-a 2' option")
					.argName("course code")
					.build());
			
		// add options by using OptionBuilder
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
				
		// add options by using OptionBuilder
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.argName("Help")
				.build());				
			 
		return options;
	}
	
	// Definition Stage
		private Options createOptions2() {
			Options options = new Options();

			// add options by using OptionBuilder
			options.addOption(Option.builder("a").longOpt("analysis")
					.desc("1: Count courses per semester, \n2: Count per course name and year")
					.hasArg()
					.argName("Analysis option")
					.required()
					.build());


			// add options by using OptionBuilder
			options.addOption(Option.builder("i").longOpt("input")
					.desc("Set an input file path")
					.hasArg()
					.argName("Input path")
					.required()
					.build());
			
			// add options by using OptionBuilder
			options.addOption(Option.builder("o").longOpt("output")
					.desc("Set an output file path")
					.hasArg()
					.argName("Output path")
					.required()
					.build());
			
			// add options by using OptionBuilder
			options.addOption(Option.builder("c").longOpt("coursecode")
					.desc("Course code for '-a 2' option")
					.hasArg()
					.argName("course code")
					.required()
					.build());
					
			// add options by using OptionBuilder
			options.addOption(Option.builder("s").longOpt("startyear")
					.desc("Set the start year for analysis e.g., -s 2002")
					.hasArg()
					.argName("Start year for analysis")
					.required()
					.build());
					
			// add options by using OptionBuilder
			options.addOption(Option.builder("e").longOpt("endyear")
					.desc("Set the end year for analysis e.g., -e 2005")
					.hasArg()
					.argName("End year for analysis")
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
		String header = "HGU Course Analyzer";
		String footer =""; // Leave this empty.
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}

}
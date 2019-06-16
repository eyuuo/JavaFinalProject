package edu.handong.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;


//trim()사용해야함.
public class HGUCoursePatternAnalyzer {
				/*
				arg[0]= input;
				arg[1]= output;
				arg[2]= startyear;
				arg[3]= endyear;
				arg[4]= analysis;
				arg[5]= coursecode;
				*/
	private HashMap<String,Student> students;
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {//수정 하지 말 것.
		/* when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.*/
		
		try {		
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} 
		
		String dataPath = args[0]; // csv file to be analyzed 
		String resultPath = args[1]; // the file path where the results are saved.
	
		
		ArrayList<String> lines = Utils.getLines(dataPath, true);

		students = loadStudentCourseRecords(lines,args[2],args[3]);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		//Map<String, Student> sortedStudents2 = new TreeMap<String,Student>(students2); 
		
		
		//Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
		
		
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * 데이터 csv파일에서 HashMap <String, Student>를 생성한다. 키는 학생 아이디이고 해당 개체는 학생의 인스턴스다.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		
	//<변수 설정> 
		Course course;
		Student student;
		HashMap<String,Student> students = new HashMap<String,Student>();
		// TODO: Implement this method
		
	//<put위한 과정.>
		//반복 : for 반목문으 파일의 모든 학생들을의 아이디를 읽는다. 그리고 그 수만큼 put.
		
		//학생 ID 모으기 hashset이용 ->Sorting 필요
		HashSet<String> key = new HashSet<String>();
		for(int i = 0; i< lines.size();i++)
			key.add(lines.get(i).split(",")[0]);
		
		//학생 ID 모으기2 정렬을 위해 arraylist를 사용한다.
		ArrayList<String> keylist = new ArrayList<String>(key); 
		Collections.sort(keylist);
		
		for(int j=0;j<keylist.size();j++) {
		//addCourse	
			student = new Student(keylist.get(j));//다음 학생!!
			//라인중에서 studentId와 같은 ID를 가지고 있는 코스만 addCourse하고 students에 넣는다.
			for(int i = 0; i< lines.size();i++) {
				if(lines.get(i).split(",")[0].equals(keylist.get(j))){
					course = new Course(lines.get(i));
					student.addCourse(course);		
					}//if1_아이디가 일치하는 line만 Course로.
				}//for-i	
		//hash Map에 학생 정보 넣기 
				students.put(keylist.get(j), student);			
		}//for-j
		//잘 push되었는지 확인 
		//System.out.print(students.get("0002").getCoursesTaken().get(0).getCourseName());

		return students; // do not forget to return a proper variable.
	}

	// HashMap<String,Student> students의 값을 처음부터 start, end로 거른다.
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines,String startYear, String endYear) {
		
		
		//<변수 설정> 
			int start = Integer.valueOf(startYear);
			int end = Integer.valueOf(endYear);
			Course course;
			Student student;
			HashMap<String,Student> students = new HashMap<String,Student>();
			
			
		//<put위한 과정.>
			//반복 : for 반목문으 파일의 모든 학생들을의 아이디를 읽는다. 그리고 그 수만큼 put.
			
			//학생 ID 모으기 hashset이용 ->Sorting 필요
			HashSet<String> key = new HashSet<String>();
			for(int i = 0; i< lines.size();i++)
				key.add(lines.get(i).split(",")[0]);
			
			//학생 ID 모으기 정렬을 위해 arraylist를 사용한다.
			ArrayList<String> keylist = new ArrayList<String>(key); 
			Collections.sort(keylist);
			
			for(int j=0;j<keylist.size();j++) {
			//addCourse	
				student = new Student(keylist.get(j));//다음 학생!!
				//라인중에서 studentId와 같은 ID를 가지고 있는 코스만 addCourse하고 students에 넣는다.
				for(int i = 0; i< lines.size();i++) {
					if(lines.get(i).split(",")[0].equals(keylist.get(j))){
						course = new Course(lines.get(i));
						
						//들은 년도 거르기**!!!!!
						if(course.getYearTaken()>=start&&course.getYearTaken()<= end) {
							student.addCourse(course);
							//System.out.println("들은 년도 확인  : "+course.getYearTaken());
						}
							
						}//if1_아이디가 일치하는 line만 Course로.
					}//for-i	
			//hash Map에 학생 정보 넣기 
					students.put(keylist.get(j), student);			
			}//for-j
			//잘 push되었는지 확인 
			//System.out.print(students.get("0002").getCoursesTaken().get(0).getCourseName());

			return students; // do not forget to return a proper variable.
		}

	
	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester 
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		// TODO: Implement this method
		ArrayList<String> NumberOfCoursesTakenInEachSemester = new ArrayList<String>();
		
		ArrayList<String> studentID = new ArrayList<String>();
		ArrayList<Integer> TotalNumberOfSemestersRegistered = new ArrayList<Integer>();
		
		HashMap<String,Integer> semestersByYearAndSemester = new HashMap<String,Integer>();
		
		//ID얻기 
		for (String studentIDKey : sortedStudents.keySet())
		{
			studentID.add(studentIDKey);
			//System.out.println("studentID: "+studentIDKey);
		}
		
		//TotalNumberOfSemestersRegistered
		for(int i =0;i<studentID.size();i++) {
			semestersByYearAndSemester =  sortedStudents.get(studentID.get(i)).getSemestersByYearAndSemester();
			TotalNumberOfSemestersRegistered.add(semestersByYearAndSemester.size());
			//System.out.println("TotalNumberOfSemestersRegistered: "+TotalNumberOfSemestersRegistered.get(i));			
		}
		
		for(int i =0;i<studentID.size();i++) {
			for(int j=0;j<TotalNumberOfSemestersRegistered.get(i);j++) {
				NumberOfCoursesTakenInEachSemester.add(studentID.get(i)+", "
						+TotalNumberOfSemestersRegistered.get(i)+", "+(j+1)+", "+sortedStudents.get(studentID.get(i)).getNumCourseInNthSementer(j+1));
				//System.out.println("total: "+studentID.get(i)+", "
				//		+TotalNumberOfSemestersRegistered.get(i)+", "+(j+1)+", "+sortedStudents.get(studentID.get(i)).getNumCourseInNthSementer(j+1));
			}	
		}
		
		return NumberOfCoursesTakenInEachSemester; // do not forget to return a proper variable.
	}
	
	
	
}
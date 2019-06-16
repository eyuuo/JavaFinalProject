package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This is Student object. For now, treat it with a student number, 
 * not with a student name.
 */
public class Student {
	
	private String studentId;
	private ArrayList<Course> coursesTaken; // List of courses student has taken
	private HashMap<String,Integer> semestersByYearAndSemester; 
	                                                         // key: Year-Semester
															// e.g., 2003-1, 
	// constructor 
	public Student(String studentId){
		this.studentId = studentId;
		coursesTaken = new ArrayList<Course>();
		semestersByYearAndSemester = new HashMap<String,Integer> ();
	}
	
	/**
	 * addCourse method add a Course instance created while reading line to the CourseTaken ArrayList in the Student instance.
	 * @param newRecord
	 */
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	
	/**
	 * getSemestersByYearAndSemester method creates a hashmap to store the student’s sequential semester information by using strong year and semester information. For example, a student entered to Handong at 2001-1 has first semester at 2001-1, second semester 
	 * at 2001-2, and 2002-1 as third semester. This will the data structure shown below. Seasonal semesters are included as official semesters.
	 * @return
	 */
	public HashMap<String,Integer> getSemestersByYearAndSemester(){//??coursesTaken이 main에서 사용되지 않으면 쓸 수 없자나...?
		
		semestersByYearAndSemester = new HashMap<String,Integer> ();
		
		//year-Semester넣기.hashset에 
		HashSet<String> key = new HashSet<String>();
		for(int i = 0; i< coursesTaken.size();i++)
			key.add(coursesTaken.get(i).getYearTaken()+"-"+coursesTaken.get(i).getSemesterCourseTaken());
		
		//정렬을 위해 arraylist를 사용한다.
		ArrayList<String> keylist = new ArrayList<String>(key); 
		Collections.sort(keylist);

		//System.out.println("---"+keylist.size());
		for(int i=0; i<keylist.size();i++) {
			semestersByYearAndSemester.put(keylist.get(i),i+1);
			//System.out.println(keylist.get(i)+"!!+"+semestersByYearAndSemester.get(keylist.get(i)));
		}	
		
		
		return semestersByYearAndSemester;
	}
	

	/**
	 * getNumCourseInNthSementer method returns the number of subjects in the semester if you enter the sequential semester number. If you enter 3 in the hashmap above, it returns number of courses taken by a student in 2002-1. You can create a key (2002-1) using a combination of year and semester information in coursesTaken, and then add a count if an obtained key value corresponds with a sequential semester number in semestersByYearAndSemester.
	 * getNumCourseInNthSementer 방법은 순차 학기 번호를 입력하면 해당 학기의 과목 수를 반환한다. 위의 해시맵에 3을 입력하면 2002년 1월에 학생이 수강하는 강좌 수를 반환한다. 과정에서의 년도와 학기 정보를 조합하여 키(2002-1)를 만들 수 있다.
	 * 취한 다음 얻은 키 값이 학기 내 순차적 학기 번호와 일치하는 경우 카운트를 추가하십시오.YearAndSemester.
	 * @param semester
	 * @return
	 */
	public int getNumCourseInNthSementer(int semester) {
		//해당학기(key) 검색
		this.semestersByYearAndSemester = getSemestersByYearAndSemester();
		
		String YearAndSemester;
		int count=0;
		for(int i = 0; i< coursesTaken.size();i++) {
			YearAndSemester=coursesTaken.get(i).getYearTaken()+"-"+coursesTaken.get(i).getSemesterCourseTaken();
			if(semestersByYearAndSemester.get(YearAndSemester).equals(semester)) {
				//System.out.println("@@@"+YearAndSemester+"##: "+semester+"~~"+semestersByYearAndSemester.get(YearAndSemester));
				count++;
			}
		}
		return count;
	} 

		

	/* Add getter and setter for the field if needed*/
	//컴파일을 위해서.
	/*
	public ArrayList<Course> getCoursesTaken() {
		// TODO Auto-generated method stub
		return coursesTaken;
	}
	public void setCoursesTaken(ArrayList<Course> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}*/
	
	
}

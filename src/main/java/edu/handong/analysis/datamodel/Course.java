package edu.handong.analysis.datamodel;

/**
* As a Course object, as long as the line is in data.csv, 
* it must have all the information in the field.
*/	
public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;


	
	/**
	 *  Split the line from constructor to initialize the field.
	 */
	public Course(String line) {

		setStudentId(line.split(",")[0]);
		this.setYearMonthGraduated(line.split(",")[1].trim());
		this.firstMajor = line.split(",")[2].trim();
		this.secondMajor = line.split(",")[3].trim();
		this.setCourseCode(line.split(",")[4].trim());
		setCourseName(line.split(",")[5].trim());
		this.courseCredit = line.split(",")[6].trim();
		this.setYearTaken(Integer.parseInt(line.split(",")[7].trim()));
		this.setSemesterCourseTaken(Integer.parseInt(line.split(",")[8].trim()));
		//컴파일 
//		System.out.println("------course-----\n"+studentId);
//		System.out.println(yearMonthGraduated+"\n"+firstMajor+"\n"+secondMajor
//				+"\n"+courseCode+"\n"+courseName+"\n"+courseCredit+"\n"+yearTaken+"\n"+semesterCourseTaken);
	
	}

	
	/* Self-define getter and setter if needed*/
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getYearTaken() {
		return yearTaken;
	}



	public void setYearTaken(int yearTaken) {
		this.yearTaken = yearTaken;
	}



	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}



	public void setSemesterCourseTaken(int semesterCourseTaken) {
		this.semesterCourseTaken = semesterCourseTaken;
	}


	public String getYearMonthGraduated() {
		return yearMonthGraduated;
	}


	public void setYearMonthGraduated(String yearMonthGraduated) {
		this.yearMonthGraduated = yearMonthGraduated;
	}


	public String getCourseCode() {
		return courseCode;
	}


	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	

}

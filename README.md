# StudentCourseRegistrationService

# Instructions
1. Open pom.xml with Intelij or Eclipse
2. Run Main.java
3. Base endpoint: http://localhost:8090

Register a new course into repository
http://localhost:8090/course
{ "name": "Introduction to computer science",
"courseCode": "CS101"
}

Register a new student
http://localhost:8090/student
{ "name": "asmat",
	"enrolledCourses": [
	{
		"courseName": "Introduction to computer science",
		 "courseCode": "CS101"
	}
		]
}

Get All Students
http://localhost:8090/student



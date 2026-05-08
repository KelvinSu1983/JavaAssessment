package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public StudentService(){
        subscribeStudent(new Student("001", "John", "johndoe@gmail.com", new Date("01/01/2000")));
        subscribeStudent(new Student("002", "Sally", "sallydoe@hotmail.com", new Date("01/01/2005")));
        subscribeStudent(new Student("003", "Jane", "janesmith@yahoo.com", new Date("01/01/2006")));
    }

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId, String courseCode )
    {
        //TODO implement this method
        //method function: to find out if a student is
        //subscribed to a given course
        Student student = findStudent(studentId);
        if (student != null) {
            return student.isAttendingCourse(courseCode);
        }
        return false;
    }

    public void showSummary()
    {
        //DONE: TODO implement
        //1. Show student information
        //2. Along with the course(s) that each student is taking

        System.out.println("Student information:");
        students.forEach((studentId, student) -> {
            System.out.println(student);

            List<Course> studentCourses = student.getApprovedCourses();

            if (studentCourses.size() > 0) {
                System.out.printf("Courses taken by studentId: %s%n", studentId);
                for (Course course: studentCourses) {
                    System.out.println(course);
                }
                System.out.printf("%n");
            }
            else {
                System.out.printf("No courses taken by studentId: %s%n%n", studentId);
            }
        });

        // TODO
    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            students.get( studentId ).enrollToCourse( course );
        }
    }

    public void showPassedCourses(Student student){

        //1.get the student
        //2.call findPassedCourses()
        //3.display the list of courses returned from findPassedCourses()

        List<Course>passedCourses = student.findPassedCourses();

        if(passedCourses.size() > 0){
            System.out.println("Courses that students passed:");
            passedCourses.forEach((course)->{
                System.out.println(course);
            });
            //there are courses to be displayed
        }else{
            //feedback that there are no passed courses
            System.out.println("The student did not pass any course(s).");
        }
    }

}

/*
    1. 001 passed INTRO-CS-1: 5
    2. 001 passed INTRO-CS-2: 5
    3. O01 failed INTRO-CS-3: 4

    1. 002 failed INTRO-CS-1: 1
    2. 002 failed INTRO-CS-2: 2
    3. O02 failed INTRO-CS-3: 3

    1. 003 did not take any course at all
*/
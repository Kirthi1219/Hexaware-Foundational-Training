package service;

import java.util.List;

import dao.TeacherDAO;
import entity.Teacher;
import exception.InvalidTeacherDataException;
import exception.TeacherNotFoundException;

public class TeacherService {
	private TeacherDAO teacherDAO;

    public TeacherService() {
        this.teacherDAO = new TeacherDAO();
    }

    //Add a new teacher
    public void addTeacher(Teacher teacher) {
        try {
            if (!isValidEmail(teacher.getEmail())) {
                throw new InvalidTeacherDataException("Invalid email format for teacher.");
            }
            teacherDAO.addTeacher(teacher);
            System.out.println("Teacher added successfully: " + teacher.getFirstName() + " " + teacher.getLastName());
        } catch (InvalidTeacherDataException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    // Get teacher by ID
    public Teacher getTeacherById(int teacherId) {
        try {
            Teacher teacher = teacherDAO.getTeacherById(teacherId);
            if (teacher == null) {
                throw new TeacherNotFoundException("No teacher found with ID: " + teacherId);
            }
            System.out.println("Teacher Found: ID: " + teacher.getTeacherId() +
                    ", Name: " + teacher.getFirstName() + " " + teacher.getLastName() +
                    ", Email: " + teacher.getEmail());
            return teacher;
        } catch (TeacherNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    // Get all teachers
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        if (teachers.isEmpty()) {
            System.out.println("No teachers found in the system.");
        } else {
            System.out.println("List of Teachers:");
            for (Teacher teacher : teachers) {
                System.out.println("▶ ID: " + teacher.getTeacherId() +
                        ", Name: " + teacher.getFirstName() + " " + teacher.getLastName() +
                        ", Email: " + teacher.getEmail());
            }
        }
        return teachers;
    }

    //update a teacher
    public void updateTeacher(Teacher teacher) {
        try {
            if (!isValidEmail(teacher.getEmail())) {
                throw new InvalidTeacherDataException("Invalid email format for teacher.");
            }
            Teacher existingTeacher = teacherDAO.getTeacherById(teacher.getTeacherId());
            if (existingTeacher == null) {
                throw new TeacherNotFoundException("Cannot update. No teacher found with ID: " + teacher.getTeacherId());
            }
            teacherDAO.updateTeacher(teacher);
            System.out.println("Teacher updated successfully: " + teacher.getFirstName() + " " + teacher.getLastName());
        } catch (InvalidTeacherDataException | TeacherNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }


    // Delete a teacher
    public void deleteTeacher(int teacherId) {
        try {
            Teacher existingTeacher = teacherDAO.getTeacherById(teacherId);
            if (existingTeacher == null) {
                throw new TeacherNotFoundException("Cannot delete. No teacher found with ID: " + teacherId);
            }
            teacherDAO.deleteTeacher(teacherId);
            System.out.println("Teacher with ID " + teacherId + " deleted successfully.");
        } catch (TeacherNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}

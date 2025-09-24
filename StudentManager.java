
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }
    // Add student
    public boolean addStudent(Student student) {
        // Check if student with same ID already exists
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                return false; // Student with this ID already exists
            }
        }
        students.add(student);
        return true;
    }
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.println("\n==== STUDENT RECORDS ====");
        System.out.printf("%-5s %-20s %-10s%n", "ID", "Name", "Marks");
        System.out.println("----------------------------------------");

        for (Student student : students) {
            System.out.printf("%-5d %-20s %-10.2f%n",
                    student.getId(), student.getName(), student.getMarks());
        }
        System.out.println();
    }
    // Update student
    public boolean updateStudent(int id, String name, double marks) {
        Student student = findStudentById(id);
        if (student != null) {
            if (name != null && !name.trim().isEmpty()) {
                student.setName(name.trim());
            }
            if (marks >= 0) {
                student.setMarks(marks);
            }
            return true;
        }
        return false;
    }
    // Delete student
    public boolean deleteStudent(int id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    // Find student by ID
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    public ArrayList<Student> searchByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }
}

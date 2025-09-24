import java.util.ArrayList;
import java.util.Scanner;
public class StudentRecordSystem {
    private static StudentManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        manager = new StudentManager();
        scanner = new Scanner(System.in);

        System.out.println("=======================================");
        System.out.println("  STUDENT RECORD MANAGEMENT SYSTEM    ");
        System.out.println("=======================================");
        System.out.println();
        loadSampleData();

        while (true) {
            displayMenu();

            switch (getChoice()) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("\nThank you for using Student Record Management System!");
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a number between 1-9.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("================================");
        System.out.print("Enter your choice (1-6): ");
    }
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private static void addStudent() {
        System.out.println("\n========== ADD STUDENT ==========");

        try {
            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Error: Name cannot be empty!");
                return;
            }

            System.out.print("Enter Student Marks: ");
            double marks = Double.parseDouble(scanner.nextLine().trim());

            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks should be between 0 and 100!");
                return;
            }

            Student student = new Student(id, name, marks);

            if (manager.addStudent(student)) {
                System.out.println("\nStudent added successfully!");
                System.out.println("Added: " + student);
            } else {
                System.out.println("\nError: Student with ID " + id + " already exists!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric values for ID and marks!");
        }
    }
    private static void viewAllStudents() {
        System.out.println("\n========== ALL STUDENTS ==========");
        manager.viewAllStudents();
    }
    private static void searchStudent() {
        System.out.println("\n========== SEARCH STUDENT ==========");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Enter your choice (1-2): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice == 1) {
                System.out.print("Enter Student ID: ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                Student student = manager.findStudentById(id);

                if (student != null) {
                    System.out.println("\nStudent found:");
                    System.out.printf("%-5s %-20s %-10s%n", "ID", "Name", "Marks");
                    System.out.println("----------------------------------------");
                    System.out.printf("%-5d %-20s %-10.2f%n",
                            student.getId(), student.getName(), student.getMarks());
                } else {
                    System.out.println("\nNo student found with ID: " + id);
                }

            } else if (choice == 2) {
                System.out.print("Enter Student Name (or part of name): ");
                String name = scanner.nextLine().trim();
                ArrayList<Student> results = manager.searchByName(name);

                if (!results.isEmpty()) {
                    System.out.println("\nStudents found:");
                    System.out.printf("%-5s %-20s %-10s%n", "ID", "Name", "Marks");
                    System.out.println("----------------------------------------");
                    for (Student student : results) {
                        System.out.printf("%-5d %-20s %-10.2f%n",
                                student.getId(), student.getName(), student.getMarks());
                    }
                } else {
                    System.out.println("\nNo students found with name containing: " + name);
                }

            } else {
                System.out.println("Invalid choice!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid input!");
        }
    }
    private static void updateStudent() {
        System.out.println("\n========== UPDATE STUDENT ==========");

        try {
            System.out.print("Enter Student ID to update: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            Student student = manager.findStudentById(id);
            if (student == null) {
                System.out.println("\nNo student found with ID: " + id);
                return;
            }

            System.out.println("\nCurrent details: " + student);
            System.out.println("\nEnter new details (press Enter to keep current value):");

            System.out.print("Enter new name [" + student.getName() + "]: ");
            String newName = scanner.nextLine().trim();

            System.out.print("Enter new marks [" + student.getMarks() + "]: ");
            String marksInput = scanner.nextLine().trim();

            double newMarks = -1;
            if (!marksInput.isEmpty()) {
                try {
                    newMarks = Double.parseDouble(marksInput);
                    if (newMarks < 0 || newMarks > 100) {
                        System.out.println("Error: Marks should be between 0 and 100!");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter valid marks!");
                    return;
                }
            }

            if (manager.updateStudent(id, newName.isEmpty() ? null : newName, newMarks)) {
                System.out.println("\nStudent updated successfully!");
                Student updatedStudent = manager.findStudentById(id);
                System.out.println("Updated details: " + updatedStudent);
            } else {
                System.out.println("\nFailed to update student!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid Student ID!");
        }
    }
    private static void deleteStudent() {
        System.out.println("\n========== DELETE STUDENT ==========");

        try {
            System.out.print("Enter Student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            Student student = manager.findStudentById(id);
            if (student == null) {
                System.out.println("\nNo student found with ID: " + id);
                return;
            }

            System.out.println("\nStudent to be deleted: " + student);
            System.out.print("Are you sure you want to delete this student? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes") || confirmation.equals("y")) {
                if (manager.deleteStudent(id)) {
                    System.out.println("\nStudent deleted successfully!");
                } else {
                    System.out.println("\nFailed to delete student!");
                }
            } else {
                System.out.println("\nDeletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid Student ID!");
        }
    }
    private static void loadSampleData() {
        // Add some sample students for demonstration
        manager.addStudent(new Student(101, "Alice Johnson", 85.5));
        manager.addStudent(new Student(102, "Bob Smith", 92.0));
        manager.addStudent(new Student(103, "Charlie Brown", 78.3));
        manager.addStudent(new Student(104, "Diana Prince", 95.8));
        manager.addStudent(new Student(105, "Edward Wilson", 67.2));

        System.out.println("Sample data loaded with 5 students.");
    }

    }

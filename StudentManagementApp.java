import java.util.*;
import java.io.*;

public class StudentManagementApp {

    static class Student {
        private String name;
        private int rollNumber;
        private String grade;
        private String email;

        public Student(String name, int rollNumber, String grade, String email) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
            this.email = email;
        }

        public int getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        public String getGrade() {
            return grade;
        }

        public String getEmail() {
            return email;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return rollNumber + "," + name + "," + grade + "," + email;
        }

        public static Student fromString(String line) {
            String[] parts = line.split(",");
            return new Student(parts[1], Integer.parseInt(parts[0]), parts[2], parts[3]);
        }
    }
    static class StudentManagementSystem {
        private Map<Integer, Student> studentMap = new HashMap<>();
        private final String FILE_NAME = "students.txt";

        public StudentManagementSystem() {
            loadFromFile();
        }

        public void addStudent(Student student) {
            if (studentMap.containsKey(student.getRollNumber())) {
                System.out.println("❌ Student with this roll number already exists.");
            } else {
                studentMap.put(student.getRollNumber(), student);
                System.out.println("✅ Student added.");
                saveToFile();
            }
        }

        public void removeStudent(int rollNumber) {
            if (studentMap.remove(rollNumber) != null) {
                System.out.println("✅ Student removed.");
                saveToFile();
            } else {
                System.out.println("❌ Student not found.");
            }
        }

        public void searchStudent(int rollNumber) {
            Student student = studentMap.get(rollNumber);
            if (student != null) {
                System.out.println("🎓 Student Found: " + student);
            } else {
                System.out.println("❌ No student with this roll number.");
            }
        }

        public void editStudent(int rollNumber, String newGrade, String newEmail) {
            Student student = studentMap.get(rollNumber);
            if (student != null) {
                student.setGrade(newGrade);
                student.setEmail(newEmail);
                System.out.println("✅ Student info updated.");
                saveToFile();
            } else {
                System.out.println("❌ Student not found.");
            }
        }

        public void displayAllStudents() {
            if (studentMap.isEmpty()) {
                System.out.println("⚠️ No students found.");
                return;
            }
            System.out.println("\n--- Student List ---");
            for (Student student : studentMap.values()) {
                System.out.println(student);
            }
        }

        private void saveToFile() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (Student s : studentMap.values()) {
                    bw.write(s.toString());
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("❌ Failed to save file.");
            }
        }

        private void loadFromFile() {
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Student student = Student.fromString(line);
                    studentMap.put(student.getRollNumber(), student);
                }
            } catch (IOException e) {
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int roll;
                    try {
                        roll = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid roll number.");
                        break;
                    }
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("❌ All fields are required.");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade, email));
                    break;

                case "2":
                    System.out.print("Enter Roll Number to remove: ");
                    try {
                        int rollToRemove = Integer.parseInt(scanner.nextLine());
                        sms.removeStudent(rollToRemove);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid input.");
                    }
                    break;

                case "3":
                    System.out.print("Enter Roll Number to search: ");
                    try {
                        int rollToSearch = Integer.parseInt(scanner.nextLine());
                        sms.searchStudent(rollToSearch);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid input.");
                    }
                    break;

                case "4":
                    System.out.print("Enter Roll Number to edit: ");
                    try {
                        int rollToEdit = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Grade: ");
                        String newGrade = scanner.nextLine();
                        System.out.print("Enter New Email: ");
                        String newEmail = scanner.nextLine();
                        if (newGrade.isEmpty() || newEmail.isEmpty()) {
                            System.out.println("❌ Fields cannot be empty.");
                            break;
                        }
                        sms.editStudent(rollToEdit, newGrade, newEmail);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid input.");
                    }
                    break;

                case "5":
                    sms.displayAllStudents();
                    break;

                case "6":
                    System.out.println("👋 Exiting. Goodbye!");
                    return;

                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }
}

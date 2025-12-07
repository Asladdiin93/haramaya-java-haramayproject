import java.util.*;

public class StudentManager {
    private static List<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🎓 Haramaya University — Student Management System");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n🔹 MENU:");
            System.out.println("1. Insert Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Update Student (by ID)");
            System.out.println("4. Delete Student (by ID)");
            System.out.println("5. Search Student (by ID)");
            System.out.println("0. Exit");
            System.out.print("👉 Choose an option: ");

            int choice = getIntInput();
            System.out.println();

            switch (choice) {
                case 1: insertStudent(); break;
                case 2: displayAll(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: searchStudent(); break;
                case 0:
                    System.out.println("✅ Goodbye, Asladdiin! Keep coding! 🇪🇹");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }

    private static void insertStudent() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter ID (e.g., IT/2025/001): ");
        String id = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        Student s = new Student(name, id, dept, course);
        students.add(s);
        System.out.println("✅ Student added successfully!");
    }

    private static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("📭 No students registered yet.");
            return;
        }
        System.out.println("📋 All Students (" + students.size() + "):");
        System.out.println("----------------------------------------");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i+1) + ".\n" + students.get(i));
            if (i < students.size() - 1) System.out.println();
        }
    }

    private static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = sc.nextLine();
        Student s = findStudentById(id);

        if (s == null) {
            System.out.println("❌ Student with ID '" + id + "' not found.");
            return;
        }

        System.out.println("📌 Current Info:\n" + s);
        System.out.println("\n✏️ Leave blank to keep current value.");

        System.out.print("New Name [" + s.getName() + "]: ");
        String name = sc.nextLine();
        if (!name.trim().isEmpty()) s.setName(name);

        System.out.print("New Department [" + s.getDepartment() + "]: ");
        String dept = sc.nextLine();
        if (!dept.trim().isEmpty()) s.setDepartment(dept);

        System.out.print("New Course [" + s.getCourse() + "]: ");
        String course = sc.nextLine();
        if (!course.trim().isEmpty()) s.setCourse(course);

        System.out.println("✅ Student updated!");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine();
        Student s = findStudentById(id);

        if (s == null) {
            System.out.println("❌ Student with ID '" + id + "' not found.");
            return;
        }

        students.remove(s);
        System.out.println("✅ Student deleted.");
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String id = sc.nextLine();
        Student s = findStudentById(id);

        if (s == null) {
            System.out.println("❌ Not found.");
        } else {
            System.out.println("✅ Found:");
            System.out.println(s);
        }
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

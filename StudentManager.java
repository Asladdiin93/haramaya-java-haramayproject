import java.util.*;

public class StudentManager {
    private static List<Person> people = new ArrayList<>();  // ✅ Polymorphic list
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();
        System.out.println("🎓 Haramaya University — People Management System");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n🔹 MENU:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Instructor");
            System.out.println("3. Display All");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. Add Specialty to Instructor");
            System.out.println("0. Exit");
            System.out.print("👉 Choose: ");

            int choice = getIntInput();
            System.out.println();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: addInstructor(); break;
                case 3: displayAll(); break;
                case 4: enrollStudent(); break;
                case 5: addSpecialty(); break;
                case 0:
                    saveToFile();
                    System.out.println("✅ Goodbye, Asladdiin! 🇪🇹");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid option.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Dept: "); String dept = sc.nextLine();
        Student s = new Student(name, id, dept);
        people.add(s);
        System.out.println("✅ Student added.");
        saveToFile();
    }

    private static void addInstructor() {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Dept: "); String dept = sc.nextLine();
        Instructor i = new Instructor(name, id, dept);
        people.add(i);
        System.out.println("✅ Instructor added.");
        saveToFile();
    }

    private static void displayAll() {
        if (people.isEmpty()) {
            System.out.println("📭 No people registered.");
            return;
        }
        System.out.println("👥 All (" + people.size() + "):");
        System.out.println("----------------------------------------");
        for (Person p : people) {
            System.out.println(p);  // ✅ POLYMORPHISM: calls correct toString()!
            System.out.println();
        }
    }

    private static Student findStudentById(String id) {
        for (Person p : people) {
            if (p instanceof Student && p.getId().equalsIgnoreCase(id)) {
                return (Student) p;
            }
        }
        return null;
    }

    private static Instructor findInstructorById(String id) {
        for (Person p : people) {
            if (p instanceof Instructor && p.getId().equalsIgnoreCase(id)) {
                return (Instructor) p;
            }
        }
        return null;
    }

    private static void enrollStudent() {
        System.out.print("Student ID: "); String id = sc.nextLine();
        Student s = findStudentById(id);
        if (s == null) { System.out.println("❌ Not found."); return; }

        System.out.print("Course Code: "); String code = sc.nextLine();
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Credits: "); int cr = getIntInput();
        if (cr <= 0) cr = 3;

        s.addCourse(new Course(code, title, cr));
        System.out.println("✅ Enrolled!");
        saveToFile();
    }

    private static void addSpecialty() {
        System.out.print("Instructor ID: "); String id = sc.nextLine();
        Instructor i = findInstructorById(id);
        if (i == null) { System.out.println("❌ Not found."); return; }

        System.out.print("Specialty (e.g., Java): "); String topic = sc.nextLine();
        i.addSpecialty(topic);
        System.out.println("✅ Added!");
        saveToFile();
    }

    // Simple save/load (enhance later)
    private static void saveToFile() {
        try (var w = new java.io.PrintWriter("people.txt")) {
            for (Person p : people) {
                if (p instanceof Student) {
                    Student s = (Student) p;
                    w.print("S|" + s.getName() + "|" + s.getId() + "|" + s.getDepartment());
                    for (Course c : s.getCourses()) {
                        w.print("|" + c.getCode() + "|" + c.getTitle() + "|" + c.getCredits());
                    }
                } else if (p instanceof Instructor) {
                    Instructor i = (Instructor) p;
                    w.print("I|" + i.getName() + "|" + i.getId() + "|" + i.getDepartment());
                    // (specialties not saved for simplicity — add later if needed)
                }
                w.println();
            }
        } catch (Exception e) {
            System.out.println("❌ Save failed: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        try (var scFile = new Scanner(new java.io.File("people.txt"))) {
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 4) continue;

                if ("S".equals(parts[0])) {
                    Student s = new Student(parts[1], parts[2], parts[3]);
                    for (int i = 4; i < parts.length; i += 3) {
                        if (i + 2 < parts.length) {
                            String code = parts[i];
                            String title = parts[i+1];
                            int cr = 3;
                            try { cr = Integer.parseInt(parts[i+2]); } catch (Exception ex) {}
                            s.addCourse(new Course(code, title, cr));
                        }
                    }
                    people.add(s);
                } else if ("I".equals(parts[0])) {
                    Instructor i = new Instructor(parts[1], parts[2], parts[3]);
                    people.add(i);
                }
            }
            System.out.println("✅ Loaded " + people.size() + " people.");
        } catch (Exception e) {
            System.out.println("📭 No people.txt — starting fresh.");
        }
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return 0;
        }
    }
}

import java.util.*;

public class Student extends Person {  // ✅ INHERITANCE
    private String department;
    private List<Course> courses;

    public Student(String name, String id, String department) {
        super(name, id);  // ✅ call parent constructor
        this.department = department;
        this.courses = new ArrayList<>();
    }

    // Student-specific getters/setters
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public List<Course> getCourses() { return new ArrayList<>(courses); }

    // Composition: course management
    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
        }
    }

    public int getTotalCredits() {
        return courses.stream().mapToInt(Course::getCredits).sum();
    }

    // ✅ Override for polymorphism
    @Override
    public String getRole() { return "Student"; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("🎓 ").append(name).append(" | ID: ").append(id)
          .append(" | Dept: ").append(department)
          .append(" | Credits: ").append(getTotalCredits());

        if (courses.isEmpty()) {
            sb.append("\n   📚 Courses: (none)");
        } else {
            sb.append("\n   📚 Courses:");
            for (Course c : courses) {
                sb.append("\n     - ").append(c);
            }
        }
        return sb.toString();
    }
}

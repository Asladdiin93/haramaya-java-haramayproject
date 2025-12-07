import java.util.*;

public class Instructor extends Person {
    private String department;
    private List<String> specialties;  // e.g., ["Java", "AI", "DB"]

    public Instructor(String name, String id, String department) {
        super(name, id);
        this.department = department;
        this.specialties = new ArrayList<>();
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public void addSpecialty(String topic) {
        if (topic != null && !topic.trim().isEmpty() && !specialties.contains(topic.trim())) {
            specialties.add(topic.trim());
        }
    }

    @Override
    public String getRole() { return "Instructor"; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("👨‍🏫 ").append(name).append(" | ID: ").append(id)
          .append(" | Dept: ").append(department);
        if (specialties.isEmpty()) {
            sb.append("\n   🎯 Specialties: (none)");
        } else {
            sb.append("\n   🎯 Specialties: ").append(String.join(", ", specialties));
        }
        return sb.toString();
    }
}

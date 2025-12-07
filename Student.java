public class Student {
    private String name;
    private String id;
    private String department;
    private String course;

    public Student(String name, String id, String department, String course) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.course = course;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return "📌 ID: " + id + 
               "\n   Name: " + name + 
               "\n   Dept: " + department + 
               "\n   Course: " + course;
    }
}

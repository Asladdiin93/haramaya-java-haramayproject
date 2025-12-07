// Person.java — common base for all university members
public class Person {
    protected String name;
    protected String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Common behavior
    public String getName() { return name; }
    public String getId() { return id; }
    public void setName(String name) { this.name = name; }

    // Abstract representation — subclasses will specialize
    public String getRole() { return "Person"; }

    @Override
    public String toString() {
        return "👤 " + name + " | ID: " + id + " | Role: " + getRole();
    }
}

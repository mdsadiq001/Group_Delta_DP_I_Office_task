package main.java.models;

public class Employee extends User {

    private String employeeId;
    private String department;

    public Employee(String id, String username, String password, String role,
                    String employeeId, String department) {

        super(id, username, password, role);

        this.employeeId = employeeId;
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Employee{" +
                "employeeId=" + employeeId +
                ", department='" + department + '\'' +
                '}';
    }
}

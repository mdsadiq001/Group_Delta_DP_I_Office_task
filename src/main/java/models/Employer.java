package main.java.models;

public class Employer extends User {

    private String employerId;

    public Employer(String id, String username, String password, String role,
                    String employerId) {

        super(id, username, password, role);

        this.employerId = employerId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Employer{" +
                "employerId=" + employerId +
                '}';
    }
}

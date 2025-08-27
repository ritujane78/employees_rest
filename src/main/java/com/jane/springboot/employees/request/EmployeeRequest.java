package com.jane.springboot.employees.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {


    @NotNull(message = "First Name is mandatory.")
    @Size(min=2, max=50, message = "First name must be in between 2 to 50 characters")
    private String firstName;

    @NotNull(message = "Last name is mandatory.")
    @Size(min=2, max=50, message = "Last name must be in between 2 to 50 characters")
    private String lastName;

    @NotNull(message = "Email is mandatory.")
    @Email
    private String email;

    public EmployeeRequest(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

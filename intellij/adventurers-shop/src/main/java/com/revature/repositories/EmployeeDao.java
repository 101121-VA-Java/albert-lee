package com.revature.repositories;

import com.revature.models.users.Employee;

public interface EmployeeDao {
    Employee[] getAllEmployees(); // returns an array of all of the employees available
    Employee getEmployeeById(int id);
    Employee login(String username, String password);
    int addEmployee(Employee e); // returns assigned id
    boolean editEmployee(Employee e);  //returns boolean depending on operation success
    boolean deleteEmployee(int id);  //returns boolean depending on operation success
}
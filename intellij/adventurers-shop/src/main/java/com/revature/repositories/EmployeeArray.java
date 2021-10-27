package com.revature.repositories;

import com.revature.models.users.Employee;

public class EmployeeArray implements EmployeeDao{

    private Employee[] employees;

    public EmployeeArray() {
        super();
        employees = new Employee[0];
    }

    public EmployeeArray(Employee[] employees) {
        this.employees = employees;
    }

    @Override
    public Employee[] getAllEmployees() {
        return this.employees;
    }

    public Employee login(String username, String password){
        Employee result = new Employee();
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) result = employee;
        }
        return result;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee result = new Employee();
        for (Employee employee : employees) {
            if (employee.getId() == id) result = employee;
        }
        return result;
    }

    @Override
    public int addEmployee(Employee e) {

        // Create a temp array of size employees + 1
        Employee[] temp = new Employee[employees.length +1 ];

        int i = 0;

        for(; i < employees.length; i++) {
            temp[i] = employees[i];
        }
        /*
         *  i is the value of the last index of the new array (employees.length)
         *  set i to be the id for the new employee
         */
        e.setId(i);

        // adding the new employee to the array
        temp[i] = e;

        /*
         * assign the newly created array to employees
         */
        employees = temp;

        return i;
    }

    @Override
    public boolean editEmployee(Employee e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        // TODO Auto-generated method stub
        return false;
    }
}
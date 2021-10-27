package com.revature.repositories;

import com.revature.models.users.Employee;

public class EmployeePostgres implements EmployeeDao{
    @Override
    public Employee login(String username, String password) {
        return null;
    }

    @Override
    public Employee[] getAllEmployees() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int addEmployee(Employee e) {
        // TODO Auto-generated method stub
        return 0;
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
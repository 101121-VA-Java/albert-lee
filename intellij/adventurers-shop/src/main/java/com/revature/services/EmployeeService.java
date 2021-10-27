package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.users.Employee;
import com.revature.models.users.Role;
import com.revature.models.users.User;
import com.revature.repositories.EmployeeArray;
import com.revature.repositories.EmployeeDao;

public class EmployeeService {

    private EmployeeDao ed;

    public EmployeeService() {
        super();
        ed = new EmployeeArray();
    }

    public int addEmployee(Employee e) {
        e.setRole(Role.BASIC_USER);
        return ed.addEmployee(e);
    }

    public Employee getEmployeeById(int id) throws UserNotFoundException  {
        Employee result = ed.getEmployeeById(id);
        if(result.equals(new Employee())) throw new UserNotFoundException();
        return result;
    }

    public Employee login(String username, String password) throws UserNotFoundException {
        Employee result = ed.login(username, password);
        if(result.equals(new Employee())) throw new UserNotFoundException();
        return result;
    }
}
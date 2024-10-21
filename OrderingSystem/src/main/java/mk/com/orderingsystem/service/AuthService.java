package mk.com.orderingsystem.service;

import mk.com.orderingsystem.models.Employee;

import java.util.List;

public interface AuthService {
    Employee login(String username, String password);

    List<Employee> findAll();

}

package mk.com.orderingsystem.service;

import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.enumeration.Role;
import mk.com.orderingsystem.models.exceptions.EmployeeNotFound;

import java.util.List;

public interface EmployeeService {
    Employee register(String username, String password, Role role);
    void approveRegistration(String username);
    String getCurrentUsername();
    Employee getEmployeeByUsername(String username);

    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByRole(Role role);
    void deleteEmployee(String username);
}

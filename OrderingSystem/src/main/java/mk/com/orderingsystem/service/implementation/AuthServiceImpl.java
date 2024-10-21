package mk.com.orderingsystem.service.implementation;

import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.exceptions.InvalidArgumentsException;
import mk.com.orderingsystem.models.exceptions.InvalidUserCredentialsException;
import mk.com.orderingsystem.repository.EmployeeRepository;
import mk.com.orderingsystem.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final EmployeeRepository userRepository;

    public AuthServiceImpl(EmployeeRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Employee login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public List<Employee> findAll() {
        return userRepository.findAll();
    }

}

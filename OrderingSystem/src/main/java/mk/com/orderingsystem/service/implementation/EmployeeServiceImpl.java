package mk.com.orderingsystem.service.implementation;

import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.Order;
import mk.com.orderingsystem.models.enumeration.Role;
import mk.com.orderingsystem.models.exceptions.EmployeeNotFound;
import mk.com.orderingsystem.models.exceptions.InvalidUsernameOrPasswordException;
import mk.com.orderingsystem.models.exceptions.UsernameAlreadyExistsException;
import mk.com.orderingsystem.repository.EmployeeRepository;
import mk.com.orderingsystem.repository.OrderRepository;
import mk.com.orderingsystem.service.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    public EmployeeServiceImpl(EmployeeRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return userRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByRole(Role role) {
        return this.userRepository.findAllByRole(role);
    }

    @Override
    public void deleteEmployee(String username) {
        Employee employee = this.userRepository.findByUsername(username).orElseThrow(EmployeeNotFound::new);
        List<Order> orders = this.orderRepository.findAllByEmployee(employee);
        if (orders.size() > 0) {
            for (Order order : orders) {
                this.orderRepository.delete(order);
            }
            this.userRepository.save(employee);
        }
        this.userRepository.delete(employee);
    }

    @Override
    public Employee register(String username, String password, Role role) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }


        if(this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        Employee user = new Employee(
                username,
                passwordEncoder.encode(password),
                role
        );

        return userRepository.save(user);
    }


    @Override
    public void approveRegistration(String username) {
        Employee employee = userRepository.findByUsername(username).orElseThrow(EmployeeNotFound::new);
        if (employee != null) {
            employee.setEnabled(true);
            userRepository.save(employee);
        }
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(EmployeeNotFound::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }


}

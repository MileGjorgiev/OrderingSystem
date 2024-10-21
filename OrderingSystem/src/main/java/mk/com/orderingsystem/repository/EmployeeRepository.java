package mk.com.orderingsystem.repository;

import mk.com.orderingsystem.models.Employee;
import mk.com.orderingsystem.models.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    Optional<Employee> findByUsernameAndPassword(String username, String password);
    List<Employee> findAllByRole(Role role);

}

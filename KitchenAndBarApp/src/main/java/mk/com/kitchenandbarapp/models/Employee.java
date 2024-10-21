package mk.com.kitchenandbarapp.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.kitchenandbarapp.models.enumeration.Role;


import java.util.Collection;
import java.util.Collections;


@Data
@NoArgsConstructor
public class Employee {

    private String username;

    private String password;

    private Role role;



}

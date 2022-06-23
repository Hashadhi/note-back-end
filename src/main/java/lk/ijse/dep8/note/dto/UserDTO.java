package lk.ijse.dep8.note.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    @Null(message = "Id can't be set")
    private String id;
    @Email(message = "Invalid email")
    @NotNull(message = "Email cannot be empty")
    private String email;
    @NotBlank (message = "Password cannot be empty")
    @Length(min = 6, message = "Password should be at least 6 characters long")
    private String password;
    @NotNull(message = "Full name cannot be empty")
    @Pattern(regexp = "[A-Za-z ]+" , message = "Invalid name")
    private String fullName;
}

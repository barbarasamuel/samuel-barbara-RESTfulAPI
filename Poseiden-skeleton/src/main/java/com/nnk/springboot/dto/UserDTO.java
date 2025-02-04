package com.nnk.springboot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * To collect the information about the user from the client website to the server
 *
 */
@Data
public class UserDTO {
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])(?=.*[0-9])[A-Za-z0-9@$!%*?&]{8,8}$",
            message = "The password must contain 8 characters and at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    private String role;

    public UserDTO(){};

    public UserDTO(Integer id,String fullname,String username,String password, String role){
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.role = role;

    }

}

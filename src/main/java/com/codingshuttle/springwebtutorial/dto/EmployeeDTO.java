package com.codingshuttle.springwebtutorial.dto;

import com.codingshuttle.springwebtutorial.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Required field in Employee : name")
    @Size(min = 3,max = 10,message = "Number of character of name should be in the range : [3,10]")
    private String name;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email should be valid ")
    private String email;

    @NotNull(message = "Age should not be non null")
    @Max(value = 80,message = "Age of the employee cannot be greater than 80")
    @Min(value = 18,message = "Age of the employee cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of the employee should be admin  or user")
    @EmployeeRoleValidation
    private String role; // ADMIN,USER

    @PastOrPresent(message = "date of joining of the employee cannot be future")
    private LocalDate dateOfJoining;

    @NotNull(message = "salary should be non null")
    @Positive(message = "salary should be positive")
    @Digits(integer = 6,fraction = 2,message = "Salary can be of the form xxxxxx.yy")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.99")
    private Double salary;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

}

package org.example.formregister.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.formregister.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements Validator {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String email;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDto userRequestDto = (UserRequestDto) target;

        if (userRequestDto.getFirstName().isEmpty()) {
            errors.rejectValue("firstName","empty");
        } else if(userRequestDto.getFirstName().length()<5) {
            errors.rejectValue("firstName","nameLengthLessThan5");
        } else if (userRequestDto.getFirstName().length() >45) {
            errors.rejectValue("firstName","nameLengthGreaterThan45");
        }

        if (userRequestDto.getLastName().isEmpty()) {
            errors.rejectValue("lastName","empty");
        } else if(userRequestDto.getLastName().length()<5) {
            errors.rejectValue("lastName","nameLengthLessThan5");
        } else if (userRequestDto.getLastName().length() >45) {
            errors.rejectValue("lastName","nameLengthGreaterThan45");
        }

        if (userRequestDto.getPhoneNumber().isEmpty()) {
            errors.rejectValue("phoneNumber","empty");
        } else if (userRequestDto.getPhoneNumber().length() > 11 || userRequestDto.getPhoneNumber().length() < 10) {
            errors.rejectValue("phoneNumber","numberLength");
        } else if (!userRequestDto.getPhoneNumber().startsWith("0")) {
            errors.rejectValue("phoneNumber","numberStartsWith");
        } else if (!userRequestDto.getPhoneNumber().matches("(^0\\d{9}$)")) {
            errors.rejectValue("phoneNumber","numberMatches");
        }

        if (userRequestDto.getAge() < 18) {
            errors.rejectValue("age","null","Tuổi phải lớn hơn 18");
        }

        if (userRequestDto.getEmail().isEmpty()) {
            errors.rejectValue("email","empty");
        } else if (!userRequestDto.getEmail().matches("(^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$)")) {
            errors.rejectValue("email","null","Email chưa đúng định dạng");
        }



    }
}

package org.example.validateinforuser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements Validator {

    private String name;
    private int age;

    //ko cần thiết
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDto userRequestDto = (UserRequestDto) target;
        if(userRequestDto.getName().isEmpty()) {
            errors.rejectValue("name","nameNotEmpty","Không được để trống");
        } else if (!userRequestDto.getName().matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$")) {
            errors.rejectValue("name", "nameNotMatch", "Không đúng định dạng");
        }

        if(userRequestDto.getAge() < 18) {
            errors.rejectValue("age","ageNotEnough","Em chưa 18!!!");
        }
    }
}

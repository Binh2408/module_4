package org.example.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Validator {

    private String name;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDto categoryDto = (CategoryDto) target;
        if (categoryDto.getName().isEmpty()) {
            errors.rejectValue("name","null","Must be enter");
        } else if (categoryDto.getName().length() > 50) {
            errors.rejectValue("name","null","Less than 50");
        }
    }
}

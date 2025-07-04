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
public class ManufactureDto implements Validator {
    private String name;
    private String country;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ManufactureDto manufactureDto = (ManufactureDto) target;
        if (manufactureDto.getName().isEmpty()) {
            errors.rejectValue("name","null","Must be enter");
        } else if (manufactureDto.getName().length() > 50) {
            errors.rejectValue("name","null","Less than 50 character");
        }

        if (manufactureDto.getCountry().isEmpty()) {
            errors.rejectValue("country","null","Must be enter");
        } else if (manufactureDto.getCountry().length() > 50) {
            errors.rejectValue("country","null","Less than 50 character");
        }
    }
}

package org.example.customizephonevalidate.model;

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
public class PhoneNumber implements Validator {
    private String number;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneNumber phoneNumber = (PhoneNumber) target;
        String number = phoneNumber.getNumber();
        if(number.isEmpty()) {
            errors.rejectValue("number","number.empty","Không được trống");
        } else if (number.length() >11 || number.length() <10) {
            errors.rejectValue("number","number.length","Độ dài số không đúng");
        } else if (!number.startsWith("0")) {
            errors.rejectValue("number","number.startsWith","Phải bắt đầu từ số '0'");
        } else if (!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("number","number.matches","Không đúng định dạng");
        }
    }
}

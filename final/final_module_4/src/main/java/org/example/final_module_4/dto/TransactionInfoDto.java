package org.example.final_module_4.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.final_module_4.model.Category;
import org.example.final_module_4.model.Customer;
import org.example.final_module_4.model.TransactionInfo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfoDto implements Validator {
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createAt;
    private double price;
    private double area;
    private Customer customer;
    private Category category;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransactionInfoDto transactionInfoDto = (TransactionInfoDto) target;
        if (transactionInfoDto.getArea() <= 20) {
            errors.rejectValue("area","null","Diện tích phải lớn hơn 20(m2)");
        }

        if (transactionInfoDto.getPrice() <= 500000) {
            errors.rejectValue("price","null","Giá phải lớn hơn 500.000(VND)");
        }

        if (transactionInfoDto.getCreateAt().isBefore(LocalDate.now()) || transactionInfoDto.getCreateAt().equals(LocalDate.now())) {
            errors.rejectValue("createAt","null","Ngày giao dịch phải lớn hơn thời gian hiện tại");
        }

        if (!transactionInfoDto.getCode().matches("^MGD-\\d{4}$")) {
            errors.rejectValue("code","null","Mã giao dịch không đúng định dạng 'MGD-XXXX'");
        }
    }
}

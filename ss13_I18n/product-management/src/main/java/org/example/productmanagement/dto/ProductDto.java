package org.example.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.productmanagement.model.Category;
import org.example.productmanagement.model.Manufacture;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Validator {
    private String name;
    private double price;
    private String description;
    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private Category category;
    private Manufacture manufacture;
    @Override
    public boolean supports(Class<?> clazz)  {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDto productDto = (ProductDto) target;

        if (productDto.getName().isEmpty()) {
            errors.rejectValue("name","null","Must be enter");
        } else if (productDto.getName().length() > 200) {
            errors.rejectValue("name","null","Lest than 200 character");
        }

        if (productDto.getPrice() <= 0) {
            errors.rejectValue("price","null","Must greater than 0");
        }


    }
}

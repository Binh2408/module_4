package org.example.musicplayerapp.dto;

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
public class SongRequestDto implements Validator {
    private String name;
    private String artist;
    private String category;
    private String filePath;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SongRequestDto songRequestDto = (SongRequestDto) target;

        if (songRequestDto.getName().isEmpty()) {
            errors.rejectValue("name","null","Tên không được để trống");
        } else if (songRequestDto.getName().length() > 800) {
            errors.rejectValue("name","null","Tên không được quá 800 ký tự");
        } else if (songRequestDto.getName().matches("(^[a-zA-ZÀ-ỹ\\\\s]+$)")) {
            errors.rejectValue("name","null", "Tên không được có ký tự đặc biệt");
        }

        if (songRequestDto.getArtist().isEmpty()) {
            errors.rejectValue("artist","null","Nghệ sĩ không được để trống");
        } else if (songRequestDto.getArtist().length() > 300) {
            errors.rejectValue("artist","null","Tên nghệ sĩ không được quá 300 ký tự");
        } else if (songRequestDto.getArtist().matches("(^[a-zA-ZÀ-ỹ\\\\s]+$)")) {
            errors.rejectValue("artist","null","Tên nghệ sĩ không được có ký tự đặc biệt");
        }

        if (songRequestDto.getCategory().isEmpty()) {
            errors.rejectValue("category","null","Thể loại không được để trống");
        } else if (songRequestDto.getCategory().length() > 1000) {
            errors.rejectValue("category","null","Thể loại không được quá 1000 ký tự");
        } else if (songRequestDto.getCategory().matches("(^[a-zA-ZÀ-ỹ0-9,\\\\s]+$)")) {
            errors.rejectValue("category","null","Thể loại không được có ký tự đặc biệt");
        }
    }
}

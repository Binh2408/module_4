package org.example.timezone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {
    @GetMapping("/world-clock")
    public String getTimeByTimezone(ModelMap modelMap, @RequestParam(name = "city",required = false,defaultValue = "Asia/Ho_Chi_Minh") String city) {

        //Lấy thời gian hiện tại
        Date date = new Date();
        //lấy ra time zone hiện tại
        TimeZone local = TimeZone.getDefault();
        //Lấy ra time zone của 1 thành phố cụ thể
        TimeZone locale = TimeZone.getTimeZone(city);
        //TÍnh thời gian hiện tại của 1 thành phố cụ thể
        long localTime = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        //Cài đặt lại thời gian cho biến date thành thời gian hiện tại của 1 thành phố cụ thể
        date.setTime(localTime);
        //chuyển dữ liệu và gửi qua view
        modelMap.addAttribute("city",city);
        modelMap.addAttribute("date",date);

        return "index";
    }
}

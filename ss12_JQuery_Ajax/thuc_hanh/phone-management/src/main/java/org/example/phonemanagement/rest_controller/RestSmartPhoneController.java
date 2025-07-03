package org.example.phonemanagement.rest_controller;

import org.example.phonemanagement.model.SmartPhone;
import org.example.phonemanagement.service.SmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/smartphones")
public class RestSmartPhoneController {
    private final SmartPhoneService smartPhoneService;

    @Autowired
    public RestSmartPhoneController(SmartPhoneService smartPhoneService) {
        this.smartPhoneService = smartPhoneService;
    }

    @GetMapping("")
    public ResponseEntity<List<SmartPhone>> findAll() {
        List<SmartPhone> smartPhoneList = smartPhoneService.findAll();
        if (smartPhoneList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(smartPhoneList,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SmartPhone> save(@RequestBody SmartPhone smartPhone) {
        smartPhoneService.save(smartPhone);
        return new ResponseEntity<>(smartPhone,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SmartPhone> delete(@PathVariable Long id) {
        SmartPhone smartPhone = smartPhoneService.findById(id);
        if (smartPhone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.remove(id);
        return new ResponseEntity<>(smartPhone,HttpStatus.OK);
    }
}

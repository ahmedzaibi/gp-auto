package com.example.demo.Controller;

import com.example.demo.Service.FormService;
import com.example.demo.dtos.stepValuesDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
@AllArgsConstructor

public class FormController {
    private final FormService formService ;
    @PostMapping("/")
    public ResponseEntity<String> createFormTables(
           @RequestBody stepValuesDTO stepValuesDTO) {


        formService.createTable(stepValuesDTO);

        return ResponseEntity.ok(" Table Created " );
    }
}

package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aurora/v1/test")
public class TestController {

    @GetMapping(path = "/{number}")
    public GenericResponse<String> testMapping(@PathVariable("number") String number){
        return new GenericResponse<>(number);
    }

}

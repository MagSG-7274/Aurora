package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.PiHoleCotrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("aurora/v1/blocking")
public class PiHoleController {

    @Autowired
    private PiHoleCotrollService piHoleCotrollService;

    @GetMapping(path = "/disable")
    public GenericResponse<String> disableBlocking() {

        return piHoleCotrollService.disableBlocking();

    }



    @GetMapping("/enable")
    public GenericResponse<String> enableBlocking() {

        return piHoleCotrollService.enableBlockingService();

    }
}

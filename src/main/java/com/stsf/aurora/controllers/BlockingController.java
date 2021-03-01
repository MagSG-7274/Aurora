package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.AdblockService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("aurora/adblock")
public class BlockingController {


    private final AdblockService adblockService = new AdblockService();

    @GetMapping(path = "/disable")
    public GenericResponse<String> disableBlocking() {

        return adblockService.disableBlocking();

    }



    @GetMapping("/enable")
    public GenericResponse<String> enableBlocking() {

        return adblockService.enableBlockingService();

    }
}

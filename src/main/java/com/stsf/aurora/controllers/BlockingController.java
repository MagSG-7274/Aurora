package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.BlockingService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/aurora/blocking")
public class BlockingController {


    private final BlockingService blockingService = new BlockingService();

    @GetMapping(path = "/disable")
    public GenericResponse<String> disableBlocking() {

        return blockingService.disableBlocking();

    }



    @GetMapping("/enable")
    public GenericResponse<String> enableBlocking() {

        return blockingService.enableBlockingService();

    }



    @GetMapping("/active")
    public GenericResponse<Boolean> isActive() {

        return blockingService.getIfBlockingIsActive();

    }
}

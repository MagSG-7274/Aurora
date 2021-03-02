package com.stsf.aurora.controllers;

import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.WhitelistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aurora/whitelist")
public class WhitelistController {

    private final WhitelistService whitelistService = new WhitelistService();

    @PostMapping("/add")
    public GenericResponse<String> addWhitelist(@RequestBody String domain) {
        return new GenericResponse<>(whitelistService.addWhitelistService(domain));
    }

    @PostMapping("/rem")
    public GenericResponse<String> removeWhitelist(@RequestBody String domain) {
        return new GenericResponse<>(whitelistService.removeWhitelistService(domain));
    }

}

package com.stsf.aurora.controllers;

import com.stsf.aurora.requestmodel.Domain;
import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.WhitelistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aurora/whitelist")
public class WhitelistController {

    private final WhitelistService whitelistService = new WhitelistService();

    @PostMapping("/add")
    public GenericResponse<String> addWhitelist(@RequestBody Domain domain) {
        return new GenericResponse<>(whitelistService.addWhitelistService(domain));
    }

    @PostMapping("/rem")
    public GenericResponse<String> removeWhitelist(@RequestBody Domain domain) {
        return new GenericResponse<>(whitelistService.removeWhitelistService(domain));
    }

    @GetMapping("/exists")
    public GenericResponse<Boolean> getIfDomainAlreadyInWhitelist(@RequestBody Domain domain) {

        boolean isInWhitelist = whitelistService.getIfDomainAlreadyInWhitelistService(domain);
        return new GenericResponse<>(isInWhitelist);

    }

}

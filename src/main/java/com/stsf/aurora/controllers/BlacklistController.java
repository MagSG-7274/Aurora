package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.Domain;
import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.BlacklistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aurora/blacklist")
public class BlacklistController {

    private final BlacklistService blacklistService = new BlacklistService();

    @PostMapping("/add")
    public GenericResponse<String> addBlacklist(@RequestBody Domain domain) {
        return new GenericResponse<>(blacklistService.addBlacklistService(domain.getDomain()));
    }

    @PostMapping("/rem")
    public GenericResponse<String> removeBlackList(@RequestBody Domain domain) {
        return new GenericResponse<>(blacklistService.removeBlacklistService(domain.getDomain()));
    }

    @GetMapping("/exists")
    public GenericResponse<Boolean> getIfDomainAlreadyInBlacklist(@RequestBody Domain domain) {

        boolean isInBlacklist = blacklistService.getIfDomainAlreadyInBlacklistService(domain.getDomain());
        return new GenericResponse<>(isInBlacklist);

    }

}

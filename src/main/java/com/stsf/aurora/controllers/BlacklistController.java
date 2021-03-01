package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.BlacklistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aurora/blocking")
public class BlacklistController {

    private final BlacklistService blacklistService = new BlacklistService();

    @GetMapping("/black/add")
    public GenericResponse<String> addBlacklist(@RequestBody String domain) {
        return blacklistService.addBlacklistService(domain);
    }

    @GetMapping("/black/rem")
    public GenericResponse<String> removeBlackList(@RequestBody String domain) {
        return blacklistService.removeBlacklistService(domain);
    }

}

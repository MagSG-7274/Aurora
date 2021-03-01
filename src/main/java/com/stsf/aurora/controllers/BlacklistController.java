package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.GenericResponse;
import com.stsf.aurora.services.BlacklistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aurora/blacklist")
public class BlacklistController {

    private final BlacklistService blacklistService = new BlacklistService();

    @PostMapping("/add")
    public GenericResponse<String> addBlacklist(@RequestBody String domain) {
        return blacklistService.addBlacklistService(domain);
    }

    @PostMapping("/rem")
    public GenericResponse<String> removeBlackList(@RequestBody String domain) {
        return blacklistService.removeBlacklistService(domain);
    }

}

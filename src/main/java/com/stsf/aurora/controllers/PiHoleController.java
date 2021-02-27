package com.stsf.aurora.controllers;


import com.stsf.aurora.requestmodel.GenericResponse;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@RestController
@RequestMapping("aurora/v1/blocking")
public class PiHoleController {

    @GetMapping(path = "/disable/{disableForSeconds}")
    public GenericResponse<String> disableBlocking(@PathVariable("disableForSeconds") Integer timeout){



        try {


            System.out.println("[DEBUG] Blocking Request Received");
            Process p = Runtime.getRuntime().exec("pihole disable");


            BufferedReader commandOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = commandOutput.readLine()) != null) {

                if (line.toLowerCase(Locale.ROOT).contains("disabled")) {
                    System.out.println("[DEBUG] Disabled Blocking Successfully");
                    return new GenericResponse<>("Blocking disabled for" + timeout);
                }

            }

            p.waitFor();
            p.destroy();


        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to execute command");
            e.printStackTrace();
        }

        try {

            Thread.sleep(timeout * 1000);

        } catch (InterruptedException e) {
            System.out.println("[INFO] Thread sleeping Interrupted");
            e.printStackTrace();
        }

        try {

            Process p = Runtime.getRuntime().exec("pihole enable");


            BufferedReader commandOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = commandOutput.readLine()) != null) {

                if (line.toLowerCase(Locale.ROOT).contains("enabled")) {
                    System.out.println("[DEBUG] Reenabled Blocking Successfully");
                }

            }

            p.waitFor();
            p.destroy();

        } catch (InterruptedException | IOException e) {
            System.out.println("Unable to execute command");
            e.printStackTrace();
        }

        return new GenericResponse<>("Command Executed Successfully");
    }

}

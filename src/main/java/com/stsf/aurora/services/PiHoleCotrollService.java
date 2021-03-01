package com.stsf.aurora.services;

import com.stsf.aurora.requestmodel.GenericResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class PiHoleCotrollService {

    public GenericResponse<String> enableBlockingService() {

        try {


            System.out.println("[DEBUG] Blocking Request Received");
            Process p = Runtime.getRuntime().exec("pihole enable");


            BufferedReader commandOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = commandOutput.readLine()) != null) {

                if (line.toLowerCase(Locale.ROOT).contains("enabled")) {
                    System.out.println("[DEBUG] Blocking Enabled Successfully");
                    return new GenericResponse<>("Blocking enabled");
                }

            }

            p.waitFor();
            p.destroy();


        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to execute command");
            e.printStackTrace();
        }

        return new GenericResponse<>("Unable to enable blocking");

    }

    public GenericResponse<String> disableBlocking() {

        try {


            System.out.println("[DEBUG] Blocking Request Received");
            Process p = Runtime.getRuntime().exec("pihole disable");


            BufferedReader commandOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = commandOutput.readLine()) != null) {

                if (line.toLowerCase(Locale.ROOT).contains("disabled")) {
                    System.out.println("[DEBUG] Disabled Blocking Successfully");
                    return new GenericResponse<>("Blocking disabled");
                }

            }

            p.waitFor();
            p.destroy();


        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to execute command");
            e.printStackTrace();
        }

        return new GenericResponse<>("Unable to disable blocking");

    }


}

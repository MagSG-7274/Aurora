package com.stsf.aurora.services;

import com.stsf.aurora.requestmodel.GenericResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@Service
public class BlockingService {


    public GenericResponse<String> enableBlockingService() {

        try {

            System.out.println("[DEBUG] Blocking Enable Request Received");
            String command = "pihole enable";
            Process p = Runtime.getRuntime().exec(command);


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


            System.out.println("[DEBUG] Blocking Disable Request Received");
            String command = "pihole disable";
            Process p = Runtime.getRuntime().exec(command);


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

    public GenericResponse<Boolean> getIfBlockingIsActive() {

        try {

            System.out.println("[DEBUG] Blocking Check Request Received");
            String command = "pihole status";
            Process p = Runtime.getRuntime().exec(command);


            BufferedReader commandOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = commandOutput.readLine()) != null) {

                if (line.toLowerCase(Locale.ROOT).contains("enabled")) {

                    return new GenericResponse<>(true);

                } else if (line.toLowerCase(Locale.ROOT).contains("disabled")) {

                    return new GenericResponse<>(false);

                }

            }

            p.waitFor();
            p.destroy();


        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to execute command");
            e.printStackTrace();
        }
        return new GenericResponse<>(false);
    }


}

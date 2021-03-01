package com.stsf.aurora.services;

import com.stsf.aurora.requestmodel.GenericResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@Service
public class BlacklistService {

    public GenericResponse<String> addBlacklistService(String domain) {

        try {


            System.out.println("[DEBUG] Blacklist add request received");
            String command = "pihole -b " + domain;

            Process p = Runtime.getRuntime().exec(command);


            BufferedReader commandOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = commandOutput.readLine()) != null) {

                if (line.toLowerCase(Locale.ROOT).contains("adding")) {

                    System.out.println("[DEBUG] Added to blacklist successfully");
                    return new GenericResponse<>("Added to blacklist: " + domain);

                } else if (line.toLowerCase(Locale.ROOT).contains("already exists")) {

                    System.out.println("[DEBUG] Blocklist already contains domain: " + domain);
                    return new GenericResponse<>("[DEBUG] Blocklist already contains domain: " + domain);

                }

            }

            p.waitFor();
            p.destroy();


        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to execute command");
            e.printStackTrace();
            return new GenericResponse<>("Unable to add domain from blacklist");
        }

        return new GenericResponse<>("Unable to add domain from blacklist");

    }

    public GenericResponse<String> removeBlacklistService(String domain) {

        try {


            System.out.println("[DEBUG] Blacklist remove request received");
            String command = "pihole -b -d " + domain;

            Process p = Runtime.getRuntime().exec(command);


            BufferedReader commandOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = commandOutput.readLine()) != null) {

                if (line.toLowerCase(Locale.ROOT).contains("removing")) {

                    System.out.println("[DEBUG] Removed from blacklist successfully");
                    return new GenericResponse<>("Removed from blacklist");

                } else if (line.toLowerCase(Locale.ROOT).contains("does not exists")) {

                    System.out.println("[DEBUG] Blacklist doesn't contain domain: " + domain);
                    return new GenericResponse<>("Blacklist doesn't contain domain: " + domain);

                }

            }

            p.waitFor();
            p.destroy();


        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to execute command");
            e.printStackTrace();
            return new GenericResponse<>("Unable to remove from blacklist");
        }

        return new GenericResponse<>("Unable to remove from blacklist");

    }
}

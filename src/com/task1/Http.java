package com.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Http {
    String request(String urlString){
        BufferedReader br =null;
        HttpURLConnection conn =null;
        try{
            URL reqURL = new URL(urlString);
            conn = (HttpURLConnection) reqURL.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();

            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);

            br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString().substring(2);
        }catch (Exception e1){
            e1.printStackTrace();
            System.out.print("Request Failed,PLEASE check the network!\n" +
                    "*****ATTENTION!*****\n" +
                    "Without Network you can't login or register!");
            return "failed\n";
        }finally {
            if(conn!=null){
                conn.disconnect();
            }
            if(br!=null){
                try{
                    br.close();
                }catch (IOException e2){
                    e2.printStackTrace();
                }
            }
        }
    }
}

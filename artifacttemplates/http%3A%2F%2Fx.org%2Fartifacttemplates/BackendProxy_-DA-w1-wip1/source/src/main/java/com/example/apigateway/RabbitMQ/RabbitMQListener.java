package com.example.apigateway.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RabbitMQListener {
    @Value("${spring.rabbitmq.url}")
    private String backendURL;

    @RabbitListener(queues = "RequestQueue")
    public String receive(Request message) throws IOException {
        String route = message.getRoute();
        String body = message.getBody();
        String authToken = message.getAuthorizationToken();
        URL url = new URL(backendURL + "/" + route);
        System.out.println(" [x] Received " + message.getType() + " request: " + url + " with body " + body);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(message.getType());
        con.setDoOutput(true);
        if (authToken != null) {
            System.err.println(authToken);
            con.setRequestProperty("Authorization", authToken);
        }
        int responseCode = HttpURLConnection.HTTP_OK;
        if (message.getType().equals("POST") || message.getType().equals("PUT") ) {
            con.setRequestProperty("Content-Type", "application/json");
            OutputStream os = con.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();
            responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
        }
        //con.connect();
        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response);
        } else {
            System.out.println("POST request not worked");
        }
        con.disconnect();

        return response.toString();
    }

}

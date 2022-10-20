package com.example.apigateway.RabbitMQ;

import java.io.Serializable;

public class Request implements Serializable {
    private final String route;
    private final String type;
    private final String body;
    private final String authorizationToken;

    public Request(String type, String route, String body, String authorizationToken) {
        this.type = type;
        this.route = route;
        this.body = body;
        this.authorizationToken = authorizationToken;
    }

    public String getRoute() {
        return route;
    }

    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}

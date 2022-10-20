package com.example.apigateway.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate template;

    @GetMapping(value = "/{path}")
    @CrossOrigin
    public String sendGET(
            @PathVariable String path,
            @RequestParam(name = "limit", required = false) String limit,
            @RequestParam(name = "offset", required = false) String offset,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "favorited", required = false) String favorited,
            @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path;
        if (limit != null)
            route += "?limit=" + limit;
        if (offset != null)
            route += "&offset=" + offset;
        if (author != null)
            route += "&author=" + author;
        if (favorited != null)
            route += "&favorited=" + favorited;
        System.out.println(" [x] Send GET Request: " + route);
        Request request = new Request("GET", route, null, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @GetMapping(value = "/{path}/{second}")
    @CrossOrigin
    public String sendGET2(@PathVariable String path,
                           @PathVariable String second,
                           @RequestParam(name = "limit", required = false) String limit,
                           @RequestParam(name = "offset", required = false) String offset,
                           @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path + "/" + second;
        if (limit != null)
            route += "?limit=" + limit;
        if (offset != null)
            route += "&offset=" + offset;
        System.out.println(" [x] Send GET Request: " + route);
        Request request = new Request("GET", route, null, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @GetMapping(value = "/{path}/{second}/{third}")
    @CrossOrigin
    public String sendGET3(@PathVariable String path,
                           @PathVariable String second,
                           @PathVariable String third,
                           @RequestParam(name = "limit", required = false) String limit,
                           @RequestParam(name = "offset", required = false) String offset,
                           @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path + "/" + second + "/" + third;
        if (limit != null)
            route += "?limit=" + limit;
        if (offset != null)
            route += "&offset=" + offset;
        System.out.println(" [x] Send GET Request: " + route);
        Request request = new Request("GET", route, null, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @PostMapping(value = "/{path}")
    @CrossOrigin
    public String sendPOST(@RequestBody String payload,
                           @PathVariable String path,
                           @RequestHeader(name = "Authorization", required = false) String auth) {
        System.out.println(" [x] Send POST Request with route '" + path + "' and payload: " + payload);
        Request request = new Request("POST", path, payload, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @PostMapping(value = "/{path}/{second}")
    @CrossOrigin
    public String sendPOST2(@RequestBody String payload,
                            @PathVariable String path,
                            @PathVariable String second,
                            @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path + "/" + second;
        System.out.println(" [x] Send POST Request with route '" + route + "' and payload: " + payload);
        Request request = new Request("POST", route, payload, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @PostMapping(value = "/{path}/{second}/{third}")
    @CrossOrigin
    public String sendPOST3(@RequestBody String payload,
                            @PathVariable String path,
                            @PathVariable String second,
                            @PathVariable String third,
                            @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path + "/" + second + "/" + third;
        System.out.println(" [x] Send POST Request with route '" + route + "' and payload: " + payload);
        Request request = new Request("POST", route, payload, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @RequestMapping(value = "/{path}", method = RequestMethod.PUT)
    @CrossOrigin
    public String sendPUT(@RequestBody String payload,
                          @PathVariable String path,
                          @RequestHeader(name = "Authorization", required = false) String auth) {
        System.out.println(" [x] Send PUT Request with route '" + path + "' and payload: " + payload);
        Request request = new Request("PUT", path, payload, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @RequestMapping(value = "/{path}/{second}", method = RequestMethod.PUT)
    @CrossOrigin
    public String sendPUT2(@RequestBody String payload,
                           @PathVariable String path,
                           @PathVariable String second,
                           @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path + "/" + second;
        System.out.println(" [x] Send PUT Request with route '" + route + "' and payload: " + payload);
        Request request = new Request("PUT", route, payload, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @RequestMapping(value = "/{path}/{second}", method = RequestMethod.DELETE)
    @CrossOrigin
    public String sendDELETE2(@PathVariable String path,
                              @PathVariable String second,
                              @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path + "/" + second;
        System.out.println(" [x] Send DELETE Request with route '" + route + "'");
        Request request = new Request("DELETE", route, null, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }

    @RequestMapping(value = "/{path}/{second}/{third}", method = RequestMethod.DELETE)
    @CrossOrigin
    public String sendDELETE3(@PathVariable String path,
                              @PathVariable String second,
                              @PathVariable String third,
                              @RequestHeader(name = "Authorization", required = false) String auth) {
        String route = path + "/" + second + "/" + third;
        System.out.println(" [x] Send DELETE Request with route '" + route + "'");
        Request request = new Request("DELETE", route, null, auth);
        String response = (String) template.convertSendAndReceive(RabbitMQConfig.exchange, RabbitMQConfig.routingKey, request);
        System.out.println(" [.] Response: " + response);
        return response;
    }
}

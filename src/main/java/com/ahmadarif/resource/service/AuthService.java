package com.ahmadarif.resource.service;

import com.ahmadarif.resource.util.RestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ARIF on 24-Feb-17.
 */
@Service
public class AuthService {

    @Value("${oauth2.urlTokenEndPoint}")
    private String checkTokenUrl;

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.clientSecret}")
    private String clientSecret;

    public Object checkToken(@RequestParam String access_token) {
        RestUtil<Object> restUtil;
        ResponseEntity<Object> response;
        MultiValueMap<String, Object> params;
        HttpHeaders headers;

        // define rest
        restUtil = new RestUtil<>();

        // set headers
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", restUtil.basicAuth(clientId, clientSecret));

        // set params
        params = new LinkedMultiValueMap<>();
        params.add("token", access_token);

        response = restUtil.post(checkTokenUrl, headers, params, Object.class);
        return response.getBody();
    }

}
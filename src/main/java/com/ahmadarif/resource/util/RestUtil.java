package com.ahmadarif.resource.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ARIF on 24-Nov-16.
 */
@Component
public class RestUtil<T> {

    public ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }

    }

    public ResponseEntity<T> get(String url, HttpHeaders headers, MultiValueMap<String, Object> params, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(headers);

        // convert map to url
        url = multiValueMapToUrl(url, params);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }
    }

    public ResponseEntity<T> get(String url, HttpHeaders headers, Map<String, Object> params, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(headers);

        // convert map to url
        url = mapToUrl(url, params);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }

//        restTemplate.exchange(url, HttpMethod.GET, request, type);
//        return response;
    }

    public ResponseEntity<T> post(String url, HttpHeaders headers, MultiValueMap<String, Object> params, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(params, headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.POST, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }
    }

    public ResponseEntity<T> post(String url, HttpHeaders headers, Map<String, Object> params, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(params, headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.POST, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }
    }

    public ResponseEntity<T> put(String url, HttpHeaders headers, MultiValueMap<String, Object> params, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(params, headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.PUT, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }
    }

    public ResponseEntity<T> delete(String url, HttpHeaders headers, MultiValueMap<String, Object> params, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(params, headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.DELETE, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }
    }

    public ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request;
        ResponseEntity response = null;
        request = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.DELETE, request, type);
        } catch (HttpClientErrorException e) {
            Map map;
            try {
                map = new ObjectMapper().readValue(e.getResponseBodyAsString(), HashMap.class);
                response = new ResponseEntity<>(map, e.getStatusCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            return response;
        }
    }

    public String basicAuth(String clientId, String clientSecret) {
        byte[] authByte = Base64.encodeBase64((clientId + ":" + clientSecret).getBytes());
        String authStr = new String(authByte);
        return "Basic " + authStr;
    }

    /**
     * Konversi parameter (Map) menjadi URL
     * @param url
     * @param params
     * @return
     */
    private String mapToUrl(String url, Map<String, Object> params) {
        url += "?";
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                url += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        url = url.substring(0, url.length() - 1);
        return url;
    }

    /**
     * Konversi parameter (MultiValueMap) menjadi URL
     * @param url
     * @param params
     * @return URL
     */
    private String multiValueMapToUrl(String url, MultiValueMap<String, Object> params) {
        url += "?";
        Map<String, Object> parameters = new HashMap<>();
        Iterator<String> it = params.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            Object value = params.getFirst(key);
            if (value != null) {
                parameters.put(key, value);
            }
        }
        url = url.substring(0, url.length() - 1);
        return url;
    }

}
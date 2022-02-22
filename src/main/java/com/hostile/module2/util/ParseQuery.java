package com.hostile.module2.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ParseQuery {

    public static Map<String, Object> parse(String s) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (s != null) {
            Arrays.stream(s.split(";")).forEach(item -> {
                String[] params = item.split(",");
                if (params.length == 2) {
                    map.put(params[0], params[1]);
                }
                else map.put("query", s);
            });
        }
        return map;
    }

    public static Map<String, String> getRequestParams(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameterMap().entrySet().stream()
                .collect(Collectors.toMap(
                        p -> p.getKey(),
                        p -> p.getValue() != null && p.getValue().length > 0 ? p.getValue()[0] : ""));
    }
}

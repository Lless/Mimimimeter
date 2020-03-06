package com.github.lless.mimimimeter.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CookieConverter implements Converter<String, List<Long>> {
    @Value("${cookie.delimiter.regexp}")
    private String regexpDelimiter;

    @Value("${cookie.delimiter}")
    private String cookieDelimiter;

    @Override
    public List<Long> convert(String s) {
        if (s.isEmpty()) return new ArrayList<>();
        return  Stream.of(s.split(regexpDelimiter)).map(Long::valueOf).collect(Collectors.toList());
    }

    public String convert(List<Long> list) {
        if (list.isEmpty()) return "";
        return list.stream().map(String::valueOf).collect(Collectors.joining(cookieDelimiter));
    }
}

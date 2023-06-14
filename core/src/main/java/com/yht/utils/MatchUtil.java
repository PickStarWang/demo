package com.yht.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class MatchUtil {


    public static String match(String str) {
        Pattern pattern = Pattern.compile("^等级(.*)门诊$" );
        Matcher matcher = pattern.matcher(str);

        String group = matcher.group();
        System.out.println(group);
        return group;
    }
}

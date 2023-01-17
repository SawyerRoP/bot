package com.rdcall.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class GetCall {
    @GetMapping("/welcome")
    public LocalDateTime hello() {
        LocalDateTime now = LocalDateTime.now();
        return  now.plusHours(7);
    }
    @PostMapping("/post")
    public void handleTexMessagePush(@RequestBody String post) throws IOException {


        //System.out.println(call);


        String decode = URLDecoder.decode(post, StandardCharsets.UTF_8.name());




        Jsoup.connect("https://notify-api.line.me/api/notify").header("Content-Type","application/x-www-form-urlencoded").header("Authorization","Bearer ").ignoreContentType(true).timeout(6000).data("message",decode).post();

    }
}

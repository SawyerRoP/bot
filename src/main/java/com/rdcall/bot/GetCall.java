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
        Document countCall = Jsoup.connect("https://script.google.com/macros/s/AKfycbyA4aMWncFv-rD2zscnty1Qh6WL4qK2GwflKrudP4TtxgUxyT1M0srNBCIVc2dQFF2YOw/exec?action=getCall").timeout(8000).ignoreContentType(true).get();


        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(countCall.text());
        String call = "";
        while (matcher.find()) {
            call += matcher.group();

        }
        //System.out.println(call);
        int oldCall = Integer.parseInt(call.substring(0, call.length() - 1));


        String decode = URLDecoder.decode(post, StandardCharsets.UTF_8.name());

        String[] send = decode.split(" ");



        Jsoup.connect("https://notify-api.line.me/api/notify").header("Content-Type","application/x-www-form-urlencoded").header("Authorization","Bearer ").ignoreContentType(true).timeout(6000).data("message",send[0]).post();

    }
}

package com.rdcall.bot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
@RestController
public class GetCall {
    @GetMapping("/welcome")
    public LocalDateTime hello() {
        LocalDateTime now = LocalDateTime.now();
        return  now.plusHours(7);
    }
    @PostMapping("/post")
    public String handleTexMessagePush(@RequestBody String post) throws IOException {
        String decode = URLDecoder.decode(post, StandardCharsets.UTF_8.name());
        Jsoup.connect("https://notify-api.line.me/api/notify").header("Content-Type","application/x-www-form-urlencoded").header("Authorization","Bearer 22Zv29T1eE10NPibcQfDsXP7toTE1KPGnZ15K7AondU").ignoreContentType(true).timeout(6000).data("",decode).post();
        return post;
    }
}

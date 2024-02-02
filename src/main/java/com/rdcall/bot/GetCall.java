package com.rdcall.bot;

import org.apache.juli.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class GetCall {
    private final Logger log = LoggerFactory.getLogger(GetCall.class);
    @Autowired
    private CallRespository callRespository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/welcome")

    public LocalDateTime hello() {
        LocalDateTime now = LocalDateTime.now();

        List<Call> calls = callRespository.findAll();
        log.info(calls.toString());
        return  now.plusHours(7);
    }
    @PostMapping("/post")
    public void handleTexMessagePush(@RequestBody String post) throws IOException {
        //Document countCall = Jsoup.connect("https://script.google.com/macros/s/AKfycbyA4aMWncFv-rD2zscnty1Qh6WL4qK2GwflKrudP4TtxgUxyT1M0srNBCIVc2dQFF2YOw/exec?action=getCall").timeout(8000).ignoreContentType(true).get();



        List<Call> calls = callRespository.findAll();


        String decode = URLDecoder.decode(post, StandardCharsets.UTF_8.name());

        String[] send = decode.split(" ");
        String countCompare = send[2];
        int check = compareStrings(calls,countCompare);

        if(check == 0) {
            jdbcTemplate.update("INSERT INTO rdcall (call) VALUES(?)",countCompare);
            log.info(countCompare);
            Jsoup.connect("https://notify-api.line.me/api/notify").header("Content-Type", "application/x-www-form-urlencoded").header("Authorization", "Bearer L8X6RrMz9WV4UVuq8HOICVqwmRWDzOrILyJt2Gda9BO").ignoreContentType(true).timeout(6000).data("message",decode.toString()).post();
            Jsoup.connect("https://notify-api.line.me/api/notify").header("Content-Type", "application/x-www-form-urlencoded").header("Authorization", "Bearer pczwLLC1jFqSjWaxOxyqcy6NghUuFpQkSTli0CEMjRr").ignoreContentType(true).timeout(6000).data("message",decode.toString()).post();
        }

            //Jsoup.connect("https://notify-api.line.me/api/notify").header("Content-Type", "application/x-www-form-urlencoded").header("Authorization", "Bearer ").ignoreContentType(true).timeout(6000).data("message",decode.toString()).post();
            //log.info("[+]Check call ====>> " + calls.toString());



    }
    public int compareStrings(List<Call>databaseStrings, String parameter) {
        for (Call s : databaseStrings) {
            if (Arrays.asList(s.getCall()).contains(parameter)) {
                //System.out.println("Duplicate " + s);
                return 1;
            }
        }
        return 0;
    }

}

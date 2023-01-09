package com.rdcall.bot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 2 * * *")
    public void setZeroCallCount() throws IOException {
        String a = "0";
        Document c = Jsoup.connect("https://script.google.com/macros/s/AKfycbyA4aMWncFv-rD2zscnty1Qh6WL4qK2GwflKrudP4TtxgUxyT1M0srNBCIVc2dQFF2YOw/exec?").data("action","update").data("name",a).ignoreContentType(true).get();
    }
}
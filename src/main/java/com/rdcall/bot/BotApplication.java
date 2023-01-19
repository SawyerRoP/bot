package com.rdcall.bot;

import org.jsoup.Jsoup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@LineMessageHandler
@EnableScheduling
public class BotApplication {

	private final Logger log = LoggerFactory.getLogger(BotApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BotApplication.class, args);
	}

	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		log.info("event: " + event);
		final String originalMessageText = event.getMessage().getText();
		String recheck = event.getMessage().getText();
		try {
			Jsoup.connect("https://script.google.com/macros/s/AKfycbyA4aMWncFv-rD2zscnty1Qh6WL4qK2GwflKrudP4TtxgUxyT1M0srNBCIVc2dQFF2YOw/exec?").timeout(8000).data("action", "update").data("name", recheck).ignoreContentType(true).get();
			log.info("[+]Repost call....");
		}catch (Exception e) {
			log.info("[+]Cannot recheck call....");
			System.exit(0);
		}
		//final String originalMessageText = event.getSource().getSenderId();
		return new TextMessage(originalMessageText);
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}

}

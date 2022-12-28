package com.rdcall.bot;

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
		//final String originalMessageText = event.getSource().getSenderId();
		return new TextMessage(originalMessageText);
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}

}

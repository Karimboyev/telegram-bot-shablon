package com.example.javaholicsbot.util;

import com.example.javaholicsbot.domain.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TelegramUtil {

    public static SendMessage createMessageTemplate(User user) {
        return createMessageTemplate(String.valueOf(user.getChatId()));
    }

    public static SendMessage createMessageTemplate(String chatId) {
        return new SendMessage()
                .setChatId(chatId)
                .enableMarkdown(true);
    }
}

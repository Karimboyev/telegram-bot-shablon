package com.example.javaholicsbot.hendler;

import com.example.javaholicsbot.domain.User;
import com.example.javaholicsbot.enums.State;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;

import java.io.Serializable;
import java.util.List;

@Component
public interface Handler {

    List<PartialBotApiMethod<? extends Serializable>> handle(User user, String message, Contact contact);

    State operatedBotState();

    List<String> operatedCallBackQuery();
}

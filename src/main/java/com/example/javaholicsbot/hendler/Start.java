package com.example.javaholicsbot.hendler;

import com.example.javaholicsbot.domain.User;
import com.example.javaholicsbot.enums.State;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;

import java.io.Serializable;
import java.util.List;

@Component
public class Start implements Handler{

    @Override
    public List<PartialBotApiMethod<? extends Serializable>> handle(User user, String message, Contact contact) {
        return null;
    }

    @Override
    public State operatedBotState() {
        return null;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return null;
    }
}

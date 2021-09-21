package com.example.javaholicsbot.bot;

import com.example.javaholicsbot.domain.User;
import com.example.javaholicsbot.enums.State;
import com.example.javaholicsbot.hendler.Handler;
import com.example.javaholicsbot.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Component
public class UpdateReceiver {

    private final List<Handler> handlers;

    private final UserRepository userRepository;

    public UpdateReceiver(List<Handler> handlers, UserRepository userRepository) {
        this.handlers = handlers;
        this.userRepository = userRepository;
    }

    public List<PartialBotApiMethod<? extends Serializable>> handle(Update update) {

        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                final Message message = update.getMessage();
                final Long chatId = Long.parseLong(message.getFrom().getId().toString());

                final User user = userRepository.getByChatId(chatId)
                        .orElseGet(() -> userRepository.save(new User(chatId)));

                if (message.getText().equals("/start")) {
                    user.setBotState(State.START);
                }

                return getHandlerByState(user.getBotState()).handle(user, message.getText(), null);

            } else if (update.hasCallbackQuery()) {
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                final Long chatId = Long.parseLong(callbackQuery.getFrom().getId().toString());
                final User user = userRepository.getByChatId(chatId)
                        .orElseGet(() -> userRepository.save(new User(chatId)));
            } else if(update.hasMessage() && update.getMessage().hasContact()){
                return null;
            }

            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        }
    }

    private Handler getHandlerByState(State state) {
        return handlers.stream()
                .filter(h -> h.operatedBotState() != null)
                .filter(h -> h.operatedBotState().equals(state))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }

    private Handler getHandlerByCallBackQuery(String query) {
        return handlers.stream()
                .filter(h -> h.operatedCallBackQuery().stream()
                        .anyMatch(query::startsWith))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }
}

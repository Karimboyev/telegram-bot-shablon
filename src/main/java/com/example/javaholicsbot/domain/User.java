package com.example.javaholicsbot.domain;

import com.example.javaholicsbot.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "chat_id")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractBaseEntity{

    @Column(name = "chat_id")
    private Long chatId;

    private String name;

    private State botState;

    private String phoneNumber;

    private String specialization;


    public User(Long chatId) {
        this.chatId = chatId;
        this.botState = State.START;
    }

}
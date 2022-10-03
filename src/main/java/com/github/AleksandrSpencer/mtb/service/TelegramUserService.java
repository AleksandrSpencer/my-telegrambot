package com.github.AleksandrSpencer.mtb.service;
import com.github.AleksandrSpencer.mtb.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;


public interface TelegramUserService {

    void save(TelegramUser telegramUser);
    List<TelegramUser> retrieveAllActiveUsers();
    Optional<TelegramUser> findByChatId(String chatId);
}

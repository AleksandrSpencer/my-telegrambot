package com.github.AleksandrSpencer.mtb.service;

import java.util.List;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
    void sendMessage(String chatId, List<String> message);
}

package com.github.AleksandrSpencer.mtb.service;

import com.github.AleksandrSpencer.mtb.bot.MyTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{

    private final MyTelegramBot myTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(MyTelegramBot myTelegramBot){
        this.myTelegramBot =  myTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message){
        if (isBlank(message)) return;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try{
            myTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e){
            //todo add logging to the project.
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String chatId, List<String> messages) {
        if (isEmpty(messages)) return;

        messages.forEach(m -> sendMessage(chatId, m));
    }

}
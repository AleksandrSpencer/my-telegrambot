package com.github.AleksandrSpencer.mtb.service;

import com.github.AleksandrSpencer.mtb.bot.MyTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{

    private final MyTelegramBot myTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(MyTelegramBot myTelegramBot){
        this.myTelegramBot =  myTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message){
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

}
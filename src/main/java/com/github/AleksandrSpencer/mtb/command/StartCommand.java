package com.github.AleksandrSpencer.mtb.command;

import com.github.AleksandrSpencer.mtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    public final static String START_MESSAGE = "Привет! Я Spencer - Telegram Bot. Я помогу тебе быть в курсе последних " +
            "статей тех авторов, котрые тебе интересны. Я еще маленький и только учусь.";
    // здесь не добавляем сервис через получение из Application Context, т.к. если это сделать так,
    // то будет циклическая зависимость,  которая ломает работу приложения.
    public StartCommand (SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public  void execute(Update update){
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}


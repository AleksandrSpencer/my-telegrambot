package com.github.AleksandrSpencer.mtb.command;

import com.github.AleksandrSpencer.mtb.repository.entity.TelegramUser;
import com.github.AleksandrSpencer.mtb.service.SendBotMessageService;
import com.github.AleksandrSpencer.mtb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    public final static String START_MESSAGE = "Привет! Я Spencer - Telegram Bot. Я помогу тебе быть в курсе последних " +
            "статей тех авторов, котрые тебе интересны. Я еще маленький и только учусь.";
    // здесь не добавляем сервис через получение из Application Context, т.к. если это сделать,
    // то будет циклическая зависимость,  которая ломает мне тут всю работу приложения.
    public StartCommand (SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService){
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }
    @Override
    public  void execute(Update update){
        String chatId = update.getMessage().getChatId().toString();
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(chatId, START_MESSAGE);


    }
}
package com.github.AleksandrSpencer.mtb.command;

import com.github.AleksandrSpencer.mtb.bot.MyTelegramBot;
import com.github.AleksandrSpencer.mtb.service.SendBotMessageService;
import com.github.AleksandrSpencer.mtb.service.SendBotMessageServiceImpl;
import com.github.AleksandrSpencer.mtb.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest {

    protected MyTelegramBot myTelegramBot = Mockito.mock(MyTelegramBot.class);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(myTelegramBot);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {
        //given
        Long chatId = 1234567824356L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        //when
        getCommand().execute(update);

        //then
        Mockito.verify(myTelegramBot).execute(sendMessage);
    }
}
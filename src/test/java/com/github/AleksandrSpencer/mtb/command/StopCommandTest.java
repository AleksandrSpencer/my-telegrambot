package com.github.AleksandrSpencer.mtb.command;


import com.github.AleksandrSpencer.mtb.service.TelegramUserService;
import org.junit.jupiter.api.DisplayName;

import static com.github.AleksandrSpencer.mtb.command.CommandName.STOP;
import static com.github.AleksandrSpencer.mtb.command.StopCommand.STOP_MESSAGE;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}
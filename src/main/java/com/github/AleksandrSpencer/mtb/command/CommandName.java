package com.github.AleksandrSpencer.mtb.command;
/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    STAT("/stat"),
    ADMIN_HELP("/ahelp"),
    NO("nocommand"),
    ADD_GROUP_SUB("/addgroupsub"),
    DELETE_GROUP_SUB("/deletegroupsub"),
    LIST_GROUP_SUB("/listgroupsub");
    private final String commandName;

    CommandName(String commandName){
        this.commandName = commandName;
    }
    public String getCommandName(){
        return  commandName;
    }

}
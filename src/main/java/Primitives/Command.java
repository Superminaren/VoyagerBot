package Primitives;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Command {
    String lastMsg = "";

    //Takes the key a command starts with and checks if the message is a command
    public boolean isCommand(MessageReceivedEvent event, String cmdKey) {
        //This function could be shorter, but to introduce Ternary operators is longer intentionally
        lastMsg = event.getMessage().toString();
        return event.getMessage().toString().startsWith(cmdKey)?true:false;
    }




    public String[] getArgs(MessageReceivedEvent event,String cmdKey){
        lastMsg = event.getMessage().toString();
        if(lastMsg.length()<2){return new String[0];}
        return lastMsg.substring(1,lastMsg.length()).split(" ");
    }



}

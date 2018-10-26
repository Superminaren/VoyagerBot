
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    //Store channels based on guild ID.
    private static Map<String, IVoiceChannel> voiceChannelMap = new HashMap<>();

    private static Map<String, Runnable> commandMap = new HashMap<>();
    private static MessageReceivedEvent event;
    private static String[] args;
    //Saves event and args and runs command related stuff.
    public void checkCommand(MessageReceivedEvent event, String[] args){
        this.event = event;
        this.args = args;
        for(int i =0; i<args.length; i++){System.out.println("Arg "+i+": "+ args[i]);}
        try{
            commandMap.get(args[0].toLowerCase()).run();
        }catch (Exception e){
            event.getMessage().reply("Command not found.");
        }

    }


    public static void play(){
        //Play song given in chat

    }
    public static void stop(){
        //Stop guild music

    }
    public static void pause(){
        //Pause guild music

    }
    public static void skip(){
        //Skip guild music

    }


     static {
        commandMap.put("play", CommandHandler::play);
        commandMap.put("stop", CommandHandler::stop);
        commandMap.put("pause", CommandHandler::pause);
        commandMap.put("skip", CommandHandler::skip);
    }

}

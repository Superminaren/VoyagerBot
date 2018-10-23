
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

import java.util.HashMap;
import java.util.Map;

public class MusicHandler {
    //Store channels based on guild ID.
    private static Map<String, AudioPlayer> voiceChannelMap = new HashMap<>();

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

    public static void joinvoice(){
        IVoiceChannel userVoiceChannel = null;
        try{
            userVoiceChannel = event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();
        }catch (Exception e){}
        if(userVoiceChannel == null){
            event.getMessage().reply("You are not currently in a channel.");
            return;
        }else{
            voiceChannelMap.put(event.getGuild().getStringID(),AudioPlayer.getAudioPlayerForGuild(event.getGuild()));
            userVoiceChannel.join();
            event.getMessage().reply("Joining voice channel.");
            return;
        }

    }
    public static void leavevoice(){
        IVoiceChannel botVoiceChannel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();

        AudioPlayer audioP = AudioPlayer.getAudioPlayerForGuild(event.getGuild());
        if(botVoiceChannel==null){
            event.getMessage().reply("Not in a voice channel.");
            return;
        }else{
            botVoiceChannel.leave();
            event.getMessage().reply("Left the voice channel.");
            return;
        }

    }
    public static void play(){

    }
    public static void stop(){

    }
    public static void pause(){

    }




    private static boolean isInVoice(){
        IVoiceChannel botVoiceChannel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();

        if(botVoiceChannel == null) {
            event.getMessage().getChannel().sendMessage( "Not in a voice channel, join one and then use joinvoice");
            return false;
        }
        return true;
    }



    static {
        commandMap.put("joinvoice", MusicHandler::joinvoice);
        commandMap.put("leavevoice", MusicHandler::leavevoice);
        commandMap.put("play", MusicHandler::play);
        commandMap.put("stop", MusicHandler::stop);
        commandMap.put("pause", MusicHandler::pause);
    }

    void checkCommand(){

    }

}

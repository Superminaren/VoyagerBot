package Primitives;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.audio.AudioPlayer;

import java.util.ArrayList;

public class Guild {

    IChannel logChannel = null;    //If the log channel is null then no actions are logged
    AudioPlayer audioPlayer = null;    //If the audioplayer is null then no music is playing
    //Determines permissions for users
    ArrayList<IUser> moderators = new ArrayList<IUser>();
    ArrayList<IUser> administrators = new ArrayList<IUser>();







    /*
    If the user is a normal user this returns 0.
    If the user is a moderator this returns 1;
    If the user is admin, 2 is returned.
     */
    int checkPermissions(IUser user){
        if(moderators.contains(user)){
            return 1;
        }
        if(administrators.contains(user)){
            return 2;
        }
        return 0;
    }



}

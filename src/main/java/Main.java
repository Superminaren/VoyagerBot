import Primitives.Command;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEditEvent;
import sx.blah.discord.util.DiscordException;
//This is a comment, these make it easy to see what I want with each line of code
//TODO this is a TODO and is used to see what goals are in the program

public class Main {
    static IDiscordClient client;
    static Command command;
    static CommandHandler ch = new CommandHandler();
    static MusicHandler mh = new MusicHandler();
    static String private_key = ""; //Keyholder for the bot


    //The main function
    public static void main(String[] args){
        //TODO Get key here
        private_key = loadKey();
        //Client is created, do stuff after this line
        client = createClient(private_key,true);
        //This creates a listener that calls different functions when the condition is met in chats
        //Check the HandlerClass and all it's conditions for each function
        client.getDispatcher().registerListener(new HandlerClass());


    }





    public static class HandlerClass extends Command {
        public String cmdKey = "!"; //Key that commands start with, if it's

        //This function is called when the bot is ready to handle commands and other features
        @EventSubscriber
        public void onReadyEvent(ReadyEvent event) { // This method is called when the ReadyEvent is dispatched

        }

        //This function is called when a message is sent in a channel the bot has permission to view
        @EventSubscriber
        public void onMessageReceivedEvent(MessageReceivedEvent event) { // This method is NOT called because it doesn't have the @EventSubscriber annotation
            if(isCommand(event, cmdKey)){
                ch.checkCommand(event,getArgs(event,cmdKey));
                mh.checkCommand(event,getArgs(event,cmdKey));

            }
        }

        //This function is called when a message is edited in a channel the bot has permission to view
        @EventSubscriber
        public void onMessageEditedEvent(MessageEditEvent event) {
            //Log that
            
        }
    }


    //This creates the client and returns the instance of it
    public static IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }
    public static String loadKey(){


        return "";
    }
}

package practicarExamen.ejer6GuessANumber;

public enum Command {
    NEW("NEW","This command indicates that the client wants to start a new game. As an argument it accept the number of tries the user want to have to guess the number. Example: NEW 8"),
    NUM("NUM","The client sends its guess to the server. A new game has to be created before using this command. Example: NUM 42."),
    HELP("HELP","The client asks the server for information about the game and the commands to use."),
    QUIT("QUIT", "The client sends the request to terminate the communication with the server.");

    private final String text;
    private final String description;
    Command(String text, String description){
        this.text = text;
        this.description = description;
    }

    public String getText(){
        return text;
    }

    public String getDescription(){
        return description;
    }

    public static Command getCommandFromText(String text){
        text = text.toUpperCase(); //convirtoo a lowe case para non diferenciar mayusc

        for (int i=0;i<Command.values().length;i++){
            Command command = Command.values()[i];
            if (command.getText().equals(text)){
                return command;
            }
        }
        return null;
    }

    public static String getCommandHelpText(){
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<Command.values().length;i++){
            Command command = Command.values()[i];
            builder.append(command.text).append(": ").append(command.description).append("\n");
        }
        return builder.toString();
    }
}

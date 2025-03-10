package practicarExamen.ejer7RandomWord;

public enum Command {
    WORD("WORD");

    private final String text;

    Command(String text) {
        this.text = text;
    }

    public static Command getCommandFromText(String text){
        text = text.strip().toUpperCase();
        for (int i=0;i<Command.values().length;i++){
            Command command = Command.values()[i];
            if (command.text.equals(text)) return command;
        }
        return null;
    }
}

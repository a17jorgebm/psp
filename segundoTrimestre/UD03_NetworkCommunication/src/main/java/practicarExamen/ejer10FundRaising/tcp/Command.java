package practicarExamen.ejer10FundRaising.tcp;

public enum Command {
    ADD("ADD"),
    SHOW("SHOW"),
    QUIT("QUIT");

    private String text;

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

    public String getText() {
        return text;
    }
}

package view;

public abstract class Menu<T> {
    private T[] choices;
    private String title;

    public Menu(T[] _choices, String _title){
        choices = _choices;
        title = _title;
    }

    public int getChoice(){
        String inp;
        System.out.println("\033[33m\n" + "-".repeat(20) + " " + title + " " + "-".repeat(20));
        for (int i = 0; i < choices.length; i++)
            System.out.println((i + 1) + ". " + choices[i]);
        System.out.println("-".repeat(42 + title.length()));

        return (inp = misc.Utils.getLine("Enter choice: \033[0m", misc.Utils.validations.vInt)).isBlank() ? -1 : Integer.parseInt(inp);
    }

    public void display(){
        while (execute(getChoice()));
    }

    protected abstract boolean execute(int choice);
}
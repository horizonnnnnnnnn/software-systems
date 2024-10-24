package work1.objectOriented;

public class Main {

    public static void main(String[] args) {
        exeMain(args);
    }
    public static void exeMain(String[] args) {

        Input input = new Input();
        input.input("input.txt");
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output("output.txt");

    }
}

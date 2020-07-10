package module;

import java.util.ArrayList;

public class Minion extends Card {
    public Minion(Minion newMinion) {
        super(newMinion);
    }

    public Minion(ArrayList<String> strings, ArrayList<Integer> integers) {
        super(strings, integers);
    }
}
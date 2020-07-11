package module;

import java.util.ArrayList;

public class Quest extends Card {
    public Quest(Quest newQuest) {
        super(newQuest);
    }

    public Quest(ArrayList<String> strings, ArrayList<Integer> integers) {
        super(strings, integers);
    }
}

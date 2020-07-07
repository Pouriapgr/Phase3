package module;

import java.util.ArrayList;

public class Weapon extends Card {
    public Weapon(Weapon newWeapon) {
        super(newWeapon);
    }
    public Weapon(ArrayList<String> strings, ArrayList<Integer> integers) {
        super(strings, integers);
    }
}
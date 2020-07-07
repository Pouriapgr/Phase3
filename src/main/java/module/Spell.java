package module;

import java.util.ArrayList;

public class Spell extends Card {
	public Spell(Spell newSpell) {
		super(newSpell);
	}
	public Spell(ArrayList<String> strings, ArrayList<Integer> integers){
		super(strings, integers);
	}
}
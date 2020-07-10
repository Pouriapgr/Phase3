package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardSprint extends SpellCard {
    public CardSprint(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
//        for(int i = 0; i <= 3; i++) {
//            PlayCard playCard = PlayHandler.draw(player);
//                playHandler.addCard(player, playCard);
//        }
    }
}

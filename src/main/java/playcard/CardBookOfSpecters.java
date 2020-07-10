package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardBookOfSpecters extends SpellCard {
    public CardBookOfSpecters(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
//        for(int i = 0; i <= 2; i++) {
//            PlayCard playCard = PlayHandler.draw(player);
//            if(!(playCard instanceof SpellCard))
//                playHandler.addCard(player, playCard);
//        }
    }
}

package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardFriendlySmith extends SpellCard {
    public CardFriendlySmith(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
//        WeaponCard weaponCard = playHandler.discoverWeapon();
//        playHandler.addCard(weaponCard, playerInfo);
    }

}

package playcard;

import module.Card;
import playlogic.PlayerInfo;

public abstract class QuestCard extends PlayCard {
    protected int manaUsedFirst;

    public QuestCard(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }

    public abstract void checkProgress();

    public int getManaUsedFirst() {
        return manaUsedFirst;
    }

    public void setManaUsedFirst(int manaUsedFirst) {
        this.manaUsedFirst = manaUsedFirst;
    }
}

package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public abstract class QuestCard extends PlayCard {
    protected int manaUsedFirst;

    public QuestCard(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);
    }

    public abstract void checkProgress();

    public int getManaUsedFirst() {
        return manaUsedFirst;
    }

    public void setManaUsedFirst(int manaUsedFirst) {
        this.manaUsedFirst = manaUsedFirst;
    }
}

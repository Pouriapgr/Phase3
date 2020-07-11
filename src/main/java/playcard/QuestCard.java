package playcard;

import module.Quest;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public abstract class QuestCard extends PlayCard {
    protected int manaUsedFirst;

    public QuestCard(Quest quest, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(quest, playerInfo, playHandler, id);
    }

    public abstract void checkProgress();

    public int getManaUsedFirst() {
        return manaUsedFirst;
    }

    public void setManaUsedFirst(int manaUsedFirst) {
        this.manaUsedFirst = manaUsedFirst;
    }
}

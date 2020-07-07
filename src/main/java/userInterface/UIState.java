package userInterface;

import javax.swing.*;

public abstract class UIState extends JPanel {
    protected GameJFrame gameJFrame = GameJFrame.getInstance();
    public UIState(){
        super(true);
    }

    public abstract boolean runState();
    public abstract void getRenew();
}
package userInterface;

import file.FileAssistance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton {
    final private String buttonName;
    private boolean pressed = false;

    public MyButton(String action, String buttonName) {
        super(action);
        this.buttonName = buttonName;
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                setPressed(true);
            }
        });
    }

    public void changeIcon(String name) {
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("cards", name, getWidth(), getHeight()));
        setIcon(imageIcon);
        setBorder(null);
        setContentAreaFilled(false);
        setVisible(true);
    }

    public String getButtonName() {
        return buttonName;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}

package userInterface;

import file.FileAssistance;
import game.TimeAssistance;
import game.UIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class State extends JPanel {
    protected UIController uiController = UIController.getInstance();

    protected ArrayList<MyButton> myButtons = new ArrayList<>();
    protected ArrayList<HintTextField> myFields = new ArrayList<>();

    public State() {
        super(true);
    }

    protected MyButton addMyButton(String action, String name, int x, int y, int width, int height) {
        MyButton myButton = new MyButton(action, name);
        myButton.setBounds(x, y, width, height);
        myButton.setBorder(null);
        add(myButton);
        myButtons.add(myButton);
        return myButton;
    }

    protected MyButton addMyButton(String action, String name, int x, int y, int width, int height, String pack, String picName) {
        MyButton myButton = new MyButton(action, name);
        myButton.setBounds(x, y, width, height);
        add(myButton);
        myButtons.add(myButton);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage(pack, picName, width, height));
        myButton.setIcon(imageIcon);
        myButton.setBorder(null);
        myButton.setContentAreaFilled(false);
        return myButton;
    }


    protected MyButton getMyButton(String name) {
        for (MyButton myButton : myButtons)
            if (myButton.getButtonName().equals(name))
                return myButton;
        return null;
    }

    protected HintTextField addMyTextFiled(String hint, String name, int x, int y, int width, int height) {
        HintTextField hintTextField = new HintTextField(hint, name);
        hintTextField.setBounds(x, y, width, height);
        add(hintTextField);
        myFields.add(hintTextField);
        return hintTextField;
    }

    protected HintTextField getMyTextField(String name) {
        for (HintTextField myField : myFields)
            if (myField.getFieldName().equals(name))
                return myField;
        return null;
    }

    protected void showError(String error, int x, int y) {
        Graphics graphics = getGraphics();
        graphics.setFont(new Font("Helvetica", Font.BOLD, 16));
        graphics.setColor(Color.RED);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(error, x - fontMetrics.stringWidth(error) / 2, y + fontMetrics.getHeight());
    }

    protected void showError(String pre, String error, JButton myButton) {
        myButton.setForeground(Color.red);
        myButton.setText(error);
        TimeAssistance.waitFor(2000L);
        myButton.setText(pre);
        myButton.setForeground(Color.black);
    }

    protected boolean newAction(String name) {
        if (getMyButton(name).isPressed()) {
            getMyButton(name).setPressed(false);
            return true;
        }
        return false;
    }

    protected void removeAllThings() {
        removeAll();
        myButtons.clear();
        myFields.clear();
    }

    public abstract boolean runState();

    public abstract void updateState();
}
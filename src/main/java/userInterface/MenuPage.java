package userInterface;

import constants.GraphicConstants;
import file.FileAssistance;
import game.InfoHolder;
import game.UIController;

import javax.swing.*;

public class MenuPage extends State {
    private UIController uiController = UIController.getInstance();
    private InfoHolder infoHolder = InfoHolder.getInstance();


    public MenuPage() {
        init();
    }

    private void init() {
        removeAll();
        setPlayButton();
        setStoreButton();
        setStatusButton();
        setCollectionButton();

        setPanel();
    }

    private void setPanel() {
        setLayout(null);
        setBounds(0, 0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        setBackground();
    }

    public void setBackground() {
        JLabel jLabel = new JLabel();
        jLabel.setBounds(0, 0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("menu", "background.jpg",
                GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT));
        jLabel.setIcon(imageIcon);
        add(jLabel);
    }

    public void setPlayButton() {
        addMyButton("", "Play", GraphicConstants.MENU_BUTTON_X, GraphicConstants.MENU_BUTTON_FIRST_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT, "menu", "play.png");
    }

    public void setStoreButton() {
        addMyButton("", "Store", GraphicConstants.MENU_BUTTON_X, GraphicConstants.MENU_BUTTON_THIRD_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT, "menu", "store.png");
    }

    public void setStatusButton() {
        addMyButton("", "Status", GraphicConstants.MENU_BUTTON_X, GraphicConstants.MENU_BUTTON_SECOND_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT, "menu", "status.png");
    }

    public void setCollectionButton() {
        addMyButton("", "Collections", GraphicConstants.MENU_BUTTON_X, GraphicConstants.MENU_BUTTON_FORTH_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT, "menu", "collections.png");
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);
        while (true) {
            uiController.validate();
            if (newAction("Play"))
                uiController.changeState(this, new ChooseDeckPage());
            if (newAction("Collections"))
                uiController.changeState(this, new CollectionPage());
            if (newAction("Status"))
                uiController.changeState(this, new StatusPage());
            if (newAction("Store"))
                uiController.changeState(this, new StorePage());
        }
    }

    @Override
    public void updateState() {
        init();
    }
}
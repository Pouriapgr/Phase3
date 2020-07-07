package userInterface;

import module.Player;
import file.FileAssistance;
import Constants.GraphicConstants;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MenuPage extends UIState {
    private Player player;

    private boolean newPlay = false;
    private boolean newStore = false;
    private boolean newStatus = false;
    private boolean newCollection = false;

    public MenuPage(Player player){
        init(player);
    }
    private void init(Player player) {
        this.player = player;
        newPlay = false;
        newStore = false;
        newStatus = false;
        newCollection = false;

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
        JButton playButton = new JButton();
        playButton.setBounds(GraphicConstants.MENU_BUTTON_X,GraphicConstants.MENU_BUTTON_FIRST_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getImage("menu", "play.png"));
        playButton.setIcon(imageIcon);
        playButton.setBorder(null);
        playButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "PlayButton");
                setNewPlay(true);
            }
        });
        add(playButton);
    }
    public void setStoreButton() {
        JButton storeButton = new JButton();
        storeButton.setBounds(GraphicConstants.MENU_BUTTON_X,GraphicConstants.MENU_BUTTON_THIRD_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getImage("menu", "store.png"));
        storeButton.setIcon(imageIcon);
        storeButton.setBorder(null);
        storeButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "StoreButton");
                setNewStore(true);
            }
        });
        add(storeButton);
    }
    public void setStatusButton() {
        JButton statusButton = new JButton();
        statusButton.setBounds(GraphicConstants.MENU_BUTTON_X,GraphicConstants.MENU_BUTTON_SECOND_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getImage("menu", "status.png"));
        statusButton.setIcon(imageIcon);
        statusButton.setBorder(null);
        statusButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "StatusButton");
                setNewStatus(true);
            }
        });
        add(statusButton);
    }
    public void setCollectionButton() {
        JButton collectionButton = new JButton();
        collectionButton.setBounds(GraphicConstants.MENU_BUTTON_X,GraphicConstants.MENU_BUTTON_FORTH_Y,
                GraphicConstants.MENU_BUTTON_WIDTH, GraphicConstants.MENU_BUTTON_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getImage("menu", "collections.png"));
        collectionButton.setIcon(imageIcon);
        collectionButton.setBorder(null);
        collectionButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "CollectionsButton");
                setNewCollection(true);
            }
        });
        add(collectionButton);
    }

    @Override
    public boolean runState() {
        player.writeLog("Signin","");
        gameJFrame.setContentPane(this);
        while (true){
            gameJFrame.validate();
            if(isNewPlay())
                gameJFrame.changeState(this, new ChooseDeckPage(player));
            if(isNewCollection())
                gameJFrame.changeState(this, new CollectionPage(player));
            if(isNewStatus())
                gameJFrame.changeState(this, new StatusPage(player));
            if(isNewStore())
                gameJFrame.changeState(this, new StorePage(player));
        }
    }

    @Override
    public void getRenew(){
        init(player);
    }

    public boolean isNewPlay() { return newPlay; }
    public boolean isNewStore() { return newStore; }
    public boolean isNewStatus() { return newStatus; }
    public boolean isNewCollection() { return newCollection; }

    public void setNewPlay(boolean newPlay) { this.newPlay = newPlay; }
    public void setNewStore(boolean newStore) { this.newStore = newStore; }
    public void setNewStatus(boolean newStatus) { this.newStatus = newStatus; }
    public void setNewCollection(boolean newCollection) { this.newCollection = newCollection; }
}
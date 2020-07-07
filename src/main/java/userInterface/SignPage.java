package userInterface;

import constants.GraphicConstants;
import file.FileAssistance;
import game.InfoHolder;
import module.Player;
import module.PlayersAll;

import javax.swing.*;

public class SignPage extends State {

    public SignPage() {
        init();
    }

    private void init() {
        removeAll();
        setLogin();
        setRegister();
        setPanel();
    }

    private void setPanel() {
        setLayout(null);
        setBounds(0, 0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        setBackground();
    }

    private void setBackground() {
        JLabel jLabel = new JLabel();
        jLabel.setBounds(0, 0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("login", "background.jpg",
                GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT));
        jLabel.setIcon(imageIcon);
        add(jLabel);
    }

    private void setLogin() {
        addMyTextFiled("Username", "Login_Username", GraphicConstants.USERNAME_FIELD_X, GraphicConstants.USERNAME_FIELD_Y,
                GraphicConstants.USERNAME_FIELD_WIDTH, GraphicConstants.USERNAME_FIELD_HEIGHT);
        addMyTextFiled("Password", "Login_Password", GraphicConstants.PASSWORD_FIELD_X, GraphicConstants.PASSWORD_FIELD_Y,
                GraphicConstants.PASSWORD_FIELD_WIDTH, GraphicConstants.PASSWORD_FIELD_HEIGHT);
        addMyButton("Login", "Login_Login", GraphicConstants.LOGIN_BUTTON_X, GraphicConstants.LOGIN_BUTTON_Y,
                GraphicConstants.LOGIN_BUTTON_WIDTH, GraphicConstants.LOGIN_BUTTON_HEIGHT);
    }

    private void setRegister() {
        addMyTextFiled("Username", "Register_Username", GraphicConstants.USERNAME_FIELD_X, GraphicConstants.USERNAME_FIELD_Y +
                        GraphicConstants.LOGIN_REGISTER_SEPARATOR, GraphicConstants.USERNAME_FIELD_WIDTH,
                GraphicConstants.USERNAME_FIELD_HEIGHT);
        addMyTextFiled("Password", "Register_Password", GraphicConstants.PASSWORD_FIELD_X, GraphicConstants.PASSWORD_FIELD_Y +
                        GraphicConstants.LOGIN_REGISTER_SEPARATOR, GraphicConstants.PASSWORD_FIELD_WIDTH,
                GraphicConstants.PASSWORD_FIELD_HEIGHT);
        addMyButton("Register", "Register_Register", GraphicConstants.LOGIN_BUTTON_X, GraphicConstants.LOGIN_BUTTON_Y +
                        GraphicConstants.LOGIN_REGISTER_SEPARATOR, GraphicConstants.LOGIN_BUTTON_WIDTH,
                GraphicConstants.LOGIN_BUTTON_HEIGHT);
    }

    private void checkNewLogin() {
        if (!newAction("Login_Login"))
            return;
        PlayersAll playersAll = PlayersAll.loadAllPlayers();
        if (playersAll.isValidUser(getMyTextField("Login_Username").getText(), getMyTextField("Login_Password").getText())) {
            enterMenu(Player.loadPlayer(FileAssistance.findPlayerJSON(getMyTextField("Login_Username").getText())));
        } else {
            showError("Please Check Your Username and Password Again", (3 * GraphicConstants.FRAME_WIDTH) / 4,
                    GraphicConstants.LOGIN_BUTTON_Y + GraphicConstants.LOGIN_BUTTON_HEIGHT +
                            GraphicConstants.LOGIN_REGISTER_SEPARATOR);
        }
    }

    private void checkNewRegister() {
        if (!newAction("Register_Register"))
            return;
        PlayersAll playersAll = PlayersAll.loadAllPlayers();
        if (playersAll.checkIfUsernameExist(getMyTextField("Register_Username").getText())) {
            showError("Username Already used", (3 * GraphicConstants.FRAME_WIDTH) / 4,
                    GraphicConstants.LOGIN_BUTTON_Y + GraphicConstants.LOGIN_BUTTON_HEIGHT +
                            GraphicConstants.LOGIN_REGISTER_SEPARATOR);
        } else {
            enterMenu(new Player(getMyTextField("Register_Username").getText(), getMyTextField("Register_Password").getFieldName()));
        }
    }

    private void enterMenu(Player player) {
        InfoHolder infoHolder = InfoHolder.getInstance(player);
        uiController.changeState(this, new MenuPage());
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);
        while (true) {
            checkNewLogin();
            checkNewRegister();
            uiController.validate();
        }
    }

    @Override
    public void updateState() {
        init();
    }
}
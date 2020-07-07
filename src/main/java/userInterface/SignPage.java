package userInterface;

import module.Player;
import module.AllPlayers;
import file.FileAssistance;
import Constants.GraphicConstants;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class SignPage extends UIState {
    GameJFrame gameJFrame = GameJFrame.getInstance();

    private JTextField loginUsername;
    private JTextField loginPassword;
    private JTextField registerUsername;
    private JTextField registerPassword;
    private JButton registerButton;
    private JButton loginButton;

    private boolean newLogin = false;
    private boolean newRegister = false;

    public SignPage(){
        init();
    }
    public void init() {
        newLogin = false;
        newRegister = false;
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
        setLoginUsernameSection();
        setLoginPasswordSection();
        setLoginSection();
    }
    private void setLoginUsernameSection() {
        loginUsername = new HintTextField("Username");
        loginUsername.setBounds(GraphicConstants.USERNAME_BUTTON_X, GraphicConstants.USERNAME_BUTTON_Y,
                GraphicConstants.USERNAME_BUTTON_WIDTH, GraphicConstants.USERNAME_BUTTON_HEIGHT);
        add(loginUsername);
    }
    private void setLoginPasswordSection() {
        loginPassword = new HintTextField("Password");
        loginPassword.setBounds(GraphicConstants.PASSWORD_BUTTON_X, GraphicConstants.PASSWORD_BUTTON_Y,
                GraphicConstants.PASSWORD_BUTTON_WIDTH, GraphicConstants.PASSWORD_BUTTON_HEIGHT);
        add(loginPassword);
    }
    private void setLoginSection() {
        loginButton = new JButton("Login");
        loginButton.setBounds(GraphicConstants.LOGIN_BUTTON_X, GraphicConstants.LOGIN_BUTTON_Y,
                GraphicConstants.LOGIN_BUTTON_WIDTH, GraphicConstants.LOGIN_BUTTON_HEIGHT);
        loginButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                setNewLogin(true);
            }
        });
        add(loginButton);
    }

    private void setRegister() {
        setRegisterUsernameSection();
        setRegisterPasswordSection();
        setRegisterSection();
    }
    private void setRegisterUsernameSection() {
        registerUsername = new HintTextField("Username");
        registerUsername.setBounds(GraphicConstants.USERNAME_BUTTON_X, GraphicConstants.USERNAME_BUTTON_Y +
                        GraphicConstants.LOGIN_REGISTER_SEPARATOR, GraphicConstants.USERNAME_BUTTON_WIDTH,
                GraphicConstants.USERNAME_BUTTON_HEIGHT);
        add(registerUsername);
    }
    private void setRegisterPasswordSection() {
        registerPassword = new HintTextField("Password");
        registerPassword.setBounds(GraphicConstants.PASSWORD_BUTTON_X, GraphicConstants.PASSWORD_BUTTON_Y +
                        GraphicConstants.LOGIN_REGISTER_SEPARATOR, GraphicConstants.PASSWORD_BUTTON_WIDTH,
                GraphicConstants.PASSWORD_BUTTON_HEIGHT);
        add(registerPassword);
    }
    private void setRegisterSection() {
        registerButton = new JButton("Register");
        registerButton.setBounds(GraphicConstants.LOGIN_BUTTON_X, GraphicConstants.LOGIN_BUTTON_Y +
                        GraphicConstants.LOGIN_REGISTER_SEPARATOR, GraphicConstants.LOGIN_BUTTON_WIDTH,
                GraphicConstants.LOGIN_BUTTON_HEIGHT);

        registerButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                setNewRegister(true);
            }
        });
        add(registerButton);
    }

    public void showInvalidUserError() {
        String error = "Please Check Your Username and Password Again";
        Graphics graphics = getGraphics();
        graphics.setFont(new Font("Helvetica", Font.BOLD, 16));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.RED);
        graphics.drawString(error, (3 * GraphicConstants.FRAME_WIDTH) / 4 - fontMetrics.stringWidth(error) / 2,
                GraphicConstants.LOGIN_BUTTON_Y + GraphicConstants.LOGIN_BUTTON_HEIGHT + fontMetrics.getHeight());
    }
    public void showUsernameError() {
        String error = "Username Already used";
        Graphics graphics = getGraphics();
        graphics.setFont(new Font("Helvetica", Font.BOLD, 16));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.RED);
        graphics.drawString(error, (3 * GraphicConstants.FRAME_WIDTH) / 4 - fontMetrics.stringWidth(error) / 2,
                GraphicConstants.LOGIN_BUTTON_Y + GraphicConstants.LOGIN_BUTTON_HEIGHT + fontMetrics.getHeight() +
                        GraphicConstants.LOGIN_REGISTER_SEPARATOR);
    }

    private void checkNewLogin() {
        if(!newLogin)
            return;
        AllPlayers allPlayers = AllPlayers.loadAllPlayers();
        setNewLogin(false);
        if(allPlayers.isValidUser(getLoginUsername(), getLoginPassword())) {
            Player player = Player.loadPlayer(FileAssistance.findPlayerJSON(getLoginUsername()));
            gameJFrame.changeState(this, new MenuPage(player));
        }
        else
            showInvalidUserError();
    }
    private void checkNewRegister() {
        if(!newRegister)
            return;
        setNewRegister(false);
        AllPlayers allPlayers = AllPlayers.loadAllPlayers();
        if(allPlayers.checkIfUsernameExist(getRegisterUsername()))
            showUsernameError();
        else{
            Player player = new Player(getRegisterUsername(), getRegisterPassword());
            gameJFrame.changeState(this, new MenuPage(player));
        }
    }

    @Override
    public boolean runState() {
        gameJFrame.setContentPane(this);
        while (true){
            checkNewLogin();
            checkNewRegister();
            gameJFrame.validate();
        }
    }
    @Override
    public void getRenew(){
        init();
    }

    public String getLoginUsername() { return loginUsername.getText(); }
    public String getLoginPassword() { return loginPassword.getText(); }
    public String getRegisterUsername() { return registerUsername.getText(); }
    public String getRegisterPassword() { return registerPassword.getText(); }

    public void setNewLogin(boolean newLogin) { this.newLogin = newLogin; }
    public void setNewRegister(boolean newRegister) { this.newRegister = newRegister; }
}
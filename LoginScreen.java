
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The LoginScreen class provides a GUI for user authentication.
 * It supports both login and sign-up  for a vehicle rental service application.
 */
public class LoginScreen extends JFrame {
    //protected HashMap<String, String> LoginInfo = new HashMap<String, String>();
    protected JFrame frame;
    FeedScreen feed;
    Management management;

    
    JPanel loginPanelInfo = new JPanel();
    JPanel loginButtoPanel = new JPanel();
    JPanel loginPanel = new JPanel();
    JPanel Su_Panel = new JPanel();
    JButton Signup = new JButton("sign up");
    JButton bButton = new JButton("Back");
    JButton submit = new JButton("login");

    //labels
    JLabel username = new JLabel("UserName");
    JLabel password = new JLabel("Password");
    
    JLabel errorLabel = new JLabel("");

    //text fields
    JTextField unameText = new JTextField(11);
    JTextField passwordText = new JTextField(11);
    JTextField Su_unameText = new JTextField(11);
    JTextField Su_passwordText = new JTextField(11);

    //private ArrayList<User> user = new ArrayList<User>();
    private boolean isSignUp = false;
   

    /**
     * Constructs a LoginScreen object to manage user login and registration.
     * 
     * @param management The management object handling user operations.
     */
    public LoginScreen(Management management){
        this.frame = this;
        //this.feed = feed;
        this.management = management;

        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // loginPanelInfo.setSize(700, 500);
        //loginPanelInfo.setBackground(Color.decode("#00001a")); 

        loginPanel.add(username);
        loginPanel.add(unameText);
        loginPanel.add(password);
        loginPanel.add(passwordText);
        //loginPanel.add(loginPanelInfo);
        loginPanel.add(loginButtoPanel);
        //loginButtoPanel.add(logon);
        loginButtoPanel.add(submit);
        loginButtoPanel.add(Signup);
        loginPanel.add(Su_Panel);

        BoxLayout boxlayout = new BoxLayout(loginPanel, BoxLayout.Y_AXIS);
        loginPanel.setBorder(new EmptyBorder(new Insets(100, 300, 200, 300))); 
        setTitle("Vehicle Rental login");
        

        bButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToLogin();
            }
        });

        
        // Add the label to your panel
        loginPanel.add(errorLabel);
        
    /**
     * Configures the action listeners for the interactive components of the login screen.
     */
        Signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToSignUp();

                String uname = Su_unameText.getText();
                String mpassword = Su_passwordText.getText();

                try {
                    // Input Validation
                    if (uname.isEmpty() || mpassword.isEmpty()) {
                        throw new IllegalArgumentException("Username and or password cannot be empty.");
                    }

                    // Check if username already exists
                    if (management.getUsers().contains(uname)) {
                        throw new IllegalArgumentException("Username already in use. Please choose a different username.");
                    }

                    // Clear any previous error messages
                    errorLabel.setText("");

                    // Perform signup logic here
                    management.createUser(uname, mpassword);
                    System.out.println("Signed up");

                } catch (IllegalArgumentException ex) {
                    // Display the error message on the label
                    errorLabel.setText("Error: " + ex.getMessage());
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(isSignUp == false){
                    String uname = unameText.getText();
                    String mpassword = passwordText.getText();
                    if(management.isAUser(uname) == true){
                        
                        management.login(uname, mpassword);
                        System.out.println(management.getWhosloggedin().getName()+" thank you");
                        //management.isAUsser(uname);
                        System.out.println("logged in");
                        new FeedScreen(uname, frame, management);
                    }
                
                }
                else{

                }
            }
        });

        this.add(loginPanel);
        this.setVisible(true);

    }

    /**
     * method to swith elements on panel
     */
    private void switchToSignUp() {
        isSignUp = true;
        loginPanel.setBorder(new EmptyBorder(new Insets(100, 300, 200, 300))); 
        loginPanel.remove(username);
        loginPanel.remove(unameText);
        loginPanel.remove(password);
        loginPanel.remove(passwordText);
        loginPanel.remove(errorLabel);
        //loginButtoPanel.remove(logon);
        loginButtoPanel.remove(submit);
        loginPanel.remove(Signup);

        loginButtoPanel.add(errorLabel);
        loginPanel.add(Su_Panel);
        Su_Panel.add(username);
        Su_Panel.add(Su_unameText);
        Su_Panel.add(password);
        Su_Panel.add(Su_passwordText);
        loginPanel.add(bButton);
        loginPanel.add(Signup);
        //loginPanel.add(submit);
        

        loginPanel.revalidate();
        loginPanel.repaint();
    }

    private void switchToLogin() {
        isSignUp = false;
        loginPanel.remove(Su_Panel);
        loginPanel.remove(username);
        loginPanel.remove(Su_unameText);
        loginPanel.remove(password);
        loginPanel.remove(Su_passwordText);
        loginPanel.remove(bButton);

        
        loginPanel.add(username);
        loginPanel.add(unameText);
        loginPanel.add(password);
        loginPanel.add(passwordText);
        loginPanel.add(submit);
        loginPanel.add(loginButtoPanel);
        //loginButtoPanel.add(logon);
        loginButtoPanel.add(Signup);
        
        
    
        loginPanel.revalidate();
        loginPanel.repaint();
    }

    /* 
    public void logOnInfo(){
        String uname = unameText.getText();
        String mpassword = passwordText.getText();
        System.out.println("in login");
        
        if(social.isRestricted(uname)){
            System.out.println("okay");
            if (social.login(uname, mpassword)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed. Please check your credentials.");
                System.out.println("names:" + social.getAllSocialsUsers());
            }
        }
    }*/

    

}



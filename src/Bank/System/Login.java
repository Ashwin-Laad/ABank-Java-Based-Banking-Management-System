package Bank.System;

import Bank.System.BankFeatures.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//JFRAME from swing package
//used to create various UI

//To implement button--ActionListener
public class Login extends JFrame implements ActionListener {
    //label2 for card label3 for pin

    JLabel label1,label2,label3;


    //jtextfield so user can enter the card number and the pin
    JTextField text1;
    JPasswordField pass;

    JButton b1,b2,b3;
    //we need three button


    //make a constructor
    Login(){
        //super ke upar kuch nahi sets title
        super("Online Banking System");
        //Jlabel to actaully add the image

        //make text feilds--> description message
        label1=new JLabel("Welcome to our Bank");
        //set colour of font
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGarde",Font.BOLD,38));

        //here frame shift wrt to frame not screen
        label1.setBounds(230,125,450,40);
        add(label1);


        label2=new JLabel("Card No:");
        //set colour of font
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Ralway",Font.BOLD,28));

        //here frame shift wrt to frame not screen
        label2.setBounds(150,190,375,30);
        add(label2);

        //to add the text field
        text1=new JTextField(15);
        text1.setBounds(325,190,230,30);
        text1.setFont(new Font("Arial",Font.BOLD,14));
        add(text1);

        //label 3 for pin
        label3=new JLabel("Pin:");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Ralway",Font.BOLD,28));
        label3.setBounds(150,250,375,30);
        add(label3);

        //add the password field
        pass=new JPasswordField(15);
        pass.setFont(new Font("Arial",Font.BOLD,14));
        pass.setBounds(325,250,230,30);
        add(pass);


        //3 button needed now sign in sign up and cancel
        b1=new JButton("Sign In");
        b1.setFont(new Font("Arial",Font.BOLD,14));
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setBounds(300,300,100,30);
        b1.addActionListener(this);
        add(b1);

        b2=new JButton("Sign Up");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(430,300,100,30);
        b2.setFont(new Font("Arial",Font.BOLD,14));
        b2.addActionListener(this);
        add(b2);

        b3=new JButton("Clear Screen");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setBounds(300,350,230,30);
        b3.setFont(new Font("Arial",Font.BOLD,14));
        b3.addActionListener(this);
        add(b3);



//----First add your labels only then image so there is no overlap
        //Image-1 for bank icon
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icon/icon1.jpeg"));
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(350,10,100,100);
        add(image);

        //Image for Background
        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("Icon/modif.jpeg"));
        Image i22=i11.getImage().getScaledInstance(850,480, Image.SCALE_DEFAULT);
        ImageIcon i33=new ImageIcon(i22);
        JLabel imagee=new JLabel(i33);
        imagee.setBounds(0,0,850,480);
        add(imagee);





        //STEP-1 ------Set the Frame------
        setLayout(null);
        //set frame size
        setSize(850,480);
        //for removing minimize title screen etc
        setUndecorated(true);
        //size set but frame is not yet visivble-> make it visible
        setVisible(true);
        //now bydefault fram from left hand side open at centre
        setLocation(450,200);



    }
    //constructor ends
    //implement the interface

//here action e is teh object on which event happened

    @Override
    public void actionPerformed(ActionEvent e) {
        //exception possible --try and catch
        try{
            if(e.getSource()==b1){
            //here get for the deposit page get the form number and the pin number
                Con c=new Con();
                //what ever user enters will be stored here
                String cardNo=text1.getText();
                String pin=pass.getText();

                //now check login table for form and the pin
                //here fecthing result from database so execute query not update

                String query="Select * from logindetails where card_number= '"+cardNo+"' and pin='"+pin+"'";

                //to store the table use result set
                ResultSet resultSet=c.statement.executeQuery(query);

                //check if data came or not
                if(resultSet.next()){
                    //result came
                    //so close login page and open main page

                    //get form number as well
                    String formNumber=resultSet.getString("form_no");
                    new EmailLogin(formNumber,pin);
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Details");
                }

            }
            else if(e.getSource()==b2){
                //go to sign up page
                new SignUp();
                setVisible(false);


            }else{
                //for button 3
                text1.setText("");
                pass.setText("");

            }

        }
        catch(Exception E){
            E.printStackTrace();
        }
    }



    public static void main(String[] args) {
        //make an object-> call the constructor
        new Login();

    }
}

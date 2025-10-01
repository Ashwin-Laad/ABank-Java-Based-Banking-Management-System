package Bank.System.BankFeatures;

import Bank.System.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

//Deposit just tell the amount deposited by user at what time etc does not store the total maount
//only individual transactions

public class Deposit extends JFrame implements ActionListener {
    String pin;
    String form;

    //in differnet package will be used so make constructor public
    JTextField text;

    JButton b1,b2,b3,submit,s;


    public Deposit(String form,String pin){
        this.pin=pin;
        this.form=form;
        JLabel label1=new JLabel("Enter Amount to Deposit");
        label1.setFont(new Font("Raleway",Font.BOLD,30));
        label1.setBounds(560,100,400,35);
        add(label1);

        text=new JTextField("  ₹");
        text.setFont(new Font("Raleway",Font.BOLD,25));
        text.setBounds(100,250,400,50);
        add(text);

        b1=new JButton("₹2000");
        b1.setBackground(new Color(0, 253, 231));
        b1.setFont(new Font("Raleway",Font.BOLD,25));
        b1.setBounds(1000,250,250,30);
        b1.addActionListener(this);
        add(b1);

        b2=new JButton("₹5000");
        b2.setBackground(new Color(0, 253, 231));
        b2.setFont(new Font("Raleway",Font.BOLD,25));
        b2.setBounds(1000,350,250,30);
        b2.addActionListener(this);
        add(b2);

        b3=new JButton("₹10000");
        b3.setBackground(new Color(0, 253, 231));
        b3.setFont(new Font("Raleway",Font.BOLD,25));
        b3.setBounds(1000,450,250,30);
        b3.addActionListener(this);
        add(b3);

        submit=new JButton("Enter");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.setFont(new Font("Raleway",Font.BOLD,25));
        submit.setBounds(100,500,150,50);
        submit.addActionListener(this);
        add(submit);

        s=new JButton("Cancel");
        s.setBackground(Color.BLACK);
        s.setForeground(Color.white);
        s.setFont(new Font("Raleway",Font.BOLD,25));
        s.setBounds(100,620,150,50);
        s.addActionListener(this);
        add(s);











        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("./Icon/OIP (4).jpeg"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        //or l3.add(label1) to add it over the image







        setSize(1550,1080);
        //make full screen
        setLocation(0,0);
        setLayout(null); //default layout is border layout
        setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1){
            text.setText("₹2000");
        }
        if(e.getSource()==b2){
            text.setText("₹5000");
        }
        if(e.getSource()==b3){
            text.setText("₹10000");
        }
        if(e.getSource()==s){
            System.exit(0);
        }

        try{
            if(e.getSource()==submit){
                String amount=text.getText();
                //here text is an object so text.equal never runs always false

                if(amount.equals("  ₹")) JOptionPane.showMessageDialog(null,"Enter the amount");
                else{
                    //establish the connection
                    Con c=new Con();

                    Date date=new Date();
                   String query="Insert into banktransactions values('"+form+"','"+pin+"','"+date+"','deposit','"+amount+"')";
                    c.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs. "+amount+" is deposited Succssfully");

                    //go to next frame--for now make visible equal to false
                    //from here goes to the main screen



                    //when user comes first time he deposits and his work is completed

                    //take user to main screen
                    new MainScreen(form,pin);


                    setVisible(false);



                }
            }

        }
        catch (Exception E){
            System.out.println(E.getMessage());
        }
//java code line by line execution


        //now to store to database
        //put form pin date type amount
    }

    public static void main(String[] args) {
        new Deposit("","");
    }


}

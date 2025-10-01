package Bank.System.BankFeatures;

import Bank.System.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
String form;
String pin;
int balance=0;
JButton b;

    BalanceEnquiry(String form,String pin){
        this.form=form;
        this.pin=pin;
        //fetch balance from database
        try{

            Con c=new Con();
            String query="Select * from banktransactions where form_no="+form;
            ResultSet rs=c.statement.executeQuery(query);
            while(rs.next()){
                String amountStr=rs.getString("amount");
                //clean the string first
                //remove the symbols
                //.trim removes the white spaces just in case


                amountStr=amountStr.replaceAll("[₹$,]", "").trim();
                //data is there
                if(rs.getString("type").equals("deposit")){




                    balance+=Integer.parseInt(amountStr);
                }
                else{
                    balance-=Integer.parseInt(amountStr);
                }
            }


        }
        catch(Exception E){
            System.out.println(E.getMessage());
        }

        JLabel l=new JLabel("Your current balance is "+ balance);
        l.setFont(new Font("Ralway",Font.BOLD,30));
        l.setBounds(250,40,600,100);
        add(l);
        b=new JButton("Back");
        b.setFont(new Font("Ralway",Font.BOLD,20));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.white);
        b.setBounds(550,300,100,50);
        b.addActionListener(this);
        add(b);



        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("./Icon/OIP (4).jpeg"));
        Image i2=i1.getImage().getScaledInstance(850,480, Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,850,480);
        add(l3);

        //or l3.add(label1) to add it over the image





        setLayout(null);
        //set frame size
        setSize(850,480);

        //size set but frame is not yet visivble-> make it visible
        setVisible(true);
        //now bydefault fram from left hand side open at centre
        setLocation(450,200);


//        setSize(1550,1080);
//        //make full screen
//        setLocation(0,0);
//        setLayout(null); //default layout is border layout
//        setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("","");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new MainScreen(form,pin);
        setVisible(false);
    }
}

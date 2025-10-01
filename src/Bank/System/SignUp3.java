package Bank.System;

import Bank.System.BankFeatures.Deposit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.FieldPosition;
import java.util.Random;

public class SignUp3 extends JFrame implements ActionListener {

    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3;
JButton b1;
String form;


    SignUp3(String form){


        this.form=form;
        JLabel l1=new JLabel("Account Details");
        l1.setFont(new Font("Raleway",Font.BOLD,22));
        l1.setBounds(280,70,400,40);
        add(l1);

        JLabel l11=new JLabel("Account Type");
        l11.setFont(new Font("Raleway",Font.BOLD,18));
        l11.setBounds(100,140,200,30);
        add(l11);

        r1=new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway",Font.BOLD,16));
        r1.setBounds(100,180,150,30);
        r1.setBackground(new Color(253,250,184));
        add(r1);

        r2=new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway",Font.BOLD,16));
        r2.setBounds(350,180,200,30);
        r2.setBackground(new Color(253,250,184));
        add(r2);

        r3=new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway",Font.BOLD,16));
        r3.setBounds(100,220,150,30);
        r3.setBackground(new Color(253,250,184));
        add(r3);

        r4=new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway",Font.BOLD,16));
        r4.setBounds(350,220,250,30);
        r4.setBackground(new Color(253,250,184));
        add(r4);

        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);

        JLabel l111=new JLabel("Card Number");
        l111.setFont(new Font("Raleway",Font.BOLD,18));
        l111.setBounds(100,300,200,30);
        add(l111);

        JLabel l2=new JLabel("Your Card Number is: ");
        l2.setFont(new Font("Raleway",Font.BOLD,12));
        l2.setBounds(100,330,200,20);
        add(l2);

        JLabel l3=new JLabel("XXXX-XXXX-XXXX-1234");
        l3.setFont(new Font("Raleway",Font.BOLD,18));
        l3.setBounds(350,300,250,30);
        add(l3);


        JLabel l22=new JLabel("Your Pin is: ");
        l22.setFont(new Font("Raleway",Font.BOLD,18));
        l22.setBounds(100,380,200,20);
        add(l22);

        JLabel l33=new JLabel("XXXX");
        l33.setFont(new Font("Raleway",Font.BOLD,18));
        l33.setBounds(350,380,250,30);
        add(l33);

        JLabel l5=new JLabel("Services Needed");
        l5.setFont(new Font("Raleway",Font.BOLD,18));
        l5.setBounds(100,450,200,30);
        add(l5);

        c1=new JCheckBox("ATM card");
        c1.setBackground(new Color(253,250,184));
        c1.setBounds(100,500,200,30);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        add(c1);

        c2=new JCheckBox("Internet Banking");
        c2.setBackground(new Color(253,250,184));
        c2.setBounds(100,550,200,30);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        add(c2);

        c3=new JCheckBox("Mobile Banking");
        c3.setBackground(new Color(253,250,184));
        c3.setBounds(100,600,200,30);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        add(c3);

        JCheckBox c6=new JCheckBox("Details entered are correct",true);
        c6.setFont(new Font("Raleway",Font.BOLD,16));
        c6.setBackground(new Color(253,250,248));
        c6.setBounds(100,680,600,20);
        add(c6);


        b1=new JButton("Submit");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white );
        b1.setBounds(400,720,150,30);
        b1.addActionListener(this);
        add(b1);

        JLabel form1=new JLabel("Form no: "+form);
        form1.setFont(new Font("Raleway",Font.BOLD,18));
        form1.setBounds(600,30,300,30);
        add(form1);








        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icon/icon1.jpeg"));
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(25,10,100,100);
        add(image);


        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("Icon/signupback.jpeg"));
        Image i22=i11.getImage().getScaledInstance(850,800, Image.SCALE_DEFAULT);
        ImageIcon i33=new ImageIcon(i22);
        JLabel imagee=new JLabel(i33);
        imagee.setBounds(0,0,850,800);
        add(imagee);
        setSize(850,800);
        setLayout(null);
        setLocation(400,20);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        try{
            String atype=null;
            if(r1.isSelected()) atype="Saving Account";
            if(r1.isSelected()) atype="Fixed Deposit Account";
            if(r3.isSelected()) atype="Current Account";
            if (r4.isSelected()) atype="Recurring Deposit Account";

            //generate card number has to be random
            Random r=new Random();
            //for card number we need a 16 digit number --> store in long
            //generate the last 5 digits as random and keep first 16-5 digits as static
            //range=0 to 89999
            long c=(r.nextLong()%90000L)+8971237489100000L;
            //c can be negative

            String cardNo=""+Math.abs(c);

            //to generate pin--> last 3 random digits

            long pin=Math.abs((r.nextLong()%900L))+1000L; //can genrate negative number decreasing and getting to 3 digits number

            String p=""+pin;

            //now for check box store as a complete string

            String ser="";
            if(c1.isSelected()) ser+=" ATM card";
            if(c2.isSelected()) ser+=" Internet Banking";
            if(c3.isSelected()) ser+=" Mobile Banking";


            //now store in db
            try{
            if(atype.equals("")) JOptionPane.showMessageDialog(null,"Fill the fields");
            else{

                //establish connection as all details are filed
                Con c5=new Con();

                //we will need 2 tables now one to insert into Login( user id and pass) and SignUp 3

                String query="Insert into accountdetails values ('"+form+"','"+atype+"','"+cardNo+"','"+p+"','"+ser+"')";
                String query2="Insert into Logindetails values('"+form+"','"+cardNo+"','"+p+"')";
                c5.statement.executeUpdate(query);
                c5.statement.executeUpdate(query2);

                //if values inserted properly then show it to the user
                JOptionPane.showMessageDialog(null,"Your Card Number is:"+cardNo+"\n Your Pin Number is: "+p);


                //Since the user is coming for first time has to deposit some money
                //so directly take to deposit screen

                new Deposit(form,p);
                setVisible(false);


            }

            }
            catch(Exception e11){
                System.out.println(e11.getMessage());
            }





        }
        catch(Exception e1){
            System.out.println(e1.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUp3("");
    }


}

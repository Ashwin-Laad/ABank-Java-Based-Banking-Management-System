package Bank.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Random;

public class SignUp extends JFrame implements ActionListener {

    //declaring text field globally as we have to use that data to share to database
    //so used later
    JTextField textName,text1,email,date,addr,addr1;

    JRadioButton r1,r11;
    JButton b;




    Random ran=new Random();
    long f4=(ran.nextLong()% 9000L)+1000L;
    String f=" "+Math.abs(f4);


    SignUp(){

        super("Sign Up");

        JLabel label=new JLabel("Application Number: "+ f);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Raleway",Font.BOLD,38));
        label.setBounds(160,20,600,40);
        add(label);

        JLabel label1=new JLabel("User Information ");
//        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Raleway",Font.BOLD,22));
        label1.setBounds(290,90,600,30);
        add(label1);

        JLabel name=new JLabel("Name");
        name.setBounds(100,190,100,30);
        name.setFont(new Font("Raleway",Font.BOLD,20));
        add(name);

        textName=new JTextField();
        textName.setFont(new Font("Raleway",Font.BOLD,14));
        textName.setBounds(300,190,400,30);
        add(textName);


        JLabel text11=new JLabel("Fathers Name");
        text11.setBounds(100,240,200,30);
        text11.setFont(new Font("Raleway",Font.BOLD,20));
        add(text11);

        text1=new JTextField();
        text1.setFont(new Font("Raleway",Font.BOLD,14));
        text1.setBounds(300,240,400,30);
        add(text1);

        JLabel name44=new JLabel("Mothers name");
        name44.setBounds(100,290,200,30);
        name44.setFont(new Font("Raleway",Font.BOLD,20));
        add(name44);

        addr1=new JTextField();
        addr1.setFont(new Font("Raleway",Font.BOLD,14));
        addr1.setBounds(300,290,400,30);
        add(addr1);


        //add gender
        JLabel g=new JLabel("Gender");
        g.setFont(new Font("Raleway",Font.BOLD,20));
        g.setBounds(100,340,200,30);
        add(g);

        r1=new JRadioButton("Male");
        r1.setFont(new Font("Raleway",Font.BOLD,14));
        r1.setBounds(300,340,90,30);
        r1.setBackground(new Color(253,250,184));
        add(r1);

        r11=new JRadioButton("Female");
        r11.setFont(new Font("Raleway",Font.BOLD,14));
        r11.setBounds(450,340,90,30);
        r11.setBackground(new Color(253,250,184));
        add(r11);
//for only one to select --group the button

ButtonGroup bg=new ButtonGroup();
bg.add(r1);
bg.add(r11);

//email
        JLabel name2=new JLabel("Email Address");
        name2.setBounds(100,390,200,30);
        name2.setFont(new Font("Raleway",Font.BOLD,20));
        add(name2);

        email=new JTextField();
        email.setFont(new Font("Raleway",Font.BOLD,14));
        email.setBounds(300,390,400,30);
        add(email);

        //addreess dob and city and state
        JLabel name3=new JLabel("Date of Birth");
        name3.setBounds(100,440,200,30);
        name3.setFont(new Font("Raleway",Font.BOLD,20));
        add(name3);

        date=new JTextField();
        date.setFont(new Font("Raleway",Font.BOLD,14));
        date.setBounds(300,440,400,30);
        add(date);

        JLabel name4=new JLabel("Address");
        name4.setBounds(100,490,200,30);
        name4.setFont(new Font("Raleway",Font.BOLD,20));
        add(name4);

        addr=new JTextField();
        addr.setFont(new Font("Raleway",Font.BOLD,14));
        addr.setBounds(300,490,400,30);
        add(addr);


        b=new JButton("Next");
        b.setFont(new Font("Raleway",Font.BOLD,14));
        b.setBounds(400,650,80,30);
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.addActionListener(this);
        add(b);




        //add the background colour
//
//        getContentPane().setBackground(new Color(253,250,184));
        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("Icon/signupback.jpeg"));
        Image i22=i11.getImage().getScaledInstance(850,800, Image.SCALE_DEFAULT);
        ImageIcon i33=new ImageIcon(i22);
        JLabel imagee=new JLabel(i33);
        imagee.setBounds(0,0,850,800);
        add(imagee);


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icon/icon1.jpeg"));
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(25,10,100,100);
        add(image);



        setSize(850,800);
        setLocation(360,40);
        setLayout(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
//now get the data the user enters

        // the date we get from user
        //formno name fname mname gender email address

        String formno=f;
        String name=textName.getText();
        String fname=text1.getText();
        String mname=addr1.getText();
        String gender=null;
        if(r1.isSelected()) gender="Male";
        else gender="Female";
        String Email=email.getText();
        String address=addr.getText();

        //now we will store in database
//to store data exception --so try and catch
        try{
            if(name.equals("")) JOptionPane.showMessageDialog(null,"Fill the fields");
            else{
                //here establish the connection
                Con con=new Con();
                String query="insert into SignUp values('"+formno+"','"+name+"','"+fname+"','"+mname+"','"+gender+"','"+Email+"','"+address+"')";
                //to execute query
                con.statement.executeUpdate(query);

                //execute update means that the values will be inserted in the table
                 //now open next sign up page
                new SignUp2(f);
                setVisible(false); // close current window



            }

        }
        catch (Exception e1){
            System.out.println(e1.getMessage());
        }



    }

    public static void main(String[] args) {
        new SignUp();
    }
}

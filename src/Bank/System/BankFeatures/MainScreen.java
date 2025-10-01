package Bank.System.BankFeatures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin,formno;

//as we are coming from login screen we need to know to which user does it belong to the form number

    public MainScreen(String formno,String pin){

        //buttons on main screen
        this.formno=formno;
        this.pin=pin;
        JLabel label=new JLabel("Choose Your Transaction ");
        label.setBounds(500,50,700,100);
        label.setFont(new Font("Raleway",Font.BOLD,50));
        label.setForeground(Color.BLACK);
        add(label);

        b1=new JButton("Deposit");
        b1.setForeground(Color.black);
        b1.setBackground(new Color(0, 253, 207, 255));
        b1.setFont(new Font("Raleway",Font.BOLD,28));
        b1.setBounds(100,250,350,60);
        b1.addActionListener(this);
        add(b1);

        b2=new JButton("Cash Withdrawl");
        b2.setForeground(Color.black);
        b2.setBackground(new Color(0, 253, 207, 255));
        b2.setFont(new Font("Raleway",Font.BOLD,28));
        b2.setBounds(1100,250,350,60);
        b2.addActionListener(this);
        add(b2);

        b3=new JButton("Statement");
        b3.setForeground(Color.black);
        b3.setBackground(new Color(0, 253, 207, 255));
        b3.setFont(new Font("Raleway",Font.BOLD,28));
        b3.setBounds(100,400,350,60);
        b3.addActionListener(this);
        add(b3);

        b4=new JButton("Pin Change");
        b4.setForeground(Color.black);
        b4.setBackground(new Color(0, 253, 207, 255));
        b4.setFont(new Font("Raleway",Font.BOLD,28));
        b4.setBounds(1100,400,350,60);
        b4.addActionListener(this);
        add(b4);

        b5=new JButton("Balance Enquiry");
        b5.setForeground(Color.black);
        b5.setBackground(new Color(0, 253, 207, 255));
        b5.setFont(new Font("Raleway",Font.BOLD,28));
        b5.setBounds(100,550,350,60);
        b5.addActionListener(this);
        add(b5);

        b6=new JButton("Exit");
        b6.setForeground(Color.black);
        b6.setBackground(new Color(0, 253, 207, 255));
        b6.setFont(new Font("Raleway",Font.BOLD,28));
        b6.setBounds(1100,550,350,60);
        b6.addActionListener(this);
        add(b6);






        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("./Icon/OIP (6).jpeg"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);



        //frame will be full screen
        setSize(1550,1080);
        setLayout(null); // so to use set bounds
        setLocation(0,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1){
            //from login class you will have to pass it as you directly come here'
            new Deposit(formno,pin);
            setVisible(false);

        }
        if(e.getSource()==b6){
            JOptionPane.showMessageDialog(null,"Thank you");
            //here code exits not frame
            //for closing frame set visibility as fasle

            System.exit(0);
        }
        if(e.getSource()==b2){
            //cash withdrawl
            new Withdrawl(pin,formno);
            setVisible(false);


        }
        if(e.getSource()==b3){
            new Statement(formno,pin);
            setVisible(false);
        }
        if(e.getSource()==b5){
            //balance enquiry
            new BalanceEnquiry(formno,pin);
            setVisible(false);
        }
        if (e.getSource()==b4){
            new PinChange(formno,pin);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MainScreen("","");
    }

}

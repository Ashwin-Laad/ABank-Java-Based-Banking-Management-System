package Bank.System.BankFeatures;

import Bank.System.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.MissingFormatArgumentException;
import java.util.jar.Manifest;

public class PinChange extends JFrame implements ActionListener {
JButton b,b1;
String form;
String pin;
JPasswordField p1,p11,p111;
    PinChange(String form,String pin){
        this.form=form;
        this.pin=pin;
        JLabel l=new JLabel("Change your Pin ");
        l.setFont(new Font("Ralway",Font.BOLD,30));
        l.setBounds(300,10,600,100);
        add(l);

        JLabel l1=new JLabel("Enter your Current Pin ");
        l1.setFont(new Font("Ralway",Font.BOLD,20));
        l1.setBounds(80,150,600,30);
        add(l1);

         p1=new JPasswordField();
        p1.setBounds(80,200,80,25);
        p1.setFont(new Font("Ralway",Font.BOLD,20));
        add(p1);

        JLabel l11=new JLabel("Enter your New Pin ");
        l11.setFont(new Font("Ralway",Font.BOLD,20));
        l11.setBounds(580,100,600,30);
        add(l11);

        p11=new JPasswordField();
        p11.setBounds(580,150,80,25);
        p11.setFont(new Font("Ralway",Font.BOLD,20));
        add(p11);

        JLabel l111=new JLabel("ReEnter new pin");
        l111.setFont(new Font("Ralway",Font.BOLD,20));
        l111.setBounds(580,200,600,30);
        add(l111);

         p111=new JPasswordField();
        p111.setBounds(580,250,80,25);
        p111.setFont(new Font("Ralway",Font.BOLD,20));
        add(p111);

        b=new JButton("Back");
        b.setFont(new Font("Ralway",Font.BOLD,20));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.white);
        b.setBounds(550,350,100,50);
        b.addActionListener(this);
        add(b);

        b1=new JButton("Enter");
        b1.setFont(new Font("Ralway",Font.BOLD,20));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.setBounds(700,350,100,50);
        b1.addActionListener(this);
        add(b1);




        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("./Icon/OIP (4).jpeg"));
        Image i2=i1.getImage().getScaledInstance(850,480, Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,850,480);
        add(l3);


        setLayout(null);
        //set frame size
        setSize(850,480);

        //size set but frame is not yet visivble-> make it visible
        setVisible(true);
        //now bydefault fram from left hand side open at centre
        setLocation(450,200);

    }

    public static void main(String[] args) {
        new PinChange("","");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //first check if new password and re enter same or not
        String pinO=p1.getText();
        String new1=p11.getText();
        String new2=p111.getText();

        if(e.getSource()==b1){
            if(new1.equals("") || new2.equals("") || pinO.equals("")){
                JOptionPane.showMessageDialog(null,"Enter the pin");
                return;
            }
            if(new1.equals(new2)){
                //yes they are same check if the old pin is correct or not
                try {

                    Con c = new Con();
                    String query = "select pin from logindetails where form_no=" + form;
                    ResultSet rs = c.statement.executeQuery(query);
                    if(rs.next()){
                    if(rs.getString("pin").equals(pinO)) {
                        //here old pin also matches so update in the db
                        String query1 = "update logindetails set pin= " + new1 + " where form_no=" + form;
                        String query2 = "update accountdetails set pin= " + new1 + " where form_no=" + form;
                        String query3 = "update banktransactions set pin= " + new1 + " where form_no=" + form;

                        c.statement.executeUpdate(query1);
                        c.statement.executeUpdate(query2);
                        c.statement.executeUpdate(query3);

                        JOptionPane.showMessageDialog(null, "Pin has been changed");
                        setVisible(false);
                        new MainScreen(form, pin);


                    } else{
                        JOptionPane.showMessageDialog(null,"No record found");
                        return;
                    }
                    }

                }catch (Exception e1){
                    System.out.println(e1.getMessage());
                }

            }
            else{
                //not equal
                JOptionPane.showMessageDialog(null,"Re enter new pin correclty");
                return;

            }
        }
        if(e.getSource()==b){
            new MainScreen(form,pin);
            setVisible(false);

        }
    }

}

package Bank.System.BankFeatures;

import Bank.System.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {
    String pin;
    String form;

    //in differnet package will be used so make constructor public
    JTextField text;

    JButton b1,b2,b3,submit,s;

    public Withdrawl(String pin,String form){
        //make withdrawl similar to deposit
        this.pin=pin;
        this.form=form;

        JLabel label4=new JLabel("Max amount to withdraw Rs.10000");
        label4.setFont(new Font("Raleway",Font.BOLD,30));
        label4.setBounds(560,50,500,35);
        add(label4);

        JLabel label1=new JLabel("Enter Amount to Withdraw");
        label1.setFont(new Font("Raleway",Font.BOLD,30));
        label1.setBounds(620,130,400,35);
        add(label1);

        text=new JTextField("  ₹");
        text.setFont(new Font("Raleway",Font.BOLD,25));
        text.setBounds(100,250,400,50);
        add(text);

        b1=new JButton("₹2000");
        b1.setBackground(new Color(0, 253, 231));
        b1.setFont(new Font("Raleway",Font.BOLD,25));
        b1.setBounds(1000,250,250,30);
//        b1.addActionListener(this);
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

        submit=new JButton("Withdraw");
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

                if(amount.equals("  ₹")) JOptionPane.showMessageDialog(null,"Enter the amount to withdraw");
                else{
                    //establish the connection
                    Con c=new Con();

                    Date date=new Date();
                    String query="select * from banktransactions where form_no= "+form;
                    ResultSet resultset=c.statement.executeQuery(query);

                    int balance=0;
                    while(resultset.next()){
                        String amountStr=resultset.getString("amount");
                        //clean the string first
                        //remove the symbols
                        //.trim removes the white spaces just in case


                        amountStr=amountStr.replaceAll("[₹$,]", "").trim();
                        //data is there
                        if(resultset.getString("type").equals("deposit")){
                            //add all deposit amounts
                            balance+=Integer.parseInt(amountStr);
                        }

                        else{
                            //either depoist or withdrawl so - the prev withdrawsl
                            balance-=Integer.parseInt(amountStr);
                        }
                    }

                    //now check if sufficient balance
                    amount=amount.replaceAll("[₹$,]", "").trim();
                    if(balance< Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null,"Insufficient Balance");

                    }
                    else{
                        //balance is sufficient update the bank transaction table
                        //+notify user for succ transaction
                        try{
                            Con c1=new Con();
                            Date dt=new Date();
                            String q1="Insert into banktransactions values('"+ form+"','"+ pin+"','"+ dt+"','withdrawl','"+ amount+"')";

                            c1.statement.executeUpdate(q1);

                            JOptionPane.showMessageDialog(null,"Withdrawl successful");
                            //once withdrawl succ take back to main page

                            new MainScreen(form,pin);
                            setVisible(false);

                        }catch (Exception E1){
                            System.out.println(E1.getMessage());
                        }

                    }


//                    String query="Insert into banktransactions values('"+form+"','"+pin+"','"+date+"','deposit','"+amount+"')";
//                    c.statement.executeUpdate(query);
//                    JOptionPane.showMessageDialog(null,"Rs. "+amount+" is deposited Succssfully");

                    //go to next frame--for now make visible equal to false
                    //from here goes to the main screen

                    //now get the value that user enter-->amount

                    //check if the withdraw amout is < deposited if yes succesful




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
        new Withdrawl("","");

    }
}

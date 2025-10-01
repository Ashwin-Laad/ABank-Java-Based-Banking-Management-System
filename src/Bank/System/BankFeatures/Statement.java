package Bank.System.BankFeatures;

import Bank.System.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Statement extends JFrame implements ActionListener {

    String form;
    String pin;
    JButton b;

    Statement(String form,String pin){
        this.form=form;
        this.pin=pin;


        JLabel l3=new JLabel("Banking App");
        l3.setFont(new Font("System", Font.BOLD,15));
        l3.setBounds(150,20,200,20);
        add(l3);


        //to store my card number dynamically as we only have form

        JLabel l4=new JLabel("");

        l4.setBounds(20,80,300,20);
        add(l4);


        //to store the account details

        JLabel l5=new JLabel("");

        l5.setBounds(20,400,300,20);
        add(l5);


        b=new JButton("Exit");
        b.setBounds(20,500,100,25);
        b.addActionListener(this);
        add(b);


        //now get value from db and insert dynamically in label

        try{

            Con c=new Con();
//            String query="select * from logindetails where form_no="+ form;
            String query = "select * from logindetails where form_no='" + form + "'";

            ResultSet rs=c.statement.executeQuery(query);

            while(rs.next()){
                l4.setText("Card Number  "+rs.getString("card_number").substring(0,4)+"XXXXXXXX"+ rs.getString("card_number").substring(12));




            }

            //Now Print all the withdraw and deposit


            String query1 = "select * from banktransactions where form_no='" + form + "'";

            ResultSet resultset=c.statement.executeQuery(query1);

            JPanel transactionsPanel = new JPanel();
            transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
            transactionsPanel.setBounds(20, 140, 350, 400); // size as needed
            transactionsPanel.setBackground(Color.BLACK);
            transactionsPanel.setForeground(Color.white);
            add(transactionsPanel);

            int balance = 0;
            while(resultset.next()){
                String date = resultset.getString("date");
                String type = resultset.getString("type");
                String amountStr = resultset.getString("amount").replaceAll("[₹$,]", "").trim();

                // Create a label for each transaction row
                JLabel rowLabel = new JLabel(date + "    " + type + "    " + amountStr);
                transactionsPanel.add(rowLabel); // add to panel

                // Update balance
                if(type.equals("deposit")){
                    balance += Integer.parseInt(amountStr);
                } else {
                    balance -= Integer.parseInt(amountStr);
                }
            }

// Wrap panel in scroll pane (optional, if too many rows)
            JScrollPane scrollPane = new JScrollPane(transactionsPanel);
            scrollPane.setBounds(20, 140, 350, 400);
            add(scrollPane);

// Show balance
            l5.setText("Your balance: " + balance);





        }catch (Exception E){
            System.out.println(E.getMessage());
        }








        getContentPane().setBackground(new Color(0, 253, 232));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);
        setVisible(true);


    }

    public static void main(String[] args) {
        new Statement("","");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new MainScreen(form,pin);

    }
}

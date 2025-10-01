package Bank.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp2 extends JFrame implements ActionListener {
    String formno;
    JComboBox comboBox,comboBox1,comboBox2;
    JTextField t1,t2;
    JButton b1;
    JRadioButton r1,r2;

 SignUp2(String form){

     super("Form");
     this.formno=form;


     //make the labels

     JLabel label=new JLabel("Additional Details");
     label.setFont(new Font("Raleway",Font.BOLD,22));
     label.setBounds(300,60,600,40);
     add(label);

     //new info needed--> religion
     JLabel label1=new JLabel("Religion");
     label1.setFont(new Font("Raleway",Font.BOLD,18));
     label1.setBounds(100,200,100,30);
     add(label1);
//we will have an array to store
     //dropdown via combo box

     String religion[]={"Hindu","Muslim","Sikh","Christian","Other"};
     comboBox=new JComboBox(religion);
     comboBox.setBackground(Color.CYAN);
     comboBox.setFont(new Font("Raleway",Font.BOLD,14));
     comboBox.setBounds(350,200,320,30);
     add(comboBox);


     //To make for category
     JLabel label11=new JLabel("Category");
     label11.setFont(new Font("Raleway",Font.BOLD,18));
     label11.setBounds(100,250,100,30);
     add(label11);
//we will have an array to store
     //dropdown via combo box

     String category[]={"General","OBC","SC","ST","Other"};
     comboBox1=new JComboBox(category);
     comboBox1.setBackground(Color.CYAN);
     comboBox1.setFont(new Font("Raleway",Font.BOLD,14));
     comboBox1.setBounds(350,250,320,30);
     add(comboBox1);



     //for income
     JLabel label111=new JLabel("Income");
     label111.setFont(new Font("Raleway",Font.BOLD,18));
     label111.setBounds(100,300,100,30);
     add(label111);
//we will have an array to store
     //dropdown via combo box

     String income[]={"<2.5L","<5L","<10L",">10L"};
     comboBox2=new JComboBox(income);
     comboBox2.setBackground(Color.CYAN);
     comboBox2.setFont(new Font("Raleway",Font.BOLD,14));
     comboBox2.setBounds(350,300,320,30);
     add(comboBox2);

     JLabel label1111=new JLabel("Pan Number");
     label1111.setFont(new Font("Raleway",Font.BOLD,18));
     label1111.setBounds(100,350,200,30);
     add(label1111);

     t1=new JTextField();
     t1.setBounds(350,350,300,30);
     t1.setFont(new Font("Raleway",Font.BOLD,18));
     add(t1);


     JLabel label11111=new JLabel("Aadhar Number");
     label11111.setFont(new Font("Raleway",Font.BOLD,18));
     label11111.setBounds(100,400,200,30);
     add(label11111);

     t2=new JTextField();
     t2.setBounds(350,400,300,30);
     t2.setFont(new Font("Raleway",Font.BOLD,18));
     add(t2);

    JLabel t=new JLabel("Marrital status");
    t.setFont(new Font("Raleway",Font.BOLD,18));
    t.setBounds(100,450,200,30);
    add(t);

    r1=new JRadioButton("Married");
    r1.setFont(new Font("Raleway",Font.BOLD,18));
    r1.setBounds(350,450,150,30);
     r1.setBackground(new Color(253,250,184));
    add(r1);

     r2=new JRadioButton("UnMarried");
     r2.setFont(new Font("Raleway",Font.BOLD,18));
     r2.setBounds(510,450,150,30);
     r2.setBackground(new Color(253,250,184));
     add(r2);
 ButtonGroup bg=new ButtonGroup();
 bg.add(r1);
 bg.add(r2);






     b1=new JButton("Next");
b1.setBackground(Color.white);
b1.setBounds(400,620,60,30);
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



     setSize(850,750);
     setLocation(450,80);
     setVisible(true);


}

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Object rel=comboBox.getSelectedItem(); //return object type cast to string
            String rel1=(String) rel;

            String caste=(String)comboBox1.getSelectedItem();
            String inc=(String)comboBox2.getSelectedItem();
            String pan=t1.getText();
            String aad=t2.getText();
            String mar=null;

            if(r1.isSelected())  mar="Married";
            else mar="Unmarried";


         //establish connect
            //first enter aadhar
            if(aad.equals("")) JOptionPane.showMessageDialog(null,"Fill the details");
            else{
//                Each value is individually wrapped in ' '.
//
//                        Column names are explicitly mentioned (good practice).
//
//                Prevents SQL syntax errors.
                //establish connect from here all details filled
                Con c1=new Con();
                String query="Insert into Signup2 values('"+formno+"','"+rel1+"','"+caste+"','"+inc+"','"+pan+"','"+aad+"','"+mar+"')";
                c1.statement.executeUpdate(query);

                //call the new page

                new SignUp3(formno);
                setVisible(false);

            }





        }
        catch(Exception E1){
            System.out.println(E1.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUp2("123");
    }

}

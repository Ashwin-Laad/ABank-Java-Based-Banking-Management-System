package Bank.System;

import Bank.System.BankFeatures.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.ResultSet;


//AFTER login you go to the email screen and if succesful you go to the main screen else go back to the o=login screen

public class EmailLogin extends JFrame implements ActionListener {
    JLabel label1,label2,label3;

    String otp="";
    String recipientEmail = "";
    //jtextfield so user can enter the card number and the pin
    JTextField text1;
    JPasswordField pass;

    JButton b1,b2,b3;
    //we need three button
String form;
String pin;


   public EmailLogin(String form,String pin) {
       //Jlabel to actaully add the image
        this.form=form;
        this.pin=pin;
       //make text feilds--> description message
       label1 = new JLabel("Welcome to Email Verification");
       //set colour of font
       label1.setForeground(Color.WHITE);
       label1.setFont(new Font("AvantGarde", Font.BOLD, 28));

       //here frame shift wrt to frame not screen
       label1.setBounds(230, 125, 450, 40);
       add(label1);


       label2 = new JLabel("Enter Your OTP:");
       //set colour of font
       label2.setForeground(Color.WHITE);
       label2.setFont(new Font("Ralway", Font.BOLD, 20));

       //here frame shift wrt to frame not screen
       label2.setBounds(150, 190, 450, 30);
       add(label2);

       //to add the text field
       text1 = new JTextField(15);
       text1.setBounds(350, 190, 230, 30);
       text1.setFont(new Font("Arial", Font.BOLD, 14));
       add(text1);



       //3 button needed now sign in sign up and cancel
       b1 = new JButton("Verify");
       b1.setFont(new Font("Arial", Font.BOLD, 14));
       b1.setForeground(Color.WHITE);
       b1.setBackground(Color.BLACK);
       b1.setBounds(350, 300, 100, 30);
       b1.addActionListener(this);
       add(b1);



       b3 = new JButton("Clear Screen");
       b3.setBackground(Color.BLACK);
       b3.setForeground(Color.WHITE);
       b3.setBounds(300, 350, 230, 30);
       b3.setFont(new Font("Arial", Font.BOLD, 14));
       b3.addActionListener(this);
       add(b3);


//----First add your labels only then image so there is no overlap
       //Image-1 for bank icon
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/icon1.jpeg"));
       Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel image = new JLabel(i3);
       image.setBounds(350, 10, 100, 100);
       add(image);

       //Image for Background
       ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("Icon/modif.jpeg"));
       Image i22 = i11.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
       ImageIcon i33 = new ImageIcon(i22);
       JLabel imagee = new JLabel(i33);
       imagee.setBounds(0, 0, 850, 480);
       add(imagee);


       //STEP-1 ------Set the Frame------
       setLayout(null);
       //set frame size
       setSize(850, 480);
       //for removing minimize title screen etc

       //size set but frame is not yet visivble-> make it visible
       setVisible(true);
       //now bydefault fram from left hand side open at centre
       setLocation(450, 200);


       //here in constructor itself make the frame and send the otp



       //now get the recipient mail from the database
       try {

           //we will store the recipient male address in the SignUp table

           Con c=new Con();
           String query="select email from signup where form_no="+form;
           ResultSet rs=c.statement.executeQuery(query);

           if(rs.next()){
               recipientEmail=rs.getString("email");


           }
           else{
               JOptionPane.showMessageDialog(null,"No record found for email");
               return;
           }


         // replace with recipient
//        String otp = "123456"; // generate dynamically if needed

           Integer db = (int) Math.round(Math.random() * 999999); //number bw 0 and 999999(not inclusive)
           otp = Integer.toString(db);
           System.out.println(otp);


           //sends the otp for verification

//           sendOtpEmail(recipientEmail, otp);---slow operation
           new Thread(() -> sendOtpEmail(recipientEmail, otp)).start();
       }catch (Exception e2){
           System.out.println(e2.getMessage());
       }
   }


    // Hardcoded SendGrid API key (for testing only)
    private static final String SENDGRID_API_KEY = "";

    // Send an OTP email
    public static void sendOtpEmail(String toEmail, String otp) {
        try {
            String subject = "Your OTP Code";
            String htmlContent = "<p>Your OTP is: <b>" + otp + "</b></p>";

            // Build JSON payload (compact, no \n replacements)
            String jsonBody = "{"
                    + "\"personalizations\":[{\"to\":[{\"email\":\"" + toEmail + "\"}],\"subject\":\"" + subject + "\"}],"
                    + "\"from\":{\"email\":\"ashwinlaad861@gmail.com\",\"name\":\"ABank\"},"
                    + "\"content\":[{\"type\":\"text/html\",\"value\":\"" + htmlContent + "\"}]"
                    + "}";

            // Build HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.sendgrid.com/v3/mail/send"))
                    .header("Authorization", "Bearer " + SENDGRID_API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // Send request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check response
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                System.out.println("OTP sent successfully to " + toEmail);
            } else {
                System.err.println("Failed to send OTP: " + response.statusCode() + " -> " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    // Test method
    public static void main(String[] args) {
new EmailLogin("","");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
if(e.getSource()==b1){
    //verify
    if(otp.equals(text1.getText())){
        new MainScreen(form,pin);
        setVisible(false);

    }
    else{
        JOptionPane.showMessageDialog(null,"Incorrect OTP try again");
        return;
    }
}
if (e.getSource()==b3){
    text1.setText("");

}
    }
}

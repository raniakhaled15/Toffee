import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Random;
import java.util.Scanner;

public class emailOtp {
    private String email;

    public emailOtp(String email) {
        this.email = email;
    }


     // @brief Send OTP code to user's email
     //@return

    public int sendOTP() {
        System.out.println("Sending OTP to " + email);
        Random random = new Random();
        int otp = random.nextInt(10000); // generate OTP code from 0 to 9999

        // email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("habibaanwar98@gmail.com", "yowahbwnqmbvcyrm");
            }
        });

        try {
            // email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("habibaanwar98@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("OTP Verification");
            message.setText("Your OTP code is: " + otp);

            // send the email
            Transport.send(message);
            System.out.println("OTP code sent to your email: " + email);
        } catch (MessagingException e) {
            System.out.println("Error sending OTP: " + e.getMessage());
            e.printStackTrace();
        }

        return otp;
    }




    /**
     * @brief Check if the OTP sent to user's email is the same as the code entered by the user
     * @param otp
     * @return
     */
    public boolean verifyOTP(int otp) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the OTP code: ");
        int userOTP = input.nextInt();
        return otp == userOTP; // check if the code entered by the user is the same OTP sent
    }
}

package capstone.model;

import java.util.Random;
import java.util.Properties;


import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

public class EmailVerification {
	private Random code;
	
	public EmailVerification() {
		this.code = new Random();	
	}
	//this method will generate a verification code which we will use to email a new user
	public String getVerificationCode() {
		this.code = new Random();
		int num = code.nextInt(999999);
		String verifCode = String.format("%06d", num);
		return verifCode;
	}
	
	public void sendEmail(User user) {
		String sendEmailTo = user.getEmail();
		String sendEmailFrom = "askthegrandparents@gmail.com";
	}

}

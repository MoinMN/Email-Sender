package com.rms.send_email;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App extends JFrame implements ActionListener	{
	JButton send, reset;
	
	JOptionPane pop = new JOptionPane();
	
	JFrame frame = new JFrame("Email Sender Application");
	JLabel label = new JLabel("Email Sender Application");
	JLabel to = new JLabel("To: ");
	JLabel subject= new JLabel("Subject: ");
	JLabel file = new JLabel("Attachment: ");
	JLabel msg = new JLabel("Message: ");
	JLabel from = new JLabel("From: ");
	JLabel password = new JLabel("Password: ");
	
	JTextField textTo = new JTextField();
	JTextField textSubject = new JTextField();
	JTextField textFile = new JTextField();
	JTextField textFrom = new JTextField();
	
	JTextArea textMsg = new JTextArea();
	
	JPasswordField textPassword = new JPasswordField();
	
	public App()	{
		String image = "C:\\Users\\Moin MN\\OneDrive\\Pictures\\Saved Pictures\\avatar.jpg";
		Font myFontHead = new Font("Times New Roman",Font.PLAIN,24);
		Font myFont = new Font("Times New Roman",Font.PLAIN,20);
		Font myFontText = new Font("Times New Roman",Font.PLAIN,16);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(820, 690);	//display window size
		frame.setLayout(null);
		
		ImageIcon icon = new ImageIcon(image);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon.getImage());
		//adding heading
		label.setFont(myFontHead);
		label.setBounds(275, 20, 300, 50);
		frame.add(label);
		
		
		//"From: " text
		from.setFont(myFont);
		from.setLayout(null);
		from.setBounds(40, 100, 150, 50);
		frame.add(from);		
		
		//From textField
		frame.setLayout(null);
		textFrom.setBounds(165, 100, 500, 40);
		textFrom.setFont(myFontText);
		frame.add(textFrom);		
		
		//"password: " text
		password.setFont(myFont);
		password.setLayout(null);
		password.setBounds(40, 150, 150, 50);
		frame.add(password);
		
		//password text field
		frame.setLayout(null);
		textPassword.setBounds(165, 150, 500, 40);
		textPassword.setFont(myFontText);
		frame.add(textPassword);

		
		//to text
		to.setFont(myFont);
		to.setLayout(null);
		to.setBounds(40, 200, 150, 50);
		frame.add(to);
		
		//to text field		
		frame.setLayout(null);
		textTo.setBounds(165, 200, 500, 40);
		textTo.setFont(myFontText);
		frame.add(textTo);
		
		//subject text
		subject.setFont(myFont);
		subject.setLayout(null);
		subject.setBounds(40, 250, 150, 50);
		frame.add(subject);
		
		//subject text field
		frame.setLayout(null);
		textSubject.setBounds(165, 250, 500, 40);
		textSubject.setFont(myFontText);
		frame.add(textSubject);
		
		//attachment text
		file.setFont(myFont);
		file.setLayout(null);
		file.setBounds(40, 300, 150, 50);
		frame.add(file);
		
		//attachment text field
		textFile.setBounds(165, 300, 500, 40);
		textFile.setLayout(null);
		textFile.setFont(myFontText);
		frame.add(textFile);
		
		//message text
		msg.setFont(myFont);
		msg.setLayout(null);
		msg.setBounds(40, 350, 150, 50);
		frame.add(msg);
		
		//message text area
		textMsg.setBounds(165, 350, 500, 200);
		textMsg.setLineWrap(true);
		textMsg.setFont(myFontText);
		frame.add(textMsg);
		
		//submit button 
		send = new JButton("Send");
		send.setBounds(165, 590, 90, 35);
		frame.add(send);
		send.addActionListener(this);
		
		//reset button
		reset = new JButton("Reset");
		reset.setBounds(325, 590, 90, 35);
		frame.add(reset);
		reset.addActionListener(this);
		
		frame.setVisible(true);
		
		//when reset button is clicked
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textTo.setText("");
				textMsg.setText("");
				textSubject.setText("");
				textFile.setText("");			
				textFrom.setText("");
				textPassword.setText("");
			}
			
		});
		
		//when send button is clicked
		send.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e)	{ 
				//reading input from text field
				String to = textTo.getText();	
				String subject = textSubject.getText();
				String message = textMsg.getText();
				String path = textFile.getText();
				String from = textFrom.getText();
				String password = textPassword.getText();
				
				//sending info sendAttach method
				sendAttach(message, subject, to, from, path, password);
				
				
				
			}

			private void sendAttach(String message, String subject, String to, final String from, String path, final String password) {
				//variable for mails
				String host = "smtp.gmail.com";

				//get the system properties
				Properties properties = System.getProperties();
				
				//setting important to properties object
				
				//host set
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port", "465");
				properties.put("mail.smtp.ssl.enable", "true");
				properties.put("mail.smtp.auth", "true");
				

				//to get the session object...
				
				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);	
						//javaemailtest001@gmail.com						
						//itwcfhfazjqdgolm
						//dshbgpysenukbbup
					}
				});
				
				//session.setDebug(true);	to display the debuging
				
				
				MimeMessage m = new MimeMessage(session);
				
				try	{
					//from mail
					m.setFrom(from);
					
					//adding recipient to message
					m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					
					//adding subject to message
					m.setSubject(subject);
					
					//adding text to message
					m.setText(message);
					
					
					MimeMultipart mimeMultipart = new MimeMultipart();
					
					MimeBodyPart textMime = new MimeBodyPart();
					
					MimeBodyPart fileMime = new MimeBodyPart();
					
					try	{
						textMime.setText(message);	//adding message to textMime
						
						File file = new File(path);
						fileMime.attachFile(file);
						
						mimeMultipart.addBodyPart(textMime);
						
						mimeMultipart.addBodyPart(fileMime);
						
					}	catch(Exception e)	{
						JOptionPane.showMessageDialog(null, "Wrong details entered!");
					}
					
					if(path.isBlank())	{
						//checking weather attachment is enter or not
					}
					else	
						m.setContent(mimeMultipart);
					
					
					//send message using transport class
					Transport.send(m);
					//pop up 
					int response = JOptionPane.showConfirmDialog(null, "Do you want to send another mail?", "Mail sended Successfully!",
					        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.NO_OPTION) {
					      System.exit(0);
					    } else if (response == JOptionPane.YES_OPTION) {
					      textTo.setText("");
					      textSubject.setText("");
					      textMsg.setText("");
					      textFile.setText("");
					      textFrom.setText("");
					      textPassword.setText("");
					    } else if (response == JOptionPane.CLOSED_OPTION) {
					      System.exit(0);
					    }
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Incorrect ID, Password");
				}
			} 
		});
	}
	
	
    public static void main( String[] args )	{
    	App a = new App();
    	
    	
    }

	public void actionPerformed(ActionEvent e) {
		if(textSubject.getText().isEmpty() || textMsg.getText().isEmpty() || textTo.getText().isEmpty() || textFrom.getText().isEmpty() || textPassword.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "The Field is Empty.");
		
	}
}

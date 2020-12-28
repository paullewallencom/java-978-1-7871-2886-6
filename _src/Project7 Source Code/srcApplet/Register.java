import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Register extends JPanel implements ActionListener{
	JLabel userL = new JLabel("Choose a Username: ");
	JTextField userTF = new JTextField();
	JLabel passL = new JLabel("Password");
	JPasswordField passTF = new JPasswordField();
	JLabel passLC = new JLabel("Confirm Password");
	JPasswordField passC = new JPasswordField();
	JButton register = new JButton("Register");
	JButton back = new JButton("Back");
	URL url;
	public Register(){
		JPanel loginP = new JPanel();
		loginP.setLayout(new GridLayout(4,2));
		loginP.add(userL);
		loginP.add(userTF);
		loginP.add(passL);
		loginP.add(passTF);
		loginP.add(passLC);
		loginP.add(passC);
		loginP.add(register);
		loginP.add(back);
		register.addActionListener(this);
		back.addActionListener(this);
		add(loginP);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == register && passTF.getPassword().length >0 && userTF.getText().length() >0){
			String pass = new String(passTF.getPassword());
			String confirm = new String(passC.getPassword());
			if(pass.equals(confirm)){
				try {
					url = new URL("http://localhost:8080/EditorServlet/SignUp");
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					md.update(pass.getBytes());
					byte byteData[] = md.digest();
					StringBuffer sb = new StringBuffer();
					for(int i = 0; i < byteData.length; i++)
						sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));
					out.write(userTF.getText()+" "+sb.toString());
					out.close();
					int rc = connection.getResponseCode();
					if(rc == HttpURLConnection.HTTP_ACCEPTED){
						Login login = (Login) getParent();
						login.cl.show(login, "login");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == back){
			Login login = (Login) getParent();
			login.cl.show(login, "login");
		}
	}
}

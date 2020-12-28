import java.applet.Applet;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends Applet implements ActionListener{
	JLabel userL = new JLabel("Username: ");
	JTextField userTF = new JTextField();
	JLabel passL = new JLabel("Password:");
	JPasswordField passTF = new JPasswordField();
	JPanel loginP = new JPanel(new GridLayout(3,2));
	JPanel panel = new JPanel();
	JButton login = new JButton("Login");
	JButton register = new JButton("Register");
	CardLayout cl;
	URL url;
	public void init(){
		setLayout(new CardLayout());
		loginP.add(userL);
		loginP.add(userTF);
		loginP.add(passL);
		loginP.add(passTF);
		login.addActionListener(this);
		register.addActionListener(this);
		loginP.add(login);
		loginP.add(register);
		panel.add(loginP);
		add(panel, "login");
		cl = (CardLayout) getLayout();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == login){
			try {
				url = new URL("http://localhost:8080/EditorServlet/Verify");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				String pass = null;
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(new String(passTF.getPassword()).getBytes());
				byte byteData[] = md.digest();
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < byteData.length; i++)
					sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));
				out.write(userTF.getText()+" "+sb.toString());
				out.close();
				int rc = connection.getResponseCode();
				if(rc == HttpURLConnection.HTTP_ACCEPTED){
					add(new FileBrowser(userTF.getText()), "fb");
					cl.show(this, "fb");
				}
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				System.out.println(in.readLine());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == register){
			add(new Register(), "register");
			cl.show(this, "register");
		}
	}
}

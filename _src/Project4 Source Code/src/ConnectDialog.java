import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.*;


public class ConnectDialog extends JDialog implements ActionListener{

	boolean isCancelled = true;
	JLabel lhost = new JLabel("Host Name");
	JTextField host = new JTextField();
	JLabel lport = new JLabel("Port");
	JTextField port = new JTextField();
	JLabel ldatabase = new JLabel("Database");
	JTextField database = new JTextField();
	JLabel luser = new JLabel("User Name");
	JTextField user = new JTextField();
	JLabel lpass = new JLabel("Password");
	JPasswordField pass = new JPasswordField();
	
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("Cancel");
	
	Properties props;
	
	public ConnectDialog(JFrame owner, String title, Properties p){
		super(owner, title, true);
		setSize(300, 200);
		setLocation(250,200);
		props = new Properties(p);
		ok.setPreferredSize(new Dimension(75, 25));
		ok.addActionListener(this);
		cancel.setPreferredSize(new Dimension(75, 25));
		cancel.addActionListener(this);
		
		JPanel cpanel = new JPanel();
		JPanel cpanel2 = new JPanel();
		
		cpanel.setLayout(new GridLayout(5,2));
		
		cpanel.add(lhost);
		cpanel.add(host);
		cpanel.add(lport);
		cpanel.add(port);
		cpanel.add(ldatabase);
		cpanel.add(database);
		cpanel.add(luser);
		cpanel.add(user);
		cpanel.add(lpass);
		cpanel.add(pass);
		
		cpanel2.add(ok);
		cpanel2.add(cancel);
		
		add(cpanel, BorderLayout.NORTH);
		add(cpanel2, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ok)
			isCancelled = false;
		
		this.dispose();
	}
	
	public Properties getProps(){
		props.setProperty("Database", database.getText());
		props.setProperty("Host_Name", host.getText());
		props.setProperty("Port", port.getText());
		props.setProperty("User_Name", user.getText());
		return props;
	}

}

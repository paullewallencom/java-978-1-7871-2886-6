import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.*;


public class ConnectDialog extends JDialog implements ActionListener{
	boolean isCanceled = true;

	private Properties props = null;

	private JLabel      l_db = new JLabel("Database");
	private JTextField tf_db = new JTextField();
	private JLabel      l_user = new JLabel("User Name");
	private JTextField tf_user = new JTextField();
	private JLabel      l_pswd = new JLabel("Password");
	public JPasswordField tf_pswd = new JPasswordField();
	private JLabel      l_host = new JLabel("Host Name");
	private JTextField tf_host = new JTextField();
	private JLabel      l_port = new JLabel("Port");
	private JTextField tf_port = new JTextField();
	private JButton buttonOK = new JButton("OK");
	private JButton buttonCancel = new JButton("Cancel");
	
	public ConnectDialog(JFrame owner, String title, boolean modal, Properties p) {
	    super(owner, title, modal);
	    setSize(300, 200);
	    setLocation(250,200);
	    props = new Properties(p);
	    buttonOK.setPreferredSize(new Dimension(75,25));
	    buttonCancel.setPreferredSize(new Dimension(75, 25));
	    buttonOK.addActionListener(this);
	    buttonCancel.addActionListener(this);
	    JPanel cpanel = new JPanel();
	    JPanel cpanel2 = new JPanel();
	    cpanel.setLayout(new GridLayout(6,2));
	    tf_host.setText("localhost");
	    tf_port.setText("3306");
	    tf_db.setText("test");
	    tf_user.setText("root");
	    cpanel.add(l_host);
	    cpanel.add(tf_host);
	    cpanel.add(l_port); 
	    cpanel.add(tf_port);
	    cpanel.add(l_db);
	    cpanel.add(tf_db);
	    cpanel.add(l_user);
	    cpanel.add(tf_user);
	    cpanel.add(l_pswd);
	    cpanel.add(tf_pswd);
	    cpanel2.add(buttonOK);
	    cpanel2.add(buttonCancel);
	    
	    
	    
	    add(cpanel, BorderLayout.NORTH);
	    add(cpanel2, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == buttonOK ) { isCanceled=false; }

	    // Close dialog after OK or Cancel button clicked
	    this.dispose();
	}
	public Properties getProps() {
        props.put("DATABASE",tf_db.getText());
        props.put("USER_NAME",tf_user.getText());
        props.put("HOST_NAME",tf_host.getText());
        props.put("PORT",tf_port.getText());
        props.put("SCHEMA", tf_db.getText());
        return props;
}

}

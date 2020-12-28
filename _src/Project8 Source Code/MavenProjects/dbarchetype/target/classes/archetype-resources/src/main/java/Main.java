import java.util.Properties;

import javax.swing.*;


public class Main extends JFrame {
	static Database dbpanel;
	JTextArea sql = null;
    JTextArea results = null;
    static Connector conn;
	public Main(){
		super("Database");
		setSize(800, 600);
		
	}
	public static void main(String[] args){
		Properties props = new Properties();
		Main main = new Main();
		ConnectDialog dialog = new ConnectDialog(main, "Database Connection", true, props);
        dialog.setVisible(true);
        if ( dialog.isCanceled ) {
          System.exit(0);
        }
        conn = new Connector(dialog.getProps(), new String(dialog.tf_pswd.getPassword()));
        dbpanel = new Database(conn);
		main.add(dbpanel);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conn.open();
	}
}

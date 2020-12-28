import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Editor extends JPanel implements ActionListener {
	File file;
	JButton save = new JButton("Save");
	JButton savec = new JButton("Save and Close");
	JTextArea text = new JTextArea(20, 40);
	URL url;
	public Editor(String s){
		save.addActionListener(this);
		savec.addActionListener(this);
		try {
			url = new URL("http://localhost:8080/EditorServlet/FileRead");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(s);
			out.close();
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = input.readLine();
			while(line != null){
				text.append(line+"\n");
				line = input.readLine();
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(save);
		add(savec);
		add(text);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			url = new URL("http://localhost:8080/EditorServlet/FileWrite");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(text.getText());
			out.close();
			int rc = connection.getResponseCode();
			if(e.getSource() == savec){
				Login login = (Login) getParent();
				login.cl.show(login, "fb");
			} 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

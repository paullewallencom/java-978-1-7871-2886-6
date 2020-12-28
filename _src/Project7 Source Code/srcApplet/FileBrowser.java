import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class FileBrowser extends JPanel implements ActionListener{
	JLabel label = new JLabel("File List: ");
	JButton newFile = new JButton("New File");
	JButton open = new JButton("Open");
	JTextField newFileTF = new JTextField(10);
	ButtonGroup bg;
	URL url;
	public FileBrowser(String dir){
		try {
			url = new URL("http://localhost:8080/EditorServlet/ReadDirectory");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			ArrayList<String> list = new ArrayList<String>();
			String line;
			while((line = in.readLine())!=null)
				list.add(line);
			JPanel fileList = new JPanel(new GridLayout(list.size()+3, 1));
			fileList.add(label);
			bg = new ButtonGroup();
			for(String file : list){
				JRadioButton radio = new JRadioButton(file);
				radio.setActionCommand(file);
				bg.add(radio);
				fileList.add(radio);
			}
			JPanel newP = new JPanel();
			newP.add(newFileTF);
			newP.add(newFile);
			newFile.addActionListener(this);
			open.addActionListener(this);
			fileList.add(open);
			fileList.add(newP);
			add(fileList);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Login login = (Login) getParent();
		if(e.getSource() == open){
			login.add(new Editor(bg.getSelection().getActionCommand()), "editor");
			login.cl.show(login, "editor");
		}
		if(e.getSource() == newFile){
			String file = newFileTF.getText()+".txt";
			if(newFileTF.getText().length() > 0){
				login.add(new Editor(file), "editor");
				login.cl.show(login, "editor");
			}
		}
	}

}

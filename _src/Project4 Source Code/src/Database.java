import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Database extends JPanel{
	static JTextArea sql = new JTextArea();
	JLabel prompt = new JLabel("Please enter your SQL statement below:");
	JButton exe = new JButton("Execute");
	JButton reset = new JButton("Reset");
	static JTable table = new JTable();
	static DefaultTableModel model = (DefaultTableModel) table.getModel();
	static Connector dc;
	
	public Database(Connector conn){
		add(prompt);
		dc = conn;
		JScrollPane spane = new JScrollPane(sql);
		spane.setPreferredSize(new Dimension(750, 100));
		add(spane);
		exe.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				execute();
			}
			
		});
		reset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setColumnCount(0);
				model.setRowCount(0);
			}
			
		});
		add(exe);
		add(reset);
		JScrollPane rpane = new JScrollPane(table);
		rpane.setPreferredSize(new Dimension(750, 400));
		add(rpane);
	}
	private static void execute(){
		model.setColumnCount(0);
		model.setRowCount(0);
		String s = sql.getText();
		try{
			if((s.length()>=6 && s.substring(0,6).equalsIgnoreCase("SELECT"))){
				ResultSet rs = dc.executeQuery(s);
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i = 1; i<= rsmd.getColumnCount(); i++){
					model.addColumn(rsmd.getColumnName(i));
				}
				while(rs.next()){
					String[] data = new String[rsmd.getColumnCount()];
					for(int i = 1; i<= rsmd.getColumnCount(); i++){
						data[i-1] = rs.getString(i);
					}
					model.addRow(data);
				}
			}
			else
				dc.executeUpdate(s);
		}
		catch (SQLException e){
			System.out.println("Error: " + e);
		}
	}
}

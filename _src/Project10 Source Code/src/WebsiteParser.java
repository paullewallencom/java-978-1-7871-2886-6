import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebsiteParser extends JTabbedPane{
	Document doc;
	JScrollPane spane;
	
	WebsiteParser(){
		try {
			doc = Jsoup.connect("http://google.com").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getLinks();
		addTab("Links", spane);
		addTab("Images", new ImageGrabber(doc));
		addTab("Word Count", new WordCount(doc));
	}
	public void getLinks(){
		Elements links = doc.getElementsByTag("a");
		JPanel linkpanel = new JPanel();
		linkpanel.setLayout(new GridLayout(links.size(), 1));
		for(Element link : links){
			String l = link.attr("href");
			if(l.length()>0){
				if(l.length()<4)
					l = doc.baseUri()+l.substring(1);
				else if(!l.substring(0,4).equals("http"))
					l = doc.baseUri()+l.substring(1);
				SwingLink label = new SwingLink(link.text(),l);
				linkpanel.add(label);
				
			}
		}
		spane = new JScrollPane(linkpanel);
		spane.setPreferredSize(new Dimension(350,350));
	}
	public static void main(String[] args){
		JFrame frame = new JFrame("Website Parser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WebsiteParser wp = new WebsiteParser();
		frame.add(wp);
		frame.setVisible(true);
		frame.setSize(400,400);
	}
}

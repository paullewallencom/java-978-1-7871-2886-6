

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FileRead
 */
@WebServlet("/FileRead")
public class FileRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileRead() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = request.getReader();
		String data = reader.readLine();
		HttpSession session = request.getSession();
		String f = "C:\\EditorServlet\\"+session.getAttribute("user")+"\\"+data;
		File file = new File(f);
		session.setAttribute("file", f);
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		if(file.exists()){
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while(line != null){
				writer.write(line);
				line = in.readLine();
			}
			in.close();
		}
		writer.flush();
		writer.close();
	}

}

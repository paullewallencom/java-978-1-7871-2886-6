

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Verify
 */
@WebServlet("/Verify")
public class Verify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verify() {
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
		BufferedReader input = new BufferedReader(new FileReader("C:\\EditorServlet\\passwords.txt"));
		StringTokenizer userData = new StringTokenizer(data);
		String user = userData.nextToken();
		String pass = userData.nextToken();
		String storedPass = null;
		String line = input.readLine();
		while(line != null){
			StringTokenizer st = new StringTokenizer(line);
			if(user.equals(st.nextToken())){
				storedPass = st.nextToken();
				break;
			}
			line = input.readLine();
		}
		input.close();
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		if(storedPass.equals(pass)){
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			writer.write(user + " has logged in");
		}
		else{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			writer.write("Unable to authenticate "+ user);
		}
		writer.flush();
		writer.close();
	}

}

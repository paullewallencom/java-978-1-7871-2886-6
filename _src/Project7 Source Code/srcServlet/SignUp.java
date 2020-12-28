

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		StringTokenizer userData = new StringTokenizer(data);
		String user = userData.nextToken();
		String pass = userData.nextToken();
		BufferedReader input = new BufferedReader(new FileReader("C:\\EditorServlet\\passwords.txt"));
		String line = input.readLine();
		while(line != null){
			StringTokenizer st = new StringTokenizer(line);
			if(user.equals(st.nextToken())){
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return;
			}
			line = input.readLine();
		}
		input.close();
		BufferedWriter output = new BufferedWriter(new FileWriter("C:\\EditorServlet\\passwords.txt", true));
		output.write(user+" "+pass+"\n");
		output.close();
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}

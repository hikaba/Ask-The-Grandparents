package capstone.controllerServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import capstone.dao.FriendDao;
import capstone.dao.UserDao;
import capstone.model.User;

/**
 * Servlet implementation class SearchFriendsServlet
 */
@WebServlet("/search")
public class SearchFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDAO;
	private User currentUser;
	
	
       
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDAO = new UserDao();
		
		currentUser = new User();
		
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFriendsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read field from the form and collect data
		String searchQuery = request.getParameter("searchquery");
		String destinationPage;
		int currentUserId;
		int friendshipStatus;
		
		boolean check = userDAO.doesUsernameExist(searchQuery);
		
		//if username exists
		if(check == true) {
			//get a user object from the database
			User otherUser = userDAO.getUserFromUsername(searchQuery);
			request.setAttribute("username", otherUser.getUsername());
			request.setAttribute("fName", otherUser.getfName());
			request.setAttribute("lName", otherUser.getlName());
			request.setAttribute("friendId", otherUser.getID());
			
			HttpSession session = request.getSession(false);
			currentUserId = (int) session.getAttribute("id");
			
			//check if current user and other user are friends
			boolean check2 = FriendDao.isFriends(currentUserId, otherUser.getID());
			//if false, set the status to 2, indicating they aren't friends
			if(check2 == false) {
				friendshipStatus=2;
				request.setAttribute("status", friendshipStatus);
			}
			//if they are, that if they have a connection, set the status of their connection, 0 for request, 1 for friends, 2 rejected
			else if(check2 == true){
				friendshipStatus = FriendDao.getStatus(currentUserId, otherUser.getID());
				
				request.setAttribute("status", friendshipStatus);
				
			}
			
			destinationPage = "/views/searchresults.jsp";
					
		}
		else {
			//redirects back to login and indicates error message
			request.setAttribute("usernameDNE", searchQuery);
			request.setAttribute("noResults", "No results found for");
			destinationPage = "/views/searchnoresults.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	


}

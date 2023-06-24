package subject_management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
	public class subjectservlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private subjectDBUtil subjectDBUtil;
		
		public void init() throws ServletException{
			subjectDBUtil = new subjectDBUtil();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					try {
					insertsubject(request, response);
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "/delete":
					try {
					deletesubject(request, response);
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "/edit":
					try {
					showEditForm(request, response);
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "/update":
					try {
					updatesubject(request, response);
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				default:
					try {
					listsubject(request, response);
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			

		
	  //new 
		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("subject_form.jsp");
			dispatcher.forward(request, response);
		}
		
		//insert
		private void insertsubject(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String name = request.getParameter("name");
			String credit = request.getParameter("credit");
			
			subject newSubject = new subject(name,credit);
			subjectDBUtil.insertsubject(newSubject);
			response.sendRedirect("list");
		}
		
		//delete
		
		private void deletesubject(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int subID = Integer.parseInt(request.getParameter("subID"));
			try {
				subjectDBUtil.deletesubject(subID);
			}catch(Exception e) {
				e.printStackTrace();
		}
			response.sendRedirect("list");
		}
		
		
		
		//edit
		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int subID = Integer.parseInt(request.getParameter("subID"));
			
			subject existingsubject;
			try {
				existingsubject = subjectDBUtil.selectsubject(subID);
				RequestDispatcher dispatcher = request.getRequestDispatcher("subject_form.jsp");
				request.setAttribute("subject", existingsubject);
				dispatcher.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}

		}

		
	//update
		private void updatesubject(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int subID = Integer.parseInt(request.getParameter("subID"));
			String name = request.getParameter("name");
			String credit = request.getParameter("credit");
			subject sub = new subject(subID, name, credit);
			System.out.println("name,"+name+"credit,"+credit);
		    subjectDBUtil.updatesubject(sub);
			response.sendRedirect("list");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("subjectListForm.jsp");
		}
			/*try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/

		//default
		private void listsubject(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			try {
				List<subject> listsubject = subjectDBUtil.selectAllsubjects();
				request.setAttribute("listsubject", listsubject);
				RequestDispatcher dispatcher = request.getRequestDispatcher("subjectListForm.jsp");
				dispatcher.forward(request, response);	
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}


}

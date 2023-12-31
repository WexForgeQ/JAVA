package Servlets;

import Models.Subject;
import database.SubjectsDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateSubjectServlet", value = "/UpdateSubjectServlet")
public class UpdateSubjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String teacher = request.getParameter("teacher");
        String faculty = request.getParameter("faculty");
        String id = request.getParameter("id");
        Subject subject = new Subject(name, teacher, faculty);
        try {
            SubjectsDAO subjectsDAO = new SubjectsDAO();
            if(!subjectsDAO.Update(id, subject)){
                request.setAttribute("UpdateErrorMessage","Error while updating subject");
            }
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("ErrorCode", ex.fillInStackTrace());
            request.setAttribute("ErrorMessage", ex.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

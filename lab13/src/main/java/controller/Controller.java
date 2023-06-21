package controller;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static models.AddNation.AddNationMethod;
import static models.DelNation.DelNationMethod;
import static models.Page.PageMethod;
import static models.Register.RegisterMethod;
import static models.UpdNation.UpdateNationMethod;
import static models.Welcome.WelcomeMethod;

public class Controller extends HttpServlet {
    private static final String COMMAND="command";
    public static final String LOGIN="login";
    public static final String REGISTER="register";
    public static final String PAGE="page";
    public static final String ADD_NATION="addNation";
    public static final String DEL_NATION="delNation";
    public static final String UPD_NATION="updNation";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter(COMMAND);
        switch (command){
            case LOGIN:{
                WelcomeMethod(request,response);
                break;
            }
            case REGISTER:{
                RegisterMethod(request,response);
                break;
            }
            case PAGE:{
                PageMethod(request,response);
                break;
            }
            case ADD_NATION:{
                AddNationMethod(request,response);
                break;
            }
            case DEL_NATION:{
                DelNationMethod(request,response);
                break;
            }
            case UPD_NATION: {
             UpdateNationMethod(request,response);
             break;
            }
            }
        }
    }



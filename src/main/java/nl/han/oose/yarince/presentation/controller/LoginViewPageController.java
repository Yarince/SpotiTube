package nl.han.oose.yarince.presentation.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.oose.yarince.domain.User;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 27/03/2017.
 */
@WebServlet("/index.html")
public class LoginViewPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("Username");
        String password = req.getParameter("Password");
        HttpSession session = req.getSession();
        User user = new User();

        try {
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/login");
            ObjectMapper mapper = new ObjectMapper();

            String input = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"; //this will be the input from the user

            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

            //check if response is successful
            if ((response.getStatus() < 200 && response.getStatus() >= 300))
                throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());

            //Response
            String userString = response.getEntity(String.class);

            //JSON from string to Object
            user = mapper.readValue(userString, User.class);

            if (user != null && response.getStatus() != 204) {
                session.setAttribute("USER", user);
                resp.sendRedirect("/home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

package by.fpmibsu.EasyLearning.controller;

import by.fpmibsu.EasyLearning.action.UserAction;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EasyLearning/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String queryType = request.getParameter("queryType");
        if (queryType.equals("log-out")) {
            request.getSession().invalidate();
            return;
        }

        JSONObject json = new JSONObject();

        var params = request.getParameterNames();
        while (params.hasMoreElements()) {
            var param = params.nextElement();
            json.put(param, request.getParameter(param));
        }

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        UserAction action = new UserAction();

        JSONObject responseJson;
        try {
            responseJson = action.act(json, queryType, userId);
        } catch (ServiceException e) {
            response.sendError(500);
            throw new RuntimeException(e);
        } catch (IncorrectFormDataException e) {
            response.sendError(400);
            throw new RuntimeException(e);
        }

        if (userId == null) {
            if (queryType.equals("create-account") || queryType.equals("check-account")) {
                session.setAttribute("userId", action.getUserId());
                session.setMaxInactiveInterval(-1);
            } else {
                response.sendError(401);
            }
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseJson.toJSONString());
        out.flush();
    }
}

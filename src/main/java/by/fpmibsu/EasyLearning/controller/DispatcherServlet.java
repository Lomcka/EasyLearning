package by.fpmibsu.EasyLearning.controller;

import by.fpmibsu.EasyLearning.action.*;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import org.apache.log4j.BasicConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/EasyLearning/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() {
        BasicConfigurator.configure();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.out.println(request.getPathInfo());
//        Arrays.stream(request.getPathInfo().split("/")).forEach(str -> System.out.println(str + " "));
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String queryType = getQueryType(request);

        if (queryType.equals("log-out")) {
            request.getSession().invalidate();
            return;
        }
        if (queryType.equals("get-cards-to-repeat") || queryType.equals("resend-ok-repeat")) {
            request.getSession().setAttribute("isDataWritten", Boolean.FALSE);
        }

        JSONObject json = getRequestJson(request, response);
        JSONObject responseJson = getResponse(request, response, queryType, json);

        if (queryType.equals("get-cards-to-repeat") || queryType.equals("resend-ok-repeat")) {
            request.getSession().setAttribute("cardsToResend", responseJson);
            request.getSession().setAttribute("isDataWritten", Boolean.TRUE);
            responseJson = new JSONObject();
        }
        if (queryType.equals("resend-ok-repeat2")) {
            responseJson = (JSONObject) request.getSession().getAttribute("cardsToResend");
            request.removeAttribute("cardsToResend");
        }

        writeResponse(response, responseJson);
    }

    private String getQueryType(HttpServletRequest request) {
        var query = request.getPathInfo().split("/");
        if (query.length < 3) {
            throw new RuntimeException("invalid query");
        }
        return query[query.length - 1];
    }

    private Action getAction(HttpServletRequest request) {
        var query = request.getPathInfo().split("/");
        if (query.length < 2) {
            System.out.println("exception");
            throw new RuntimeException("no query");
        }
//        System.out.println("Query: " + query[0]);
        return switch (query[1]) {
            case "user" -> new UserAction();
            case "share" -> new ShareAction();
            case "add" -> new AddAction();
            case "getAll" -> new GetAllAction();
            case "module" -> new ModuleAction();
            case "folder" -> new FolderAction();
            case "resend" -> new ResendAction();
            default -> null;
        };
    }

    private JSONObject getRequestJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        if (!requestData.isEmpty()) {
            try {
                json = (JSONObject) parser.parse(requestData);
            } catch (ParseException e) {
                response.sendError(400);
                throw new RuntimeException(e);
            }
        }

        var params = request.getParameterNames();
        while (params.hasMoreElements()) {
            var param = params.nextElement();
            json.put(param, request.getParameter(param));
        }
        return json;
    }

    private JSONObject getResponse(HttpServletRequest request, HttpServletResponse response,
                                   String queryType, JSONObject json) throws IOException {
        var action = getAction(request);
        Long userId = (Long) request.getSession().getAttribute("userId");

        JSONObject responseJson;
        try {
            if (queryType.equals("resend-ok-repeat2")) {
                while (!(Boolean) request.getSession().getAttribute("isDataWritten")) {}
            }
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
                request.getSession().setAttribute("userId", ((UserAction) action).getUserId());
                request.getSession().setMaxInactiveInterval(-1);
            } else {
                response.sendError(401);
            }
        }
        return responseJson;
    }

    private void writeResponse(HttpServletResponse response, JSONObject responseJson) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseJson.toJSONString());
        out.flush();
    }
}

package by.fpmibsu.EasyLearning.controller;

import by.fpmibsu.EasyLearning.action.*;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() {
//        DataConfigure.configure();
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
        String requestData = request.getReader().lines().collect(Collectors.joining());
        String queryType = request.getParameter("queryType");

        if (queryType.equals("get-cards-to-repeat") || queryType.equals("resend-ok-repeat")) {
            request.getSession().setAttribute("isDataWritten", Boolean.FALSE);
        }

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

        if (queryType.equals("check-account") || queryType.equals("create-account") ||
            queryType.equals("change-login") || queryType.equals("change-security-word") ||
                queryType.equals("get-data-for-settings")) {
            String uri = request.getRequestURI() + "/user?" + jsonToParams(json);
            response.sendRedirect(uri);
            return;
        }

        var action = getAction(queryType);
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

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
                response.sendError(401);
                return;
        }


        if (queryType.equals("get-cards-to-repeat") || queryType.equals("resend-ok-repeat")) {
            request.getSession().setAttribute("cardsToResend", responseJson);
            request.getSession().setAttribute("isDataWritten", Boolean.TRUE);
            responseJson = new JSONObject();
        }
        if (queryType.equals("resend-ok-repeat2")) {
            responseJson = (JSONObject) request.getSession().getAttribute("cardsToResend");
            request.removeAttribute("cardsToRepeat");
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseJson.toJSONString());
        out.flush();
    }

    private Action getAction(String queryType) {
        return switch (queryType) {
            case "share-module" -> new ShareAction();
            case "add-new-module", "add-new-folder" -> new AddAction();
            case "get-modules-and-folders" -> new GetAllAction();
            case "get-module-data", "add-Card", "merge-modules", "get-cards-to-repeat",
                    "resend-ok-repeat", "resend-ok-repeat2" -> new ModuleAction();
            case "merge-folders", "get-modules", "add-module-to-folder" -> new FolderAction();
            default -> null;
        };
    }

    String jsonToParams(JSONObject json) {
        StringBuilder stringBuilder = new StringBuilder();
        json.forEach((key, value) -> stringBuilder.append(key).append("=").append(value).append("&"));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}

package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/teste")
public class TesteServlet extends HttpServlet {

	private static final long serialVersionUID = 8741228436985652127L;
	
	private static final Logger logger = LogManager.getLogger(TesteServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		String mes = sdf.format(new Date());
		logger.info(String.format("Mês: [%s]", mes));
		
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Bootstrap Page</title>");
        out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("<style>");
        out.println("body { background-color: #e0f7fa; }");
        out.println(".container { margin-top: 50px; }");
        out.println(".card { background-color: #a5d6a7; }");
        out.println(".btn-custom { background-color: #4caf50; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<div class=\"card\">");
        out.println("<div class=\"card-body\">");
        out.println("<h5 class=\"card-title\">Bem-vindo!</h5>");
        out.println("<p class=\"card-text\">Esta é uma página de exemplo usando Bootstrap.</p>");
        out.println("<a href=\"#\" class=\"btn btn-custom\">Clique aqui</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>");
        out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
        out.println("</body>");
        out.println("</html>");
	}
}

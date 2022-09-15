package servlet;

import manager.ItemManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("item", itemManager.getAll());
        req.getRequestDispatcher("/WEB-INF/item.jsp").forward(req, resp);
    }
}

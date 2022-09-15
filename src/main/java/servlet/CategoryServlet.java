package servlet;

import manager.CategoryManager;
import model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryManager.getAll();
        req.setAttribute("category", categories);
        req.getRequestDispatcher("/WEB-INF/authors.jsp").forward(req, resp);
    }
}

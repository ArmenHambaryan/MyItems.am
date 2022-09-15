package servlet;


import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/item/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddItemServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();

    private final CategoryManager categoryManager = new CategoryManager();

    private final UserManager userManager = new UserManager();

    private static final String IMAGE_PATH = "C:\\Users\\lenovo\\IdeaProjects\\MyItems.am\\ProfileImages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("category", categoryManager.getAll());
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Part picUrl = req.getPart("picUrl");
        String filename = null;
        if (picUrl.getSize() != 0) {
            long nanoTime = System.nanoTime();
            filename = nanoTime + "_" + picUrl.getSubmittedFileName();
            picUrl.write(IMAGE_PATH + filename);
        }
        itemManager.add(Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .picUrl(filename)
                .user(userManager.getById(user.getId()))
                .build());
        resp.sendRedirect("/item"); //fsyo nkar" qcec, hmi edik inch ooshibka er cuyc cher ta aysinqn che?
       // arandzin servlet? // ed sax show i chunim hmi show servlet vayte
    }//ha chunis ha sarqem arandzin href xrgem index voch grancvacin vercnem iterov tam ha, bayc en categoryaneri vra
    //sxmeluc ?ov parametr bdi tanis or stuges te inchi vraes sxme, orinak es es Glxavor" smeluc ?i hed 0 em xrge
    // or stugem yete 0e uremn showall methods kkanche, yete CHE knshanage categoryai id ine ege, edel kdnem get categoryById i mej u hed kxgrem
    //)))) esinch bard ogika es mtace du guzes l" zut ashxadelu mar en showAlln porce hedo myusnern or avelcnelu exar cen han noric nkarnerov bacadrem
    // okay show chgrancvacin en limit metody tam vercnem grancvacin all tam che ? limit 20 i hedes?ha edig arden grelem methodi mej, bdi prost" kanches ed method"
    // ha tesa  logikan ksem grancvacin tam show all enor limit 20 iferi mej eli ap show alli depqum imast chuni, guzes grancvav exi guzes che, krna lyuboyne tesni
    //inqy kse glxavori mej verjin qani haty tesninq isk usery sax tesni
}

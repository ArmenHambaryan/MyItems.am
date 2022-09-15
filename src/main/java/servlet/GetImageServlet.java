package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/getImage")
public class GetImageServlet extends HttpServlet {

    private static final String IMAGE_PATH = "C:\\Users\\lenovo\\IdeaProjects\\MyItems.am\\ProfileImages";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picUrl = req.getParameter("picUrl");

        String filepath = IMAGE_PATH + picUrl;
        File ImageFile = new File(filepath);
        if (ImageFile.exists()) {
            try (FileInputStream inputStream = new FileInputStream(filepath)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) ImageFile.length());

                OutputStream outputStream = resp.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

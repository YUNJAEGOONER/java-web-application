import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

    // HttpServletRequest - 브라우저(클라이언트)에서 요청을 보낸 모든 정보들에 대한 접근이 가능
    // HttpServletResponse - 서버에서 브라우저(클라이언트)에 값을 전달하고 싶을때

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        PrintWriter out = resp.getWriter();
        out.println(name + " Hello World!!!");
    }

}

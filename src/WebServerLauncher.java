import java.io.File;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class WebServerLauncher {
    public static void main(String[] args) throws Exception{
        String webappDirection = "webapp/";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()){
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        Connector connector = tomcat.getConnector();
        connector.setURIEncoding("UTF-8");
        tomcat.addWebapp("/", new File(webappDirection).getAbsolutePath());
        System.out.print("configuring app with basedir: " + new File("./" + webappDirection).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();

    }
}
package view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class View {

    public static String home_view;

    static {
        try {
            home_view = Files.readString(Paths.get("./src/resources/home.html"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

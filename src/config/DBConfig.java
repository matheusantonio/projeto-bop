package config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.ServiceLoader;

//import com.uff.bop.Requester;
import com.util.APILoader;
//import com.uff.bop.Coinrequest;

public class DBConfig {
    static List<String> resultados;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/notas_db", "root", "root");
    }

    /*
     * public static String loadAPI() throws ClassNotFoundException, IOException {
     * String ans1, ans2; try { Class.forName("com.uff.bop.Requester"); ans1 =
     * Requester.doRequest(); } catch (Exception e) { ans1 =
     * "We did not find the desired class, i am sorry :("; }
     * 
     * try { Class.forName("com.uff.bop.Coinrequest"); ans2 =
     * Coinrequest.doRequest(); } catch (ClassNotFoundException e) { ans2 =
     * "We did not find the desired class, i am really sorry D:"; } catch (Exception
     * e) { ans2 = "Other error"; }
     * 
     * return ans1 + "\n" + ans2;
     * 
     * }
     */

    private static void addString(String s) {
        resultados.add(s);
    }

    public static String loadAPI2() throws ClassNotFoundException, IOException {

        String res = "";
        resultados = new ArrayList<String>();

        ServiceLoader.load(APILoader.class).forEach(i -> {
            try {
                addString(i.doRequest());
            } catch (Exception e) {
                addString("Deu problema!!!");
            }
        });

        for (String s : resultados)
            res += s + "</br>";

        return res;
    }
}
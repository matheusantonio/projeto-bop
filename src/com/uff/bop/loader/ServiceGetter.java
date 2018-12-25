package com.uff.bop.loader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.ServiceLoader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

//import com.uff.bop.Requester;
import com.util.APILoader;
//import com.uff.bop.Coinrequest;

public class ServiceGetter extends HttpServlet{

    private static final long serialVersionUID = 1L;
    static List<String> resultados;
    /*
     * public static String loadAPI2() throws ClassNotFoundException, IOException {
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

    public static String loadAPI() throws ClassNotFoundException, IOException {

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
            res += s + " | ";

        return res;
    }


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();

        try{
            out.println(loadAPI());
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
    }
}
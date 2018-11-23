<html>

    <head>

        <%@ page import="config.DBConfig"%>
        <%@ page import="dao.NotasDAO"%>
        <%@ page import="entidade.Nota"%>
        
        <%@ page import="java.util.ArrayList"%>
        <%@ page import="java.util.List"%>

        <title>Sistema de BOP</title>

    </head>

    <body>
        <h1>Bem vindo ao sistema de BOP!</h1></br>

        <% 
            
            List<Nota> list = NotasDAO.listarNotas();

            for(Nota n : list){
                out.println("<li>" + n.getId() + " " + 
                                     n.getTitulo() + " " + 
                                     n.getDescricao() + "</li>");
            }


            out.println("</br>" + DBConfig.loadAPI2());

        %>
    </body>

</html>
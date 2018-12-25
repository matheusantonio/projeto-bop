<html>

    <head>

        <%@ page import="config.DBConfig"%>
        <%@ page import="dao.NotasDAO"%>
        <%@ page import="entidade.Nota"%>
        <%@ page import="com.uff.bop.loader.ServiceGetter"%>
        
        <%@ page import="java.util.ArrayList"%>
        <%@ page import="java.util.List"%>

        <title>Sistema de BOP</title>

        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script>
            $(document).ready(function() {
                $('#submit').click(function(event) {
                    var username=$('#user').val();
                 $.get('ServiceGetter',{user:username},function(responseText) {
                        $('#welcometext').text(responseText);
                    });
                });
            });
        </script>
        

    </head>

    <body>
        <h1>Bem vindo ao sistema de BOP!</h1></br>

        <% 
            /*   
             *  
             *    
             */  
            List<Nota> list = NotasDAO.listarNotas();

            for(Nota n : list){
                out.println("<li>" + n.getId() + " " + 
                                     n.getTitulo() + " " + 
                                     n.getDescricao() + "</li>");
            }
            //out.println(ServiceGetter.loadAPI());
        %>

        <form id="form1">
        <h2>APIs:</h2>
        <input type="button" id="submit" value="Carregar APIs"/>
        <br/>
        <div id="welcometext">
        </div>
        </form>

        <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

    </body>

</html>
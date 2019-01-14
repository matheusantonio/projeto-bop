<html>

    <head>

        <%@ page import="config.DBConfig"%>
        <%@ page import="dao.NotasDAO"%>
        <%@ page import="dao.MongoDAO"%>
        <%@ page import="entidade.Nota"%>
        <%@ page import="com.uff.bop.loader.ServiceGetter"%>
        
        <%@ page import="java.util.ArrayList"%>
        <%@ page import="java.util.List"%>

        <title>Sistema de BOP</title>
        

        <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>
        
        

    </head>

    <body>
        <h1>Bem vindo ao sistema de BOP!</h1></br>
        <h2>Acesso com mysql:</h2>

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

        <h2>Acesso com Mongodb:</h2>

        <%
            List<Nota> listMongo = new ArrayList<>();

            listMongo = MongoDAO.listarNotas();

            for(Nota n : list){
                out.println("<li>" + n.getId() + " " + 
                                     n.getTitulo() + " " + 
                                     n.getDescricao() + "</li>");
            }
        %>


        <div id="root"></div>
        
        <script type="text/babel">

            class APILoader extends React.Component {
                
                constructor(props) {
                    super(props);
                    this.state = { apistring : "" };
                }

                tick() {

                    $.get("ServiceGetter", function(data, textStatus) {
                    }, "text").then((data) => {
                        this.setState(state => ({
                            apistring : data
                        }));
                    });
                }
                
                componentDidMount() {
                    this.interval = setInterval(() => this.tick(), 1000);
                }
                
                componentWillUnmount() {
                    clearInterval(this.interval);
                }
                
                render() {
                    return (
                    <div>
                        <br/>
                        Return String: {this.state.apistring}
                    </div>
                    );
                }

                }
                ReactDOM.render(
                    <div><APILoader/></div>,
                    document.getElementById('root')
                );

        </script>

    </body>

</html>
package dao;

import config.DBConfig;
import entidade.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotasDAO {

    public static List<Nota> listarNotas() throws Exception{
        List<Nota> lista = new ArrayList<>();

        Connection conexao = DBConfig.getConnection();

        String sql = "SELECT * FROM tb_nota";

        PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            Nota nota = new Nota();
            nota.setId(rs.getInt("id_note"));
            nota.setTitulo(rs.getString("titulo"));
            nota.setDescricao(rs.getString("descricao"));

            lista.add(nota);
        }

        conexao.close();

        return lista;
    }
}
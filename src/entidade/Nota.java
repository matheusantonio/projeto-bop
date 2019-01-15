package entidade;

public class Nota {

    private int id_note;

    private String titulo;

    private String descricao;

    public int getId(){
        return id_note;
    }

    public void setId(int id){
        this.id_note = id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
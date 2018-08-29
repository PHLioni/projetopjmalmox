package pjm.projetoalmox.com.projetopjmalmox;

public class buscaHistorico {

    private String id, n;
    private String nome;
    private String data, hora,status;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getN(String login) {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

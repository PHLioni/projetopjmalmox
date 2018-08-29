package pjm.projetoalmox.com.projetopjmalmox;

import android.widget.Spinner;

public class buscaItens {

    private String getQuantidade;
    private String item,codigo,getNome,getCodigo,senha;
    private Spinner quantidade;


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGetCodigo() {
        return getCodigo;
    }

    public String getGetNome() {
        return getNome;
    }

    public void setGetNome(String getNome) {
        this.getNome = getNome;
    }

    public void setGetCodigo(String getCodigo) {
        this.getCodigo = getCodigo;
    }

    public  String getGetQuantidade() {
        return getQuantidade;
    }

    public void setGetQuantidade(String getQuantidade) {
        this.getQuantidade = getQuantidade;
    }

    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public  String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Spinner getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Spinner quantidade) {
        this.quantidade = quantidade;
    }
}

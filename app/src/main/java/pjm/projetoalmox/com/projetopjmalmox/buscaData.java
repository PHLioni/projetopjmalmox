package pjm.projetoalmox.com.projetopjmalmox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class buscaData {

    public String getPagou() {
        return pagou;
    }

    public void setPagou(String pagou) {
        this.pagou = pagou;
    }

    private String data, status, ident,pagou;

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {

        return data;
    }

    public void setData(String data) {

        this.data = data;
    }
}

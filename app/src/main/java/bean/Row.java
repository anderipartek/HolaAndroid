package bean;

/**
 * Created by ur00 on 26/05/2015.
 */
public class Row {

    private String titulo1;
    private String titulo2;

    public Row(String titulo1, String titulo2) {
        this.titulo1 = titulo1;
        this.titulo2 = titulo2;
    }

    public String getTitulo1() {
        return titulo1;
    }

    public void setTitulo1(String titulo1) {
        this.titulo1 = titulo1;
    }

    public String getTitulo2() {
        return titulo2;
    }

    public void setTitulo2(String titulo2) {
        this.titulo2 = titulo2;
    }
}

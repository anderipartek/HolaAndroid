package bean;

/**
 * Created by ur00 on 28/05/2015.
 */
public class RowBean {

    //atributos privados
    private String titulo;
    private String fecha;

    //constructores

    public RowBean(String titulo, String fecha) {
        this.titulo = titulo;
        this.fecha = fecha;
    }


    //getters y setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    //funciones personalizadas


    @Override
    public String toString() {
        return "RowBean{" +
                "titulo='" + titulo + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}

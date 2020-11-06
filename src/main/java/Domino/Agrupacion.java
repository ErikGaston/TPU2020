package Domino;

public class Agrupacion {
    private String codigo;
    private String Nombre;
    private int votos;

    public Agrupacion(String codigo, String nombre) {
        this.codigo = codigo;
        Nombre = nombre;
        votos = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() { return Nombre; }

    public void sumarVotos(int votos) {
        this.votos += votos;
    }

    @Override
    public String toString() {
        return "Agrupacion{" +
                "codigo='" + codigo + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", votos=" + votos +
                '}';
    }
}


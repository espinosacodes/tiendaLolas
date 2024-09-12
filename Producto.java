import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private int cantidadDisponible;

    public Producto(int id, String nombre, double precio, int cantidadDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio + ", Cantidad Disponible: " + cantidadDisponible;
    }
}

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTienda {
    private static final String SERVER_IP = "192.168.7.125"; // Cambia esto a la IP del servidor
    private static final int SERVER_PORT = 12344; // Asegúrate de que este puerto coincide con el puerto del servidor

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("1. Buscar productos");
                System.out.println("2. Comprar producto");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer de entrada

                if (opcion == 1) {
                    out.writeObject("BUSCAR");
                    out.flush();
                    // Recibir y mostrar los productos
                    Object response = in.readObject();
                    if (response instanceof List<?>) {
                        List<?> productos = (List<?>) response;
                        for (Object producto : productos) {
                            System.out.println(producto);
                        }
                    } else {
                        System.out.println("Error al recibir productos.");
                    }
                } else if (opcion == 2) {
                    System.out.print("Ingrese ID del producto: ");
                    int productoId = scanner.nextInt();
                    System.out.print("Ingrese cantidad: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer de entrada

                    String solicitud = "COMPRAR " + productoId + " " + cantidad;
                    out.writeObject(solicitud);
                    out.flush();

                    // Recibir respuesta sobre la compra
                    Object response = in.readObject();
                    if (response instanceof String) {
                        System.out.println(response);
                    } else {
                        System.out.println("Error al procesar la compra.");
                    }
                } else if (opcion == 3) {
                    System.out.println("Saliendo...");
                    break;
                } else {
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

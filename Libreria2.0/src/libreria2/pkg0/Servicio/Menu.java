/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Servicio;

import java.util.Scanner;
import utilidad.UTILIDAD;

/**
 *
 * @author nacho
 */
public class Menu {

    Scanner leer = new Scanner(System.in);
    LibroServicio libro = new LibroServicio();
    EditorialServicio editorial = new EditorialServicio();
    AutorServicio autor = new AutorServicio();
    ClienteServicio cliente = new ClienteServicio();
    PrestamoServicio prestamo = new PrestamoServicio();
    UTILIDAD u1 = new UTILIDAD();

    public void menu() {

        try {
            int opc = 0;
            do {
                System.out.println(u1.colorRojo() + "BIENVENIDOS A LA LIBRERIA DEL BARRIO:");
                System.out.println(u1.colorRojo() + "------------------------------------------");
                System.out.println("1. Ingresar un libro \n"
                        + "2. Buscar autor por nombre   \n"
                        + "3. Buscar libro por codigo ISBN \n"
                        + "4. Buscar libro por Titulo \n"
                        + "5. Buscar Libro por Nombre de Autor \n"
                        + "6. Buscar Libro por Nombre de Editorial \n"
                        + "7. Mostrar todos los libros \n"
                        + "8. Borrar un libro (Mala Practica)  \n"
                        + "9. Dar de Alta o Baja un libro  \n"
                        + "10. Crear cliente \n"
                        + "11. Prestar un libro  \n"
                        + "12. Salir");
                System.out.println("");

                opc = leer.nextInt();

                switch (opc) {
                    case 1:
                        libro.crearLibro();
                        System.out.println("");
                        break;

                    case 2:
                        autor.buscarAutor();
                        System.out.println("");
                        break;

                    case 3:
                        libro.buscarPorIsbn();
                        System.out.println("");
                        break;
                    case 4:
                        libro.buscarPorTitulo();
                        System.out.println("");
                        break;

                    case 5:

                        libro.buscarPorNombreAutor();
                        System.out.println("");
                        break;

                    case 6:
                        libro.buscarPorNombreEditorial();
                        System.out.println("");
                        break;

                    case 7:
                        libro.mostrarLibros();
                        System.out.println("");
                        break;

                    case 8:

                        libro.borrarLibro();
                        System.out.println("");
                        break;

                    case 9:

                        libro.darAltaoBaja();
                        System.out.println("");

                        break;

                    case 10:

                        cliente.crearCliente();
                        System.out.println("");

                        break;

                    case 11:

                        prestamo.prestarLibro();
                        System.out.println("");

                        break;

                    case 12:
                        System.out.println("Saliendo...");
                }

            } while (opc != 12);

        } catch (Exception e) {
        }
    }
}

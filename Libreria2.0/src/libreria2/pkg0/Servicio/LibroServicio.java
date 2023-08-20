/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Servicio;

import java.util.List;
import java.util.Scanner;
import libreria2.pkg0.Entidad.Libro;
import libreria2.pkg0.Persistencia.LibroDao;

/**
 *
 * @author nacho
 */
public class LibroServicio {

    private final LibroDao libroDAO;
    EditorialServicio editorial = new EditorialServicio();
    AutorServicio autor = new AutorServicio();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public LibroServicio() {
        this.libroDAO = new LibroDao();
    }

    public Libro crearLibro() {

        Libro libro = new Libro();
        try {

            System.out.println("Ingrese el Nombre del libro: ");
            libro.setTitulo(leer.next());
            if (libro.getTitulo().isEmpty()) {
                throw new Exception("No pueden haber campos vacios bobo");

            }

            System.out.println("Ingrese el Codigo ISBN del libro: ");
            libro.setIsbn(leer.nextLong());

            if (libro.getIsbn() == 0) {
                throw new Exception("No pueden haber campos vacios bobo");

            }

            System.out.println("Ingrese el Año de creación del libro: ");
            libro.setAnio(leer.nextInt());

            if (libro.getAnio() == 0) {
                throw new Exception("No pueden haber campos vacios bobo");

            }

            System.out.println("Ingrese la Cantidad de Ejemplares del libro: ");
            libro.setEjemplares(leer.nextInt());

            if (libro.getEjemplares() == 0) {
                throw new Exception("No pueden haber campos vacios bobo");

            }

            libro.setEjemplaresRestantes(libro.getEjemplares());

            libro.setEjemplaresPrestados(0);

            boolean aux = libroDAO.existeLibro(libro); // METODO PARA NO INGRESAR VALORES DUPLICADOS

            if (!aux) {
                System.out.println("Creando libro... ");
                libro.setEditorial(editorial.CrearEditorial());
                libro.setAutor(autor.crearAutor());
                libroDAO.guardar(libro);
            } else {
                System.out.println("El libro ya existe en la base de datos, por favor verifique los datos nuevamente");
            }

            System.out.println(libro);
            return libro;

        } catch (Exception e) {
            System.out.println(e.getMessage() + " \nNo pueden haber campos vacios bobo ");
            return null;
        }
    }

    public List<Libro> mostrarLibros() {
        List<Libro> libros = null;
        try {

            libros = libroDAO.listarTodos();

            System.out.println("Lista de libros: ");
            System.out.println("");
            System.out.format("%-40s%-25s%-10s%-15s%-10s%-10s%-10s%10s%n", "Título", "Autor", "Año", "Editorial", "ISBN", "Disp.", "Prest.", "Cant.Total");
            for (Libro libro : libros) {
                System.out.format("%-40s%-25s%-10s%-15s%-10s%-10s%-10s%-10s%n",
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getAnio(),
                        libro.getEditorial().getNombre(),
                        libro.getIsbn(),
                        libro.getEjemplaresRestantes(),
                        libro.getEjemplaresPrestados(),
                        libro.getEjemplares());
                System.out.println("");
            }

        } catch (Exception e) {
        }
        return libros;
    }

    public Libro buscarPorIsbn() {
        Libro libro = null;
        try {
            System.out.println("Ingrese el codigo ISBN para buscar libro: ");
            Long isbn = leer.nextLong();

            libro = libroDAO.buscarPorIsbn(isbn);

        } catch (Exception e) {
        }
        return libro;
    }

    public Libro buscarPorTitulo() {
        Libro libro = null;
        try {
            System.out.println("Ingrese el Titulo para buscar libro: ");
            String titulo = leer.next();

            libro = libroDAO.buscarPorTitulo(titulo);
            
            

        } catch (Exception e) {
        }
        return libro;
    }

    public void buscarPorNombreAutor() {

        try {
            System.out.println("Ingrese el nombre del autor a buscar: ");
            String nombre = leer.next();

            libroDAO.buscarLibroXAutor(nombre);

        } catch (Exception e) {
        }
    }

    public void buscarPorNombreEditorial() {
        try {
            System.out.println("Ingrese el Nombre de la editorial a buscar: ");
            String nombre = leer.next();

            libroDAO.BuscarLibroXEditorial(nombre);

        } catch (Exception e) {
        }
    }

    public void borrarLibro() {
        try {
            System.out.println("Ingrese el ID del libro a borrar: ");
            Long id = leer.nextLong();

            Libro libro = libroDAO.eliminarXId(id);
            if (libro == null) {
                System.out.println("El libro no existe");
            } else {
                System.out.println("Borrando libro...");
            }
        } catch (Exception e) {
        }

    }

    public void darAltaoBaja() throws Exception {

        try {
            Libro libro = null;

            System.out.println("¿Desea dar de ALTA o BAJA?");

            System.out.println("1- ALTA");
            System.out.println("2- BAJA");
            int op = leer.nextInt();

            if (op == 1) {
                System.out.println("Ingrese el Id del Libro a dar de Alta: ");
                Long id = leer.nextLong();
                libro = libroDAO.darAltaOBaja(id, op);

                if (libro.isAlta()) {
                    System.out.println("Dando de Alta el Libro... ");
                    System.out.println(libro.isAlta());
                } else {
                    System.out.println("El libro + " + libro.getTitulo() + " ya está dado de Alta:  ");

                }

            } else {
                System.out.println("Ingrese el Id del Libro a dar de Baja: ");
                Long id = leer.nextLong();
                libro = libroDAO.darAltaOBaja(id, op);

                if (!libro.isAlta()) {
                    System.out.println("Dando de Baja el Libro... ");
                    System.out.println(libro.isAlta());
                } else {
                    System.out.println("El libro + " + libro.getTitulo() + " ya está dado de Baja");

                }
            }

        } catch (Exception e) {
        }
    }

}

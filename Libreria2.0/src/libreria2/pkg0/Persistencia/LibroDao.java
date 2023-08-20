/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Persistencia;

import java.util.List;
import javax.persistence.NoResultException;
import libreria2.pkg0.Entidad.Libro;

/**
 *
 * @author nacho
 */
public class LibroDao extends DaoMaster<Libro> {

    public LibroDao() {
    }

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    public Libro eliminarXId(Long id) {

        Libro libro = em.find(Libro.class, id);
        super.eliminar(libro);
        return libro;
    }

    public Libro buscarPorIsbn(Long isbn) throws Exception {

        conectar();
        Libro libro = null;
        try {
            libro = (Libro) em.createQuery("SELECT d FROM Libro d WHERE d.isbn LIKE :isbn")
                    .setParameter("isbn", isbn).getSingleResult();
            System.out.println(libro);

        } catch (Exception e) {
            System.out.println("No se encontró ningun libro con ese codigo");

        }

        desconectar();
        return libro;
    }

    public Libro buscarPorTitulo(String titulo) throws Exception {
        conectar();
        Libro libro = null;
        try {
            libro = (Libro) em.createQuery("SELECT d FROM Libro d WHERE d.titulo LIKE :titulo")
                    .setParameter("titulo", titulo).getSingleResult();
            System.out.println(libro);
        } catch (Exception e) {
            System.out.println("No se encontró ningun libro con ese titulo");
            return null;
        }

        desconectar();
        return libro;
    }

    public List<Libro> listarTodos() throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT d FROM Libro d").getResultList();
        for (Libro libro : libros) {
           
        }
        desconectar();
        return libros;
    }

    public Libro BuscarLibroXEditorial(String nombre) throws Exception {
        conectar();
        Libro libro = null;
        try {
            libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.editorial.nombre LIKE :nombre")
                    .setParameter("nombre", nombre).getSingleResult();

            System.out.println(libro);
        } catch (Exception e) {
            System.out.println("No se encontró ningun libro de ese editorial");
        }
        desconectar();
        return libro;
    }

    public Libro buscarLibroXAutor(String nombre) throws Exception {
        conectar();
        Libro libro = null;
        try {
            libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre LIKE :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            System.out.println(libro);
        } catch (Exception e) {
            System.out.println("No se encontró ningun libro de ese autor");
            return null;
        }
        desconectar();
        return libro;
    }

    public boolean existeLibro(Libro libro) throws Exception {
        try {
            conectar();

            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo")
                    .setParameter("titulo", libro.getTitulo())
                    .getResultList();

            if (!libros.isEmpty()) {
                System.out.println("*-- El libro ya existe --*");
                return true;
            }
        } catch (Exception e) {
            System.out.println("*-- Error al verificar el libro --*");
            System.out.println(e.getMessage());
            return true;
        } finally {
            desconectar();
        }

        return false;
    }

    public Libro darAltaOBaja(Long id, int op) {

        conectar();
        Libro libro;
        libro = em.find(Libro.class, id);
        try {

            if (libro != null) {

                if (op == 1) {

                    libro.setAlta(true);
                    System.out.println("ALTA");
                } else if (op == 2) {

                    libro.setAlta(false);
                    System.out.println("BAJA");
                }

                em.getTransaction().begin();
                em.merge(libro);
                em.getTransaction().commit();

            } else {
                System.out.println("El libro ingresado no existe. ");
            }

        } catch (Exception e) {
        }
        desconectar();
        return libro;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Servicio;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import libreria2.pkg0.Entidad.Autor;
import libreria2.pkg0.Entidad.Editorial;
import libreria2.pkg0.Persistencia.AutorDao;
import libreria2.pkg0.Persistencia.EditorialDao;

/**
 *
 * @author nacho
 */
public class AutorServicio {

    private final AutorDao autorDAO;
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Random r1 = new Random();

    public AutorServicio() {
        this.autorDAO = new AutorDao();
    }

    public Autor crearAutor() {

        try {

            String nombre[] = {"Quino", "J.K Rowling", "Ybañez", "Tolkien", "Edgar Allan Poe", "Jane Austen", "Paulo Coelho",
                "Federico García Lorca", "Agatha Chistie", "William Shakespeare", "Oscar Wilde", "Jorge Luis Borges"};

            Autor autor = new Autor(null, nombre[(int) (Math.random() * nombre.length)], true);

            autorDAO.guardar(autor);

            System.out.println(autor);

            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Autor buscarAutor() {

        try {

            System.out.println("Ingrese el nombre del autor a buscar: ");
            String nombreAux = leer.next();
            
            Autor autores = autorDAO.buscarAutorxNombre(nombreAux);
            return autores;

        } catch (Exception e) {
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Servicio;

import java.util.Random;
import java.util.Scanner;
import libreria2.pkg0.Entidad.Editorial;
import libreria2.pkg0.Entidad.Libro;
import libreria2.pkg0.Persistencia.EditorialDao;
import libreria2.pkg0.Persistencia.LibroDao;

/**
 *
 * @author nacho
 */
public class EditorialServicio {

    private final EditorialDao editorialDAO;
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Random r1 = new Random();

    public EditorialServicio() {
        this.editorialDAO = new EditorialDao();
    }

    
    
    
    public Editorial CrearEditorial() {

        
        
        try {

            String aux[] = {"Mandioca", "Miranda", "Ybañez", "Rowling", "Brahma", "Quilmes", "Mafalda", "Varela","Santillan"};
            
            Editorial editorial = new Editorial(null,aux[(int) (Math.random() * aux.length)] , true);
            
            editorialDAO.guardar(editorial);

            
            System.out.println(editorial);
            
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Persistencia;

import javax.persistence.TypedQuery;
import libreria2.pkg0.Entidad.Autor;
import libreria2.pkg0.Entidad.Libro;

/**
 *
 * @author nacho
 */
public class AutorDao extends DaoMaster {

    public AutorDao() {
    }
    
//     PROHIBIDO USAR (ARTES OSCURAS) (MALAS PRACTICAS)
//    public void eliminar() {
//
//        Autor autor = em.find(Autor.class, 2l);
//        super.eliminar(autor);
//    }

    public Autor buscarAutorxNombre(String nombre) throws Exception {
        conectar();
        nombre = nombre.toLowerCase();
        Autor autor = null;
        try {
           autor = (Autor) em.createQuery("SELECT d FROM Autor d WHERE d.nombre LIKE :nombre")
                   .setParameter("nombre", nombre).getSingleResult();

            System.out.println(autor);
        } catch (Exception e) {
            System.out.println("No se encontró ningun autor con ese nombre, banana");
        }

        desconectar();
        return autor;
    }

}

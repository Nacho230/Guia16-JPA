/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Persistencia;

import java.util.List;
import libreria2.pkg0.Entidad.Cliente;
import libreria2.pkg0.Entidad.Libro;

/**
 *
 * @author nacho
 */
public class ClienteDao extends DaoMaster {

    public List<Cliente> listarTodos() throws Exception {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT d FROM Cliente d").getResultList();
        for (Cliente cliente : clientes) {
            //System.out.println("");
            //System.out.println(cliente);
        }
        desconectar();
        return clientes;
    }

    public boolean VerificarDNI(Cliente cliente) throws Exception {
        conectar();
        try {
            List<Cliente> clientes = em.createQuery("SELECT l FROM Cliente l WHERE l.documento = :documento")
                    .setParameter("documento", cliente.getDocumento())
                    .getResultList();

            if (!clientes.isEmpty()) {
                System.out.println("*-- El Documento ya existe --*");
                return true;
            }
        } catch (Exception e) {
            System.out.println("*-- Error al verificar el Documento del cliente --*");
            System.out.println(e.getMessage());
            return true;

        }

        desconectar();
        return false;
    }

    public boolean VerificarTelefono(Cliente cliente) throws Exception {
        conectar();
        try {
            List<Cliente> clientes = em.createQuery("SELECT l FROM Cliente l WHERE l.telefono = :telefono")
                    .setParameter("telefono", cliente.getTelefono())
                    .getResultList();

            if (!clientes.isEmpty()) {
                System.out.println("*-- El Telefono ya existe --*");
                return true;
            }
        } catch (Exception e) {
            System.out.println("*-- Error al verificar el Telefono del cliente --*");
            System.out.println(e.getMessage());
            return true;
        } finally {
            desconectar();
        }

        return false;
    }

}

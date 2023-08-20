/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Servicio;

import java.util.List;
import java.util.Scanner;
import libreria2.pkg0.Entidad.Cliente;
import libreria2.pkg0.Persistencia.ClienteDao;
import libreria2.pkg0.Persistencia.LibroDao;

/**
 *
 * @author nacho
 */
public class ClienteServicio {

    private final ClienteDao clienteDao;

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public ClienteServicio() {
        this.clienteDao = new ClienteDao();
    }

    public List<Cliente> mostrarClientes() throws Exception {

        List<Cliente> clientes = clienteDao.listarTodos();

        try {
            System.out.format("%-10s%-10s%-10s%-10s%n", "Nombre", "Apellido", "Dni", "Telefono");
            for (Cliente cliente : clientes) {

                System.out.format("%-10s%-10s%-10s%-10s%n",
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getDocumento(),
                        cliente.getTelefono());
                System.out.println("");

            }

        } catch (Exception e) {
        }
        return clientes;
    }

    public Cliente crearCliente() throws Exception {

        Cliente cliente = new Cliente();
        try {

            System.out.println("Ingrese el Nombre del cliente: ");
            cliente.setNombre(leer.next());

            if (cliente.getNombre().isEmpty()) {
                throw new Exception("No pueden haber campos vacios bobo");
            }

            System.out.println("Ingrese el Apellido del cliente: ");
            cliente.setApellido(leer.next());

            if (cliente.getApellido().isEmpty()) {
                throw new Exception("No pueden haber campos vacios bobo");
            }

            System.out.println("Ingrese el DNI del cliente: ");
            cliente.setDocumento(leer.nextLong());

            if (cliente.getDocumento() == 0) {
                throw new Exception("No pueden haber campos vacios bobo");
            }

            System.out.println("Ingrese el numero de Telefono del cliente: ");
            cliente.setTelefono(leer.next());

            if (cliente.getTelefono().isEmpty()) {
                throw new Exception("No pueden haber campos vacios bobo");
            }

            boolean verificarDocumento = clienteDao.VerificarDNI(cliente);
            boolean verificarTelefono = clienteDao.VerificarTelefono(cliente);
            if (verificarDocumento || verificarTelefono) {
                System.out.println("Por favor revise que los datos ingresados no esten repetidos ");
            } else {

                System.out.println("Creando cliente... ");
                clienteDao.guardar(cliente);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println(cliente);
        return cliente;

    }

}

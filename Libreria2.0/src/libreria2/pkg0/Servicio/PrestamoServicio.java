/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria2.pkg0.Servicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import libreria2.pkg0.Entidad.Cliente;
import libreria2.pkg0.Entidad.Libro;
import libreria2.pkg0.Entidad.Prestamo;
import libreria2.pkg0.Persistencia.PrestamoDao;

/**
 *
 * @author nacho
 */
public class PrestamoServicio {

    // La idea para prestar un libro es: 
    //- Le pido al usuario que indique de que manera desea buscar el libro: -Isbn o -Titulo
    // Lo seteo al prestamo
    // Le pido el DNI para guardarlo a su nombre. Si DNI no existe le preguntamos si quiere crear un usuario
    // FECHA DE DEVOLUCION
    private final PrestamoDao prestamoDao;

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    LibroServicio libroS = new LibroServicio();
    ClienteServicio clienteS = new ClienteServicio();

    public PrestamoServicio() {
        this.prestamoDao = new PrestamoDao();
    }

    public void prestarLibro() throws Exception {

        Prestamo pm = new Prestamo();
        Libro libro = null;

        List<Cliente> clientes = clienteS.mostrarClientes();


        int dia = 0;
        int mes = 0;
        int anio = 0;
        int auxContador = 0;
        Calendar fechaActual = Calendar.getInstance();
        Calendar fecha = Calendar.getInstance();

        try {

            int opc = 0;

            System.out.println("PRESTAMO DE LIBROS ");
            System.out.println("Seleccione de que manera desea buscar el libro: ");
            System.out.println("1. Buscar Libro por Titulo \n"
                    + "2. Buscar Libro por Isbn   \n");

            System.out.println("");

            opc = leer.nextInt();

            if (opc >= 3) {
                throw new Exception("Ingresa bien los datos salame");
            }

            if (opc == 1) {

                libroS.mostrarLibros();

                libro = libroS.buscarPorTitulo();

                if (libro == null) {
                    throw new Exception();
                } else {
                    pm.setLibro(libro);
                }

                for (Cliente cliente : clientes) {
                    auxContador++;

                    if (auxContador == clientes.size()) {
                        System.out.println("Por favor ingrese su DNI ");
                        Long dni = leer.nextLong();
                        if (cliente.getDocumento().equals(dni)) {
                            pm.setCliente(cliente);
                            System.out.println("Dni ingresado correctamente ");
                            System.out.println("Ingrese la fecha de devolucion del libro: (Dia/mes/año) ");
                            pm.setFechaPrestamo(fechaActual);
                            System.out.println("Dia: ");
                            dia = leer.nextInt();
                            System.out.println("Mes: ");
                            mes = leer.nextInt();
                            System.out.println("Año: ");
                            anio = leer.nextInt();

                            if (fecha.before(fecha)) {
                                throw new Exception("ERROR AL INGRESAR LA FECHA. LA FECHA DEBE SER POSTERIOR");
                            } else {
                                fecha.set(dia, mes - 1, anio);
                                pm.setFechaDevolucion(fecha);
                                System.out.println("La fecha de devolución del libro es: ");
                                System.out.println("Dia: " + fecha.get(Calendar.DAY_OF_MONTH));
                                System.out.println("Dia: " + fecha.get(Calendar.MONTH) + 1);
                                System.out.println("Dia: " + fecha.get(Calendar.YEAR));
                                  
                            prestamoDao.guardar(pm);
                                System.out.println(pm);
                            }
                            
                            
                        } else {
                            System.out.println("Ningun cliente coincide con ese DNI \n"
                                    + "¿Desea crear un cliente nuevo? \n"
                                    + "S/N");
                            String aux = leer.next();
                            if (aux.equalsIgnoreCase("s")) {
                                clienteS.crearCliente();
                                pm.setCliente(cliente);
                                System.out.println("Ahora por favor, ingrese la fecha de devolucion: (Dia/Mes/Año) ");
                                pm.setFechaPrestamo(fechaActual);
                                System.out.println("Dia: ");
                                dia = leer.nextInt();
                                System.out.println("Mes: ");
                                mes = leer.nextInt();
                                System.out.println("Año: ");
                                anio = leer.nextInt();

                                if (fecha.compareTo(fechaActual) <= 0) {
                                    throw new Exception("ERROR AL INGRESAR LA FECHA. LA FECHA DEBE SER POSTERIOR");
                                } else {
                                    fecha.set(dia, mes - 1, anio);
                                    pm.setFechaDevolucion(fecha);
                                }
                                System.out.println("Prestamo: " + pm);
                                System.out.println("La fecha de devolución del libro es:\n"
                                        + "Dia: " + fecha.get(dia)
                                        + "Mes: " + fecha.get(mes)
                                        + "Año: " + fecha.get(anio));
                                prestamoDao.guardar(pm);

                            } else {
                                throw new Exception("Por favor ingrese de nuevo los datos");
                            }
                        }
                        
                    }
                }
            } else {
                libroS.mostrarLibros();

                libro = libroS.buscarPorIsbn();

                if (libro == null) {
                    throw new Exception();
                } else {

                    pm.setLibro(libro);
                }

                for (Cliente cliente : clientes) {
                    auxContador++;
                    if (auxContador == clientes.size()) {
                        System.out.println("Por favor ingrese su DNI ");
                        Long dni = leer.nextLong();
                        if (cliente.getDocumento().equals(dni)) {
                            pm.setCliente(cliente);
                            System.out.println("Dni ingresado correctamente ");
                            System.out.println("Ingrese la fecha de devolucion del libro: (Dia/mes/año) ");
                            pm.setFechaPrestamo(fechaActual);
                            System.out.println("Dia: ");
                            dia = leer.nextInt();
                            System.out.println("Mes: ");
                            mes = leer.nextInt();
                            System.out.println("Año: ");
                            anio = leer.nextInt();

                            if (fecha.compareTo(fechaActual) <= 0) {
                                throw new Exception("ERROR AL INGRESAR LA FECHA. LA FECHA DEBE SER POSTERIOR");
                            } else {
                                fecha.set(dia, mes - 1, anio);
                                pm.setFechaDevolucion(fecha);
                            }
                            System.out.println("Prestamo: " + pm);
                            System.out.println("La fecha de devolución del libro es:\n"
                                    + "Dia: " + fecha.get(dia)
                                    + "Mes: " + fecha.get(mes)
                                    + "Año: " + fecha.get(anio));
                            prestamoDao.guardar(pm);
                        } else {
                            System.out.println("Ningun cliente coincide con ese DNI \n"
                                    + "¿Desea crear un cliente nuevo? \n"
                                    + "S/N");
                            String aux = leer.next();
                            if (aux.equalsIgnoreCase("s")) {
                                clienteS.crearCliente();
                                pm.setCliente(cliente);
                                System.out.println("Ahora por favor, ingrese la fecha de devolucion: (Dia/Mes/Año) ");
                                pm.setFechaPrestamo(fechaActual);
                                System.out.println("Dia: ");
                                dia = leer.nextInt();
                                System.out.println("Mes: ");
                                mes = leer.nextInt();
                                System.out.println("Año: ");
                                anio = leer.nextInt();

                                if (fecha.compareTo(fechaActual) <= 0) {
                                    throw new Exception("ERROR AL INGRESAR LA FECHA. LA FECHA DEBE SER POSTERIOR");
                                } else {
                                    fecha.set(dia, mes - 1, anio);
                                    pm.setFechaDevolucion(fecha);
                                }

                                System.out.println("Prestamo: " + pm);
                                System.out.println("La fecha de devolución del libro es:\n"
                                        + "Dia: " + fecha.get(dia)
                                        + "Mes: " + fecha.get(mes)
                                        + "Año: " + fecha.get(anio));
                                prestamoDao.guardar(pm);

                            } else {
                                throw new Exception("Por favor ingrese de nuevo los datos");
                            }
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

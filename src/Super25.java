
import java.awt.BorderLayout;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Usuario
 */
public class Super25 {
//variables globales

    Scanner entrada = new Scanner(System.in);

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static String[][] productos = new String[20][3];
    public static String[][] codigos = new String[20][3];
    public static String[][] ventasDetalles;
    public static String[][] ventasDetalles2 = new String[100][3];
    public static int nunDetallesVentas = 0;
    public static int numCodigos = 0;
    public static int numProductos = 0;
    String usuario = "cajero_202202182";
    String contra = "ipc1_202202182";
    boolean verificar = false;
    String cliente;
    int nit;

//inicia sesion
    public void Inicia() {
        int intentos = 0;

        do {

            System.out.println("-------------Inicios de sesion-------------");
            System.out.print("Ingrese su Usuario: ");
            usuario = entrada.nextLine();
            System.out.print("Ingrese su Contraseña: ");
            contra = entrada.nextLine();

            intentos++;

            if (usuario.equals("cajero_202202182") && contra.equals("ipc1_202202182")) {
                System.out.println("---------------------------------------------");
                System.out.println("Bienvenido usuario: " + usuario);
                verificar = true;
            } else {
                System.out.println(ANSI_RED + "Usuario o Contraseña, incorrecta vuelva a iniciar denuevo, intentos 3, numeros de intentos realizados : " + intentos + ANSI_RESET);
                verificar = false;
            }

            if (intentos == 3) {
                System.out.println(ANSI_YELLOW + "Se ha alcanzado el limites de intentos, intente al rato" + ANSI_RESET);
                break;
            }
        } while (!(verificar && intentos < 3));
    }
//----------------ingresar prodcutor--------------------------------------

    String produc;
    String precio;

    //agregar prodcuto principal
    public void agregarProductos() {
        Scanner sn = new Scanner(System.in);
        boolean salir = true;
        double numero;
        String cerra;

        System.out.println("-------------Ingrese los Productos -------------------");

        do {
            if (numProductos < productos.length) {
                System.out.print("Ingrese el producto: ");
                produc = sn.nextLine();

                do {

                    System.out.print("Ingrese precio: ");
                    precio = sn.nextLine();
                    numero = Double.parseDouble(precio);

                    if (numero > 0) {
                        salir = true;

                    } else {
                        System.out.println(ANSI_RED + "No se acepta precio negativos" + ANSI_RESET);
                        salir = false;

                    }

                } while (!salir);

                nuevoProducto(produc, precio);

            } else {
                System.out.println(ANSI_RED + "Se alcanzo el limete de prodcutos" + ANSI_RESET);
                break;
            }

            System.out.print(ANSI_CYAN + "Quiere agregar otro producto(si/no)" + ANSI_RESET);
            cerra = sn.nextLine();
            if (cerra.equalsIgnoreCase("si")) {
                continue;
            } else {
                salir = false;
                break;
            }

        } while (salir);

    }

    //agregar 
    public static void nuevoProducto(String produc, String precio) {
        if (validarProdcuto(produc) == -1) {
            productos[numProductos][0] = produc;
            productos[numProductos][1] = precio;
            productos[numProductos][2] = String.valueOf(0);

            numProductos++;
        } else {
            System.out.println(ANSI_YELLOW + "Producto ya existente: " + ANSI_RESET + produc);
        }

    }

    //verificar si existe el producto o no
    public static int validarProdcuto(String produc) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null) {
                if (productos[i][0].equalsIgnoreCase(produc)) {
                    return i;
                }
            } else {
                return -1;
            }
        }
        return -1;
    }

    public static void agregarPopularidad(String produc) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null) {
                if (productos[i][0].equalsIgnoreCase(produc)) {
                    int suma = Integer.parseInt(productos[i][2]) + 1;

                    productos[i][2] = String.valueOf(suma);
                }
            }
        }

    }

    //listado
    public void listados() {
        System.out.println("------------------Productos existente------------------------");
        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null) {

                System.out.print("Prodcuto:  " + productos[i][0]);
                System.out.print("  Precio:  " + productos[i][1] + "\n");

            }
        }
    }
    //-------------------------ingresar cupones----------------------------------

    String codigo;
    String porcentaje;

    public void agregarCodigo() {
        Scanner nn = new Scanner(System.in);
        boolean salir = true;
        boolean verificacion = true;
        String cerra;
        int num = 0, tam = 0, mayor;
        String cadena = "";

        System.out.println("----------------Ingrese los cupones-------------");
        do {

            if (numCodigos < codigos.length) {
                do {
                    System.out.print("Agrege el codigo, ejemplo(1321): ");
                    codigo = nn.nextLine();
                    num = Integer.parseInt(codigo);
                    cadena = String.valueOf(num);
                    tam = cadena.length();

                    if (tam == 4) {
                        verificacion = true;

                        do {
                            System.out.print("Agregue el porcentaje: ");
                            porcentaje = nn.nextLine();
                            mayor = Integer.parseInt(porcentaje);

                            if (mayor > 0 && mayor <= 100) {
                                verificacion = true;
                            } else {
                                verificacion = false;
                                System.out.println(ANSI_RED + "Ingrese descuentro entre 0 a 100%" + ANSI_RESET);
                            }

                        } while (!verificacion);

                        nuevoCodigo(codigo, porcentaje);

                        break;

                    } else {
                        verificacion = false;
                        System.out.println(ANSI_RED + "Error el cupon debe de tener 4 digitos" + ANSI_RESET);

                    }

                } while ((verificacion == true));

            } else {
                System.out.println(ANSI_RED + "Se alcanzo el limete de codigos" + ANSI_RESET);
                break;
            }

            System.out.print(ANSI_CYAN + "Quiere agregar otro codigo(si/no): " + ANSI_RESET);
            cerra = nn.nextLine();

            if (cerra.equalsIgnoreCase("si")) {
                continue;
            } else {
                salir = false;
            }

        } while (salir);

    }
//agregar cupon con este metodod

    public static void nuevoCodigo(String codigo, String porcentaje) {

        if (validarCupon(codigo) == -1) {
            codigos[numCodigos][0] = codigo;
            codigos[numCodigos][1] = porcentaje;
            codigos[numCodigos][2] = "SIN_USO";

            numCodigos++;

        } else {
            System.out.println(ANSI_YELLOW + "Cupon ya existente: " + ANSI_RESET + codigo);
        }

    }
//verifica si existe o no

    public static int validarCupon(String codigo) {
        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i][0] != null) {
                if (codigos[i][0].equalsIgnoreCase(codigo)) {

                    return i;
                }
            } else {
                return -1;
            }
        }
        return -1;
    }

//lista cupones
    public static void listarCupones() {
        System.out.println("--------------------Cupones existente-------------------");
        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i][0] != null) {

                System.out.print("Codigo es: " + codigos[i][0]);
                System.out.println("Porcentaje: " + codigos[i][1] + "%");

            }
        }

    }

    //-----------------------realizar venta--------------------
    String nombre;

    int conteo = 1;
    boolean salir;

    public void ventaProdcuto() {

        Scanner cn = new Scanner(System.in);
        Scanner nc = new Scanner(System.in);
        ventasDetalles = new String[20][3];
        boolean salir = true;
        String opcion = "";
        int cantidad;

        System.out.println("-------------" + usuario + "--------------------");

        System.out.print("Nombre del cliente: ");
        cliente = cn.nextLine();
        System.out.print("Numero de nit: ");
        nit = cn.nextInt();

        listados();

        do {
            System.out.println("----------------------------------------------");

            System.out.print("Ingrese el producto que quiere: ");
            nombre = nc.nextLine();

            boolean siExisteEnCompra = verificaProducCompra(nombre);

            int posicionProdcuto = validarProdcuto(nombre);

            if (posicionProdcuto != -1) {

                agregarPopularidad(nombre);

                if (!siExisteEnCompra) {
                    ventasDetalles[nunDetallesVentas][0] = nombre;

                    double precioProducto = obtenerPrecio(nombre);
                    ventasDetalles[nunDetallesVentas][1] = String.valueOf(precioProducto);

                    System.out.print("Ingrese la cantidad: ");
                    cantidad = cn.nextInt();

                    ventasDetalles[nunDetallesVentas][2] = String.valueOf(cantidad);
                } else {
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = cn.nextInt();
                    verificarCantidad(nombre, cantidad);

                }

            } else {
                System.out.println(ANSI_RED + "No existe este producto: " + ANSI_RESET + nombre);
            }

            nunDetallesVentas++;
            System.out.print(ANSI_CYAN + "Quiere otro producto(si/no): " + ANSI_RESET);
            opcion = nc.nextLine();

            if (opcion.equalsIgnoreCase("si")) {
                continue;
            } else {
                salir = false;

            }

        } while (salir);
        descuento();
    }

    //agregar si tiene descuento
    String cupones;
    String obtene;
    double numDescuento;
    int pocicioCupon;

    public void descuento() {
        Scanner nm = new Scanner(System.in);

        boolean validar = true;
        String ocpion = "";
        while (validar) {

            System.out.print(ANSI_CYAN + "Tiene cupon de descuento(si/no): " + ANSI_RESET);
            ocpion = nm.nextLine();

            if (ocpion.equalsIgnoreCase("si")) {

                System.out.print("Ingrese el cupon: ");
                cupones = nm.nextLine();

                pocicioCupon = validarCupon(cupones);

                if (pocicioCupon != -1) {

                    String estaEnUso = codigos[pocicioCupon][2];
                    if (estaEnUso.equals("SIN_USO")) {

                        numDescuento = obtenerPorcentaje(cupones);
                        codigos[numCodigos][1] = String.valueOf(numDescuento);
                        codigos[pocicioCupon][2] = "EN_USO";

                        validar = false;

                    } else {
                        System.out.println(ANSI_RED + "Cupon ya usado" + ANSI_RESET);
                    }

                } else {
                    System.out.println(ANSI_RED + "No existe este cupon: " + ANSI_RESET + cupones);

                }

            } else {
                codigos[numCodigos][1] = String.valueOf(-1);

                break;
            }

        }

    }

    //impirmir factura
    double matriz;
    double resultado;

    public void factura() {
        int n = 0;
        int m = 0;
        System.out.println("-------------------FACTURA-------------------");
        System.out.println("SUPER-25");
        System.out.println("Nombre del cajero: " + usuario);
        System.out.println("Nombre del cliente: " + cliente);
        System.out.println("Numero de nit: " + nit);
        System.out.println("fecha realizada la compra: 10/02/2023");

        for (int i = 0; i < ventasDetalles.length; i++) {
            if (ventasDetalles[i][0] != null) {
                n++;
                System.out.println("No. " + n);
                System.out.print("Productos adquiridos: " + ventasDetalles[i][0] + ",");
                System.out.println("Precio unitario: " + ventasDetalles[i][1] + ",");
                System.out.println("Cantidad: " + ventasDetalles[i][2]);

            }
        }
        double suma = 0;

        for (int i = 0; i < ventasDetalles.length; i++) {
            if (ventasDetalles[i][0] != null) {
                m++;

                double matriz1 = Double.parseDouble(ventasDetalles[i][1]);
                double matriz2 = Double.parseDouble(ventasDetalles[i][2]);

                resultado = matriz1 * matriz2;
                suma += resultado;
                System.out.println("No." + m + " Subtotal: " + resultado);

            }
        }

        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i][0] != null) {
                m++;

                matriz = Double.parseDouble(codigos[i][1]);

            }
        }

        double parseaCodigo = Double.parseDouble(codigos[numCodigos][1]);

        if (parseaCodigo == -1) {
            System.out.println("total: " + suma);

        } else {
            System.out.println("descuento obtenido :  " + matriz + "%");

            double descuento = (matriz * suma) / 100;
            double totalDescuento = suma - descuento;
            System.out.println("Total con descuento agrego: " + totalDescuento);

        }

    }

    //realizar el reporte
    String temporal[][] = new String[20][2];

    //0             1                  2
    //nombre     precio       nVendidas
    public void reporte() {

        String vector;
        String vector2;

        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null) {
                temporal[i][0] = productos[i][0];

                if (productos[i][2] == null) {
                    temporal[i][1] = "0";
                } else {
                    temporal[i][1] = productos[i][2];
                }
            }

        }

        for (int pasada1 = 1; pasada1 < temporal.length; pasada1++) {

            for (int pasada2 = temporal.length - 1; pasada2 >= pasada1; pasada2--) {

                if (temporal[pasada2][0] != null) {
                    int venta1 = parseInt(temporal[pasada2][1]);
                    int venta2 = parseInt(temporal[pasada2 - 1][1]);

                    if (venta1 > venta2) {
                        vector = temporal[pasada2][1];
                        vector2 = temporal[pasada2][0];

                        temporal[pasada2][1] = temporal[pasada2 - 1][1];
                        temporal[pasada2][0] = temporal[pasada2 - 1][0];

                        temporal[pasada2 - 1][1] = vector;
                        temporal[pasada2 - 1][0] = vector2;

                    }

                }
            }
        }

        System.out.println("----------Reportes de productos----------");
        System.out.println(" Producto " + " No. cantidad ");
        mostrarMatriz();

    }

    public void mostrarMatriz() {
        for (int i = 0; i < temporal.length; i++) {
            if (temporal[i][0] != null) {

                System.out.println(temporal[i][0].toUpperCase() + "  ------------  " + temporal[i][1]);

            }
        }
    }

    //buscar el precio unitario
    public static double obtenerPrecio(String produc) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null) {
                if (productos[i][0].equalsIgnoreCase(produc)) {
                    return Double.parseDouble(productos[i][1]);
                }
            } else {
                return -1;
            }
        }
        return -1;
    }

    //buscar el procentaje del cupon
    public static int obtenerPorcentaje(String codigo) {
        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i][0] != null) {
                if (codigos[i][0].equalsIgnoreCase(codigo)) {
                    return Integer.parseInt(codigos[i][1]);
                }
            } else {
                return -1;
            }
        }
        return -1;
    }

    //verifica y sumar la cantidad agregada al ususario 
    public static void verificarCantidad(String produc, int cantidad) {
        for (int i = 0; i < ventasDetalles.length; i++) {
            if (ventasDetalles[i][0] != null) {
                if (ventasDetalles[i][0].equalsIgnoreCase(produc.trim())) {
                    double total = Double.parseDouble(ventasDetalles[i][2]) + cantidad;
                    ventasDetalles[i][2] = String.valueOf(total);
                }
            }
        }

    }

    //verificar si existe el prodcuto en la compra
    public static boolean verificaProducCompra(String produc) {
        for (int i = 0; i < ventasDetalles.length; i++) {
            if (ventasDetalles[i][0] != null) {
                if (ventasDetalles[i][0].equalsIgnoreCase(produc)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    //establecer la matriz como una nueva
    //--------------menu principal -------------------------
    public void menu() {
        int opciones;
        boolean salir = false;
        boolean rellenar = false;

        if (verificar == true) {

            do {
                System.out.println("--------------------------------------------------");
                System.out.println("1.Agregar nuevo productos");
                System.out.println("2.Agregar cupos de descuentos");
                System.out.println("3.Realizar ventas");
                System.out.println("4.Realizar reporte");
                System.out.println("5. salir");

                System.out.println("--------------------------------------------------");
                opciones = entrada.nextInt();

                switch (opciones) {
                    case 1:
                        agregarProductos();
                        rellenar = true;

                        break;
                    case 2:
                        agregarCodigo();

                        break;
                    case 3:
                        if (rellenar) {

                            ventaProdcuto();
                            factura();

                        } else {
                            System.out.println("Debe de agregar productos");
                        }

                        break;
                    case 4:

                        if (rellenar) {

                            reporte();

                        } else {
                            System.out.println("Debes de agregar ventas");
                        }

                        break;
                    case 5:

                        salir = true;

                        break;

                    default:
                        System.out.println("Opciones incorrecta" + opciones);
                }

            } while (!salir);

        }
    }

    //main principal
    public static void main(String[] args) {
        Super25 super25 = new Super25();
        super25.Inicia();
        super25.menu();
    }

}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cuenta> listaCuentas = new ArrayList<>();
        int indiceCuentaActual = -1;

        boolean salirDelMenu = false;
        while (!salirDelMenu) {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1": {
                    System.out.print("Ingrese el nombre del titular: ");
                    String nombreCuenta = scanner.nextLine().trim();
                    System.out.print("Ingrese el saldo inicial: ");
                    double saldoInicial;
                    try {
                        saldoInicial = Double.parseDouble(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido. Se usará 0.0 como saldo inicial");
                        saldoInicial = 0.0;
                    }

                    Cuenta cuentaNueva = new Cuenta(nombreCuenta, saldoInicial);
                    listaCuentas.add(cuentaNueva);
                    indiceCuentaActual = listaCuentas.size() - 1;
                    System.out.println("Cuenta creada correctamente con índice " + indiceCuentaActual + " y Código: " + cuentaNueva.getCodCuenta());
                    System.out.println("Total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "2": {
                    System.out.println("Cantidad total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "3": {
                    if (listaCuentas.isEmpty()) {
                        System.out.println("No existen cuentas creadas.");
                    } else {
                        System.out.println("===== Lista de cuentas registradas =====");
                        for (int i = 0; i < listaCuentas.size(); i++) {
                            System.out.println("Índice " + i + ":");
                            System.out.println(listaCuentas.get(i).toString());
                        }
                    }
                    break;
                }
                case "4": {
                    if (listaCuentas.isEmpty()) {
                        System.out.println("Primero debe crear una cuenta.");
                        break;
                    }
                    System.out.print("Ingrese el índice de la cuenta a seleccionar: ");
                    try {
                        int indiceSeleccionado = Integer.parseInt(scanner.nextLine().trim());
                        if (indiceSeleccionado >= 0 && indiceSeleccionado < listaCuentas.size()) {
                            indiceCuentaActual = indiceSeleccionado;
                            Cuenta cuentaSeleccionada = listaCuentas.get(indiceCuentaActual);
                            System.out.println("Cuenta seleccionada con índice " + indiceCuentaActual + " y código: " + cuentaSeleccionada.getCodCuenta());
                        } else {
                            System.out.println("El índice está fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                    }
                    break;
                }
                case "5": {
                    if (indiceCuentaActual < 0 || listaCuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta.");
                        break;
                    }
                    System.out.print("Ingrese el monto a depositar: ");
                    try {
                        double montoDeposito = Double.parseDouble(scanner.nextLine().trim());
                        double nuevoSaldo = listaCuentas.get(indiceCuentaActual).depositar(montoDeposito);
                        System.out.printf("Depósito realizado. Saldo actual: %.2f%n", nuevoSaldo);
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }
                case "6": {
                    if (indiceCuentaActual < 0 || listaCuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta.");
                        break;
                    }
                    System.out.print("Ingrese el monto a retirar: ");
                    try {
                        double montoRetiro = Double.parseDouble(scanner.nextLine().trim());
                        Cuenta cuentaSeleccionada = listaCuentas.get(indiceCuentaActual);
                        if (cuentaSeleccionada.validarRetiro(montoRetiro)) {
                            double nuevoSaldo = cuentaSeleccionada.retirar(montoRetiro);
                            System.out.printf("Retiro realizado. Saldo actual: %.2f%n", nuevoSaldo);
                        } else {
                            System.out.printf("Fondos insuficientes. Saldo actual: %.2f%n", cuentaSeleccionada.getSaldo());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }
                case "7": {
                    if (indiceCuentaActual < 0 || listaCuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta.");
                        break;
                    }
                    System.out.printf("Saldo actual: %.2f%n", listaCuentas.get(indiceCuentaActual).getSaldo());
                    break;
                }
                case "8": {
                    if (indiceCuentaActual < 0 || listaCuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta.");
                        break;
                    }
                    System.out.println(listaCuentas.get(indiceCuentaActual).toString());
                    break;
                }
                case "0": {
                    salirDelMenu = true;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }
}

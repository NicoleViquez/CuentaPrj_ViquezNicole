import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta {

    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;

    public Cuenta(String nombreCuentaHabiente, double pSaldo) {
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        this.saldo = pSaldo;
        cantCuentasCreadas++;
        this.codCuenta = codCuenta + cantCuentasCreadas;
        this.fechaCreacion = generarFecha();
    }

    public Cuenta(double pSaldo) {
        this.saldo = pSaldo;
        cantCuentasCreadas++;
        this.codCuenta = codCuenta + cantCuentasCreadas;
        this.fechaCreacion = generarFecha();
    }

    private String generarFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formato.format(new Date());
    }

    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        this.nombreCuentaHabiente = pNombreCuentaHabiente;
    }

    public String getCodCuenta() {
        return this.codCuenta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public double depositar(double monto) {
        this.saldo += monto;
        this.cantDepositosRealizados++;
        return this.saldo;
    }

    public double retirar(double monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            this.cantRetirosExitososRealizados++;
        }
        return this.saldo;
    }

    public boolean validarRetiro(double monto) {
        return (this.saldo >= monto) ? true : false;
    }

    public static int getCantCuentasCreadas() {
        return cantCuentasCreadas;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "Código: " + codCuenta + "\n";
        msg += "Titular: " + ((nombreCuentaHabiente != null) ? nombreCuentaHabiente : "(no definido)") + "\n";
        msg += "Saldo actual: " + String.format("%.2f", saldo) + "\n";
        msg += "Fecha de creación: " + fechaCreacion + "\n";
        msg += "Depósitos realizados: " + cantDepositosRealizados + "\n";
        msg += "Retiros realizados: " + cantRetirosExitososRealizados + "\n";
        msg += "========================================\n";
        return msg;
    }
}

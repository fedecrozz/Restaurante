
public class MesaClase {
	
	
	
	private String cuenta,estado,mesero,observacion;
	private double total,subtotal,descuento,recargo;
	private int numero;

	
	
	public MesaClase(int numero, String cuenta, String estado) {
		super();
		this.numero = numero;
		this.cuenta = cuenta;
		this.estado = estado;
	}
	
	public MesaClase() {
		
	}
	

	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMesero() {
		return mesero;
	}
	public void setMesero(String mesero) {
		this.mesero = mesero;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getRecargo() {
		return recargo;
	}
	public void setRecargo(double recargo) {
		this.recargo = recargo;
	}
	
	
}

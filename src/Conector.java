import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conector {

	private String path = new File("bd.db").getAbsolutePath();	
	
	private Connection conexion;
		
	public Connection getConexion() {
		return conexion;
	}
	
	public void setDireccion(String dir) {
		this.path = dir;
	}
	
	public String getDireccion() {
		return this.path;
	}
	
	public void conectar() {
						
		try {
			conexion=DriverManager.getConnection("jdbc:sqlite:"+path);			
		} catch (SQLException ex) {
			System.out.println("No se ha podido establecer conexion: "+ex);
		}
	
	}
	
	public void cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException ex) {
			Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null ,ex);
		}
	}

	//-------------------------------------------------
	
	public boolean existeCliente(String nombre) {
		ResultSet result = null;
		boolean ret = false;
		
		try {
			PreparedStatement st = conexion.prepareStatement("select nombre from CLIENTES where cliente = '"+nombre+"'");
			result = st.executeQuery();			
			String Nombre= result.getString("cliente");
            
            if(Nombre.equals(nombre)) {
            	ret=true;
            }
            
		} catch (Exception e) {}
				
		return ret;
	}
	
	public boolean existeCategoria(String Descripcion) {
		ResultSet result = null;
		boolean ret = false;
		
		try {
			PreparedStatement st = conexion.prepareStatement("select descripcion from CATEGORIAS where descripcion= '"+Descripcion+"'");
			result = st.executeQuery();			
			String descripcion= result.getString("descripcion");
            
            if(descripcion.equals(Descripcion)) {
            	ret=true;
            }
            
		} catch (Exception e) {}
				
		return ret;
	}
	
	public void guardarCliente(Cliente c) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into CLIENTES(cliente,saldo,telefono,direccion,dni) values (?,?,?,?,?)");
			
            
            st.setString(1,c.getCliente());
            st.setDouble(2,c.getSaldo());
            st.setString(3,c.getTelefono());
            st.setString(4,c.getDireccion());
            st.setString(5,c.getDni());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public boolean existeProveedor(String nombre) {
		ResultSet result = null;
		boolean ret = false;
		
		try {
			PreparedStatement st = conexion.prepareStatement("select nombre from CLIENTES where cliente = '"+nombre+"'");
			result = st.executeQuery();			
			String Nombre= result.getString("cliente");
            
            if(Nombre.equals(nombre)) {
            	ret=true;
            }
            
		} catch (Exception e) {}
				
		return ret;
	}
	
	public void guardarProveedor(Proveedor c) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into PROVEEDORES(proveedor,saldo,telefono,direccion) values (?,?,?,?)");
			
            
            st.setString(1,c.getProveedor());
            st.setDouble(2,c.getSaldo());
            st.setString(3,c.getTelefono());
            st.setString(4,c.getDireccion());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void guardarCategoria(String Descripcion) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into CATEGORIAS (codigo, descripcion) values (?,?)");
			String ultimo = getCodigoUltimaCategoria();
			
            
            st.setString(1,String.valueOf(Integer.valueOf(ultimo)+1));
            st.setString(2,Descripcion);
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public String getCodigoUltimaCategoria() {
	
		
		ResultSet result = null;
		String codigo = "";
		
		
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from CATEGORIAS order by codigo DESC limit 1");
			result = st.executeQuery();			
			
            codigo= result.getString("codigo");
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return codigo;
	}
	
	
	public boolean existeArticulo(String nombre) {
		ResultSet result = null;
		boolean ret = false;
		
		try {
			PreparedStatement st = conexion.prepareStatement("select nombre from ARTICULOS where codigo = '"+nombre+"'");
			result = st.executeQuery();			
			String Nombre= result.getString("descripcion");
            
            if(Nombre.equals(nombre)) {
            	ret=true;
            }
            
		} catch (Exception e) {}
				
		return ret;
	}
	
	public String getCodigoUltimoArticulo() {
		
		
		ResultSet result = null;
		String codigo = "";
		
		
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS order by codigo DESC limit 1");
			result = st.executeQuery();			
			
            codigo= result.getString("codigo");
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return codigo;
	}
	
	public void guardarArticulo(Articulo c) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into ARTICULOS(codigo,descripcion,categoria,precio,costo,stock,observacion) values (?,?,?,?,?,?,?)");
			
            
            st.setString(1,c.getCodigo());
            st.setString(2,c.getDescripcion());
            st.setString(3,c.getCategoria());
            st.setDouble(4,c.getPrecio());
            st.setDouble(5,c.getCosto());
            st.setDouble(6,c.getStock());
            st.setString(7,c.getObservacion());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void guardarPago(Pago c) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into PAGOS(fecha,monto,proveedor,detalle) values (?,?,?,?)");
			
            
            st.setString(1,c.getFecha());
            st.setDouble(2,c.getMonto());
            st.setString(3,c.getProveedor());
            st.setString(4,c.getDetalle());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void guardarRecibo(Recibo c) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into RECIBOS(fecha,monto,cliente,detalle) values (?,?,?,?)");
			
            
            st.setString(1,c.getFecha());
            st.setDouble(2,c.getMonto());
            st.setString(3,c.getCliente());
            st.setString(4,c.getDetalle());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Proveedor> getProveedores(){
		ArrayList<Proveedor> proveedores= new ArrayList<Proveedor>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from PROVEEDORES order by proveedor ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Proveedor p = new Proveedor();
                String proveedor = result.getString("proveedor");
                String telefono= result.getString("telefono");
                String direccion= result.getString("direccion");
                
                p.setProveedor(proveedor);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
                
                proveedores.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return proveedores;
	}
	
	public ArrayList<Proveedor> getProveedores(String Proveedor){
		ArrayList<Proveedor> proveedores= new ArrayList<Proveedor>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from PROVEEDORES where proveedor LIKE '%"+Proveedor+"%' order by proveedor ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Proveedor p = new Proveedor();
                String proveedor = result.getString("proveedor");
                String telefono= result.getString("telefono");
                String direccion= result.getString("direccion");
                
                p.setProveedor(proveedor);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
                
                proveedores.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return proveedores;
	}
	
	public ArrayList<Cliente> getClientes(){
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from CLIENTES order by cliente ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Cliente p = new Cliente();
                String cliente= result.getString("cliente");
                String telefono= result.getString("telefono");
                String direccion= result.getString("direccion");
                
                p.setCliente(cliente);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
                
                clientes.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return clientes;
	}
	
	public ArrayList<Cliente> getClientes(String Cliente){
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from CLIENTES where cliente LIKE '%"+Cliente+"%' order by cliente ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Cliente p = new Cliente();
                String cliente= result.getString("cliente");
                String telefono= result.getString("telefono");
                String direccion= result.getString("direccion");
                
                p.setCliente(cliente);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
                
                clientes.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return clientes;
	}
	
	public ArrayList<Articulo> getArticulos(){
		ArrayList<Articulo> articulos= new ArrayList<Articulo>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS order by descripcion ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Articulo p = new Articulo();
                String codigo= result.getString("codigo");
                String descripcion= result.getString("descripcion");
                String categoria= result.getString("categoria");
                double precio= result.getDouble("precio");
                double precio2= result.getDouble("precio2");
                double costo= result.getDouble("costo");
                String observacion= result.getString("observacion");
                double stock= result.getDouble("stock");
                
                p.setCodigo(codigo);
                p.setCategoria(categoria);                
                p.setDescripcion(descripcion);
                p.setPrecio(precio);
                p.setPrecio2(precio2);
                p.setCosto(costo);
                p.setStock(stock);
                p.setObservacion(observacion);
                articulos.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return articulos;
	}
	
	public ArrayList<Articulo> getArticulos(String Categoria){
		ArrayList<Articulo> articulos= new ArrayList<Articulo>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS where categoria = '"+Categoria+"' order by descripcion ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Articulo p = new Articulo();
                String codigo= result.getString("codigo");
                String descripcion= result.getString("descripcion");
                String categoria= result.getString("categoria");
                double precio= result.getDouble("precio");
                double precio2= result.getDouble("precio2");
                double costo= result.getDouble("costo");
                String observacion= result.getString("observacion");
                double stock= result.getDouble("stock");
                
                p.setCodigo(codigo);
                p.setCategoria(categoria);                
                p.setDescripcion(descripcion);
                p.setPrecio(precio);
                p.setPrecio2(precio2);
                p.setCosto(costo);
                p.setStock(stock);
                p.setObservacion(observacion);
                articulos.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return articulos;
	}
	
	public ArrayList<Articulo> getArticulosBusqueda(String Articulo){
		ArrayList<Articulo> articulos= new ArrayList<Articulo>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS where descripcion LIKE '%"+Articulo+"%' order by codigo ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Articulo p = new Articulo();
                String codigo= result.getString("codigo");
                String descripcion= result.getString("descripcion");
                String categoria= result.getString("categoria");
                double precio= result.getDouble("precio");
                double precio2= result.getDouble("precio2");
                double costo= result.getDouble("costo");
                String observacion= result.getString("observacion");
                double stock= result.getDouble("stock");
                
                p.setCodigo(codigo);
                p.setCategoria(categoria);                
                p.setDescripcion(descripcion);
                p.setPrecio(precio);
                p.setPrecio2(precio2);
                p.setCosto(costo);
                p.setStock(stock);
                p.setObservacion(observacion);
                articulos.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return articulos;
	}
	
	public ArrayList<Categoria> getCategorias(){
		ArrayList<Categoria> categorias= new ArrayList<Categoria>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from CATEGORIAS order by descripcion ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Categoria p = new Categoria();
                String codigo= result.getString("codigo");
                String descripcion= result.getString("descripcion");
                
                p.setCodigo(codigo);              
                p.setDescripcion(descripcion);
                categorias.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return categorias;
	}
	
	public ArrayList<Articulo> getArticulosCategoria(String Categoria){
		ArrayList<Articulo> articulos= new ArrayList<Articulo>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS where categoria ='"+Categoria+"' order by codigo ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Articulo p = new Articulo();
                String codigo= result.getString("codigo");
                String descripcion= result.getString("descripcion");
                String categoria= result.getString("categoria");
                double precio= result.getDouble("precio");
                double precio2= result.getDouble("precio2");
                double costo= result.getDouble("costo");
                String observacion= result.getString("observacion");
                double stock= result.getDouble("stock");
                
                p.setCodigo(codigo);
                p.setCategoria(categoria);                
                p.setDescripcion(descripcion);
                p.setPrecio(precio);
                p.setPrecio2(precio2);
                p.setCosto(costo);
                p.setStock(stock);
                p.setObservacion(observacion);
                articulos.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return articulos;
	}
	
	public void guardarMesa(int numeroMesa, ArticuloMesa m) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into ARTICULOS_MESA(mesa_numero,hora,articulo_codigo,articulo_descripcion,cantidad,precio,total,observacion,item_numero) values (?,?,?,?,?,?,?,?,?)");
			
            
            st.setInt(1,m.getMesa_numero());
            st.setString(2,m.getHora());
            st.setString(3,m.getArticulo_codigo());
            st.setString(4,m.getArticulo_descripcion());
            st.setDouble(5,m.getCantidad());
            st.setDouble(6,m.getPrecio());
            st.setDouble(7,m.getTotal());
            st.setString(8,m.getObservacion());
            st.setInt(9,m.getItem_numero());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	
	public ArrayList<Pago> getPagos(){
		ArrayList<Pago> pagos= new ArrayList<Pago>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from PAGOS order by numero ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Pago p = new Pago();
                int numero= result.getInt("numero");
                String fecha= result.getString("fecha");
                double monto= result.getDouble("monto");                
                String proveedor= result.getString("proveedor");
                String detalle= result.getString("detalle");
                
                p.setNumero(numero);
                p.setFecha(fecha);
                p.setMonto(monto);
                p.setProveedor(proveedor);
                p.setDetalle(detalle);
                
                pagos.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return pagos;
	}
	
	public ArrayList<Pago> getPagos(String Proveedor,String fecha_desde, String fecha_hasta){
		ArrayList<Pago> pagos= new ArrayList<Pago>();
		ResultSet result = null;
		fecha_desde+= " 00:00:00";
		fecha_hasta+= " 23:59:59";
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from PAGOS where proveedor LIKE '%"+Proveedor+"%' and fecha between '"+fecha_desde+"' and '"+fecha_hasta+"' order by numero ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Pago p = new Pago();
                int numero= result.getInt("numero");
                String fecha= result.getString("fecha");
                double monto= result.getDouble("monto");                
                String proveedor= result.getString("proveedor");
                String detalle= result.getString("detalle");
                
                p.setNumero(numero);
                p.setFecha(fecha);
                p.setMonto(monto);
                p.setProveedor(proveedor);
                p.setDetalle(detalle);
                
                pagos.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return pagos;
	}
                
	public ArrayList<Recibo> getRecibos(){
		ArrayList<Recibo> recibos= new ArrayList<Recibo>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from RECIBOS order by numero ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Recibo p = new Recibo();
                int numero= result.getInt("numero");
                String fecha= result.getString("fecha");
                double monto= result.getDouble("monto");                
                String cliente= result.getString("cliente");
                String detalle= result.getString("detalle");
                
                p.setNumero(numero);
                p.setFecha(fecha);
                p.setMonto(monto);
                p.setCliente(cliente);
                p.setDetalle(detalle);
                
                recibos.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return recibos;
	}
	
	public ArrayList<Recibo> getRecibos(String Cliente,String fecha_desde, String fecha_hasta){
		ArrayList<Recibo> recibos= new ArrayList<Recibo>();
		ResultSet result = null;
		fecha_desde+= " 00:00:00";
		fecha_hasta+= " 23:59:59";
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from RECIBOS where cliente LIKE '%"+Cliente+"%' and fecha between '"+fecha_desde+"' and '"+fecha_hasta+"' order by numero ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Recibo p = new Recibo();
                int numero= result.getInt("numero");
                String fecha= result.getString("fecha");
                double monto= result.getDouble("monto");                
                String cliente= result.getString("cliente");
                String detalle= result.getString("detalle");
                
                p.setNumero(numero);
                p.setFecha(fecha);
                p.setMonto(monto);
                p.setCliente(cliente);
                p.setDetalle(detalle);
                
                recibos.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return recibos;
	}
	
	public Articulo getArticulo(String Articulo) {		
		ResultSet result = null;
		Articulo p = null;
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS where codigo = '"+Articulo+"'");
			result = st.executeQuery();			
			
            p = new Articulo();
            String codigo= result.getString("codigo");
            String descripcion= result.getString("descripcion");
            String categoria= result.getString("categoria");
            double precio= result.getDouble("precio");
            double precio2= result.getDouble("precio2");
            double costo= result.getDouble("costo");
            String observacion= result.getString("observacion");
            double stock= result.getDouble("stock");
            
            p.setCodigo(codigo);
            p.setCategoria(categoria);                
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setPrecio2(precio2);
            p.setCosto(costo);
            p.setStock(stock);
            p.setObservacion(observacion);
        	
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return p;
	}

	public ArrayList<ArticuloMesa> getArticulosMesa(){
		ArrayList<ArticuloMesa> articulosMesa = new ArrayList<ArticuloMesa>();
		ResultSet result = null;
		ArticuloMesa a = null;
		try {
    		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS_MESA");
            result = st.executeQuery();
            
            while (result.next()) {
            	a = new ArticuloMesa();
            	
            	
                int mesa_numero = result.getInt("mesa_numero");
                String hora= result.getString("hora");
                String articulo_codigo= result.getString("articulo_codigo");
                String articulo_descripcion= result.getString("articulo_descripcion");
                double cantidad= result.getDouble("cantidad");  
                double precio = result.getDouble("precio");
                String total= result.getString("total");
                String observacion= result.getString("observacion");
                int item_numero = result.getInt("item_numero");
                
                a.setMesa_numero(mesa_numero);
                a.setArticulo_codigo(articulo_codigo);
                a.setArticulo_descripcion(articulo_descripcion);
                a.setHora(hora);
                a.setCantidad(cantidad);
                a.setPrecio(precio);
                a.setTotal(item_numero);
                a.setObservacion(observacion);
                a.setItem_numero(item_numero);
                
                
                articulosMesa.add(a);
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return articulosMesa;
		
		
	}
	
	public ArrayList<ArticuloMesa> getArticulosMesa(int numeroMesa){
		ArrayList<ArticuloMesa> articulosMesa = new ArrayList<ArticuloMesa>();
		ResultSet result = null;
		ArticuloMesa a = null;
		try {
    		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS_MESA where mesa_numero ='"+numeroMesa+"'");
            result = st.executeQuery();
            
            while (result.next()) {
            	a = new ArticuloMesa();
                int mesa_numero = result.getInt("mesa_numero");
                String hora= result.getString("hora");
                String articulo_codigo= result.getString("articulo_codigo");
                String articulo_descripcion= result.getString("articulo_descripcion");
                double cantidad= result.getDouble("cantidad");  
                double precio = result.getDouble("precio");
                double total= result.getDouble("total");
                String observacion= result.getString("observacion");
                int item_numero = result.getInt("item_numero");
                
                a.setMesa_numero(mesa_numero);
                a.setArticulo_codigo(articulo_codigo);
                a.setArticulo_descripcion(articulo_descripcion);
                a.setHora(hora);
                a.setCantidad(cantidad);
                a.setPrecio(precio);
                a.setTotal(total);
                a.setObservacion(observacion);
                a.setItem_numero(item_numero);
                
                
                articulosMesa.add(a);
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return articulosMesa;
		
		
	}
	
	
 	public void guardarVenta(Venta v) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into VENTAS(numero,fecha,cliente,camion,forma_de_pago,total_venta,total_costo,haber,detalle,ganancia) values (?,?,?,?,?,?,?,?,?,?)");
			
            
            st.setInt(1,v.getNumero());
            st.setString(2,v.getFecha());
            st.setString(3,v.getCliente());
            st.setString(4,v.getCamion());
            st.setString(5,v.getForma_de_pago());
            st.setDouble(6,v.getPrecio_venta());
            st.setDouble(7,v.getPrecio_costo());
            st.setDouble(8,v.getHaber());
            st.setString(9,v.getDetalle());
            st.setDouble(10,v.getGanancia());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void guardarCompra (Compra v) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into COMPRAS(numero,fecha,proveedor,forma_de_pago,total,detalle,haber) values (?,?,?,?,?,?,?)");
			
            
            st.setInt(1,v.getNumero());
            st.setString(2,v.getFecha());
            st.setString(3,v.getProveedor());
            st.setString(4,v.getForma_de_pago());
            st.setDouble(5,v.getTotal());
            st.setString(6,v.getDetalle());
            st.setDouble(7,v.getHaber());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public int getNumeroUltima_Venta() {
		
		int numero = 0;
				
		ResultSet result = null;		
		try {
            PreparedStatement st = conexion.prepareStatement("select numero from VENTAS order by numero DESC limit 1");
            result = st.executeQuery();
            numero =result.getInt("numero");            
        }catch (SQLException e) {
				System.out.println(e);
		}		
		return numero;		
	}
	
	public int getNumeroUltima_Compra() {
			int numero = 0;
					
			ResultSet result = null;		
			try {
	            PreparedStatement st = conexion.prepareStatement("select numero from COMPRAS order by numero DESC limit 1");
	            result = st.executeQuery();
	            numero =result.getInt("numero");            
	        }catch (SQLException e) {
					System.out.println(e);
			}		
			return numero;		
		
	}
	
	public void guardarVentaDetalle(ArticuloSeleccionado v, int numero_venta) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into VENTAS_ARTICULOS(venta_numero,articulo,cantidad,precio_unitario,precio_costo,subtotal,subtotal_costo,ganancia) values (?,?,?,?,?,?,?,?)");
			
            
            st.setInt(1,numero_venta);
            st.setString(2,v.getArticulo());
            st.setDouble(3,v.getCantidad());
            st.setDouble(4,v.getPrecio_unitario());
            st.setDouble(5,v.getPrecio_costo());
            st.setDouble(6,v.getSubtotal());
            st.setDouble(7,v.getSubtotal_costo());
            st.setDouble(8,v.getGanancia());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void guardarCompraDetalle(ArticuloSeleccionadoCompra v, int numero_compra) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into COMPRAS_ARTICULOS(compra_numero,articulo,cantidad,precio_costo,subtotal) values (?,?,?,?,?)");
			
            
            st.setInt(1,numero_compra);
            st.setString(2,v.getArticulo());
            st.setDouble(3,v.getCantidad());
            st.setDouble(4,v.getPrecio_costo());
            st.setDouble(5,v.getSubtotal());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public ArrayList<Venta> getVentas(String fecha_desde, String fecha_hasta,String Camion, String Cliente, String Forma_de_pago){
		fecha_desde+= " 00:00:00";
		fecha_hasta+= " 23:59:59";
		
		String Query ="select * from VENTAS where fecha between '"+fecha_desde+"' and '"+fecha_hasta+"'";

		if(!Camion.equals("TODOS")) {
				Query+= " and camion = '"+Camion+"'  ";
			}
		
		if(!Cliente.equals("TODOS")) {
			Query+= " and cliente = '"+Cliente+"'  ";
		}
		
		if(!Forma_de_pago.equals("TODAS")) {
			Query+= " and forma_de_pago = '"+Forma_de_pago+"'  ";
		}
		
		Query+= " order by numero DESC";	
		ArrayList<Venta> ventas= new ArrayList<Venta>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement(Query);
            result = st.executeQuery();
            
            while (result.next()) {
            	Venta p = new Venta();
                int numero= result.getInt("numero");
                String fecha= result.getString("fecha");              
                String cliente= result.getString("cliente");
                String camion= result.getString("camion");
                String forma_de_pago= result.getString("forma_de_pago");              
                double total_venta= result.getDouble("total_venta");
                double total_costo= result.getDouble("total_costo");
                double haber= result.getDouble("haber");              
                String detalle= result.getString("detalle");
                double ganancia= result.getDouble("ganancia");
                
                p.setNumero(numero);
                p.setFecha(fecha);
                p.setCliente(cliente);
                p.setCamion(camion);
                p.setForma_de_pago(forma_de_pago);
                p.setPrecio_venta(total_venta);
                p.setPrecio_costo(total_costo);
                p.setHaber(haber);
                p.setDetalle(detalle);
                p.setGanancia(ganancia);
                
                ventas.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
        return ventas;
	}
	
	public Venta getVenta(int Numero) {
		Venta p = new Venta();
		ResultSet result = null;
		try {
            PreparedStatement st = conexion.prepareStatement("select * from VENTAS where numero = "+Numero);
            result = st.executeQuery();
                       
            int numero= result.getInt("numero");
            String fecha= result.getString("fecha");              
            String cliente= result.getString("cliente");
            String camion= result.getString("camion");
            String forma_de_pago= result.getString("forma_de_pago");              
            double total_venta= result.getDouble("total_venta");
            double total_costo= result.getDouble("total_costo");
            double haber= result.getDouble("haber");              
            String detalle= result.getString("detalle");
            double ganancia= result.getDouble("ganancia");
            
            p.setNumero(numero);
            p.setFecha(fecha);
            p.setCliente(cliente);
            p.setCamion(camion);
            p.setForma_de_pago(forma_de_pago);
            p.setPrecio_venta(total_venta);
            p.setPrecio_costo(total_costo);
            p.setHaber(haber);
            p.setDetalle(detalle);
            p.setGanancia(ganancia);
                           	
        }catch (SQLException e) {
        	System.out.println(e);
        }
        return p;
	}

	public Compra getCompra(int Numero) {
		Compra p = new Compra();
		ResultSet result = null;
		try {
            PreparedStatement st = conexion.prepareStatement("select * from COMPRAS where numero = "+Numero);
            result = st.executeQuery();
                       
            int numero= result.getInt("numero");
            String fecha= result.getString("fecha");              
            String proveedor= result.getString("proveedor");
            String forma_de_pago= result.getString("forma_de_pago");              
            double total= result.getDouble("total");
            double haber= result.getDouble("haber");              
            String detalle= result.getString("detalle");
            
            p.setNumero(numero);
            p.setFecha(fecha);
            p.setProveedor(proveedor);
            p.setForma_de_pago(forma_de_pago);
            p.setTotal(total);
            p.setHaber(haber);
            p.setDetalle(detalle);
                           	
        }catch (SQLException e) {
        	System.out.println(e);
        }
        return p;
	}
	

	public ArrayList<Compra> getCompras(String fecha_desde, String fecha_hasta,String Proveedor, String Forma_de_pago){
		fecha_desde+= " 00:00:00";
		fecha_hasta+= " 23:59:59";
		
		String Query ="select * from COMPRAS where fecha between '"+fecha_desde+"' and '"+fecha_hasta+"'";

		if(!Proveedor.equals("TODOS")) {
			Query+= " and proveedor = '"+Proveedor+"'  ";
		}
		
		if(!Forma_de_pago.equals("TODAS")) {
			Query+= " and forma_de_pago = '"+Forma_de_pago+"'  ";
		}
		
		Query+= " order by numero DESC";	
		ArrayList<Compra> compras= new ArrayList<Compra>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement(Query);
            result = st.executeQuery();
            
            while (result.next()) {
            	Compra p = new Compra();
                int numero= result.getInt("numero");
                String fecha= result.getString("fecha");              
                String proveedor= result.getString("proveedor");
                String forma_de_pago= result.getString("forma_de_pago");              
                double total= result.getDouble("total");
                double haber= result.getDouble("haber");              
                String detalle= result.getString("detalle");
                
                p.setNumero(numero);
                p.setFecha(fecha);
                p.setProveedor(proveedor);
                p.setForma_de_pago(forma_de_pago);
                p.setTotal(total);
                p.setHaber(haber);
                p.setDetalle(detalle);
                
                compras.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
        return compras;
	}
	
	public  ArrayList<ArticuloSeleccionado> getVentasArticulos(int numero_venta){
		
		String Query ="select * from VENTAS_ARTICULOS where venta_numero = '"+numero_venta+"'";

		ArrayList<ArticuloSeleccionado> articulos= new ArrayList<ArticuloSeleccionado>();
		ResultSet result = null;
        try {
        		
            PreparedStatement st = conexion.prepareStatement(Query);
            result = st.executeQuery();
            
            while (result.next()) {
            	ArticuloSeleccionado p = new ArticuloSeleccionado();
            	
                String articulo= result.getString("articulo");              
                int cantidad= result.getInt("cantidad");
                double precio_venta= result.getDouble("precio_unitario");
                double precio_costo= result.getDouble("precio_costo");              
                double subtotal_venta= result.getDouble("subtotal");
                double total_costo= result.getDouble("subtotal_costo");
                double ganancia= result.getDouble("ganancia");
                
                p.setArticulo(articulo);
                p.setCantidad(cantidad);
                p.setPrecio_unitario(precio_venta);
                p.setPrecio_costo(precio_costo);
                p.setSubtotal(subtotal_venta);
                p.setSubtotal_costo(total_costo);
                p.setGanancia(ganancia);
                
                articulos.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
        return articulos;
	}

	public ArrayList<ArticuloSeleccionadoCompra> getComprasArticulos(int numero_compra){
		String Query ="select * from COMPRAS_ARTICULOS where compra_numero = '"+numero_compra+"'";

		ArrayList<ArticuloSeleccionadoCompra> articulos= new ArrayList<ArticuloSeleccionadoCompra>();
		ResultSet result = null;
        try {
        		
            PreparedStatement st = conexion.prepareStatement(Query);
            result = st.executeQuery();
            
            while (result.next()) {
            	ArticuloSeleccionadoCompra p = new ArticuloSeleccionadoCompra();
            	
                String articulo= result.getString("articulo");              
                double cantidad= result.getDouble("cantidad");
                double precio_costo= result.getDouble("precio_costo");              
                double subtotal= result.getDouble("subtotal");
                
                p.setArticulo(articulo);
                p.setCantidad(cantidad);
                p.setPrecio_costo(precio_costo);
                p.setSubtotal(subtotal);
                
                articulos.add(p);     
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
        return articulos;
	}
	
	public double ejecutarQuery(String query,String get) {
		double numero = 0;
		
		ResultSet result = null;		
		try {
            PreparedStatement st = conexion.prepareStatement(query);
            result = st.executeQuery();
            numero =result.getDouble(get);            
        }catch (SQLException e) {
				System.out.println(e);
		}		
		return numero;	
	}

	public void descontarStock(String articulo, double cantidad) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("update ARTICULOS set stock = stock-"+cantidad+" where articulo ='"+articulo+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void sumarStock(String articulo, double cantidad) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("update ARTICULOS set stock = stock+"+cantidad+" where articulo ='"+articulo+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarVenta(int numero) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from VENTAS where numero='"+numero+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarArticulosVenta(int numero) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from VENTAS_ARTICULOS where venta_numero='"+numero+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarCompra(int numero) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from COMPRAS where numero='"+numero+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarArticulosCompra(int numero) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from COMPRAS_ARTICULOS where compra_numero='"+numero+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarArticulo(String articulo) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from ARTICULOS where codigo ='"+articulo+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarCliente(String cliente) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from CLIENTES where cliente ='"+cliente+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarProveedor(String proveedor) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from PROVEEDORES where proveedor ='"+proveedor+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarRecibo(int numero) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from RECIBOS where numero ='"+numero+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarPago(int numero) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from PAGOS where numero ='"+numero+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void resetAutoincrement(String tabla, int numero) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("ALTER TABLE "+tabla+" AUTO_INCREMENT = "+numero+";");
			 ps.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
	public int getUltimoNumero(String tabla) {
		int numero = 0;
		
		ResultSet result = null;		
		try {
            PreparedStatement st = conexion.prepareStatement("select numero from "+tabla+" order by numero DESC limit 1");
            result = st.executeQuery();
            numero =result.getInt("numero");            
        }catch (SQLException e) {
				System.out.println(e);
		}		
		return numero;	
	}

	public void modificarArticulo(Articulo c) {
		try {
			
			String query = "update ARTICULOS set "+			 
					"descripcion = '"+c.getDescripcion()+"',"+
					"precio = '"+c.getPrecio()+"',"+
					"costo = '"+c.getCosto()+"',"+
					"categoria = '"+c.getCategoria()+"',"+
					"stock = '"+c.getStock()+"',"+
					"observacion = '"+c.getObservacion()+"' "+
					"WHERE codigo = '"+c.getCodigo()+"'";
					
					
			 PreparedStatement ps = conexion.prepareStatement(query);
			 
			 			 
			 
				    ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	
	
	public double getTotalMesa(int numeroMesa) {

		double total = 0;
		
		ResultSet result = null;
		
		try {
            PreparedStatement st = conexion.prepareStatement("SELECT sum(total) FROM ARTICULOS_MESA where mesa_numero ='"+numeroMesa+"'");
            result = st.executeQuery();
            total =result.getInt("sum(total)");            
        }catch (SQLException e) {
				System.out.println(e);
		}
		
		return total;
	}
	
}


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
	
	public void guardarCategoria(String Descripcion) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into CATEGORIAS (codigo, descripcion) values (?,?)");
			String ultimo = getCodigoUltimaCategoria();			
			
			if(ultimo.isEmpty()) {
            	ultimo = "0"; 
            }
			
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
	
	public String getCodigoUltimoMesero() {
	
		
		ResultSet result = null;
		String codigo = "";
		
		
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from MESEROS order by codigo DESC limit 1");
			result = st.executeQuery();			
			
            codigo= result.getString("codigo");
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return codigo;
	}
	
	public int getNumeroUltimaVenta() {
	
		
		ResultSet result = null;
		int numero = 0;
		
		
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from VENTAS order by numero DESC limit 1");
			result = st.executeQuery();			
			
			numero= result.getInt("numero");
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return numero;
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
	
	public boolean existeMesero(String nombre) {
		ResultSet result = null;
		boolean ret = false;
		
		try {
			PreparedStatement st = conexion.prepareStatement("select nombre from MESEROS where nombre = '"+nombre+"'");
			result = st.executeQuery();			
			String Nombre= result.getString("nombre");
            
            if(Nombre.equals(nombre)) {
            	ret=true;
            }
            
		} catch (Exception e) {}
				
		return ret;
	}
	
	public String getCodigoUltimoArticulo() {
		
		
		ResultSet result = null;
		String codigo = "";
		String ultimo ="";
		
		
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
			
            
            st.setInt(1,c.getCodigo());
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

	public void guardarMesero(String nombre) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into MESEROS (codigo,nombre) values (?,?)");
			String ultimo = getCodigoUltimoMesero();
			
			if(ultimo.isEmpty()) {
            	ultimo = "0"; 
            }
            
            st.setString(1,String.valueOf(Integer.valueOf(ultimo)+1));
            st.setString(2,nombre);
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void guardarVenta(Venta Venta) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into VENTAS (mesa_numero,precio,costo,fecha,hora,descuento,recargo,mesero_nombre,observacion) values (?,?,?,?,?,?,?,?,?)");
			
            st.setInt(1,Venta.getMesa_numero());
            st.setDouble(2,Venta.getPrecio());
            st.setDouble(3,Venta.getCosto());
            st.setString(4,Venta.getFecha());
            st.setString(5,Venta.getHora());
            st.setDouble(6,Venta.getDescuento());
            st.setDouble(7,Venta.getRecargo());
            st.setString(8,Venta.getMesero());
            st.setString(9,Venta.getObservacion());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Articulo> getArticulos(){
		ArrayList<Articulo> articulos= new ArrayList<Articulo>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS order by descripcion ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Articulo p = new Articulo();
                int codigo= result.getInt("codigo");
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
	
	public ArrayList<Mesero> getMeseros(){
		ArrayList<Mesero> meseros= new ArrayList<Mesero>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from MESEROS order by nombre ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Mesero m = new Mesero();
                String codigo= result.getString("codigo");
                String nombre= result.getString("nombre");
                
                m.setCodigo(codigo);
                m.setNombre(nombre);
                meseros.add(m);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return meseros;
	}
	
	
	
	public ArrayList<Articulo> getArticulos(String Categoria){
		ArrayList<Articulo> articulos= new ArrayList<Articulo>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS where categoria = '"+Categoria+"' order by descripcion ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Articulo p = new Articulo();
                int codigo= result.getInt("codigo");
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
        		
            PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS where descripcion LIKE '%"+Articulo+"%' order by descripcion ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Articulo p = new Articulo();
                int codigo= result.getInt("codigo");
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
                int codigo= result.getInt("codigo");
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
		
	public Articulo getArticulo(String Articulo) {		
		ResultSet result = null;
		Articulo p = null;
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from ARTICULOS where codigo = '"+Articulo+"'");
			result = st.executeQuery();			
			
            p = new Articulo();
            int codigo= result.getInt("codigo");
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
	
	public String getMesero(int Mesa_numero) {		
		ResultSet result = null;
		String mesero = null;
		try {			
			PreparedStatement st = conexion.prepareStatement("select mesero_nombre from MESAS where numero = "+Mesa_numero+"");
			result = st.executeQuery();			
			
            
            mesero= result.getString("mesero_nombre");
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return mesero;
	}
	
	
	
	public MesaClase getMesa(int Mesa) {		
		ResultSet result = null;
		MesaClase p = null;
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from MESAS where numero = '"+Mesa+"'");
			result = st.executeQuery();			
			
            p = new MesaClase();
            int numero= result.getInt("numero");
            String cuenta= result.getString("cuenta");
            String estado= result.getString("estado");
            String mesero_nombre= result.getString("mesero_nombre");
            double total= result.getDouble("total");
            double descuento= result.getDouble("descuento");
            double recargo= result.getDouble("recargo");
            String nota= result.getString("nota");
            
            p.setNumero(numero);
            p.setCuenta(cuenta);
            p.setEstado(estado);
            p.setMesero(mesero_nombre);
            p.setTotal(total);
            p.setDescuento(descuento);
            p.setRecargo(recargo);
            p.setObservacion(nota);
        	
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
	
	public ArticuloMesa getArticulosMesaCodigo(int numeroMesa, int codigo){
		
		ResultSet result = null;
		ArticuloMesa a = null;
		try {
    		String query = "select * from ARTICULOS_MESA where mesa_numero ='"+numeroMesa+"' AND articulo_codigo = '"+codigo+"'";
    		
            PreparedStatement st = conexion.prepareStatement(query);
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
                
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return a;
		
		
	}
	
	
	
	public ArrayList<MesaClase> getMesaClase(){
		ArrayList<MesaClase> Mesas = new ArrayList<MesaClase>();
		ResultSet result = null;
		MesaClase a = null;
		try {
    		
            PreparedStatement st = conexion.prepareStatement("select * from MESAS order by numero ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	a = new MesaClase();
            	
            	
                int numero = result.getInt("numero");
                String cuenta= result.getString("cuenta");
                String estado= result.getString("estado");
                String mesero_nombre= result.getString("mesero_nombre");
                double total= result.getDouble("total");  
                double descuento = result.getDouble("descuento");
                double recargo= result.getDouble("recargo");
                String nota= result.getString("nota");
                
                a.setNumero(numero);
                a.setCuenta(cuenta);
                a.setEstado(estado);
                a.setMesero(mesero_nombre);
                a.setTotal(total);
                a.setDescuento(descuento);
                a.setRecargo(recargo);                
                a.setObservacion(nota);
                
                
                Mesas.add(a);
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return Mesas;
		
		
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
	
	public void ejecutarQuery(String query) {
		try {
            PreparedStatement ps = conexion.prepareStatement(query);
			ps.executeUpdate();
        }catch (SQLException e) {
				System.out.println(e);
		}		
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
	
	public void eliminarCategoria(String Descripcion) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from CATEGORIAS where descripcion='"+Descripcion+"'");
			 ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarMesero(String nombre) {
		try {
			 PreparedStatement ps = conexion.prepareStatement("delete from MESEROS where nombre='"+nombre+"'");
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
	
	public void modificarMesa(MesaClase c) {
		try {
			
			String query = "update MESAS set "+			 
					"cuenta = '"+c.getCuenta()+"',"+
					"estado = '"+c.getEstado()+"',"+
					"mesero_nombre= '"+c.getMesero()+"',"+
					"total = '"+c.getTotal()+"',"+
					"subtotal = '"+c.getSubtotal()+"',"+
					"descuento = '"+c.getDescuento()+"',"+
					"recargo = '"+c.getRecargo()+"',"+
					"nota = '"+c.getObservacion()+"' "+
					"WHERE numero = '"+c.getNumero()+"'";
					
					
			 PreparedStatement ps = conexion.prepareStatement(query);
			 
			 			 
			 
				    ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void modificarMesero(String codigo, String nombre) {
		try {
			
			String query = "update MESEROS set "+
					"nombre = '"+nombre+"' "+
					"WHERE codigo = '"+codigo+"'";
					
			 PreparedStatement ps = conexion.prepareStatement(query);

			 ps.executeUpdate();
			 
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void modificarCategoria(String codigo, String nombre) {
		try {
			
			String query = "update CATEGORIAS set "+
					"descripcion = '"+nombre+"' "+
					"WHERE codigo = '"+codigo+"'";
					
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
	
	
	public void modificarArticuloMesa(ArticuloMesa c) {
		try {
			
			String query = "update ARTICULOS_MESA set "+			 
					"cantidad = '"+c.getCantidad()+"', "+
					"total = '"+c.getTotal()+"' "+
					"WHERE mesa_numero = '"+c.getMesa_numero()+"' AND articulo_codigo = '"+c.getArticulo_codigo()+"'";
					
					
			 PreparedStatement ps = conexion.prepareStatement(query);
			 
			 			 
			 
				    ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<String> getValores(){
		ArrayList<String> Valores = new ArrayList<String>();
		ResultSet result = null;
		String a = null;
		try {
    		
            PreparedStatement st = conexion.prepareStatement("select * from VALORES order by nombre ASC");
            result = st.executeQuery();
            
            while (result.next()) {
            	
                String nombre= result.getString("nombre");
                
                Valores.add(nombre);
            	}
            	
        }catch (SQLException e) {
        	System.out.println(e);
            }
       
        return Valores;
	}
	
	
}


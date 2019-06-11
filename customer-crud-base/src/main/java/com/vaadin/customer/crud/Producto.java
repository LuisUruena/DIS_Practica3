package com.vaadin.customer.crud;

public class Producto 
{
	    private String codigo_barras;
	    private String nombre;
	    private String cantidad;
	    private String marca;
	    private String precio;
	  

	    public Producto() 
	    {

	    }
	    
	    public Producto(String codigo_barras, String nombre, String cantidad, String marca, String precio) 
	    {
	        setCodigo_barras(codigo_barras);
	        setNombre(nombre);
	        setCantidad(cantidad);
	        setMarca(marca);
	        setPrecio(precio);
	    }

		public String getCodigo_barras() {
			return codigo_barras;
		}

		public void setCodigo_barras(String codigo_barras) {
			this.codigo_barras = codigo_barras;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getCantidad() {
			return cantidad;
		}

		public void setCantidad(String cantidad) {
			this.cantidad = cantidad;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getPrecio() {
			return precio;
		}

		public void setPrecio(String precio) {
			this.precio = precio;
		}
	    
}

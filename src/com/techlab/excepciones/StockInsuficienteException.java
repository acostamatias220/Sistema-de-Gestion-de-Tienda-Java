package com.techlab.excepciones;
 
public class StockInsuficienteException extends Exception {
 
    private String nombreProducto;
    private int stockDisponible;
    private int cantidadSolicitada;
 
    public StockInsuficienteException(String nombreProducto, int stockDisponible, int cantidadSolicitada) {
        super("Stock insuficiente para '" + nombreProducto + "'. Disponible: " + stockDisponible + ", Solicitado: " + cantidadSolicitada);
        this.nombreProducto = nombreProducto;
        this.stockDisponible = stockDisponible;
        this.cantidadSolicitada = cantidadSolicitada;
    }
 
    public String getNombreProducto() { return nombreProducto; }
    public int getStockDisponible() { return stockDisponible; }
    public int getCantidadSolicitada() { return cantidadSolicitada; }
}
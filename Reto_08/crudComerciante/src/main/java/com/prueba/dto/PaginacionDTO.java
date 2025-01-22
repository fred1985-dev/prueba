package com.prueba.dto;


import java.util.List;

public class PaginacionDTO<T> {

    private List<T> contenido; // Elementos de la página actual
    private long totalElementos; // Número total de elementos disponibles
    private int totalPaginas; // Número total de páginas
    private int paginaActual; // Página actual solicitada
    private int elementosPorPagina; // Elementos por página

    // Constructor
    public PaginacionDTO(List<T> contenido, long totalElementos, int paginaActual, int elementosPorPagina) {
        this.contenido = contenido;
        this.totalElementos = totalElementos;
        this.paginaActual = paginaActual;
        this.elementosPorPagina = elementosPorPagina;
        this.totalPaginas = (int) Math.ceil((double) totalElementos / elementosPorPagina);
    }

    // Getters y Setters
    public List<T> getContenido() {
        return contenido;
    }

    public void setContenido(List<T> contenido) {
        this.contenido = contenido;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getElementosPorPagina() {
        return elementosPorPagina;
    }

    public void setElementosPorPagina(int elementosPorPagina) {
        this.elementosPorPagina = elementosPorPagina;
    }
}

package com.filosofiadelsoftware.pruebadb.cocurrencia.dead;

public class Filosofo implements Runnable {
    private String nombre;
    private Tenedor tenedorIzquierdo;
    private Tenedor tenedorDerecho;

    public Filosofo(String nombre, Tenedor izquierdo, Tenedor derecho) {
        this.nombre = nombre;
        this.tenedorIzquierdo = izquierdo;
        this.tenedorDerecho = derecho;
    }

    @Override
    public void run() {
        synchronized (tenedorIzquierdo) {
            System.out.println(nombre + " tomó el tenedor izquierdo 🥄");

            // Simulamos una pausa para aumentar la probabilidad de deadlock
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (tenedorDerecho) {
                System.out.println(nombre + " tomó el tenedor derecho 🍴 y está comiendo");
            }
        }
    }
}

package com.filosofiadelsoftware.pruebadb.cocurrencia.dead;

public class Main {
    public static void main(String[] args) {
        Tenedor t1 = new Tenedor();
        Tenedor t2 = new Tenedor();

        // Dos filósofos que compiten por los mismos recursos
        Thread filosofo1 = new Thread(new Filosofo("Sócrates", t1, t2));
        Thread filosofo2 = new Thread(new Filosofo("Platón", t2, t1));

        filosofo1.start();
        filosofo2.start();
    }
}

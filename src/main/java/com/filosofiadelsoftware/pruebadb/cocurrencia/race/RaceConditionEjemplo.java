package com.filosofiadelsoftware.pruebadb.cocurrencia.race;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RaceConditionEjemplo {
    public static void main(String[] args) {
        Contador contador = new Contador();

        Thread hilo1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) contador.incrementar();
        });

        Thread hilo2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) contador.incrementar();
        });

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            log.error("", e);
        }

        System.out.println("Valor final: " + contador.getValor()); // No será 2000 siempre
    }
}

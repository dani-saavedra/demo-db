package com.filosofiadelsoftware.pruebadb.cocurrencia.starvation;

import java.util.concurrent.locks.ReentrantLock;

public class StarvationEjemplo {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 1 hilo de baja prioridad
        Thread hiloHambriento = new Thread(() -> {
            while (true) {
                if (lock.tryLock()) {
                    try {
                        System.out.println("😢 Hilo hambriento logró entrar. :D");
                    } finally {
                        lock.unlock();
                    }
                    break;
                } else {
                    System.out.println("😢 Hilo hambriento sigue esperando...");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        hiloHambriento.start();
        Thread.sleep(1000);
        // 5 hilos "acaparadores" que siempre entran primero
        for (int i = 1; i <= 5; i++) {
            Thread acaparador = new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        System.out.println("😈 Hilo acaparador trabajando...");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            });
            acaparador.setDaemon(true); // siguen funcionando siempre
            acaparador.start();
        }


    }
}

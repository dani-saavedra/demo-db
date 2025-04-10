package com.filosofiadelsoftware.pruebadb.cocurrencia.ioc;

import org.springframework.stereotype.Service;

@Service
public class PagoService {


    private PagoDTO pagoGlobalDTO;

    public PagoDTO procesarPago(String origen, String destino, int monto) throws InterruptedException {
        pagoGlobalDTO = new PagoDTO(origen, destino, monto);
        System.out.println("Comenzando transacion " + pagoGlobalDTO);
        Thread.sleep(10000);
        System.out.println("Finalizando de la  transacion " + pagoGlobalDTO);
        return pagoGlobalDTO;
    }

}
package com.filosofiadelsoftware.pruebadb.cocurrencia.ioc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagoController {

    @Autowired
    private PagoService pagoService;


    @GetMapping("/pago")
    public PagoDTO procesarPago(@RequestParam String origen, @RequestParam String destino,
                                @RequestParam int monto) throws InterruptedException {
        return pagoService.procesarPago(origen, destino, monto);
    }
}
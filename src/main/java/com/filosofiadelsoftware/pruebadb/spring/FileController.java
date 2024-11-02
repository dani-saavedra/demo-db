package com.filosofiadelsoftware.pruebadb.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@Slf4j
public class FileController {

    private static final String FILE_PATH = "/Users/danielsaavedra/temporal/output.txt";

    @PostMapping("/add")
    public String addStringToFile(@RequestBody String input) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                String content = "Fecha: " + LocalDateTime.now() + " - Contenido: " + input + System.lineSeparator();
                writer.write(content);
            }
            return "El contenido ha sido agregado al archivo.";
        } catch (IOException e) {
            log.error("error al agregar texto ", e);
            return "Ocurrió un error al agregar el contenido al archivo.";
        }
    }
}

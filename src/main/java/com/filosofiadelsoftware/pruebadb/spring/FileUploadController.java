package com.filosofiadelsoftware.pruebadb.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private static final String UPLOAD_DIRECTORY = "/Users/danielsaavedra/temporal/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {


        try {
            // Asegúrate de que el directorio exista
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Guarda el archivo en el directorio de destino
            String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not save the file. Error: " + e.getMessage());
        }
    }
}


package com.filosofiadelsoftware.pruebadb;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class ExponentialBackoffRetry {

    private static final int MAX_ATTEMPTS = 5;
    private static final int INITIAL_DELAY_MS = 1000; // 1 segundo

    public static void main(String[] args) {
        String url = "http://example.com/api";
        int attempt = 1;
        int delay = INITIAL_DELAY_MS;

        while (attempt <= MAX_ATTEMPTS) {
            try {
                int responseCode = makeRequest(url);
                if (responseCode == 200) {
                    System.out.println("Request successful!");
                    break;
                }
                throw new IOException("Unexpected response code: " + responseCode);
            } catch (IOException e) {
                log.error("Attempt {} failed: ", attempt, e);
                if (attempt == MAX_ATTEMPTS) {
                    System.err.println("Max attempts reached. Failing.");
                    break;
                }
                try {
                    System.out.println("Retrying in " + delay + " ms...");
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                delay *= 2;
                attempt++;
            }
        }
    }

    private static int makeRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        return connection.getResponseCode();
    }
}


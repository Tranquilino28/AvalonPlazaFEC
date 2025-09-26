package org.softfriascorporations.avalonplazafec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvalonPlazaFecApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvalonPlazaFecApplication.class, args);
    }


    /**
     *
     * EN LA APP DE ESCRITORIO
     * MANEJO DE LOS ERRORES Y CODIHOS HTTP
     *
     * public class ClienteAPIHttpClient {
     *     public static void main(String[] args) throws Exception {
     *         HttpClient client = HttpClient.newHttpClient();
     *         HttpRequest request = HttpRequest.newBuilder()
     *                 .uri(new URI("http://localhost:8080/usuarios/1"))
     *                 .GET()
     *                 .build();
     *
     *         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
     *
     *         int status = response.statusCode();
     *
     *         if (status == 200) {
     *             System.out.println("Usuario: " + response.body());
     *         } else if (status == 404) {
     *             System.out.println("⚠️ Usuario no encontrado");
     *         } else {
     *             System.out.println("Código recibido: " + status);
     *         }
     *     }
     * }
     */
}

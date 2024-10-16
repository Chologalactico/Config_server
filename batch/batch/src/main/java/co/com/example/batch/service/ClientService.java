package co.com.example.batch.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    @Scheduled(fixedRate = 15000)  
    public void callOrchestrator() {
        String requestBody = """
        {
            "data": [
                {
                    "header": {
                        "id": "12345",
                        "type": "TestGiraffeRefrigerator"
                    }
                }
            ]
        }
        """;

        Mono<String> response = webClient.post()
                .uri("/execute") // EL endpoint de mi orquestador 
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(
                result -> System.out.println("Orquestador: \n" + result),
                error -> System.err.println("Error,al momento de llamar el orquestador: " + error.getMessage())
        );
    }
}
package co.com.example.webHook.service;

import org.springframework.stereotype.Service;

@Service
public class WebhookService {
	
	public void processWebhook() {
        // Lógica para procesar el webhook
        System.out.println("Procesando el mensaje del orquestador");
    }

}

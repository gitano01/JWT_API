package com.sec.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CredencialesServiceInvoke {

	private final String url_credenciales = "http://localhost:8085/efvServ/credenciales";
	private final RestTemplate restTemplate;
	
	
	public CredencialesServiceInvoke(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}	
//    public List<String> fetchDataFromApi() {
//        // Realiza una solicitud GET al servicio REST
//        String response = restTemplate.getForObject(url_credenciales + "/endpoint", String.class);
//        return response;
//    }

	
	
	
	
	/// funcion para realizar llamada post
	public String postDataToApi(String requestBody) throws Exception {
		// Configura las cabeceras para una solicitud POST
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			requestBody = "{\"user_id\":1,\"api_id\": 1}";

			// Crea una entidad HTTP con el cuerpo de la solicitud
			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

			// Realiza una solicitud POST al servicio REST
			String response = restTemplate.postForObject(url_credenciales + "/getCredencial", requestEntity,
					String.class);
			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}

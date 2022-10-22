package telran.fixer.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.fixer.dto.ResponseCurrencyDto;

public class FixerCurrencyController {
	static final RestTemplate restTemplate = new RestTemplate();
	static final String TOKEN = "XAbzZQtuYzhRq0wvnW4nPZdy91jp6ZuG";
	static String baseUrl = "https://api.apilayer.com/fixer/convert";

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("From currency");
		String fromCurrency = br.readLine();
		System.out.println("To currency");
		String toCurrency = br.readLine();
		System.out.println("Amount");
		int amount = Integer.parseInt(br.readLine());
		System.out.println("Do you need exchange rates for today? If yes, type 'Y', If no type 'N'");
		String answer = br.readLine();
		String dateString = null;
		if (answer.equals("Y")) {
			dateString = LocalDate.now().toString();
		}
		if (answer.equals("N")) {
			System.out.println("Specify a date (format YYYY-MM-DD)");

			dateString = br.readLine();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("apikey", TOKEN);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("from", fromCurrency)
				.queryParam("to", toCurrency).queryParam("amount", amount).queryParam("date", dateString);
		RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, builder.build().toUri());
		ResponseEntity<ResponseCurrencyDto> responseEntity = restTemplate.exchange(requestEntity,
				ResponseCurrencyDto.class);

		System.out.println(amount + " " + fromCurrency + " -> " + responseEntity.getBody().getResult() + " "
				+ toCurrency + " " + dateString + " (Rate -> " + responseEntity.getBody().getInfo().get("rate") + ")");
	}

}

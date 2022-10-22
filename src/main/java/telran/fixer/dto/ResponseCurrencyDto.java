package telran.fixer.dto;

import java.util.Map;

import lombok.Getter;

@Getter
public class ResponseCurrencyDto {
	double result;
	Map<String, Double> info;
	String date;

}

package telran.fixer.dto;

import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class ResponseCurrencyDto {
	double result;
	Map<String, Double> info;
//	String date;
//	@JsonFormat(pattern = "yyyy-MM-dd") 
	//в случае если будет с апи приходить другой патерн даты
	LocalDate date;

}

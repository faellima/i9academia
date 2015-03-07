package br.unipe.academia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.services.MedicaoService;

public class Util {
	
	public static Date manipulardorDeDatas(String data) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		Date oficialData = new Date(format.parse(data).getTime());  
		return oficialData;
	}
	
	public static Date editorDeData(String data){
		Date dataFinal = null;
		String parteData[];
		String dataIntermediaria = "";
		//
		
		System.out.println("Vem verจจจจจจจจจจจจจจจจจจจจจจจจจจ"+data);
		
		if(data.contains("/")){
			parteData = data.split("/");
		}else{
			parteData = data.split("-");
		}
		
		if(parteData[0].length() > 2){
			dataIntermediaria = parteData[2]+"/"+parteData[1]+"/"+parteData[0];
		}else{
			dataIntermediaria = parteData[0]+"/"+parteData[1]+"/"+parteData[2];
		}
		try {
			return manipulardorDeDatas(dataIntermediaria);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static String converterDataEmString(Date date){
		try {
			String formated = null;
	        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
	        formated = formatar.format(date);
	        return formated;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return null;
	    }
	}
}


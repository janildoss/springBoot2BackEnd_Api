package com.Api.springBoot2BackEnd_Api.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	//Retirar espaço da URL de um item da pesquisa exemplo cama mesa>cama%20mesa
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		}
	    catch(UnsupportedEncodingException e){
	    	return "";
		}		
	}
	
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		
		//com labda faz o mesmo que o de cima
		//return Arrays.asList(s.split(",")).stream().map(x-> Integer.parseInt(x)).collect(Collectors.toList());
	}

}


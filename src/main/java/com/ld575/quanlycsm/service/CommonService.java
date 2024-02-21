package com.ld575.quanlycsm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

	public String removefirstLastCharInString(char chr, String s) {
		if (isEmpty(s)) {
			return "";
		}
		s = s.trim();
		if (s.charAt(0) == chr) {
			s = s.substring(1); 
		}
		if (s.charAt(s.length() - 1) == chr) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
	
	public boolean isEmpty(String s) {
		return s == null || s.isEmpty() || s == "null";
	}
	
	public String upperFirstCharacter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public String upperFirstCharInWords(String str) {
		String [] arr = str.split("\\s");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; ++i) {
			if (isEmpty(arr[i])) {
				continue;
			}
			if (sb.length() != 0) {
				sb.append(" ");
			}
			sb.append(arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1));
		}
		return sb.toString().trim();
	}
	
	public Date convertToDate(String str) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(str);
		} catch (ParseException exception) {
			return new Date();
		}
	}
	
	public String emptyValue(String str) {
		return isEmpty(str) ? "" : str; 
	}
	
	public Integer emptyValue(Integer i) {
		return i == null ? 0 : i;
	}
}

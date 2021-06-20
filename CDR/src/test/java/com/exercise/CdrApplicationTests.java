package com.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class CdrApplicationTests {

	public static void main(String[] args) throws ParseException {
		//2021-06-13 21:38:45
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-06-13 21:38:45");
		System.out.println(d);
	}

}

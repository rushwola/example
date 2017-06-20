package com.test;

import java.io.IOException;
import java.sql.SQLException;

import com.rulai.framework.generator.FrameworkGenerator;

public class FrameworkGeneratorTest {

	public static void main(String[] args) throws Exception {
		FrameworkGenerator generator=new FrameworkGenerator();
		
		generator.serviceGenerator();
	}

}

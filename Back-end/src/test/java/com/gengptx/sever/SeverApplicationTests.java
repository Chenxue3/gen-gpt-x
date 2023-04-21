package com.gengptx.sever;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeverApplicationTests {

	@Test
	void contextLoads() {

	}

	//a Junit test to test ServerApplication
	@Test
	public void testServerApplication() {
		SeverApplication.main(new String[] {});
	}

}

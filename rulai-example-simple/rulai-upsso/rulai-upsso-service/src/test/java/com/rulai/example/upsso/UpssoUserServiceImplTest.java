package com.rulai.example.upsso;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ruali.upsso.dao.model.UpssoUserExample;
import com.ruali.upsso.facade.UpssoUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:applicationContext.xml",
    "classpath:META-INF/spring/applicationContext-jdbc.xml",
    "classpath:META-INF/spring/applicationContext-listener.xml",
})
public class UpssoUserServiceImplTest {
	
	
	@Autowired
	private UpssoUserService   upssoUserService;
	
	

	@Test
	public void testCountByExample() {
//		fail("Not yet implemented");
		
		UpssoUserExample example=new UpssoUserExample();
		
		int num=upssoUserService.countByExample(example);
		
		assertTrue("测试", num>=0);
	}

	
}

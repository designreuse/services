package com.il.sod.utils;

import org.junit.Assert;
import org.junit.Test;

import com.il.sod.rest.util.PropertyHandler;

public class TestUtils {
	
	@Test
	public void testProperties(){
		try{
			Assert.assertNotNull(PropertyHandler.getInstance().getValue("rest.api.basepath"));
			Assert.assertTrue(PropertyHandler.getInstance().getValue("rest.api.basepath").equals("/api"));
			Assert.assertEquals("Error in property", PropertyHandler.getInstance().getValue("rest.api.basepath"), "/api");
		}catch(Exception e){
			Assert.fail("Assert Exception:" + e.getMessage());
		}
	}
}

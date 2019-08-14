package com.shaodw.security;

import org.junit.Test;

public class SecurityTest {
	@Test
	public void demo1() {
		//≤‚ ‘“ÏªÚ
		String str = "hello world";
		str = SecurityUtil.xor(str);
		System.out.println(str);
		str = SecurityUtil.xor(str);
		System.out.println(str);
	}
	
	@Test
	public void demo2() {
		//≤‚ ‘md5
		System.out.println(SecurityUtil.md5Encode("hello world".getBytes()));
	}
	
	@Test
	public void demo3() {
		//≤‚ ‘sha256
		System.out.println(SecurityUtil.sha256Encode("hello world".getBytes()));
	}
	
	@Test
	public void demo4() {
		//≤‚ ‘base64
		SecurityUtil securityUtil = new SecurityUtil();
		String st = securityUtil.base64Encode("hello world".getBytes());
		System.out.println(st);
		byte[] bys = securityUtil.base64Decode(st);
		System.out.println(new String(bys));
	}
}

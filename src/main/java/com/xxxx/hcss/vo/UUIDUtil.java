package com.xxxx.hcss.vo;

import java.util.UUID;

/**
 * 唯一id生成
 */
public class UUIDUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}

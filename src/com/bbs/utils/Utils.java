package com.bbs.utils;

import java.util.UUID;

/**
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月16日下午9:22:35
 */
public final class Utils {
	public static String createUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

}

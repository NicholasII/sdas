// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) braces fieldsfirst ansi nonlb space 
// Source File Name:   Message.java

package com.iscas.sdas.util;

import java.text.MessageFormat;

// Referenced classes of package com.june.common:
//			CustomizedPropertyPlaceholderConfigurer

public class Message {

	public Message() {
	}

	private static String getMessage(String msgId) {
		return (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty(msgId);
	}

	public static String $KEY(String key) {
		return getMessage(key);
	}
	
	public static String format(String patt, Object args[]) {
		return MessageFormat.format(patt, args);
	}
}

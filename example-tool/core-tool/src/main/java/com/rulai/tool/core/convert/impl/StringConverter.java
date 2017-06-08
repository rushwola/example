package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;

/**
 * 字符串转换器
 * @author Looly
 *
 */
public class StringConverter extends AbstractConverter<String>{

	@Override
	protected String convertInternal(Object value) {
		return convertToStr(value);
	}

}

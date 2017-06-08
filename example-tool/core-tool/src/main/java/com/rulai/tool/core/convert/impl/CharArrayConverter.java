package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.convert.ConverterRegistry;
import com.rulai.tool.core.util.ArrayUtil;

/**
 * char类型数组转换器
 * @author Looly
 *
 */
public class CharArrayConverter extends AbstractConverter<char[]>{
	
	@Override
	protected char[] convertInternal(Object value) {
		final Character[] result = ConverterRegistry.getInstance().convert(Character[].class, value);
		return ArrayUtil.unWrap(result);
	}

}

package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.convert.ConverterRegistry;
import com.rulai.tool.core.util.ArrayUtil;

/**
 * short 类型数组转换器
 * @author Looly
 *
 */
public class ShortArrayConverter extends AbstractConverter<short[]>{
	
	@Override
	protected short[] convertInternal(Object value) {
		final Short[] result = ConverterRegistry.getInstance().convert(Short[].class, value);
		return ArrayUtil.unWrap(result);
	}

}

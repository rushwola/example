package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.convert.ConverterRegistry;
import com.rulai.tool.core.util.ArrayUtil;

/**
 * long 类型数组转换器
 * @author Looly
 *
 */
public class LongArrayConverter extends AbstractConverter<long[]>{
	
	@Override
	protected long[] convertInternal(Object value) {
		final Long[] result = ConverterRegistry.getInstance().convert(Long[].class, value);
		return ArrayUtil.unWrap(result);
	}

}

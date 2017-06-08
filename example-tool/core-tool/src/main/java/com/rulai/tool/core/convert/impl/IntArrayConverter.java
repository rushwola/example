package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.convert.ConverterRegistry;
import com.rulai.tool.core.util.ArrayUtil;

/**
 * int 类型数组转换器
 * @author Looly
 *
 */
public class IntArrayConverter extends AbstractConverter<int[]>{
	
	@Override
	protected int[] convertInternal(Object value) {
		final Integer[] result = ConverterRegistry.getInstance().convert(Integer[].class, value);
		return ArrayUtil.unWrap(result);
	}

}

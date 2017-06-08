package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.convert.ConverterRegistry;
import com.rulai.tool.core.util.ArrayUtil;

/**
 * boolean类型数组转换器
 * @author Looly
 *
 */
public class BooleanArrayConverter extends AbstractConverter<boolean[]>{
	
	@Override
	protected boolean[] convertInternal(Object value) {
		final Boolean[] result = ConverterRegistry.getInstance().convert(Boolean[].class, value);
		return ArrayUtil.unWrap(result);
	}

}

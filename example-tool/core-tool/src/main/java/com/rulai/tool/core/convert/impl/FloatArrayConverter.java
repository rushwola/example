package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.convert.ConverterRegistry;
import com.rulai.tool.core.util.ArrayUtil;

/**
 * float 类型数组转换器
 * @author Looly
 *
 */
public class FloatArrayConverter extends AbstractConverter<float[]>{
	
	@Override
	protected float[] convertInternal(Object value) {
		final Float[] result = ConverterRegistry.getInstance().convert(Float[].class, value);
		return ArrayUtil.unWrap(result);
	}

}

package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.convert.ConverterRegistry;
import com.rulai.tool.core.util.ArrayUtil;

/**
 * double 类型数组转换器
 * @author Looly
 *
 */
public class DoubleArrayConverter extends AbstractConverter<double[]>{
	
	@Override
	protected double[] convertInternal(Object value) {
		final Double[] result = ConverterRegistry.getInstance().convert(Double[].class, value);
		return ArrayUtil.unWrap(result);
	}

}

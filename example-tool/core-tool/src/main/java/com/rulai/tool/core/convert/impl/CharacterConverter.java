package com.rulai.tool.core.convert.impl;

import com.rulai.tool.core.convert.AbstractConverter;
import com.rulai.tool.core.util.StrUtil;

/**
 * 字符转换器
 * @author Looly
 *
 */
public class CharacterConverter extends AbstractConverter<Character>{

	@Override
	protected Character convertInternal(Object value) {
		if(char.class == value.getClass()){
			return Character.valueOf((char)value);
		}else{
			final String valueStr = convertToStr(value);
			if (StrUtil.isNotBlank(valueStr)) {
				try {
					return Character.valueOf(valueStr.charAt(0));
				} catch (Exception e) {
					//Ignore Exception
				}
			}
		}
		return null;
	}

}

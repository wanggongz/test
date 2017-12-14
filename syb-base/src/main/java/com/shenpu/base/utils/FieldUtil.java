package com.shenpu.base.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 反射工具类
 * 
 *
 */
public class FieldUtil {

	public static Object map2Entity(Map<String, Object> map, Class<?> clazz)
			throws InstantiationException, IllegalAccessException {
		Object object = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			String fieldType = field.getType().getSimpleName();
			String value = String.valueOf(map.get(fieldName));
			if(!value.equals("null")&&value!=null){
				if ("String".equals(fieldType)) {
					field.set(object, value);
				} else if ("Date".equals(fieldType)) {
					Date temp = DateUtil.parse(value);
					field.set(object, temp);
				} else if ("Integer".equalsIgnoreCase(fieldType) || "int".equals(fieldType)) {
						Integer temp = Integer.parseInt(value);
						field.set(object, temp);
				} else if ("Long".equalsIgnoreCase(fieldType)) {
					Long temp = Long.parseLong(value);
					field.set(object, temp);
				} else if ("Double".equalsIgnoreCase(fieldType)) {
					Double temp = Double.parseDouble(value);
					field.set(object, temp);
				} else if ("Boolean".equalsIgnoreCase(fieldType)) {
					Boolean temp = Boolean.parseBoolean(value);
					field.set(object, temp);
				} else if ("Short".equalsIgnoreCase(fieldType)) {
					Short temp = Short.parseShort(value);
					field.set(object, temp);
				} else if ("Float".equalsIgnoreCase(fieldType)) {
					Float temp = Float.parseFloat(value);
					field.set(object, temp);
				} else if (("Character").equals(fieldType) || "char".equals(fieldType)) {
					field.set(object, value.charAt(0));
				} else {
					System.out.println("not support type : " + fieldType);
				}
			}
		}
		return object;
	}

	public static Map<String, Object> entity2Map(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object fieldValue = field.get(object);
			map.put(fieldName, fieldValue);
		}
		return map;
	}

	/**
	 * 两个属性相同的对象互相填充值，例如：AppntDTO<->Appnt
	 * 
	 * @param object1
	 * @param object2
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object entityFillEntity(Object object, Class<?> toClazz)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Object toObject = toClazz.newInstance();
		Field[] toFields = toClazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				for (Field tofield : toFields) {
					tofield.setAccessible(true);
					String value = String.valueOf(getFieldValueByName(field.getName(), object));
					String fieldType = tofield.getType().getSimpleName();
					if(value.equals("null")){
						value="";
					}
					if (field.getName().equals(tofield.getName())) {
						if ("String".equals(fieldType)) {
							tofield.set(toObject, value);
						} else if ("Date".equals(fieldType)) {
							Date temp = DateUtil.parse(value);
							tofield.set(toObject, temp);
						} else if ("Integer".equalsIgnoreCase(fieldType) || "int".equals(fieldType)) {
							Integer temp = Integer.parseInt(value);
							tofield.set(toObject, temp);
						} else if ("Long".equalsIgnoreCase(fieldType)) {
							Long temp = Long.parseLong(value);
							tofield.set(toObject, temp);
						} else if ("Double".equalsIgnoreCase(fieldType)) {
							Double temp = Double.parseDouble(value);
							tofield.set(toObject, temp);
						} else if ("Boolean".equalsIgnoreCase(fieldType)) {
							Boolean temp = Boolean.parseBoolean(value);
							tofield.set(toObject, temp);
						} else if ("Short".equalsIgnoreCase(fieldType)) {
							Short temp = Short.parseShort(value);
							tofield.set(toObject, temp);
						} else if ("Float".equalsIgnoreCase(fieldType)) {
							Float temp = Float.parseFloat(value);
							tofield.set(toObject, temp);
						} else if (("Character").equals(fieldType) || "char".equals(fieldType)) {
							tofield.set(toObject, value.charAt(0));
						} else {
							System.out.println("not support type : " + fieldType);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toObject;
	}
	
	/***
     * 将一个对象的属性值赋给另一个对象的相同的属性 这两个对象必须都符合javaBean的标准
     * 
     * @param source
     *            要赋值的源对象
     * @param target
     *            被赋值的目标对象
     * @param ignoreProperties
     *            被忽略赋值的属性数组
	 * @return 
     * @throws Exception
     *
     */
    public static Object copyProperties(Object source, Object target,
            String ignoreProperties[]) throws Exception {
        Class<?> targetClass = target.getClass();
        Class<?> sourceClass = source.getClass();
 
        // 得到目标对象和源对象的属性数组
        PropertyDescriptor targetPds[] = Introspector.getBeanInfo(targetClass)
                .getPropertyDescriptors();
        PropertyDescriptor sourcetPds[] = Introspector.getBeanInfo(sourceClass)
                .getPropertyDescriptors();
        // 将忽略字段的数组转化为list
        List<String> ignoreList = ignoreProperties == null ? null : Arrays.asList(ignoreProperties);
        // 把源对象的所有属性放的map中
        Map<String, PropertyDescriptor> sourcePropertyMap = new HashMap<String, PropertyDescriptor>();
        for (int i = 0; i < sourcetPds.length; i++) {
            PropertyDescriptor pd = sourcetPds[i];
            sourcePropertyMap.put(pd.getName(), pd);
        }
        for (int i = 0; i < targetPds.length; i++) {
            PropertyDescriptor targetPd = targetPds[i];
 
            if (targetPd.getWriteMethod() == null || ignoreProperties != null
                    && ignoreList.contains(targetPd.getName()))
                continue;
            PropertyDescriptor sourcePd = sourcePropertyMap.get(targetPd
                    .getName());
            if (sourcePd == null || sourcePd.getReadMethod() == null)
                continue;
            try {
                // 得到源对象对应的属性值
                Method readMethod = sourcePd.getReadMethod();
                if (!Modifier.isPublic(readMethod.getDeclaringClass()
                        .getModifiers()))
                    readMethod.setAccessible(true);
                Object value = readMethod.invoke(source, new Object[0]);
                // 将源对象的属性值赋值给目标对象对应的属性
                Method writeMethod = targetPd.getWriteMethod();
                if (!Modifier.isPublic(writeMethod.getDeclaringClass()
                        .getModifiers()))
                    writeMethod.setAccessible(true);
                writeMethod.invoke(target, new Object[] { value });
            } catch (Throwable ex) {
                throw new IllegalArgumentException(
                        "Could not copy properties from source to target", ex);
            }
        }
        return target;
 
    }

	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static String getRediskey(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI()+"?";
        @SuppressWarnings("unchecked")
		Map<String, Object> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for(String s: keySet){
        	Object object = parameterMap.get(s);
        	requestURI+=s+"="+((Object[])object)[0].toString()+"&";
        }
        return requestURI.substring(0, requestURI.length()-1);
	}
	
}

package study.java.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ClassAnnotation(id=1,msg="我是王锐")
public class TestAnnotation {
	@FieldAnnotation(value = "这是成员变量")
	private Integer myField;

	@MethodAnnotation
	public Integer getMyField() {
		return myField;
	}

	public void setMyField(Integer myField) {
		this.myField = myField;
	}
	
	public static void main(String[] args){
		TestAnnotation test = new TestAnnotation();
		
		if(test.getClass().isAnnotationPresent(ClassAnnotation.class)){
			ClassAnnotation classAnnotation = test.getClass().getAnnotation(ClassAnnotation.class);
			System.out.println(classAnnotation.id());
			System.out.println(classAnnotation.msg());
		}
		
		try {
			Field field = test.getClass().getDeclaredField("myField");
			field.setAccessible(true);
			if(field.isAnnotationPresent(FieldAnnotation.class)){
				FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
				System.out.println(fieldAnnotation.value());
			}
			field.set(test, 100);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Method[] methods = test.getClass().getDeclaredMethods();
			for(Method method : methods){
				method.setAccessible(true);
				if(method.isAnnotationPresent(MethodAnnotation.class)){
					System.out.println(method.invoke(test, null));
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

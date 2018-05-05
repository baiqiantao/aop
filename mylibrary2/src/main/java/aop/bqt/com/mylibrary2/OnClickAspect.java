package aop.bqt.com.mylibrary2;

import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 拦截所有View或其子类的【onClick方法】以及通过ButterKnife的注解添加的点击事件
 */
@Aspect
public class OnClickAspect {
	@Pointcut("execution(* android.view.View.On*Listener.on*Click(..)) ")//定义匹配范围：执行指定方法时拦截
	public void onClick() {//匹配View.OnClickListener中的onClick方法和View.OnLongClickListener中的OnLongClickListener方法
	}
	
	@Pointcut("execution(* *.on*ItemClick(..)) ")//如果有多个匹配范围，可以定义多个，多个规则之间通过 || 或 && 控制
	public void onItemClick() {//匹配任意名字以on开头以ItemClick结尾的方法
	}
	
	@Pointcut("execution(@butterknife.OnClick * *(..))")//匹配通过butterknife的OnClick注解添加的点击事件
	public void butterKnifeOnClick() {
	}
	
	@Around("onClick() || onItemClick() || butterKnifeOnClick()")//@Around 拦截方法，这个注解可以同时拦截方法的执行前后
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = SystemClock.currentThreadTimeMillis();
		printJoinPointInfo(joinPoint);
		
		if (joinPoint.getSignature() instanceof MethodSignature) {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();//要根据Pointcut匹配的类型强转
			printMethodSignatureInfo(signature);
			printArgs(joinPoint);
			printParameterInfo(joinPoint);
		}
		
		Object result = joinPoint.proceed();
		Log.i("bqt", "【@Around】返回值=" + ObjToStringUtils.toString(result)// null
				+ "  方法执行耗时=" + (SystemClock.currentThreadTimeMillis() - beginTime));//2
		return result;
	}
	
	//必须是静态方法
	private static void printJoinPointInfo(ProceedingJoinPoint joinPoint) {
		Log.i("bqt", "【@Around】MethodSignature"
				+ "\nKind=" + joinPoint.getKind()//method-execution
				+ "\nArgs=" + ObjToStringUtils.toString(joinPoint.getArgs())//[android.widget.TextView{d090a1d V.ED..C.. ...P.... 0,0-1440,210}]
				+ "\nSignature=" + ObjToStringUtils.toString(joinPoint.getSignature())//void com.bqt.aop.MainActivity.1.onClick(View)
				+ "\nSourceLocation=" + ObjToStringUtils.toString(joinPoint.getSourceLocation())//MainActivity.java:25
				+ "\nStaticPart=" + ObjToStringUtils.toString(joinPoint.getStaticPart())//execution(void com.bqt.aop.MainActivity.1.onClick(View))
				+ "\nTarget=" + ObjToStringUtils.toString(joinPoint.getTarget())//com.bqt.aop.MainActivity$1@d5c5492
				+ "\nThis=" + ObjToStringUtils.toString(joinPoint.getThis()));//com.bqt.aop.MainActivity$1@d5c5492
	}
	
	private static void printMethodSignatureInfo(MethodSignature signature) {
		//下面通过MethodSignature的方式获取方法的详细信息，也基本都可以通过Method对象获取
		Log.i("bqt", "【@Around】MethodSignature"
				+ "\n方法=" + ObjToStringUtils.toString(signature.getMethod())// public void com.bqt.aop.MainActivity$1.onClick(android.view.View)
				+ "\n方法名=" + signature.getName()// onClick
				+ "\n返回值类型=" + ObjToStringUtils.toString(signature.getReturnType())// void
				+ "\n声明类型=" + ObjToStringUtils.toString(signature.getDeclaringType())// class com.bqt.aop.MainActivity$1
				+ "\n声明类型名=" + signature.getDeclaringTypeName()// com.bqt.aop.MainActivity$1
				+ "\n异常类型=" + ObjToStringUtils.toString(signature.getExceptionTypes())// []
				+ "\n修饰符=" + signature.getModifiers()// 1，对应为 public static final int PUBLIC  = 0x00000001
				+ "\n参数名=" + ObjToStringUtils.toString(signature.getParameterNames())// ["v"]
				+ "\n参数类型=" + ObjToStringUtils.toString(signature.getParameterTypes()));// [class android.view.View]
	}
	
	private static void printArgs(ProceedingJoinPoint joinPoint) {
		String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();//获取参数名列表
		Object[] parameterValues = joinPoint.getArgs();//获取参数值列表
		
		StringBuilder builder = new StringBuilder("");
		for (int i = 0; i < parameterValues.length; i++) {
			builder.append("\n")
					.append(parameterNames[i])
					.append("=")//拼接参数名
					.append(ObjToStringUtils.toString(parameterValues[i]));//拼接参数值
		}
		Log.i("bqt", "【@Around】参数列表" + builder.toString());//v=android.widget.TextView{d090a1d V.ED..C.. ...P.... 0,0-1440,210}
	}
	
	private static void printParameterInfo(ProceedingJoinPoint joinPoint) {
		Object[] parameterValues = joinPoint.getArgs();//获取参数值列表
		for (Object obj : parameterValues) {
			if (obj instanceof TextView) {
				TextView textView = (TextView) obj;
				Log.i("bqt", "【@Around】TextView的信息"
						+ "  文字=" + textView.getText()
						+ "  所属界面=" + textView.getContext().getClass().getSimpleName()
						+ "  ID=" + textView.getId()
						+ "  父页面名称=" + textView.getParent().getClass().getSimpleName()
				);
			}
		}
	}
}
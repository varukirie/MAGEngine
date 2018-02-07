package magengine.groovy;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import groovy.lang.Closure;

@SuppressWarnings("rawtypes")
public class ClosureLambdaConverter {

	public static Runnable $Run(Closure<?> c){
		return c::call;
	}
	
	
	public static Consumer $Con(Closure<?> c){
		return c::call;
	}
	
	public static BiConsumer $BiC(Closure<?> c){
		return c::call;
	}
	
	public static Function $Func(Closure<?> c){
		return c::call;
	}
	
	public static BiFunction $BiFunc(Closure<?> c){
		return c::call;
	}
}

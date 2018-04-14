package magengine.groovy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.groovy.control.CompilationFailedException;

import groovy.lang.GroovyClassLoader;

/**
 * 执行 groovy脚本 
 * 
 * @author Astronic
 *
 */
public class GroovySheetExecutor {

	private Map<String, Class<?>> classCache = new ConcurrentHashMap<>();

	private GroovyClassLoader loader = new GroovyClassLoader();

	private String header = "";
	
	public Class<?> load(File sheetFile)throws IOException{
		String md5 = MD5Util.getFileMD5String(sheetFile);
		return loadWithName(new FileInputStream(sheetFile),md5);
	}
	
	public Class<?> loadCp(InputStream in) throws IOException{
		return loadWithName(in,in.hashCode()+"");
	}
	/**
	 * 执行classpath下的groovysheet
	 * @param in
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void invokeCp(InputStream in) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> cls = loadCp(in);
		Method m = cls.getMethod("main", String[].class);
		Object[] x = new Object[] { new String[0] };
		m.invoke(null, x);
	}
	
	public Class<?> loadWithName(InputStream in,String name)throws IOException{
		Class<?> cls = classCache.get(name);
		if (cls == null) {
			cls = loader.parseClass(appendHeaderInMemStream(this.header, in));
			classCache.put(name, cls);
		}
		return cls;
	}
	/**
	 * 用于装载DSL进入JVM 不会append header
	 * 
	 * @param path
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Class<?> loadDSLInClassPath(String path){
		return loader.parseClass(GroovySheetExecutor.class.getResourceAsStream(path), "CDSL.groovy");
	}
	
	/**
	 * 执行 groovy脚本 
	 * 会以文件的md5码为凭据缓存Class对象
	 * @param sheetFile
	 * @throws IOException
	 * @throws ReflectiveOperationException
	 * @throws CompilationFailedException
	 */
	public void invoke(File sheetFile) throws IOException, ReflectiveOperationException ,CompilationFailedException {
		Class<?> cls = load(sheetFile);
		Method m = cls.getMethod("main", String[].class);
		Object[] x = new Object[] { new String[0] };
		m.invoke(null, x);
	}
	/**
	 * 配合load使用
	 * @param name
	 * @throws ReflectiveOperationException
	 */
	public void invokeWithName(String name) throws  ReflectiveOperationException{
		Class<?> cls = classCache.get(name);
		Method m = cls.getMethod("main", String[].class);
		Object[] x = new Object[] { new String[0] };
		m.invoke(null, x);
	}
	
	
	private String appendHeaderInMemStream(String header,InputStream in) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(header.getBytes());
		byte[] buffer = new byte[1024 * 4];
	    int len = 0;
	    while ((len = in.read(buffer)) != -1) {
	        baos.write(buffer, 0, len);
	    }
	    in.close();
	    return new String(baos.toByteArray());
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
}

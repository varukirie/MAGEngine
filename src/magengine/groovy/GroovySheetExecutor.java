package magengine.groovy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
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
		return loadWithName(sheetFile,md5);
	}
	
	public Class<?> loadWithName(File sheetFile,String name)throws IOException{
		Class<?> cls = classCache.get(name);
		if (cls == null) {
			cls = loader.parseClass(appendHeaderInMemStream(this.header, sheetFile));
			classCache.put(name, cls);
		}
		return cls;
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
	
	
	private String appendHeaderInMemStream(String header,File file) throws IOException{
		InputStream in = new FileInputStream(file);
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

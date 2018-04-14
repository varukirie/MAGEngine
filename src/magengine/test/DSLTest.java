package magengine.test;

import java.io.File;


import magengine.groovy.GroovySheetExecutor;

public class DSLTest {

	public static void main(String[] args) throws Exception {
		GroovySheetExecutor gse =  new GroovySheetExecutor();
		gse.loadDSLInClassPath("/magengine/groovy/CDSL.groovy");
		gse.setHeader("import static magengine.groovy.CDSL.*;"
				+ "import magengine.groovy.CDSL;"
				+ "import static magengine.groovy.ClosureLambdaConverter.*;");

		gse.invoke(new File("d:/T.groovy"));
	}

}

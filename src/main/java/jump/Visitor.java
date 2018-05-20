package jump;
// set you Java file path.

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Visitor {
    public static Map<String, String> methodTable = new HashMap<String, String>();

    public static void main(String[] args) {
        String path = "/Users/zhangxiaodong10/IdeaProjects/indexer/src/main/java/indexer.java";
        File file = new File(path);
        String str = "";
        try {
            str = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            System.out.println(e);
        }

        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);
        String unitName = "indexer.java";
        parser.setUnitName(unitName);
        String[] sources = {"/Users/zhangxiaodong10/IdeaProjects/indexer/src/"};
        String[] classpath = {"/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home/jre/librt.jar"};
        parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
        parser.setSource(str.toCharArray());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        if (cu.getAST().hasBindingsRecovery()) {
            System.out.println("Binding activated.");
        }

        MethodDeclarationVisitor declarationVisitor = new MethodDeclarationVisitor(cu);
        cu.accept(declarationVisitor);


        MethodCallingFinderVisitor definitionVisitor = new MethodCallingFinderVisitor(cu);
        cu.accept(definitionVisitor);
    }
}

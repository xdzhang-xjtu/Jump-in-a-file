import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodDeclarationVisitor extends ASTVisitor {

    CompilationUnit compilationUnit;

    public MethodDeclarationVisitor(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public boolean visit(MethodDeclaration node) {

        SimpleName name = node.getName();
        String line = String.valueOf(compilationUnit.getLineNumber(name.getStartPosition()));
//        System.out.println("Declaration of '" + name + "' at line " + line);
        Visitor.methodTable.put(name.getIdentifier(), line);
        return true;
    }
}

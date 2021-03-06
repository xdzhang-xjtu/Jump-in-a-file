package jump;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.MethodInvocation;

class MethodCallingFinderVisitor extends ASTVisitor {
    CompilationUnit compilationUnit;

    public MethodCallingFinderVisitor(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public boolean visit(MethodInvocation node) {
        String defLine;
        SimpleName name = node.getName();
        if (Visitor.methodTable.containsKey(name.getIdentifier()))
            defLine = "at Line " + Visitor.methodTable.get(name.getIdentifier());
        else
            defLine = "Unknown";
        System.out.println("Invocation of '" + name + "' at line " +
                compilationUnit.getLineNumber(name.getStartPosition())
                + ", its Definition is " + defLine);
        return true;
    }
}
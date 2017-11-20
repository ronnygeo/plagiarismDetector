package cs5500.project.engine;

import cs5500.project.engine.ast.*;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * AST Strategy for method comparison
 */
public class ASTMethodStrategy implements PDStrategy {
	/**
	 * Method to invoke the plagiarism detection inside the given assignment
	 */
	@Override
	public float checkPlagiarism(String testCode1, String testCode2) {
		Parser<CompilationUnit> astParser = new CustomASTParser();
		CompilationUnit cu1 = astParser.parse(testCode1);
		CompilationUnit cu2 = astParser.parse(testCode2);

		ASTVisitor visitor1 = new ASTMethodVisitor();
		ASTVisitor visitor2 = new ASTMethodVisitor();
		cu1.accept(visitor1);
		cu2.accept(visitor2);
		return new ASTMethodCompare().compare(((ASTMethodVisitor) visitor1).getList(), ((ASTMethodVisitor) visitor2).getList());
	}
}

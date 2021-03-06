package com.dupedetective.engine.ast.visitor;

import com.dupedetective.engine.ast.ASTHashObject;
import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An AST Visitor that visits only loops and conditionals and creates a list
 */
public class ASTMethodVisitor extends ASTVisitorAC {

    private boolean typeCheck;

    /**
     * Default constructor
     */
    public ASTMethodVisitor() {
        nodes = new ArrayList<>();
        currentNode = new ASTHashObject();
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Package Declaration
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(PackageDeclaration node) {
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node An Import Declaration
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ImportDeclaration node) {
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A ClassInstanceCreation
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ClassInstanceCreation node) {
        addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Modifier
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(Modifier node) {
       addNodeToNodes(new ASTHashObject(node.getKeyword().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Compilation Unit
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(CompilationUnit node) {
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Method Declaration
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(MethodDeclaration node) {
        resetCurrentNode(node);
        return true;
    }

    /**
     * Post Visit the method declaration, add it to the node list
     *
     * @param node A Method Declaration
     */
    @Override
    public void endVisit(MethodDeclaration node) {
        addCurrentNodeToNodes();
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Block
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(Block node) {
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Return Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ReturnStatement node) {
       addNodeToNodes(new ASTHashObject("return", node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
       return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A For loop Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ForStatement node) {
        return true;
    }

    /**
     * Method called once the visit is complete on the given component
     *
     * @param node A For loop Statement
     */
    @Override
    public void endVisit(ForStatement node) { }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Enhanced For loop Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(EnhancedForStatement node) {
        return true;
    }

    /**
     * Method called once the visit is complete on the given component
     *
     * @param node A Enhanced For loop Statement
     */
    @Override
    public void endVisit(EnhancedForStatement node) { }

    /**
     * Visit the given component using this visitor
     *
     * @param node A While loop Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(WhileStatement node) {
        return true;
    }

    /**
     * Method called once the visit is complete on the given component
     *
     * @param node A While loop Statement
     */
    @Override
    public void endVisit(WhileStatement node) { }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Do While loop Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(DoStatement node) {
        return true;
    }

    /**
     * Method called once the visit is complete on the given component
     *
     * @param node A Do while loop Statement
     */
    @Override
    public void endVisit(DoStatement node) { }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Break Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(BreakStatement node) {
       addNodeToNodes(new ASTHashObject("break", node.getStartPosition(), node.getLength(), node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Continue Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ContinueStatement node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node An If Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(IfStatement node) {
        return true;
    }

    /**
     * Post Visit operations for the given component
     *
     * @param node An If Statement
     */
    @Override
    public void endVisit(IfStatement node) {

    }

    /**
     * Visit the given component using this visitor
     *
     * @param node An Expression Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ExpressionStatement node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Method Invocation
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(MethodInvocation node) {
       addNodeToNodes(new ASTHashObject(node.getName().getFullyQualifiedName(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Super Constructor Invocation
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(SuperConstructorInvocation node) {
       addNodeToNodes(new ASTHashObject("super", node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Constructor Invocation
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ConstructorInvocation node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Switch Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(SwitchStatement node) {
        addNodeToNodes(new ASTHashObject(node.getExpression().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Post Visit the given component using this visitor
     *
     * @param node A Switch Statement
     */
    @Override
    public void endVisit(SwitchStatement node) {

    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Switch Case Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(SwitchCase node) {
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Type Declaration
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(TypeDeclaration node) {
       addNodeToNodes(new ASTHashObject(node.getName().getIdentifier(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Try Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(TryStatement node) {
       addNodeToNodes(new ASTHashObject("try", node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node An Catch Clause
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(CatchClause node) {
       addNodeToNodes(new ASTHashObject(node.getException().getName().getIdentifier(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Throw Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ThrowStatement node) {
       addNodeToNodes(new ASTHashObject(node.getExpression().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     *
     * @param node A SimpleName
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(SimpleName node) {
        if (typeCheck) {
                addNodeToNodes(new ASTHashObject(node.getIdentifier(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
            typeCheck = false;
        }
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A SimpleType
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(SimpleType node) {
        typeCheck = true;
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A SimpleType
     */
    @Override
    public void endVisit(SimpleType node) {
        typeCheck = false;
    }


    /**
     * Visit the given component using this visitor
     *
     * @param node A Variable Declaration Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(VariableDeclarationStatement node) {
       addNodeToNodes(new ASTHashObject(node.getType().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Variable Declaration Fragment
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(VariableDeclarationFragment node) {
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node An Assignment
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(Assignment node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Boolean Literal
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(BooleanLiteral node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A CastExpression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(CastExpression node) {
       addNodeToNodes(new ASTHashObject(node.getType().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Switch Case Statement
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(CharacterLiteral node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A ConditionExpression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ConditionalExpression node) {
        return true;
    }

    /**
     * POST Visit the given component using this visitor
     *
     * @param node A ConditionExpression
     */
    public void endVisit(ConditionalExpression node) { }

    /**
     * Visit the given component using this visitor
     *
     * @param node An Infix Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(InfixExpression node) {
       addNodeToNodes(new ASTHashObject(node.getOperator().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Instanceof Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(InstanceofExpression node) {
       addNodeToNodes(new ASTHashObject("instanceOf", node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A NullLiteral
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(NullLiteral node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A NumberLiteral
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(NumberLiteral node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Parenthesized Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ParenthesizedExpression node) {
       addNodeToNodes(new ASTHashObject("block", node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
       return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Postfix Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(PostfixExpression node) {
       addNodeToNodes(new ASTHashObject(node.getOperator().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
       return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Prefix Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(PrefixExpression node) {
       addNodeToNodes(new ASTHashObject(node.getOperator().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
       return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A String Literal
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(StringLiteral node) {
       addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
       return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A this Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ThisExpression node) {
       addNodeToNodes(new ASTHashObject("this", node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return false;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Variable Declaration Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(VariableDeclarationExpression node) {
        System.out.println(node.toString());
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Variable Declaration Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ArrayInitializer node) {
       addNodeToNodes(new ASTHashObject(node.expressions().toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Field Declaration
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(FieldDeclaration node) {
        addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node A Variable Declaration Expression
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(ArrayCreation node) {
        addNodeToNodes(new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode()));
        return true;
    }

    /**
     * Visit the given component using this visitor
     *
     * @param node An Initializer
     * @return a boolean whether to traverse subtrees or not
     */
    @Override
    public boolean visit(Initializer node) {
        return true;
    }

    /**
     * @param node An ASTNode
     */
    private void addNodeToNodes(ASTHashObject node) {
        currentNode.addNode(node);
    }
    
    /**
     * Reset the nodes in the current list of nodes
     */
    private void resetCurrentNode(ASTNode node) {
        currentNode = new ASTHashObject(node.toString(), node.getNodeType(), node.getStartPosition(), node.getLength(), (long) node.hashCode());
    }

    /**
     * Adds the current nodes list to all node list
     */
    private void addCurrentNodeToNodes() {
        nodes.add(currentNode);
        currentNode = new ASTHashObject();
    }



    /**
     * Returns the list of nodes in the AST
     * @return list of nodes in AST
     */
    public List<ASTHashObject> getList() {
        return nodes;
    }

    private ASTHashObject currentNode;
    private List<ASTHashObject> nodes;
}


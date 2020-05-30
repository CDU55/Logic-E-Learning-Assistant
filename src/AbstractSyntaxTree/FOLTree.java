package AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import FirstOrderLogicSubstitutions.Substitution;
import FormulaRemodelers.PostfixNotationFOL;
import Operators.CorrespondingConnector;
import Operators.TypeTesterFirstOrderLogic;
import Parsers.CheckSyntax;


public class FOLTree {

	private FOLTreeNode root;

	public FOLTree(String formula) {
		List<String> postfix = PostfixNotationFOL.shuntingYard(formula);
		this.buildTree(postfix);
	}
	public FOLTree(FOLTreeNode root)
	{
		this.root=new FOLTreeNode(root);
	}
	
	public FOLTreeNode getRoot() {
		return root;
	}

	public void setRoot(FOLTreeNode root) {
		this.root = root;
	}

	public int getHeight() {
		return calculateHeight(this.root);
	}

	private int calculateHeight(FOLTreeNode currentNode) {
		if (currentNode == null) {
			return -1;
		} else if (currentNode.isConnector()) {
			return 1 + Math.max(calculateHeight(currentNode.getLeftChild()),
					calculateHeight(currentNode.getRightChild()));
		} else if (currentNode.isVariable()) {
			return 0;
		} else {
			if (currentNode.getArguments().size() == 0) {
				return 0;
			} else {
				int maxHeight = 0;
				for (FOLTreeNode arg : currentNode.getArguments()) {
					int currentArgHeight = calculateHeight(arg);
					if (currentArgHeight > maxHeight) {
						maxHeight = currentArgHeight;
					}
				}
				return maxHeight + 1;
			}
		}
	}

	public int getSize() {
		return calculateSize(this.root);
	}

	private int calculateSize(FOLTreeNode currentNode) {
		if (currentNode == null) {
			return 0;
		} else if (currentNode.isConnector()) {
			return 1 + calculateSize(currentNode.getLeftChild()) + calculateSize(currentNode.getRightChild());
		} else if (currentNode.isVariable()) {
			return 1;
		} else {
			int result = 1;
			for (FOLTreeNode arg : currentNode.getArguments()) {
				result += calculateSize(arg);
			}
			return result;
		}
	}

	public void buildTree(List<String> formulaPostfix) {

		TypeTesterFirstOrderLogic operatorType = new TypeTesterFirstOrderLogic();
		Stack<FOLTreeNode> treeStack = new Stack<FOLTreeNode>();
		FOLTreeNode current;
		FOLTreeNode currentLeft;
		FOLTreeNode currentRight;
		for (int i = 0; i < formulaPostfix.size(); i++) {
			if (CheckSyntax.checkFunctionSyntaxBoolean(formulaPostfix.get(i))) {
				current = new FOLTreeNode(formulaPostfix.get(i));
				treeStack.push(current);

			} else if (operatorType
					.isOperatorExcludeNegation(CorrespondingConnector.getRemodeledConnector(formulaPostfix.get(i)))) {
				currentRight = treeStack.pop();
				currentLeft = treeStack.pop();
				current = new FOLTreeNode(formulaPostfix.get(i), currentLeft, currentRight);
				treeStack.push(current);

			} else if (formulaPostfix.get(i).equals("!")
					|| TypeTesterFirstOrderLogic.isCuantifierWithTerm(formulaPostfix.get(i))) {
				currentLeft = treeStack.pop();
				current = new FOLTreeNode(formulaPostfix.get(i), currentLeft, null);
				treeStack.push(current);
			}
		}

		this.root = treeStack.pop();
	}

	public void reaplceImplications(FOLTreeNode currentNode) {
		if (currentNode.getLabel().equals("->")) {
			currentNode.setLabel("\\/");
			FOLTreeNode leftChild = currentNode.getLeftChild();
			currentNode.setLeftChild(new FOLTreeNode("!", leftChild, null));
		} else if (currentNode.getLabel().equals("<-")) {
			currentNode.setLabel("\\/");
			FOLTreeNode rightChild = currentNode.getRightChild();
			currentNode.setRightChild(new FOLTreeNode("!", rightChild, null));
		} else if (currentNode.getLabel().equals("<->")) {
			currentNode.setLabel("/\\");
			FOLTreeNode leftChild = currentNode.getLeftChild();
			FOLTreeNode rightChild = currentNode.getRightChild();
			currentNode.setLeftChild(new FOLTreeNode("->", leftChild, rightChild));
			currentNode.setRightChild(new FOLTreeNode("->", rightChild, leftChild));

		}
		if (currentNode.getLeftChild() != null) {
			reaplceImplications(currentNode.getLeftChild());
		}
		if (currentNode.getRightChild() != null) {
			reaplceImplications(currentNode.getRightChild());
		}
	}

	private  List<String> getBoundVariables(FOLTreeNode t)
	{
		List<String> bound=new ArrayList<String>(); 
		if(t.isConnector() && !TypeTesterFirstOrderLogic.isCuantifierWithTerm(t.getLabel()))
		{
			if(t.getLabel().equals("!"))
			{
				bound=getBoundVariables(t.getLeftChild());
			}
			else
			{
				bound.addAll(getBoundVariables(t.getLeftChild()));
				bound.addAll(getBoundVariables(t.getRightChild()));
			}
		}
		else if(TypeTesterFirstOrderLogic.isCuantifierWithTerm(t.getLabel()))
		{
			bound=getBoundVariables(t.getLeftChild());
			bound.add(t.getLabel().substring(1,t.getLabel().length()-1));
		}
		return new ArrayList<String>(new HashSet<>(bound));

	}
	
	public List<String> getBound()
	{
		return getBoundVariables(this.root);
	}
	
	public static List<String> getFreeVariables(FOLTreeNode t) {
		List<String> free = new ArrayList<String>();
		if(t.isConnector() && !TypeTesterFirstOrderLogic.isCuantifierWithTerm(t.getLabel()))
		{
			if(t.getLabel().equals("!"))
			{
				free=getFreeVariables(t.getLeftChild());
			}
			else
			{
				free.addAll(getFreeVariables(t.getLeftChild()));
				free.addAll(getFreeVariables(t.getRightChild()));
			}
		}
		else if(TypeTesterFirstOrderLogic.isCuantifierWithTerm(t.getLabel()))
			{
				free = getFreeVariables(t.getLeftChild());
				String boundVariable=t.getLabel().substring(1, t.getLabel().length() - 1);
				while(free.contains(boundVariable))
				{
					free.remove(boundVariable);
				}
			}
		 else if (!t.isConnector() && !t.isVariable() && t.getArguments().size()!=0 ) {
			for (FOLTreeNode subTerm : t.getArguments()) {
				if (subTerm.isVariable()) {
					free.add(subTerm.getLabel());
				} else
					free.addAll(getFreeVariables(subTerm));
			}
		}
		
		return new ArrayList<String>(new HashSet<>(free));
	}
	public void getAllVariables(FOLTreeNode currentNode,List<String> variables)
	{
		String label=currentNode.getLabel();
		if( currentNode.isVariable() && !variables.contains(label))
		{
			variables.add(label);
		}
		else if(currentNode.isConnector())
		{
			if(TypeTesterFirstOrderLogic.isCuantifierWithTerm(currentNode.getLabel()))
			{
				variables.add(currentNode.getLabel().substring(1, currentNode.getLabel().length()-1));
			}
			if(currentNode.getLeftChild()!=null)
			{
				getAllVariables(currentNode.getLeftChild(),variables);
			}
			if(currentNode.getRightChild()!=null)
			{
				getAllVariables(currentNode.getRightChild(),variables);
			}
		}
		else if(!currentNode.isConnector() && !currentNode.isVariable())
		{
			for(FOLTreeNode arg:currentNode.getArguments())
			{
				if(arg.isVariable() && !variables.contains(arg.getLabel()))
				{
					variables.add(arg.getLabel());
				}
				else
				{
					getAllVariables(arg,variables);
				}
			}
		}
	}
	public List<String> getVariables()
	{
		List<String> variables=new ArrayList<String>();
		getAllVariables(this.root,variables);
		return variables;
	}
	public List<String> getFree()
	{
		return getFreeVariables(this.root);
	}
	
	private void replaceVariable(FOLTreeNode currentNode,Substitution substitution)
	{
		if(currentNode.isConnector())
		{
			if(currentNode.getLeftChild()!=null)
			{
				replaceVariable(currentNode.getLeftChild(),substitution);
			}
			if(currentNode.getRightChild()!=null)
			{
				replaceVariable(currentNode.getRightChild(),substitution);
			}
		}
		else if(!currentNode.isConnector() && !currentNode.isVariable())
		{
			for(FOLTreeNode arg:currentNode.getArguments())
			{
				replaceVariable(arg,substitution);
			}
		}
		else if(currentNode.isVariable() && currentNode.equals(substitution.Initial))
		{
			currentNode.setLabel(substitution.Final.getLabel());
			List<FOLTreeNode> newArg=new ArrayList<FOLTreeNode>();
			for(FOLTreeNode arg:substitution.Final.getArguments())
			{
				newArg.add(new FOLTreeNode(arg));
			}
			currentNode.setArguments(newArg);
			currentNode.setVariable(substitution.Final.isVariable());
		}
	}
	
	public void executeSubstitution(Substitution substitution)
	{
		replaceVariable(this.root,substitution);
	}
	@Override
	public String toString() {
		return this.root.toString();
	}

}

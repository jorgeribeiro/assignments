package controller;

import java.util.Scanner;

import trees.*;

public class Controller {
	private static AVLTree<String> avlTree;
	private static RedBlackTree<String> rbTree;
	private static BTree<String> bTree;
	private Scanner sc;
	
	/**
	 * Construtor
	 */
	public Controller() {
		sc = new Scanner(System.in);
		startProgram();
	}
	
	/**
	 * Inicia a execução do programa
	 */
	private void startProgram() {
		boolean execute = true;
		
		System.out.println("\t\t === Atividade Pratica II - ED2 === ");
		System.out.println("Aluno: Jorge Ribeiro");
		System.out.println("Comandos:");
		System.out.println("VL - p/ AVL ; BT - p/ B ; RB - p/ Rubro-Negra");
		System.out.println("NEW - nova arvore ; I - inserir elemento ; R - remover elemento");
		System.out.println("PRINT IN, PRE, POS - imprimir arvore ; COPY IN, PRE, POST - copiar elementos");
		System.out.println("SAIR - sair do programa\n");
		
		/**
		 * Executa enquanto o usuário não inserir o comando SAIR
		 */
		while(execute) {
			System.out.print("Comando: ");
			if(validateInput(getInput())) {
				/**
				 * recebe comandos até o usuário inserir SAIR
				 */
			} else execute = false;
		}
	}
	
	/**
	 * Método recebe comando inserido pelo usuário.
	 * @return comando inserido.
	 */
	private String getInput() {
		return sc.nextLine();
	}
	
	/**
	 * Valida comando inserido
	 * @param input
	 * 			comando inserido
	 * @return true se comando válido, false caso contrário
	 */
	private boolean validateInput(String input) {
		String[] tokens = input.split(" ");
		
		switch(tokens[0]) {
			case "VL" : runAVLTree(tokens);
				break;
			case "RB" : runRedBlackTree(tokens);
				break;
			case "BT" : runBTree(tokens);
				break;
			case "PRINT" : printTree(tokens);
				break;
			case "COPY"  : copyTree(tokens);
				break;
			case "P"     : printTree2(tokens);
				break;
			case "SAIR"  : return false;
			default : System.out.println("> Comando inválido.");
				break;
		}
		return true;
	}
	
	/**
	 * Executa operações na árvore AVL
	 * @param tokens
	 * 			comandos inseridos pelo usuário
	 */
	private void runAVLTree(String[] tokens) {
		String op = "";
		String value = "";
		if(tokens.length > 1) op = tokens[1];
		if(tokens.length > 2) value = tokens[2];
		
		switch(op) {
			case "NEW" : avlTree = new AVLTree<String>();
				break;
			case "I"   : if(avlTree != null && !value.isEmpty()) avlTree.add(value);
				break;
			case "R"   : if(avlTree != null) avlTree.remove(value);
				break;
			default    : System.out.println("> Comando inválido.");
				break;
		}		
	}
	
	/**
	 * Executa operações na árvore RB
	 * @param tokens
	 * 			comandos inseridos pelo usuário
	 */
	private void runRedBlackTree(String[] tokens) {
		String op = "";
		String value = "";
		if(tokens.length > 1) op = tokens[1];
		if(tokens.length > 2) value = tokens[2];
		
		switch(op) {
			case "NEW" : rbTree = new RedBlackTree<String>();
				break;
			case "I"   : if(rbTree != null && !value.isEmpty()) rbTree.add(value);
				break;
			case "R"   : if(rbTree != null) rbTree.remove(value);
				break;
			default    : System.out.println("> Comando inválido.");
				break;
		}
	}
	
	/**
	 * Executa operações na árvore B
	 * @param tokens
	 * 			comandos inseridos pelo usuário
	 */
	private void runBTree(String[] tokens) {
		String op = "";
		String value = "";
		int order = 1;
		if(tokens.length > 1) op = tokens[1];
		if(tokens.length > 2) {
			value = tokens[2];
			order = Integer.parseInt(tokens[2]);
		}
		
		switch(op) {
			/**
			 * caso o usuário não defina a ordem, ela será de ordem 1
			 * a ordem nesse algoritmo corresponde ao número mínimo de chaves em um nó,
			 * logo, caso a ordem não seja indicada, a árvore B terá:
			 * chaves min = 1 ; chaves max = 2
			 * filhos min = 2 ; filhos max = 3
			 */
			case "NEW" : bTree = new BTree<String>(order);
				break;
			case "I"   : if(bTree != null && !value.isEmpty()) bTree.add(value);
				break;
			case "R"   : if(bTree != null) bTree.remove(value);
				break;
			default    : System.out.println("> Comando inválido.");
				break;
		}
	}
	
	/**
	 * Imprime a árvore na ordem escolhida pelo usuário
	 * obs: Método não aceito para árvores B pois as mesmas não são binárias
	 * @param <T>
	 * @param tokens
	 * 			comandos inseridos pelo usuário
	 */
	@SuppressWarnings("unchecked")
	private <T> void printTree(String[] tokens) {
		T[] toPrint = null;
		String order = "";
		String treeKind = "";
		if(tokens.length > 1) order = tokens[1];
		if(tokens.length > 2) treeKind = tokens[2];
		
		switch(treeKind) {
			case "VL" : 
				if(order.equals("IN")) toPrint = (T[]) (avlTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.inOrder));
				else if(order.equals("PRE")) toPrint = (T[]) avlTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.preOrder);
				else if(order.equals("POS")) toPrint = (T[]) avlTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.postOrder);
				break;
			case "RB" :
				if(order.equals("IN")) toPrint = (T[]) rbTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.inOrder);
				else if(order.equals("PRE")) toPrint = (T[]) rbTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.preOrder);
				else if(order.equals("POS")) toPrint = (T[]) rbTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.postOrder);
				break;
			default   : System.out.println("> Comando inválido.");
				break;
		}
		
		if(toPrint != null) {
			for(int i = 0; i < toPrint.length; i++)
				System.out.print(toPrint[i] + " ");
			System.out.println();
		}
	}
	
	/**
	 * Copia a árvore na ordem escolhida pelo usuário
	 * e copia na segunda árvore também escolhida
	 * obs: Método não aceito para árvores B pois as mesmas não são binárias
	 * @param <T>
	 * @param tokens
	 * 			comandos inseridos pelo usuário
	 */
	@SuppressWarnings("unchecked")
	private <T> void copyTree(String[] tokens) {
		T[] toCopy = null;
		String order = "";
		String treeKind1 = "", treeKind2 = "";
		if(tokens.length > 1) order = tokens[1];
		if(tokens.length > 2) treeKind1 = tokens[2];
		if(tokens.length > 3) treeKind2 = tokens[3];
		
		switch(treeKind1) {
			case "VL" :
				if(order.equals("IN")) toCopy = (T[]) (avlTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.inOrder));
				else if(order.equals("PRE")) toCopy = (T[]) avlTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.preOrder);
				else if(order.equals("POS")) toCopy = (T[]) avlTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.postOrder);
				else {
					System.out.println("> Comando inválido.");
					break;
				}
				switch(treeKind2) {
					case "RB" :
						rbTree = new RedBlackTree<String>();
						for(int i = 0; i < toCopy.length; i++) rbTree.add((String) toCopy[i]);
						break;
					case "BT" :
						bTree = new BTree<String>(); //árvore sem ordem definida, logo terá ordem 1
						for(int i = 0; i < toCopy.length; i++) bTree.add((String) toCopy[i]);
						break;
					default : System.out.println("> Comando inválido.");
						break;
				}
				break;
			case "RB" :
				if(order.equals("IN")) toCopy = (T[]) (rbTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.inOrder));
				else if(order.equals("PRE")) toCopy = (T[]) rbTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.preOrder);
				else if(order.equals("POS")) toCopy = (T[]) rbTree.getDFS(BinarySearchTree.DepthFirstSearchOrder.postOrder);
				else {
					System.out.println("> Comando inválido.");
					break;
				}
				switch(treeKind2) {
					case "VL" :
						avlTree = new AVLTree<String>();
						for(int i = 0; i < toCopy.length; i++) avlTree.add((String) toCopy[i]);
						break;
					case "BT" :
						bTree = new BTree<String>(); //árvore sem ordem definiStringda, logo terá ordem 1
						for(int i = 0; i < toCopy.length; i++) bTree.add((String) toCopy[i]);
						break;
					default : System.out.println("> Comando inválido.");
						break;
				}
				break;
			default : System.out.println("> Comando inválido.");
				break;
		}
	}
	 /**
	  * Método auxiliar para verificar árvore impressa
	  * apenas utilizado por mim
	  */
	private <T> void printTree2(String[] tokens) {
		String treeKind = "";
		if(tokens.length > 1) treeKind = tokens[1];
		
		switch(treeKind) {
			case "VL" : 
				System.out.println(avlTree);
				break;
			case "RB" :
				System.out.println(rbTree);
				break;
			case "BT" :
				System.out.println(bTree);
				break;
			default   : System.out.println("> Comando inválido.");
				break;
		}
	}
	
}

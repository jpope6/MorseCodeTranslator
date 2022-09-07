package edu.miracosta.cs113;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 * @param <E>
 */
public class MorseCodeTree extends BinaryTree<Character> implements Serializable{
	Scanner in;
	
	/* Constructor */
	public MorseCodeTree() {
		this.root = new Node<Character>(null);
		readMorseCodeTree();
	}

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
    	// Create an array with Strings split at the spaces
       String[] list = morseCode.split(" ");
       // String builder for the final answer
       StringBuilder str = new StringBuilder();
       
       for (String s : list) {
    	   Node<Character> node = this.root;
    	   
    	   // if the length of the string is more than 4, it is invalid
    	   if(s.length() > 4) {
    		   throw new StringIndexOutOfBoundsException();
    	   }
    	   
    	   for (int i = 0; i < s.length(); i++) {
    		   if (s.charAt(i) == '*') {
    			   if (node.left != null) {
    				   node = node.left;
    			   } else {
    				   System.exit(0);
    			   }
    		   } else if (s.charAt(i) == '-') {
    			   if (node.right != null) {
    				   node = node.right;
    			   } else {
    				   System.exit(0);
    			   }
    		   } else {
    			   // if the character is not '*' nor '-'
    			   throw new NumberFormatException();
    		   }
    	   }
    	   // append the data at the current node to the string
    	   str.append(node.data);
       }
       return str.toString();
    }
    
    private void readMorseCodeTree() {
    	try {
    		in = new Scanner(new FileInputStream("/home/jared/git/DS-MorseCodeTree/MorseCodeText/morseCode.txt"));
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		System.exit(1);
    	}
    	
    	while(in.hasNextLine()) {
			String s = in.nextLine();
			Character letter = s.charAt(0);
			String morseCode = s.substring(2, s.length());
			
			this.add(letter, morseCode);
		}
    }
    
    private void add(Character letter, String morseCode) {
    	Node<Character> node = root;
    	
    	for(int i = 0; i < morseCode.length(); i++) {
    		if(morseCode.charAt(i) == '*') {
    			if(node.left != null) {
    				node = node.left;
    			} else {
    				node.left = new Node<Character>(letter);
    				node = node.left;
    			}
    		} else {
    			if(node.right != null) {
    				node = node.right;
    			} else {
    				node.right = new Node<Character>(letter);
    				node = node.right;
    			}
    		}
    	}
    }

} // End of class MorseCodeTree
package edu.miracosta.cs113;

public class Main {

	public static void main(String[] args) {
		MorseCodeTree mct = new MorseCodeTree();
		String answer;
		
		answer = mct.translateFromMorseCode("-** * *** - -*-- -* **");
		System.out.println(answer);
	}

}

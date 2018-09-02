package lessen.windesheim;

public class Array {
	public static void main(String[] args) {
		String[] letter = {"A", "B", "C"};
		int a = 1;
		System.out.println(letter[0]);
		System.out.println("---------------");
		
		for(int i = 0; i < letter.length; i++) {
			System.out.println(i+1 +" "+ letter[i]);
		}
		System.out.println("---------------");
		
		for(String temp: letter) {
			System.out.println(a +" " +temp);
			a++;
		}
	}
}

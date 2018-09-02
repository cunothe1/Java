package lessen.windesheim;

public class Les1 {

	public static void main(String[] args) {
		Student st1 = new Student();
		st1.setNaam("Cai");
		Student st2 = new Student();
		st2.setNaam("Xander");
		System.out.println("student: " + st1.getNaam());
		System.out.println("student: " + st2.getNaam());
	}
}

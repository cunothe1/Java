package lessen.windesheim;

public class Student extends Persoon{	
		private int studentnr;
		
		public Student(String n, int l, int std) {
			this.naam = n;
			this.lengte = l;
			this.studentnr = std;
		}
		public int getStudentnr() {
			return studentnr;
		}
		
		public String toString() {
			return "De student: " + studentnr + " heet " + naam + " en is " + lengte + " cm lang";
		}
	
}

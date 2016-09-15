package committee;

import committee.faculties.BioFaculty;
import committee.faculties.MathFaculty;
import committee.faculties.MixFaculty;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Semaphore fac = new Semaphore(1);
		Semaphore prod = new Semaphore(2);
		Queue q = new Queue(prod);
		StudentProducer p = new StudentProducer(q, prod);
		MixFaculty mx = new MixFaculty(q, fac);
		MathFaculty mt = new MathFaculty(q, fac);
		BioFaculty bi = new BioFaculty(q, fac);
		p.start();
		mx.start();
		mt.start();
		bi.start();
	}

}

package committee;

import committee.Document.Type;

import java.util.concurrent.Semaphore;

public class StudentProducer extends Thread {
	
	public final static int TOTAL_BIO_DOCS = 200;
	public final static int TOTAL_MATH_DOCS = 250;
	public final static int NUMBER_OF_DOCS_TO_ADD = 50;
	
	private Queue queue;
	public int bioDocs = 0;
	public int mathDocs = 0;
	private Semaphore prod;
	
	public StudentProducer(Queue queue, Semaphore prod) {
		this.queue = queue;
		this.prod = prod;
	}
	
	public Document getDoc() {
		if(bioDocs >= TOTAL_BIO_DOCS && mathDocs >= TOTAL_MATH_DOCS)
			return null;
		else if((int)(Math.random()* Type.values().length) > 0 && mathDocs < TOTAL_MATH_DOCS) {
			System.out.println(">>> pushed math " + ++mathDocs);
			return new Document(Type.MATHEMATICIAN, mathDocs);
		} else if(bioDocs < TOTAL_BIO_DOCS) {
			System.out.println(">>> pushed bio " + ++bioDocs);
			return new Document(Type.BIOLOGIST, bioDocs);
		} else {
			System.out.println(">>> pushed math " + ++mathDocs);
			return new Document(Type.MATHEMATICIAN, mathDocs);
		}
	}
	
	@Override
	public void run() {
		Document d;
		while(!isInterrupted()) {
			try {
				prod.acquire();
				int count = (int)(Math.random()*NUMBER_OF_DOCS_TO_ADD + 1);
				System.out.println("+++ will push " + count + " docs");
				for(int i = 0; i < count; i++) {
					if((d = getDoc()) == null)						
						interrupt();
					else queue.push(d);
				}
				if(!isInterrupted()) prod.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
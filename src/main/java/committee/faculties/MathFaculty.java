package committee.faculties;

import committee.Document;
import committee.Document.Type;
import committee.Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MathFaculty extends Thread {

	private Semaphore semaphore;
	private Queue queue;
	private List<Document> docs = new LinkedList<>();
	
	public MathFaculty(Queue queue, Semaphore s) {
		this.queue = queue;
		this.semaphore = s;
	}
	
	public int size() {
		return docs.size();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(300);
			while(!isInterrupted()) {
				semaphore.acquire();
				Document doc;
				System.out.println("...math started...");
				while(true) {
					if((doc = queue.pop()) == null) {
						semaphore.release();
						interrupt();
						break;
					} else if (doc.getType() == Type.BIOLOGIST) 
						break;
					docs.add(doc);
					System.out.println("<-< math took doc " + doc.getType() + " " + doc.getNum());
				}
				if(!isInterrupted()) {
					System.out.println(">-> math returned doc " + doc.getType() + " " + doc.getNum());
					queue.push(doc);
					semaphore.release();
					Thread.sleep(300);
				}
			}
			System.out.println("Math finished and has " + docs.size() + " docs");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

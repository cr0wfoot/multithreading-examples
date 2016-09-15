package committee.faculties;

import committee.Document;
import committee.Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MixFaculty extends Thread {

	private Semaphore semaphore;
	private Queue queue;
	private List<Document> docs = new LinkedList<>();
	
	public MixFaculty(Queue queue, Semaphore s) {
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
				System.out.println("...mix started...");
				Document doc;
				int count = (int)(Math.random()*5 + 1);
				for(int i = 0; i < count; i++) {
					if((doc = queue.pop()) == null) {
						semaphore.release();
						interrupt();
						break;
					}
					docs.add(doc);
					System.out.println("<-< mix took doc " + doc.getType() + " " + doc.getNum());
				}
				if(!isInterrupted()) {
					semaphore.release();
					Thread.sleep(300);
				}
			}
			System.out.println("Mix finished and has " + docs.size() + " docs");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

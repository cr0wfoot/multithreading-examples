package patterns.barber;

public class BarberShop {

	private volatile int clients = 0;
	private volatile boolean barber = false;
	private volatile boolean service = false;
	
	public synchronized void signalClient() {
		System.out.println(Thread.currentThread().getName() + " is here");
		clients++;
		notifyAll();
	}
	
	public synchronized void signalService() {
		System.out.println("+++Barber finished!!!");
		service = true;
		notifyAll();
	}
	
	public synchronized boolean signalBarber() {
		System.out.println(">>>Barber is ready");
		barber = true;
		if(clients == 0) {
			return false;
		} else {
			notifyAll();
			return true;
		}
	}
	
	public synchronized void waitBarber() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " wait barber zzzzzzzzzzzzzzzzzzzzzzzzzz");
		while(!barber) 
			wait();
		barber = false;
		clients--;
	}
	
	public synchronized void waitClient() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " wait client");
		while(clients == 0) 
			wait();
	}
	
	public synchronized void waitService() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " service ...........................");
		while(!service) 
			wait();
	}
	
}

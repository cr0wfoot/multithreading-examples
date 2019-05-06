package patterns.barber;

public class Client extends Thread {

	private BarberShop shop;
	
	public Client(BarberShop shop, String name) {
		super(name);
		this.shop = shop;
	}

	@Override
	public void run() {
		try {
			shop.signalClient();
			shop.waitBarber();
			shop.waitService();
			Thread.sleep(100);
			System.out.println(Thread.currentThread().getName() + " finished!!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

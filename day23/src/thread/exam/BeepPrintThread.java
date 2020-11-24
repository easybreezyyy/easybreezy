package thread.exam;

public class BeepPrintThread {

	public static void main(String[] args) {
		Runnable beepTask = new BeepTask();
		Thread thread = new Thread(beepTask);
		thread.start();
		
		for(int i = 0; i < 5; i++) {
			System.out.println("beep-!");
			try { Thread.sleep(3000);} catch(Exception e) {}
		}
	}

}

package homework9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SimpleTransmissionProtocol {
	int size;

	List<String> senderBuffer;
	List<String> receiverBuffer;

	String channel;

	ReentrantLock lock = new ReentrantLock();

	public SimpleTransmissionProtocol(int size) {
		this.size = size;

		this.senderBuffer = new ArrayList<String>(size);
		this.receiverBuffer = new ArrayList<String>(size);

		for (int i = 0; i < size; ++i)
			this.senderBuffer.add("Data " + i);

		this.channel = "";
	}

	public void simulate() throws InterruptedException {
		Thread sender = new Thread() {
			public void run() {
				while (!senderBuffer.isEmpty()) {
					lock.lock();
					channel = senderBuffer.remove(0);
					lock.unlock();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.lock();
				channel = null;
				lock.unlock();

			}
		};

		Thread receiver = new Thread() {
			public void run() {
				String previous = "";
				lock.lock();
				String tempChannel = channel; 
				lock.unlock();
				while (tempChannel != null) {
					if (!tempChannel.equals(previous)) {
						receiverBuffer.add(tempChannel);
						previous = tempChannel;
					}
					lock.lock();
					tempChannel = channel;
					lock.unlock();
				}

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};

		receiver.start();
		sender.start();

		sender.join();
		receiver.join();
	}

	public static void main(String[] args) throws InterruptedException {
		SimpleTransmissionProtocol protocol = new SimpleTransmissionProtocol(5);
		System.out.println("Before Transmission");
		System.out.println("-------------------------------------");
		System.out.println("Sender Buffer 1: " + protocol.senderBuffer);
		System.out.println("Receiver Buffer 1: " + protocol.receiverBuffer);

		protocol.simulate();

		System.out.println("\nAfter Transmission");
		System.out.println("-------------------------------------");
		System.out.println("Sender Buffer 2: " + protocol.senderBuffer);
		System.out.println("Receiver Buffer 2: " + protocol.receiverBuffer);
	}
}

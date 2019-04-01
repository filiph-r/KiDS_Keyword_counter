package threadSafeObj;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeList<E> {
	private final ArrayList<E> lista = new ArrayList<E>();;
	private final Lock lock = new ReentrantLock();

	public void add(E value) {
		lock.lock();

		try {
			if (!lista.contains(value)) {
				System.out.println("Adding dir " + value);
				lista.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void remove(int x) {
		lock.lock();

		try {
			lista.remove(x);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public E get(int x) {
		lock.lock();
		E retVal = null;

		try {

			retVal = lista.get(x);

		} catch (Exception e) {
			System.err.println("ThreadSafeList: Nemoguce je pristupiti objektu pod indexom " + x);
		} finally {
			lock.unlock();
		}

		return retVal;
	}

	public int size() {
		lock.lock();
		int size = 0;

		try {
			size = lista.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return size;
	}

}

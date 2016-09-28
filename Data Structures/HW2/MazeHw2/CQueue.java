// Simple Queue using a Circular Linked List type Data Structure
// rear.next always point to front of queue 
public class CQueue {
	public CQueue() {
		rear = null;
	}

	public boolean isEmpty() {
		return (rear == null);
	}

	public void enQueue(Object i) {
		if (isEmpty()) {
			CNode tmp = new CNode(i, null);
			rear = tmp;
			tmp.next = rear;
		} else {
			CNode front = rear.next;
			rear.next = new CNode(i, front);
			rear = rear.next;
		}
	}

	public Object deQueue() {
		if (isEmpty()) {
			System.out.println("error: dequeue from empty queue!");
			return (null);
		} else {
			CNode front = rear.next;
			Object x = front.data;
			if (rear == rear.next)
				rear = null; // deQueue single element
			else
				rear.next = front.next;
			return x;
		}
	}

	public void printQueue() {
		System.out.print("the Queue: ");
		if (!isEmpty()) {
			CNode p = rear.next;
			while (p != rear) {
				System.out.print(" " + p.data);
				p = p.next;
			}
			System.out.print(" " + p.data);
			System.out.println();
		} else
			System.out.println("Queue empty!");
	}

	public CNode rear;

}

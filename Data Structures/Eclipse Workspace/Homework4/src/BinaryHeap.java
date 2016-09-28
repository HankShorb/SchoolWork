public class BinaryHeap {

	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize; // Number of elements in heap
	private HuffNode[] array; // The heap array

	public BinaryHeap() {
		array = new HuffNode[DEFAULT_CAPACITY + 1];
		currentSize = 0;
	}

	public BinaryHeap(int size) {
		array = new HuffNode[size + 1];
		currentSize = 0;
	}

	public BinaryHeap(HuffNode[] items) {
		currentSize = items.length;
		array = new HuffNode[(currentSize + 2) * 11/10 ];

		int i=1;
		for(HuffNode item : items)
			array[ i++ ] = item;
		buildHeap();
	}

	public void insert(HuffNode x) {
		if (currentSize == array.length - 1) {
			enlargeArray(array.length * 2 + 1);
		}

		// Percolate up
		int hole = ++currentSize;
		for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
			array[hole] = array[hole / 2];
		}
		array[hole] = x;
	}

	public HuffNode findMin() {
		if (isEmpty())
			return null;
		else
			return array[1];
	}

	public HuffNode deleteMin() {
		if (isEmpty())
			return null;
		else {
			HuffNode min = findMin();
			array[1] = array[currentSize--];
			percolateDown(1);
			return min;
		}
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	private void percolateDown(int hole) {
		int child;
		HuffNode temp = array[hole];

		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			
			if (child != currentSize
					&& array[child + 1].compareTo(array[child]) < 0) {
				child++;
			}

			if (array[child].compareTo(temp) < 0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = temp;
	}

	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--){
			percolateDown(i);
		}
	}

	private void enlargeArray(int newSize) {
		HuffNode[] temp = new HuffNode[array.length+10];
		
		for(int i = 0; i<=currentSize; i++){
			temp[i] = array[i];
		}
		array = temp;
	}
	
	public int currentSize(){
		return currentSize;
	}
}
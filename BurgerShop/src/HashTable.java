import java.util.ArrayList;

public class HashTable {
	public static class HashNode<String, Drink>{
		String key;
		Drink order;
		HashNode<String, Drink> next;
		public HashNode(String k, Drink o) {
			key = k;
			order = o;
		}
	}
	
	protected ArrayList<HashNode<String, Drink>> bucketArray;
	protected int numBuckets;
	private int size;
	public HashTable() {
		bucketArray = new ArrayList<>();
		numBuckets = 10;
		size = 0;
		for(int i=0;i<numBuckets;i++) {
			bucketArray.add(null);
		}
	}
	
	private int getBucketIndex(String k) {
		int hashCode = k.hashCode();
		int index = hashCode % numBuckets;
		return index;
	}
	
	private void generateBiggerArray() {
		ArrayList<HashNode<String, Drink>> tempNode = bucketArray;
		bucketArray = new ArrayList<>();
		numBuckets = 2*numBuckets;
		for(int i=0;i<numBuckets;i++) {
			bucketArray.add(null);
		}
		size = 0;
		for(HashNode<String, Drink> headNode:tempNode) {
			while(headNode != null) {
				add(headNode.key, headNode.order);
				//size++;
				headNode = headNode.next;
			}
		}
	}
	
	public void add(String k, Drink o) {
		int bucketIndex = getBucketIndex(k);
		HashNode<String, Drink> head = bucketArray.get(bucketIndex);
		while(head!=null) {
			if(head.key.equals(k)) {
				head.order = o;
			}
			head = head.next;
		}
		head = bucketArray.get(bucketIndex);
		HashNode<String, Drink> newNode = new HashNode<String, Drink>(k, o);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);
		size++;
		if((1.0*size)/numBuckets>=0.9) {
			generateBiggerArray();
		}
	}
	
	public Drink search(String k) {
		int bucketIndex = getBucketIndex(k);
		HashNode<String, Drink> head = bucketArray.get(bucketIndex);
		while(head != null) {
			if(head.key.equals(k)) {return head.order;}
			head = head.next;
		}
		return null;
	}
	
	public Drink remove(String k) {
		int bucketIndex = getBucketIndex(k);
		HashNode<String, Drink> head = bucketArray.get(bucketIndex);
		HashNode<String, Drink> pre = null;
		while (head != null) {
			if(head.key.equals(k)) {
				break;
			}
			pre = head;
			head = head.next;
		}
		if(head == null) {
			return null;
		}
		if(pre != null) {
			pre.next = head.next;
		}
		else {
			bucketArray.set(bucketIndex, head.next);
		}
		size--;
		return head.order;
	}
	
	public int getsize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
}

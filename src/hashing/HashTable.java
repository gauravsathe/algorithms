package hashing;

public class HashTable {
	private final static int TABLE_SIZE = 1000;
	private HashEntry[] table;
	
	public HashTable() {
		table = new HashEntry[TABLE_SIZE];
		
		for(int i=0; i<TABLE_SIZE; i++) {
			table[i] = null;
		}
	}
	
	public void put(int key, int value) {
		int hash = key % TABLE_SIZE;
		if(table[hash] == null) {
			table[hash] = new HashEntry(key, value);
		}
		else {
			HashEntry head = table[hash];
			while( head != null && head.getKey() != key ) {
				head = head.getNext();
			}
			
			if(head == null) {
				table[hash].setPrev(new HashEntry(key, value, table[hash], null));
				table[hash] = table[hash].getPrev();
			}
			else {
				head.setValue(value);
			}
		}
	}
	
	public int get(int key) {
		int hash = key % TABLE_SIZE;
		if(table[hash] == null) {
			return -1;
		}
		else {
			HashEntry head = table[hash];
			while(head != null && head.getKey() != key) {
				head = head.getNext();
			}
			
			if(head == null) {
				return -1;
			}
			else return head.getValue();
		}
	}
	
	public void remove(int key) {
		int hash = key % TABLE_SIZE;
		if(table[hash] == null) {
			return;
		}
		else {
			HashEntry head = table[hash];
			if(head.getKey() == key) {
				table[hash] = head.getNext();
				table[hash].setPrev(null);
			}
			else {
				while(head != null && head.getKey() != key) {
					head = head.getNext();
				}
				if(head == null) {
					return;
				}
				else {
					head.getPrev().setNext(head.getNext());
					head.getNext().setPrev(head.getPrev());
				}
			}
			
		}
	}
}

class HashEntry {
	
	private int key;
	private int value;
	private HashEntry next;
	private HashEntry prev;
	
	public HashEntry(int key, int value) {
		this.setKey(key);
		this.setValue(value);
		this.setNext(null);
		this.setPrev(null);
	}
	
	public HashEntry(int key, int value, HashEntry next, HashEntry prev) {
		this.setKey(key);
		this.setValue(value);
		this.setNext(next);
		this.setPrev(prev);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public HashEntry getNext() {
		return next;
	}

	public void setNext(HashEntry next) {
		this.next = next;
	}
	
	public HashEntry getPrev() {
		return prev;
	}

	public void setPrev(HashEntry prev) {
		this.prev = prev;
	}
}

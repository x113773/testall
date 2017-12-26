package com.ansel.testall.dataStructure;

class Node {
	int data;
	Node next;
}

public class LinkList {
	public static Node topInsert(Node head, int[] a) {
		head = new Node();
		head.next = null;
		for (int i = 0; i < a.length; i++) {
			Node t = new Node();
			t.data = a[i];
			t.next = head.next;
			head.next = t;
		}
		return head;
	}

	public static Node tailInsert(Node head, int[] a) {
		head = new Node();
		Node p = head;
		for (int i = 0; i < a.length; i++) {
			Node t = new Node();
			t.data = a[i];
			p.next = t;
			p = p.next;
		}
		p.next = null;
		return head;
	}

	public static void display(Node head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	public static void main(String args[]) {
		Node head = null;
		int[] a = { 1, 2, 3 };
		head = topInsert(head, a);
		display(head);
		System.out.println(" ");
		head = tailInsert(head, a);
		display(head);
	}
}
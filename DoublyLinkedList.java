/**
 * Doubly linked list implementation
 * @author Kyle Rabago-Banjo
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    private Node<T> head;
    private Node<T> tail;
    private int sze = 0;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(data);
        } else if (index > sze) {
            throw new IndexOutOfBoundsException();
        } else if (index == sze) {
            addToBack(data);
        } else {
            Node<T> temp = head;
            Node<T> temp2 = new Node<T>(data);
            if (index == 1) {
                temp2.setNext(temp.getNext());
                temp.setNext(temp2);
                temp2.setPrevious(temp);
                temp2.getNext().setPrevious(temp2);
            } else {
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.getNext();
                }
                temp2.setNext(temp.getNext());
                temp.setNext(temp2);
                temp2.setPrevious(temp);
                temp2.getNext().setPrevious(temp2);
            }
            sze++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index > sze - 1) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return head.getData();
        } else if (index == sze - 1) {
            return tail.getData();
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            return temp.getNext().getData();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index > sze - 1) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == (sze - 1)) {
            return removeFromBack();
        } else {
            Node<T> temp = head;
            if (index == 1) {
                temp = temp.getNext();
            } else {
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.getNext();
                }
            }
            T ret = temp.getData();
            temp.getPrevious().setNext(temp.getNext());
            temp.getNext().setPrevious(temp.getPrevious());
            sze--;
            return ret;
        }
    }

    @Override
    public void addToFront(T t) {
        if (sze == 0) {
            head = new Node<T>(t);
            tail = head;
            sze++;
        } else if (sze == 1) {
            head = new Node<T>(t);
            head.setNext(tail);
            tail.setPrevious(head);
            sze++;
        } else {
            Node<T> temp = head;
            head = new Node<T>(t);
            head.setNext(temp);
            temp.setPrevious(head);
            sze++;
        }
    }

    @Override
    public void addToBack(T t) {
        if (sze == 0) {
            head = new Node<T>(t);
            tail = head;
            sze++;
        } else if (sze == 1) {
            tail = new Node<T>(t);
            tail.setPrevious(head);
            head.setNext(tail);
            sze++;
        } else {
            Node<T> temp = tail;
            tail = new Node<T>(t);
            tail.setPrevious(temp);
            temp.setNext(tail);
            sze++;
        }
    }

    @Override
    public T removeFromFront() {
        if (sze == 0) {
            return null;
        } else if (sze == 1) {
            T ret = head.getData();
            head = null;
            tail = null;
            sze--;
            return ret;
        } else {
            T ret = head.getData();
            head = head.getNext();
            head.setPrevious(null);
            sze--;
            return ret;
        }
    }

    @Override
    public T removeFromBack() {
        if (sze == 0) {
            return null;
        } else if (sze == 1) {
            T ret = tail.getData();
            head = null;
            tail = null;
            sze--;
            return ret;
        } else if (sze == 2) {
            T ret = tail.getData();
            tail = head;
            head.setNext(null);
            tail.setPrevious(null);
            sze--;
            return ret;
        } else {
            T ret = tail.getData();
            tail = tail.getPrevious();
            tail.setNext(null);
            sze--;
            return ret;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] ret = new Object[sze];
        Node<T> n = head;
        for (int i = 0; i < sze; i++) {
            ret[i] = n.getData();
            n = n.getNext();
        }
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return sze == 0;
    }

    @Override
    public int size() {
        return sze;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        sze = 0;
    }


    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * You will get a 0 if you do not implement this method.
     *
     * @return Node representing the head of the linked list
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * You will get a 0 if you do not implement this method.
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail() {
        return tail;
    }
}

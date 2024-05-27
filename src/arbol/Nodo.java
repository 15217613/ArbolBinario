package arbol;

public class Nodo<T extends Comparable<T>> {
    private T element;
    private Nodo<T> parent;
    private Nodo<T> left;
    private Nodo<T> right;

    public Nodo(T element) {
        this.element = element;
    }

    public Nodo(T element, Nodo<T> parent, Nodo<T> left, Nodo<T> right) {
        this.element = element;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Nodo<T> getParent() {
        return parent;
    }

    public void setParent(Nodo<T> parent) {
        this.parent = parent;
    }

    public Nodo<T> getLeft() {
        return left;
    }

    public void setLeft(Nodo<T> left) {
        this.left = left;
    }

    public Nodo<T> getRight() {
        return right;
    }

    public void setRight(Nodo<T> right) {
        this.right = right;
    }
}

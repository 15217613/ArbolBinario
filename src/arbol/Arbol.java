package arbol;

public class Arbol<T extends Comparable<T>> {
    private final int NODE_LEFT = 1;
    private final int NODE_RIGHT = 2;
    private final int NODES = 3;
    private Nodo<T> root;

    public Nodo<T> getRoot() {
        return root;
    }
    
    // Si la raiz es null entonces el arbol esta vacio
    public boolean isEmpty() {
        return root == null;
    }
    
    // Si el nodo es igual a la raiz entonces es la raiz
    public boolean isRoot(Nodo<T> nodo) {
        return root == nodo;
    }
    
    // Si el nodo del lado izquierdo y del lado derecho no apuntan a otro nodo entonces son hojas
    public boolean isLeaf(Nodo<T> nodo) {
        return nodo.getLeft() == null && nodo.getRight() == null;
    }
    
    // Si el nodo no es una hoja entonces es interno
    public boolean isInternal(Nodo<T> nodo) {
        return !isLeaf(nodo);
    }
    
    // Niveles
    public int height(Nodo<T> nodo) {
        int height = 0;
        
        // Si el nodo tiene mas nodos
        if (isInternal(nodo)) {
            if (nodo.getLeft() != null) {
                height = Math.max(height, height(nodo.getLeft()));
            }
            
            if (nodo.getRight()!= null) {
                height = Math.max(height, height(nodo.getRight()));
            }
            height++;
        }
        return height;
    }
    
    // Profundidad
    public int depth(Nodo<T> nodo) {
        int depth = 0;
        
        if (nodo != getRoot()) {
            depth = 1 + depth(nodo.getParent());
        }
        
        return depth;
    }
    
    // Añadir de manera recursiva
    public Nodo<T> add(Nodo<T> origin, T element) {
        Nodo<T> nodo = null;
        
        if (root == null) { // Si no existe el root
            root = new Nodo<>(element);
        } else if(origin == null) { // Si el elemento es nulo
            System.out.println("Mi origen es nulo");
        } else {
            if (origin.getElement().compareTo(element) > 0) { // Si la raiz es mayor al elemento que mandamos
                if (origin.getLeft() != null) { // Si existe la rama izquierda
                    nodo = add(origin.getLeft(), element);
                } else { // Si no existe la rama izquierda
                    // Enlace de nodos en caso de que no exista el padre
                    nodo = new Nodo<>(element);
                    nodo.setParent(origin); // Establecemos el padre
                    origin.setLeft(nodo); // Enlazamos el padre con el hijo
                }
            } else { // Si la raiz es menor al elemento que mandamos
                if (origin.getRight()!= null) { // Si existe la rama derecha
                    nodo = add(origin.getRight(), element);
                } else { // Si no existe la rama izquierda
                    // Enlace de nodos en caso de que no exista el padre
                    nodo = new Nodo<>(element);
                    nodo.setParent(origin); // Establecemos el padre
                    origin.setRight(nodo); // Enlazamos el padre con el hijo
                }
            }
        }
        return nodo;
    }
    
    // Añadir de manera iterativa
    public Nodo<T> add(T element) {
        Nodo<T> nodo = null;
        
        if (root == null) { // Si no existe el root
            root = new Nodo<>(element);
        } else {
            boolean insert = false;
            Nodo<T> aux = root;
            
            while(!insert) {                
                if (aux.getElement().compareTo(element) > 0) { // Si la raiz es mayor al elemento que mandamos
                    if (aux.getLeft() != null) { // Si existe la rama izquierda
                        aux = aux.getLeft();
                    } else { // Si no existe la rama izquierda
                        // Enlace de nodos en caso de que no exista el padre
                        nodo = new Nodo<>(element);
                        nodo.setParent(aux); // Establecemos el padre
                        aux.setLeft(nodo); // Enlazamos el padre con el hijo
                        insert = true;
                    }
                } else {
                    if (aux.getRight()!= null) { // Si existe la rama derecha
                        aux = aux.getRight();
                    } else { // Si no existe la rama derecha
                        // Enlace de nodos en caso de que no exista el padre
                        nodo = new Nodo<>(element);
                        nodo.setParent(aux); // Establecemos el padre
                        aux.setRight(nodo); // Enlazamos el padre con el hijo
                        insert = true;
                    }
                }
            }
        }
        return nodo;
    }
    
    // Eliminar una hoja
    private void removeLeaf(Nodo<T> nodo) {
        // Si es el root
        if (isRoot(nodo)) {
            root = null;
        } else { // Si tiene hijos
            Nodo<T> parent = nodo.getParent();
            
            // Si es la hoja izquierda
            if (parent.getLeft() == nodo) {
                parent.setLeft(null);
            }
            
            // Si es la hoja derecha
            if (parent.getRight()== nodo) {
                parent.setRight(null);
            }
            
            // Cortamos
            nodo = null;
        }
    }
    
    // Minimo valor del subarbol
    // Tomamos el nodo que este mas a la izquierda a partir del nodo que se va a remover
    private Nodo<T> minSubTree(Nodo<T> nodo) {
        // Si hay nodos
        if (nodo != null && nodo.getLeft() != null) {
            while(!isLeaf(nodo)) {
                nodo = minSubTree(nodo.getLeft());
            }
        }
        return nodo;
    }
    
    // Remover 
    private void removeWithChild(Nodo<T> nodo, int type) {
        Nodo<T> next = null; // Siguiente nodo de la siguiente rama
        
        switch(type) {
            case NODE_LEFT: // Cuando tiene solo un nodo izquierdo
                next = nodo.getLeft();
                break;
            case NODE_RIGHT: // Cuando tiene solo un nodo derecho
                next = minSubTree(nodo.getRight());
                break;
            case NODES: // Cuando tiene 2 nodos
                next = minSubTree(nodo.getRight());
                
                // Si el padre es diferente del root
                if (root != next.getElement()) { // root != next.getElement()
                    // Enlazamos con el padre
                    nodo.getLeft().setParent(next);
                    nodo.getRight().setParent(next);
                    
                    // Quitamos las referencias de nuestro siguiente nodo con lo que tenia
                    if (next.getParent().getLeft() == next) {
                        next.getParent().setLeft(null);
                    } else if (next.getParent().getRight()== next) {
                        next.getParent().setRight(null);
                    }
                }
                break;
        }
        next.setParent(nodo.getParent()); // El nodo siguiente cambiamos el padre
        
        if (!isRoot(nodo)) {
            // Referenciamos al padre con el hijo (el padre reconoce al hijo)
            if (nodo.getParent().getLeft() == nodo) {
                nodo.getParent().setLeft(next);
            } else if (nodo.getParent().getRight()== nodo) {
                nodo.getParent().setRight(next);
            }
        } else {
            root = next;
        }
        
        // Si el nodo tiene hijos hacemos que lo adopte el otro nodo
        if (nodo.getRight() != null && nodo.getRight() != next) {
            next.setRight(nodo.getRight());
        }
        
        // Si el nodo tiene hijos hacemos que lo adopte el otro nodo
        if (nodo.getLeft()!= null && nodo.getLeft()!= next) {
            next.setLeft(nodo.getLeft());
        }
        nodo = null;
    }
    
    public void remove(Nodo<T> nodo) {
        if (isEmpty()) { // Si no hay nodos
            System.out.println("No hay nodos");
        } else if(isLeaf(nodo)) { // Si es una hoja
            removeLeaf(nodo);
        } else if(nodo.getRight() != null && nodo.getLeft() == null) { // Si se tienen nodos del lado derecho
            removeWithChild(nodo, NODE_RIGHT);
        } else if(nodo.getRight() == null && nodo.getLeft() != null) { // Si se tienen nodos del lado izquierdo
            removeWithChild(nodo, NODE_LEFT);
        } else { // Si se tienen nodos en ambos lados
            removeWithChild(nodo, NODES);
        }
    }
    
    // Obtener el nodo de un elemento (Solo el primer elemento que encuentre)
    public Nodo<T> getNodo(Nodo<T> nodo, T element) {
        Nodo<T> aux = null;
        
        // Si el nodo es igual al elemento
        if (nodo.getElement().compareTo(element) == 0) {
            aux = nodo;
        } else {
            // Recorremos el arbol
            if (nodo.getLeft() != null) {
                aux = getNodo(nodo.getLeft(), element);
            }

            if (nodo.getRight()!= null) {
                aux = getNodo(nodo.getRight(), element);
            }
        }
        return aux;
    }
    
    // Obtener el elemento de un nodo
    public T getElement(Nodo<T> nodo, T element) {
        Nodo<T> aux = getNodo(nodo, element);
        
        // Si el elemento no existe
        if (aux == null) {
            return null;
        }
        
        return aux.getElement();
    }
    
    // Mostrar estructura jerarquica
    public void show(Nodo<T> nodo) {
        int depth = this.depth(nodo);
        
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        
        System.out.println("- " + nodo.getElement().toString());
        
        if (nodo.getLeft() != null) {
            show(nodo.getLeft());
        }
        
        if (nodo.getRight() != null) {
            show(nodo.getRight());
        }
    }
    
    // Recorridos
    // Recursivo
    public void preorder(Nodo<T> nodo) {
        System.out.println(nodo.getElement().toString());
        
        if (nodo.getLeft() != null) {
            preorder(nodo.getLeft());
        }
        
        if (nodo.getRight() != null) {
            preorder(nodo.getRight());
        }
    }
    
    // Recursivo
    public void postorder(Nodo<T> nodo) {
        if (nodo.getLeft() != null) {
            preorder(nodo.getLeft());
        }
        
        if (nodo.getRight() != null) {
            preorder(nodo.getRight());
        }
        
        System.out.println(nodo.getElement().toString());
    }
    
    // Recursivo
    public void inorder(Nodo<T> nodo) {
        if (nodo.getLeft() != null) {
            preorder(nodo.getLeft());
        }
        
        System.out.println(nodo.getElement().toString());
        
        if (nodo.getRight() != null) {
            preorder(nodo.getRight());
        }
    }
}

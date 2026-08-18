# Linked Lists

## What is a Linked List?

- A linear data structure where elements are **not stored in contiguous memory locations** (unlike Arrays or ArrayLists).
- Built using **Nodes**, where each node contains the actual **data** and a **reference (pointer)** to the next node in the sequence.
- Relies entirely on the **Heap** memory and object references; the nodes can be scattered anywhere in RAM, linked together like a scavenger hunt.
- A massive interview favorite — because manipulating linked lists forces you to prove you truly understand references, pointers, and edge-case handling (like avoiding `NullPointerException`).

## Anatomy of a Node

| Component | Type             | Purpose                                                                 |
| --------- | ---------------- | ----------------------------------------------------------------------- |
| **Data**  | `int`, `Object`  | The actual payload or value you want to store.                          |
| **Next**  | `Node` (Reference)| The memory address pointing to the next node. `null` if it's the last node. |
| **Prev**  | `Node` (Reference)| *(Doubly Linked Lists only)* Points backward to the previous node.      |

```java
class Node {
    int data;
    Node next; // This reference is the "glue" that holds the list together
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

## Types of Linked Lists

| Type                 | Directionality      | Structure                                                      | Best Used For                                  |
| -------------------- | ------------------- | -------------------------------------------------------------- | ---------------------------------------------- |
| **Singly Linked**    | One-way (Forward)   | `A -> B -> C -> null`                                          | Simple queues, basic stacks, memory efficiency |
| **Doubly Linked**    | Two-way (Bi-directional)| `null <- A <-> B <-> C -> null`                            | LRU Caches, browser history, text editors      |
| **Circular Linked**  | Infinite Loop       | `A -> B -> C -> A` (Tail points back to Head)                  | Multiplayer turn-based games, Round-Robin scheduling |

## The Three Golden Rules of Pointers

1. **Order of operations is critical** — When inserting a new node, you must link the new node to the *rest of the list* BEFORE you update the previous node to point to the new node. If you do it backward, you sever the chain and lose the rest of the list to the Garbage Collector.
2. **Always check the Head** — Inserting or deleting at the very beginning of the list requires updating the `head` reference itself. Forgetting this is the #1 cause of bugs.
3. **Beware of Cycles** — If a node's `next` pointer accidentally points to a previous node in the list, you create an infinite loop. Any traversal code (like `while(current != null)`) will run forever and crash the program.

## Arrays vs. Linked Lists (The Core Trade-off)

| Feature              | Array / ArrayList                            | Linked List                                  |
| -------------------- | -------------------------------------------- | -------------------------------------------- |
| **Memory Layout**    | Contiguous (one solid block of RAM)          | Scattered (nodes allocated individually on Heap) |
| **CPU Cache**        | Extremely friendly (spatial locality)        | Terrible (jumping around RAM causes cache misses) |
| **Access by Index**  | **O(1)** (Instant math: `base + index * size`) | **O(n)** (Must walk step-by-step from Head)  |
| **Insert at Head**   | **O(n)** (Must shift every single element right) | **O(1)** (Just update two pointers)          |
| **Sizing**           | Fixed capacity (must resize/copy when full)  | Fully dynamic (grows one node at a time)     |

## Operations in Plain Words

- **Insertion at Head:** Create a new node. Point its `next` to the current `head`. Update the `head` reference to be the new node. (Instant, no shifting required).
- **Deletion:** You cannot just "delete" a node. Instead, you must find the node *before* it (`prev`), and tell `prev.next` to skip over the target and point to `target.next`. The target node is now unreachable and will be garbage collected.
- **Traversal:** You cannot use a standard `for (int i=0; i<length; i++)` loop because there are no indices. You must use a `while` loop: start at `head`, do your work, and move forward via `current = current.next` until you hit `null`.

## Java Examples

### The "Order of Operations" Insertion

```java
public void insertAtHead(int newData) {
    Node newNode = new Node(newData);
    
    // 1. Point new node to the current head (Preserve the chain!)
    newNode.next = head; 
    
    // 2. Update head to be the new node
    head = newNode;      
}
```
*If you swapped lines 1 and 2, `newNode.next` would point to itself, and the original list would be lost forever.*

### The "Bridge the Gap" Deletion

```java
public void deleteNode(int key) {
    // Edge Case 1: The head itself holds the key
    if (head != null && head.data == key) {
        head = head.next; // Just move the head pointer forward
        return;
    }

    Node current = head;
    Node prev = null;

    // Find the node and keep track of the one behind it
    while (current != null && current.data != key) {
        prev = current;
        current = current.next;
    }

    // Edge Case 2: The key wasn't in the list
    if (current == null) return;

    // Bridge the gap: skip over 'current'
    prev.next = current.next; 
}
```

## Big O of Linked Lists

| Operation                               | Big O       | Why                                                              |
| --------------------------------------- | ----------- | ---------------------------------------------------------------- |
| **Access** by Index                     | O(n)        | No math shortcuts; must traverse node-by-node from the head.     |
| **Search** by Value                     | O(n)        | Data is unsorted; must linearly scan until found or `null`.      |
| **Insert** at Head                      | O(1)        | Just update two references (`newNode.next` and `head`).          |
| **Insert** at Tail                      | O(n) / O(1) | O(n) to walk to the end. O(1) if you maintain a `tail` pointer.  |
| **Insert** in Middle                    | O(n)        | O(1) to update pointers, but O(n) to walk to the insertion spot. |
| **Delete** at Head                      | O(1)        | Simply reassign `head = head.next`.                              |
| **Delete** in Middle                    | O(n)        | O(n) to find the node and its predecessor.                       |

## Cheat Sheet Takeaways

- **The "Dummy Head" Trick:** In interviews, create a `dummy` node that points to the `head` (`Node dummy = new Node(0); dummy.next = head;`). This completely eliminates the need to write separate `if` statements for edge cases where the head itself is deleted or modified.
- **Fast and Slow Pointers (The Runner Technique):** If you need to find the middle of a linked list, or detect if it has a cycle (infinite loop), use two pointers. The `slow` pointer moves 1 step at a time, the `fast` pointer moves 2 steps. If there is a cycle, they will eventually collide. If `fast` hits `null`, you've found the end.
- **Java's `LinkedList` class is Doubly Linked:** When you use `java.util.LinkedList`, you are using a Doubly Linked List. It implements both `List` and `Deque` (Double Ended Queue), making it excellent for `addFirst()`, `addLast()`, `removeFirst()`, and `removeLast()` (all O(1)).
- **Memory Overhead:** A linked list uses significantly more memory than an array. For every 4 bytes of `int` data, you are also paying for the memory overhead of the `Node` object header and the 8-byte `next` reference. Only use them when frequent insertions/deletions at the ends outweigh the cost of memory and cache misses.
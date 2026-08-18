# Big O Notation

## What is Big O?

- A mathematical framework for **evaluating algorithm scalability** as the size of the input data ($n$) approaches infinity.
- **Time complexity** — tracks the **growth rate of operations**, completely independent of hardware speed, language, or compiler optimizations. It tells you how the runtime *scales*, not the exact seconds it takes.
- **Space complexity** — measures the **auxiliary memory** (RAM) an algorithm requires relative to the input size. It helps you avoid `OutOfMemoryError` crashes in production.
- A fundamental engineering skill — it provides a universal vocabulary for developers to discuss efficiency, predict system bottlenecks, and pass technical whiteboard interviews.

## Big O is Always Worst Case

| Notation          | Asymptotic Bound       | Meaning                                                              |
| ----------------- | ---------------------- | -------------------------------------------------------------------- |
| Ω (Omega)         | Lower Bound (Best case)| The absolute minimum operations the algorithm will ever perform.     |
| Θ (Theta)         | Tight Bound (Average)  | The expected operations on typical, randomized data.                 |
| O (Big O)         | Upper Bound (Worst case)| The absolute maximum operations; the ceiling of resource consumption.|

While developers frequently say things like "the Big O of a hash map lookup is O(1)", they are colloquially describing the average case. Formally, Big O establishes the **absolute upper ceiling**. We focus on the worst case because in engineering, we need guarantees that our system won't crash or time out when given the most difficult possible input.

## The Four Rules of Simplification

1. **Ignore constant multipliers** — `O(5n)` simplifies to `O(n)`. We drop constants because a faster CPU might execute 5 operations in the same time another executes 1. Big O measures the *shape* of the growth curve, not the exact step count.
2. **Focus on the dominant term** — `O(n³ + n² + 500)` simplifies to `O(n³)`. As $n$ gets massive (e.g., $1,000,000$), $n^3$ becomes $1,000,000,000,000,000,000$. The $n^2$ and $500$ become mathematically irrelevant dust.
3. **Account for multiple inputs** — iterating through array `A` (size $a$) then array `B` (size $b$) is `O(a + b)`. Nesting them is `O(a * b)`. Never lazily assume both inputs are the same variable `n`, as this is a classic interview trap.
4. **Assume the worst-case scenario** — an algorithm that searches for a value might exit early after 1 step (Best Case $\Omega(1)$), but if the value is at the very end, it iterates $n$ times. Therefore, it is classified as `O(n)`.

## The Big O Chart

| Complexity | Name          | Growth Behavior                             | n=10   | n=1000      | Real-World Java Example             |
| ---------- | ------------- | ------------------------------------------- | ------ | ----------- | ----------------------------------- |
| O(1)       | Instant       | Flat, execution time never changes          | 1      | 1           | `arr[0]`, `map.get(key)`            |
| O(log n)   | Logarithmic   | Halving the problem space each step         | ~4     | ~10         | `Arrays.binarySearch()`             |
| O(n)       | Linear        | Grows exactly in lockstep with input size   | 10     | 1000        | `for` loop over an `ArrayList`      |
| O(n log n) | Linearithmic  | Standard baseline for efficient sorting     | ~33    | ~10,000     | `Arrays.sort()`, MergeSort          |
| O(n²)      | Quadratic     | Nested iterations over the same dataset     | 100    | 1,000,000   | Nested `for` loops, BubbleSort      |
| O(2^n)     | Exponential   | Doubling work for every single input added  | 1024   | Uncomputable| Recursive Fibonacci (naive)         |
| O(n!)      | Factorial     | Permutations; grows faster than exponential | Huge   | Impossible  | Traveling Salesperson (brute force) |

As `n` scales into the millions, the difference between `O(n log n)` and `O(n²)` is the difference between a program finishing in milliseconds versus taking hours.

## O(log n) in Plain Words

- Think of a physical dictionary or phonebook: you open to the exact middle, check the word, and instantly throw away the entire half that doesn't contain your target.
- Because the search space is **halved every step**, the growth is incredibly slow. A dataset of **1,000,000 elements** takes a maximum of **20 steps** to search. A dataset of **4 billion elements** takes just **32 steps**.
- **The Catch:** It strictly requires data to be **pre-sorted** or structured hierarchically (like a Binary Search Tree or a Database B-Tree index).

## Java Examples

### O(1) — Constant Time

```java
public int getFirstElement(int[] arr) {
    // Accessing an index or doing basic math takes the exact same 
    // amount of time, whether the array has 10 items or 10 million.
    return arr[0]; 
}
```

### O(n) — Linear Time

```java
public boolean findTarget(int[] arr, int target) {
    // In the worst case (target is at the very end or missing), 
    // the loop must execute exactly 'n' times.
    for (int item : arr) {
        if (item == target) {
            return true;
        }
    }
    return false;
}
```

### O(n²) — Quadratic Time

```java
public void printAllPairs(int[] arr) {
    // Every element in the outer loop triggers a FULL iteration 
    // of the inner loop, resulting in n * n operations.
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
            System.out.println("Pair: " + arr[i] + ", " + arr[j]);
        }
    }
}
```

## Big O of Linked Lists vs Arrays

Understanding *why* data structures have different Big O costs is critical for choosing the right tool.

| Operation                               | Array / ArrayList | Linked List   | Why Linked List behaves this way                               |
| --------------------------------------- | ----------------- | ------------- | -------------------------------------------------------------- |
| **Access** by Index                     | O(1)              | **O(n)**      | Must traverse node-by-node from the head; no contiguous memory.|
| **Search** by Value                     | O(n)              | **O(n)**      | Linear scan required; data isn't sorted or indexed by default. |
| **Insert** at Head (beginning)          | O(n)              | **O(1)**      | Create a new node and point it to the old head. No shifting!   |
| **Insert** at Tail (end)                | O(1)*             | **O(n) / O(1)**| O(n) to traverse, but O(1) if you maintain a `tail` pointer.   |
| **Insert** in Middle                    | O(n)              | **O(n)**      | O(n) to find the spot, but O(1) to update the pointers.        |
| **Delete** at Head                      | O(n)              | **O(1)**      | Simply move the head pointer to the second node.               |

*\*Note: ArrayList `add()` is amortized O(1), but can be O(n) if the internal backing array runs out of capacity and must be resized.*

## Cheat Sheet Takeaways (bigocheatsheet.com)

- **Trade-offs are everywhere** — you can almost always improve time complexity by sacrificing space complexity. For example, using a `HashMap` to cache results (Memoization) turns an `O(n)` lookup into an `O(1)` lookup, but costs `O(n)` memory.
- **Amortized analysis matters in Java** — Java's `ArrayList` is `O(1)` for appending *on average*. However, when it hits its capacity limit, it creates a new array double the size and copies everything over, which is an `O(n)` operation.
- **Recursion has hidden space costs** — An `O(n)` recursive function actually uses `O(n)` **space** because every recursive call adds a new frame to the JVM Call Stack. If it goes too deep, you will get a `StackOverflowError`.
- **Graph/Tree traversals** — Breadth-First Search (BFS) and Depth-First Search (DFS) are both `O(V + E)` (Vertices + Edges) in time complexity. Space complexity is `O(V)` because, in the worst case, you must store every node in a Queue (BFS) or the Call Stack (DFS).

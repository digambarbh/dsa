# Big O Notation

## What is Big O?

- A mathematical framework for **evaluating algorithm scalability** as the size of the input data approaches infinity
- **Time complexity** — tracks the **growth rate of operations**, completely independent of hardware speed, language, or compiler optimizations
- **Space complexity** — measures the **auxiliary memory** (RAM) an algorithm requires relative to the input size
- A fundamental engineering skill — essential for preventing production crashes and passing technical whiteboard interviews

## Big O is Always Worst Case

| Notation          | Asymptotic Bound       |
| ----------------- | ---------------------- |
| Ω (Omega)         | Lower Bound (Best case)|
| Θ (Theta)         | Tight Bound (Average)  |
| O (Big O)         | Upper Bound (Worst case)|

While developers frequently say things like "the Big O of a hash map lookup is O(1)", they are colloquially describing the average case. Formally, Big O establishes the **absolute upper ceiling** of an algorithm's resource consumption.

## The Four Rules of Simplification

1. **Ignore constant multipliers** — `O(5n)` simplifies to `O(n)` because constants do not alter the shape of the growth curve.
2. **Focus on the dominant term** — `O(n³ + n² + 500)` simplifies to `O(n³)` because lower-order terms and static numbers become mathematically irrelevant for large inputs.
3. **Account for multiple inputs** — iterating through array `A` then array `B` is `O(a + b)`. Nesting them is `O(a * b)`. Never assume both inputs are the same variable `n`.
4. **Assume the worst-case scenario** — an algorithm that *might* exit early after 1 step but *could* iterate through all elements is still classified by its maximum possible iterations.

## The Big O Chart

| Complexity | Name          | Growth Behavior                             | n=10   | n=1000      |
| ---------- | ------------- | ------------------------------------------- | ------ | ----------- |
| O(1)       | Constant      | Flat, instant execution                     | 1      | 1           |
| O(log n)   | Logarithmic   | Halving the problem space each step         | ~4     | ~10         |
| O(n)       | Linear        | Grows exactly in lockstep with input size   | 10     | 1000        |
| O(n log n) | Linearithmic  | Standard baseline for efficient sorting     | ~33    | ~10,000     |
| O(n²)      | Quadratic     | Nested iterations over the same dataset     | 100    | 1,000,000   |
| O(2^n)     | Exponential   | Doubling work for every single input added  | 1024   | Uncomputable|

As `n` scales into the millions, the difference between `O(n log n)` and `O(n²)` is the difference between a program finishing in milliseconds versus taking hours.

## O(log n) in Plain Words

- Think of a physical dictionary: you open to the middle, check the word, and throw away the entire half that doesn't contain your target
- Because the search space is **halved every step**, a dataset of **4 billion elements** takes a maximum of **32 steps** to search
- Requires data to be **pre-sorted** or structured hierarchically (like a Binary Search Tree)

## Python Examples

### O(1) — constant time

```python
def get_first_element(arr):
    return arr[0] if arr else None
```

Accessing an index or performing a basic math calculation takes the exact same amount of time regardless of array size.

### O(n) — linear time

```python
def find_target(arr, target):
    for item in arr:
        if item == target:
            return True
    return False
```

In the worst case (the target is at the very end or not in the list), the loop must execute `n` times.

### O(n²) — quadratic time

```python
def print_all_pairs(arr):
    for i in arr:
        for j in arr:
            print(f"Pair: {i}, {j}")
```

Every element in the outer loop triggers a full iteration of the inner loop, resulting in `n * n` operations.

## Big O of Linked Lists

| Operation                               | Big O       | Why                                              |
| --------------------------------------- | ----------- | ------------------------------------------------ |
| **Access** by Index                     | O(n)        | Must traverse node-by-node from the head         |
| **Search** by Value                     | O(n)        | Linear scan required; data isn't sorted/indexed  |
| **Insert** at Head (beginning)          | O(1)        | Create a new node and point it to the old head   |
| **Insert** at Tail (end)*               | O(n) / O(1) | O(n) to traverse, but O(1) if a tail pointer exists |
| **Delete** at Head                      | O(1)        | Simply move the head pointer to the second node  |

## Cheat Sheet Takeaways (bigocheatsheet.com)

- **Trade-offs are everywhere** — you can often improve time complexity by sacrificing space complexity (e.g., caching results in a Hash Map)
- **Amortized analysis matters** — dynamic arrays (like Python's `list`) are `O(1)` for appending *on average*, even though occasional memory resizing takes `O(n)`
- **Recursion has space costs** — an `O(n)` recursive function actually uses `O(n)` space due to the call stack building up in memory
- **Graph/Tree traversals** — Breadth-First Search (BFS) and Depth-First Search (DFS) are both `O(V + E)` (Vertices + Edges) in both time and space complexity
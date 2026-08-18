# Classes & References

## What are Classes & References?

- A **class** is a **blueprint** that defines what data and behavior an object can have
- An **object** is an **instance** of that class — a real thing created in memory
- A **reference** is a variable that **points to an object** in memory
- The reference is **not the object itself** — it is more like a remote control, address, or pointer to the actual object
- Understanding references is essential for:
  - Avoiding unexpected object mutation
  - Fixing `NullPointerException` bugs
  - Understanding method parameters
  - Working with arrays, strings, lists, trees, graphs, and linked lists
  - Passing technical interview questions about memory and object behavior

---

## Class vs Object vs Reference

| Term        | Meaning                                      | Analogy                          |
| ----------- | -------------------------------------------- | -------------------------------- |
| **Class**   | Blueprint or template for creating objects    | Architectural house blueprint    |
| **Object**  | Actual instance created from the class        | The real house built from it     |
| **Reference** | Variable that points to the object          | The house address or remote control |

A class by itself does not create a real object in memory.

```java
class Car {
    String color;
}
```

This only defines the structure. It does **not** create a `Car` object yet.

To create an actual object, we use the `new` keyword:

```java
Car myCar = new Car();
```

Here:

- `Car` is the class
- `myCar` is the reference
- `new Car()` creates the actual object on the heap

---

## Memory Model: Stack vs Heap

In languages like Java, memory is divided into two major areas:

| Memory Area | What it stores                              | Speed        | Lifetime                            |
| ----------- | ------------------------------------------- | ------------ | ----------------------------------- |
| **Stack**   | Local variables, method calls, primitive values, references | Very fast    | Automatically cleaned when method ends |
| **Heap**    | Actual objects and arrays                   | Slower       | Managed by garbage collector        |

Example:

```java
int score = 10;
Car myCar = new Car();
```

What happens:

| Variable / Value | Where it lives | Explanation |
| ---------------- | -------------- | ----------- |
| `score`          | Stack          | Primitive values are stored directly |
| `myCar`          | Stack          | The reference variable is stored on the stack |
| `new Car()`      | Heap           | The actual object lives on the heap |

So `myCar` does not contain the car object.

It contains a reference that points to the car object.

---

## The Remote Control Analogy

Think of an object as a **TV**.

Think of a reference as a **remote control**.

```java
TV myTV = new TV();
TV anotherRemote = myTV;
```

Now both `myTV` and `anotherRemote` are remote controls pointing to the **same TV**.

If you do this:

```java
anotherRemote.volume = 50;
```

The actual TV changes.

So this:

```java
System.out.println(myTV.volume);
```

will also show `50`.

But if you do this:

```java
anotherRemote = new TV();
```

You are not changing the original TV.

You are just making `anotherRemote` point to a completely different TV.

---

## Important Rules of References

### 1. Multiple references can point to the same object

```java
Player p1 = new Player();
Player p2 = p1;
```

Now both `p1` and `p2` point to the same object.

Changing the object through one reference affects the other.

---

### 2. Reassigning one reference does not affect the object

```java
Player p1 = new Player();
Player p2 = p1;

p1 = new Player();
```

After this:

- `p1` points to a new object
- `p2` still points to the old object
- The old object is not destroyed unless no references point to it anymore

---

### 3. Passing an object to a method passes a copy of the reference

Java is technically **pass-by-value**, but when you pass an object, the value being passed is the **reference**.

That means the method receives a copy of the remote control.

It can modify the actual object.

But it cannot permanently reassign the caller’s original reference.

---

### 4. A null reference points to no object

```java
Car myCar = null;
```

This means:

- The reference variable exists
- But it points to nothing
- Trying to use it will cause an error

Example:

```java
myCar.start(); // NullPointerException
```

---

### 5. Objects are destroyed by garbage collection when unreachable

An object becomes eligible for garbage collection when:

- No active reference points to it
- It cannot be accessed anymore
- The program no longer needs it

Example:

```java
Car myCar = new Car();
myCar = null;
```

Now the original `Car` object has no reference pointing to it.

It may be removed later by the garbage collector.

---

## Step-by-Step Memory Example

```java
Person p1 = new Person();
Person p2 = p1;
p1 = new Person();
```

### Step 1

```java
Person p1 = new Person();
```

| Stack | Heap |
| ----- | ---- |
| `p1` points to object A | Object A created |

---

### Step 2

```java
Person p2 = p1;
```

| Stack | Heap |
| ----- | ---- |
| `p1` points to object A | Object A still exists |
| `p2` points to object A | No new object created |

---

### Step 3

```java
p1 = new Person();
```

| Stack | Heap |
| ----- | ---- |
| `p1` points to object B | Object A still exists |
| `p2` still points to object A | Object B created |

Important:

`p2` did not change.

Only `p1` was reassigned.

---

## Java Examples

### Example 1: Creating an object

```java
class Dog {
    String name;
}

Dog dog1 = new Dog();
dog1.name = "Rex";
```

Memory idea:

```text
Stack:
dog1 --> Heap: Dog object with name = "Rex"
```

---

### Example 2: Two references to the same object

```java
Dog dog1 = new Dog();
dog1.name = "Rex";

Dog dog2 = dog1;

dog2.name = "Max";

System.out.println(dog1.name);
```

Output:

```text
Max
```

Why?

Because `dog1` and `dog2` point to the same object.

Changing the object using `dog2` also affects what `dog1` sees.

---

### Example 3: Reassignment does not affect other references

```java
Dog dog1 = new Dog();
Dog dog2 = dog1;

dog1 = new Dog();
```

After this:

```text
dog1 --> new Dog object
dog2 --> old Dog object
```

`dog2` still points to the original object.

---

### Example 4: Passing an object to a method

```java
class Player {
    int score;
}

void increaseScore(Player p) {
    p.score = p.score + 10;
}
```

Usage:

```java
Player player = new Player();
player.score = 100;

increaseScore(player);

System.out.println(player.score);
```

Output:

```text
110
```

Why?

Because `increaseScore` received a copy of the reference to the same object.

It modified the actual object on the heap.

---

### Example 5: Reassigning inside a method does not affect the original reference

```java
void replacePlayer(Player p) {
    p = new Player();
    p.score = 0;
}
```

Usage:

```java
Player player = new Player();
player.score = 100;

replacePlayer(player);

System.out.println(player.score);
```

Output:

```text
100
```

Why?

Because inside the method, `p` was reassigned to a new object.

But the original `player` variable outside the method still points to the old object.

---

### Example 6: Null reference error

```java
Dog dog = null;
System.out.println(dog.name);
```

This causes:

```text
NullPointerException
```

Because `dog` does not point to any object.

---

## Primitive Variables vs Reference Variables

| Feature | Primitive Variable | Reference Variable |
| ------- | ------------------ | ------------------ |
| Examples | `int`, `double`, `boolean`, `char` | Objects, arrays, strings, custom classes |
| Stores | Actual value | Memory reference to object |
| Assignment copies | The value | The reference |
| Memory location | Usually stack | Reference on stack, object on heap |
| Null possible? | No | Yes |
| Equality check | Usually `==` | Usually `.equals()` for objects |

Example:

```java
int a = 10;
int b = a;

b = 20;

System.out.println(a); // 10
```

Changing `b` does not affect `a`.

But with objects:

```java
Dog dog1 = new Dog();
Dog dog2 = dog1;

dog2.name = "Max";

System.out.println(dog1.name); // Max
```

Changing through `dog2` affects `dog1` because both references point to the same object.

---

## Equality with References

This is one of the most common interview gotchas.

### `==` checks reference equality

It asks:

> Do both variables point to the exact same object in memory?

### `.equals()` checks content equality

It asks:

> Do both objects contain the same data?

---

## String Equality Example

```java
String a = new String("hello");
String b = new String("hello");

System.out.println(a == b);
System.out.println(a.equals(b));
```

Output:

```text
false
true
```

Explanation:

| Check | Result | Reason |
| ----- | ------ | ------ |
| `a == b` | `false` | Different objects in memory |
| `a.equals(b)` | `true` | Same text content |

---

## String Literal Pool Trap

Java has a special area called the **String Pool**.

```java
String x = "hello";
String y = "hello";

System.out.println(x == y);
```

Output:

```text
true
```

Why?

Because Java reuses string literals to save memory.

Both `x` and `y` may point to the same pooled string object.

But this is why you should usually avoid using `==` for string comparison.

Use:

```java
x.equals(y)
```

---

## Reference Equality Table

| Comparison | Meaning | Use When |
| ---------- | ------- | -------- |
| `a == b` | Same memory object | Checking identity |
| `a.equals(b)` | Same logical content | Checking equality |
| `Objects.equals(a, b)` | Safe equality check | Avoiding NullPointerException |
| `a != null` | Reference exists | Preventing null errors |

Safe comparison example:

```java
Objects.equals(name, "Alice")
```

This avoids:

```java
NullPointerException
```

if `name` is null.

---

## Common Bugs with References

### Bug 1: Accidentally modifying shared object

```java
User user1 = new User();
User user2 = user1;

user2.name = "Alice";

System.out.println(user1.name); // Alice
```

If you wanted a separate copy, you need to create a new object.

```java
User user2 = new User();
```

Or implement copying/cloning.

---

### Bug 2: Assuming reassignment changes the original reference

```java
void reset(User user) {
    user = new User();
}
```

This does not reset the caller’s object.

It only changes the local method reference.

---

### Bug 3: Using `==` to compare objects

```java
String a = new String("test");
String b = new String("test");

if (a == b) {
    System.out.println("Same");
} else {
    System.out.println("Not same");
}
```

Output:

```text
Not same
```

Because `==` compares references, not text content.

Correct:

```java
if (a.equals(b)) {
    System.out.println("Same");
}
```

---

### Bug 4: Forgetting null check

```java
String name = null;

if (name.equals("Alice")) {
    System.out.println("Found");
}
```

This crashes.

Safer:

```java
if ("Alice".equals(name)) {
    System.out.println("Found");
}
```

Or:

```java
if (name != null && name.equals("Alice")) {
    System.out.println("Found");
}
```

Or:

```java
if (Objects.equals(name, "Alice")) {
    System.out.println("Found");
}
```

---

## References in Data Structures

References are the foundation of many data structures.

### Arrays

```java
int[] arr1 = {1, 2, 3};
int[] arr2 = arr1;

arr2[0] = 99;

System.out.println(arr1[0]);
```

Output:

```text
99
```

Arrays are objects.

`arr1` and `arr2` point to the same array.

---

### ArrayList

```java
ArrayList<String> list1 = new ArrayList<>();
list1.add("Apple");

ArrayList<String> list2 = list1;

list2.add("Banana");

System.out.println(list1.size());
```

Output:

```text
2
```

Because `list1` and `list2` refer to the same list.

---

### Linked List Node

A linked list is built using references.

```java
class Node {
    int data;
    Node next;
}
```

Example:

```java
Node node1 = new Node();
node1.data = 1;

Node node2 = new Node();
node2.data = 2;

node1.next = node2;
```

Memory idea:

```text
node1 --> [1 | next] --> [2 | next] --> null
```

The `next` field is a reference to another node object.

Without references, linked structures would not be possible.

---

## Shallow Copy vs Deep Copy

When copying objects, there are two important ideas.

### Shallow copy

A shallow copy copies the references.

It does not create new copies of the internal objects.

Example idea:

```java
class School {
    String name;
}

class Student {
    School school;
}
```

If you copy a `Student` object shallowly, the new student may still point to the same `School`.

---

### Deep copy

A deep copy creates new objects for internal referenced objects too.

```text
Original Student --> Original School
Copied Student   --> Copied School
```

Now changing one school does not affect the other.

---

## Shallow vs Deep Copy Table

| Copy Type | What is copied? | Internal objects? | Risk |
| --------- | --------------- | ----------------- | ---- |
| Shallow copy | Top-level object | Still shared | Accidental mutation |
| Deep copy | Top-level object and internal objects | New copies created | More memory/time |

---

## Method Parameter Behavior

### Primitive parameter

```java
void changeValue(int x) {
    x = 100;
}
```

Usage:

```java
int number = 5;
changeValue(number);

System.out.println(number);
```

Output:

```text
5
```

Primitive value is copied.

---

### Object reference parameter

```java
void changeName(Dog dog) {
    dog.name = "Max";
}
```

Usage:

```java
Dog dog = new Dog();
dog.name = "Rex";

changeName(dog);

System.out.println(dog.name);
```

Output:

```text
Max
```

The reference is copied, but it still points to the same object.

---

## Interview Questions to Expect

### Question 1

```java
Car a = new Car();
Car b = a;
a = new Car();
```

Are `a` and `b` pointing to the same object?

Answer:

```text
No.
```

`b` still points to the first object.

`a` points to the second object.

---

### Question 2

```java
Car a = new Car();
Car b = a;

b.color = "Red";
```

What is `a.color`?

Answer:

```text
Red.
```

Because both references point to the same object.

---

### Question 3

```java
void makeRed(Car c) {
    c.color = "Red";
}
```

Will this change the caller’s car?

Answer:

```text
Yes.
```

The method receives a copy of the reference, but that reference points to the same object.

---

### Question 4

```java
void replace(Car c) {
    c = new Car();
    c.color = "Red";
}
```

Will this change the caller’s car?

Answer:

```text
No.
```

The method reassigns its local reference, not the caller’s reference.

---

## Cheat Sheet Takeaways

- A **class** is a blueprint
- An **object** is an instance created from that blueprint
- A **reference** points to an object but is not the object itself
- References are usually stored on the **stack**
- Objects are stored on the **heap**
- Multiple references can point to the same object
- Changing an object through one reference affects all references pointing to it
- Reassigning a reference only changes where that reference points
- Java passes a **copy of the reference** when passing objects to methods
- Methods can modify the object, but cannot permanently reassign the caller’s reference
- Use `.equals()` to compare object contents
- Use `==` only when checking if two references point to the exact same object
- Always check for `null` before using a reference
- Arrays, strings, lists, and custom objects are all reference types
- Linked lists, trees, and graphs rely heavily on references to connect objects
- An object becomes garbage-collectable when no live reference points to it
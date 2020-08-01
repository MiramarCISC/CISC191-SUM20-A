# Inefficiencies

These inefficiencies I noticed while unit testing are not critical with the
scope of our project, but will start to drag down performance once more
GameObjects are added.

Also, I noticed some of the loops we have over the GameObject LinkedList are
generally inefficient as the `get()` method also iterates over the LinkedList.
We can use `java.util.ListIterator` to prevent from double unnecessary extra
loops.

Sample code with ListIterator:

```java
LinkedList<String> authorsList = new LinkedList<String>();
String authorName;
ListIterator<String> listIterator;

authorsList.add("Gamow");
authorsList.add("Greene");
authorsList.add("Penrose");

listIterator = authorsList.listIterator();

while (listIterator.hasNext()) {
   authorName = listIterator.next();
   System.out.println(authorName);
}
```

Inefficient way of looping over a LinkedList:

```java
LinkedList<String> authorsList = new LinkedList<String>();

String authorName;

authorsList.add("Gamow");
authorsList.add("Greene");
authorsList.add("Penrose");

for (int i = 0; i < authorsList.size(); i++) {
    authorName = authorsList.get(i); // get() method also iterates over the list
    System.out.println(authorName);
}
```

## KeyInput.java

- `keyPressed()` -- Iterates over the whole GameObjects LinkedList while only
  looking for the `Wizard` object or a GameObject with an ID of `Player`.
- `keyReleased()` -- Same as `keyPressed()`

  - Possible solution: Instead of iterating over the whole GameObjects, it would
    be better to pass along the reference of the `Wizard` instance in the
    `KeyInput` class.

## MouseInput.java

- `fireBullet()` -- Iterates over the whole GameObjects LinkedList while only
  looking for the `Wizard` object or a Game Object with an ID of `Player`.

  - Possible solution: Instead of iterating over the whole GameObjects, it would
    be better to pass along the reference of the `Wizard` instance in the
    `MouseInput` class.

## Wizard.java

- `collision()` -- Iterates over the whole GameObjects LinkedList for Rectangle
  bounds interception (collision). Only one object can occupy a space in canvas
  so iterating over every game object is unnecessary.

  - Possible solution: Map every coordinates of each GameObjects such that they
    can be accessed, at constant time, via their coordinates. e.g. **Trie** data
    structure.

## Bullet.java

- `tick()` -- Iterates over the whole GameObjects LinkedList while only looking
  for all `Block` object instances or GameObjects with an ID of `Block`.

  - Possible solution: This the same as `collision()` of `Wizard` class. It can
    be optimized by checking only the coordinates of the current next location
    of the bullet and check for conditions. Also would benefit with a data
    structure such as the **Trie** data structure.

## All the Enemy GameObjects

- `tick()` -- Iterates over the whole GameObjects LinkedList for Rectangle
  bounds interception (collision). Would also benefit from a more fitting data
  structure of `Handler` gameObjects instead of LinkedList.


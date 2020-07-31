# Inefficiencies

These inefficiencies I noticed while unit testing are not critical with the
scope of our project, but will start to drag down performance once more
GameObjects are added or with a bigger screen resolution.

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


# jlox

jlox is an implementation of the Lox programming language, written in Java.
It uses the tree-walk interpreter from the [Crafting Interpreters](https://craftinginterpreters.com) book.

---

## Data Types

### Booleans
```
true;
false;
```

### Numbers
Lox uses only floating-point numbers.
```
wholeNumber = 34;           // 34.0
fractionalNumber = 12.5;    // 12.5
```

### Strings
```
"Hello, world!";
"";     // An empty string.
```

### nil
`nil` represents no value, similar to `null` in other languages.


## Expressions
### Mathematical Expressions
jlox features common numeric operations and negation:
```
a + b - a;
a * b / c;
-value;
```

### Comparisons
Numbers can be compared with the usual comparison operators `<` `>` `<=` `>=`:
```
1 <= 2;             // true
```
We can test for equality or inequality with `==` and `!=`:
```
1 == 1;             // true
"foo" != "bar";     // true
"42" == 42;         // false
```
Different types are never equivalent.

### Logical Expressions
```
!true;              // false
true or false;      // true
true and false;     // false
```

### Variables
Declare variables with `var`:
```
var message = "Hello";
var uninitialisedVariable;      // set to nil
```

## Control Flow
`if` and `else` statements:
```
if (condition) {
    // ...
} else {
    // ...
}
```

`while` and `for` loops:
```
while (num > 0) {
    num = num - 1;
}
for (var i = 0; i < 5; i = i + 1) {
    print i;
}
```

## Functions
Functions are first class in Lox, and return `nil` by default.
```
fun sum(first, second) {
    return first + second;
}
sum(1, 2);
```

## Classes
Class methods are declared without the `fun` keyword.
```
class Entity {
    move() {
        print "Entity moved!";
    }
}

var myEntity = Entity();      // Created instance of Entity class
```

We can assign fields, or create them if they don't already exist:
```
myEntity.age = 57;
myEntity.name = "Bob";
```
We can also do this in the `init` method which is called when the object is constructed:
```
class Entity {
    init(age, name) {
        this.age = age;
        this.name = name;
    }

    // ...
}

var myEntity = Entity(57, "Bob");
```
The arguments passed to the class are forwareded to the `init` method.

### Inheritance
Lox supports single inheritance using the `<` operator.
```
class Alien < Entity {
    init(age, name, planet) {
        super.init(age, name);
        this.planet = planet;
    }
}
```
We can access methods of the superclass using `super`.

## Comments
jlox allows single-line comments with `//` or C-style block comments with `/**/`.

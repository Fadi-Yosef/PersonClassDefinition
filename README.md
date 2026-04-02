# PersonClassDefinition

Small Java Maven project for practicing functional programming and the Stream API with a `Person` list.

## What this project does

The project shows:

- functional programming without streams
- custom functional interfaces
- reusable person processing logic
- Stream API operations like `filter`, `map`, `count`, `sorted`, and `findFirst`

## Classes we created

- `App`
  Main class. Contains sample data and runs the exercise examples.
- `Person`
  Model class with `name`, `age`, `city`, and `active`.
- `PersonRule`
  Functional interface that checks if a `Person` matches a rule.
- `PersonAction`
  Functional interface that performs an action on a `Person`.
- `PersonProcessor`
  Reusable class for finding people and applying actions.
- `AppTest`
  Test class for a few core behaviors.

## Build the project

From the project folder:

```powershell
mvn package
```

This compiles the code, runs the tests, and creates the output in `target/`.

## Run the project

From terminal:

```powershell
mvn exec:java
```

## Run in IntelliJ

1. Open `Run -> Edit Configurations`
2. Add a new `Maven` configuration
3. Set `Command line` to `exec:java`
4. Set the working directory to the project folder
5. Run it

## Output

The program prints:

- filtered people
- names
- adult count
- sorted people by age
- first active person in Stockholm
- formatted strings and email examples

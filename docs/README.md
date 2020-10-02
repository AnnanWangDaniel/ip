# User Guide -- Annan's Duke Project
   

## Quick Start

1. Working Environment: Java 11.
2. Download at least the whole Duke folder. 
3. Type `java -jar AnnanJar.jar` in commandline to run the app.
4. After the app lauched, you will see the greeting from Duke:

```____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
```

5. Use the commands to interact with Duke.

## Features 

+ All commands are case-insensitive

### Task Management

#### Types of tasks support

+ todo`[T]`: tasks without any date/time attached to it
+ deadline `[D]`: tasks that need to be done before a specific date/time
+ event `[E]`: tasks that start at a specific time and ends at a specific time

Notes: Duke supports date function, ensure the date is the format of `yyyy-MM-dd`, e.g. `2020-10-01`; otherwise the date will be treated as string only. 

#### Add Tasks

##### `todo`: add todo 

```
todo DESCRIPTION
```

Example input:

```
todo RETURN BOOK
```

Example output:

```
____________________________________________________________
Got it. I've added this task: 
[T][✘]return book
Now you have 8 tasks in the list.
____________________________________________________________
```

##### `deadline`

```
deadline DESCRIPTION /by DUE_DATE
```

Example input:

```
deadline assignment 2 /by 2020-10-06
```

Example output:

```
____________________________________________________________
Got it. I've added this task: 
[D][✘]assignment 2 (by: Oct 6 2020)
Now you have 9 tasks in the list.
____________________________________________________________
```

##### `event`

```
event DESCRITPION /at OCCURRING_DATE
```

Example input:

```
event Quiz /at 2020-10-10
```

Example output:

```
____________________________________________________________
Got it. I've added this task: 
[E][✘]Quiz (at: Oct-10-2020)
Now you have 10 tasks in the list.
____________________________________________________________
```



#### `done`: mark the task as done

```java
done INDEX_OF_TASK
```

Note: 

+ `INDEX_OF_TASK` corresponds to the output of `list` command shown below

Example input:

```
done 1
```

Example output:

```
____________________________________________________________
Nice! I've marked this task as done: 
[✓] read book
____________________________________________________________
```

#### `delete`: delete certain task

```
delete TASK_INDEX
```

Example input:

```
delete 3
```

Example output:

```
____________________________________________________________
Noted. I've removed this task: 
[E][✓]project meeting (at: wednesday, 11:30)
Now you have 9 tasks in the list.
____________________________________________________________
```



#### `list`: view the task list

```java
list
```

Example output:

```
____________________________________________________________
Here are the tasks in your list:
1.[T][✘]read book
2.[D][✘]return book     (by: tmr))))
3.[E][✓]project meeting (at: wednesday, 11:30)
4.[T][✓]join club
____________________________________________________________
```

#### `find`: find task based on keyword

```
find KEYWORD
```

Example input:

```
find assignment
```

Example output:

```
____________________________________________________________
Here are the matching tasks in your list:
8.[D][✘]assignment 2    (by: Oct 6 2020))))
____________________________________________________________
```

### Storage

The storage file containing all tasks in task list will be updated each time the user gives the input.

The user can specify the storage path in the command-line argument:

```
java -jar ip.jar TEXT_DIRECTORY
```

e.g. `java -jar ip.jar data1/test.txt`

If the file does not exist before, a new storage file will be created; otherwise, the file's task information will be loaded into the program. 

### `bye`: Exit

```java
bye
```

Expected output:

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```





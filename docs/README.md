# User Guide -- Annan's Duke Project
   

## Quick Start

1. Working Environment: Java 11.
2. Download at least the whole Duke folder. 
3. Type `java -jar AnnanJar.jar` in commandline to run the app.
4. After the app lauched, you will see the greeting from Duke:

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
```

5. Duke will automaticly load tasks stored at last exit.
6. Use the commands to interact with Duke.

## Avaliable commands: 

+ All commands are case-insensitive

### Add Tasks

#### `todo`: add todo 

```
todo TASK DESCRIPTION
```

Example input:

```
todo borrow book
```

Example output:

```
____________________________________________________________
Got it. I've added this task: 
 [T][✘] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
```

#### `deadline`

```
deadline DESCRIPTION /by DEADLINE_TIME
```

Example input:

```
deadline return book /by 2020-10-08
```

Example output:

```
____________________________________________________________
Got it. I've added this task: 
 [D][✘] return book (2020-10-08)
Now you have 2 tasks in the list.
____________________________________________________________
```

#### `event`

```
event DESCRITPION /at EVENT_TIME
```

Example input:

```
event read book /at 2020-10-08
```

Example output:

```
____________________________________________________________
Got it. I've added this task: 
 [E][✘] read book (2020-10-08)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### `list`: list all tasks

```
list
```

Example output:

```
____________________________________________________________
Here are the tasks in your list:
  1.[T][✘] borrow book
  2.[D][✘] return book (2020-10-08)
  3.[E][✘] read book (2020-10-08)
____________________________________________________________
```

#### `done`: mark a task as done

```
done INDEX_OF_TASK(same as in the list view)
```

Example input:

```
done 1
```

Example output:

```
____________________________________________________________
Nice! I've marked this task as done: 
  [T][✓] read book
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
  [E][✘] read book (2020-10-08)
Now you have 2 tasks in the list.
____________________________________________________________
```



#### `find`: find task based on keyword

```
find KEYWORD
```

Example input:

```
find return
```

Example output:

```
____________________________________________________________
Here are the matching tasks in your list:
   1.[D][✘] return book (2020-10-08)
____________________________________________________________
```

#### `save`: save tasks to a file
+ The file 'duke.txt' in data folder acts as a database for the app and the path is fixed.

```
save
```

Example output:

```
____________________________________________________________
 Tasks saved!
____________________________________________________________
```
+ If the user do not type this command, tasks will automatically be saved when exiting the app.

#### `bye`: exit the app

```
bye
```

Expected output:

```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```





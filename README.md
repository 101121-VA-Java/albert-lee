## Review notes (skipped a lot)

### WEEK 1
<details>
    <summary>Some features of Java</summary>

    - Java is a mature language
		- lot of documentation 
		- supported/ backed by Oracle

	- Highlevel programming language

	- OOP language
		- also supports other programming paradigms (ie: functional)

	- Compiled language
		- provides compile time error checking

	- Strictly typed language
		- variable needs to declared with a type

	- WORA/ Platform independence
		- via the JVM

	- Multithreaded language

	- Automatic memory management
		- Garbage collector
</details>

<details>
    <summary>JDK (Java Development Kit)</summary>

	- Contains the JVM and the JRE
	- Contains the compiler (javac) and other tools for development
	- Provide an environment to develop and execute a Java program

	- Java Runtime Environment
		- Includes core libraries of Java
		- Includes the JVM
		- ALL THAT'S NEEDED TO RUN an application

	- Java Virtual Machine
		- takes the compiled code and executes it starting from the main method

	- Compilation process in Java
		- Highlevel Java code (.java) => Compilation Step (javac) => Bytecode (intermediary language) (.class) 
		- then interpreted by the JVM
</details>

<details>
    <summary>Datatypes</summary>

    - primitive
		- boolean
			- true/false: size is jvm dependent
		- character
			- 'A': 2bytes
		- Whole numbers
			- byte: 1byte
			- short: 2bytes
			- int: 4bytes // default
			- long: 8 bytes
		- Floating points, accomodate for decimals
			- float: 4 bytes
			- double: 8 bytes // default
            
	- reference

	- Strings
		- objects
		- Arrays of characters
</details>
 
 <details>
    <summary>AWS Services</summary>

    - EC2
		- Elastic Cloud Compute
		- Virtual machine on the cloud
		- Connecting to EC2
			- ssh -i path/to/file.pem ec2-user@[public-dns]

	- AMI
		- Amazon Machine Image
		- Template that is needed to launch an instance of a virtual server
		- Includes OS
			- We're going to be using Amazon Linux 2
	- EBS
		- Elastic Block Storage
		- Block storage that is associated to EC2
		- Virtual hard drive
		- Backed up in the Availability Zone

	- Security Groups
		- Details what traffic is allowed in and out
		- Traffic filter

</details>
<hr>

### WEEK 2
<details>
	<summary>String</summary>

	- reference type, array of characters
	- String is a class, the strings that we're working with are instances of that class
	- immutable - cannot be changed, value of a string remains the same (final keyword)
	- Strings are usually stored in the String pool (in the heap)
		- where all strings instantiated literally (ie: String s = "Hello";) are stored
		- this allows Java to reuse the save String for repeated valued strings
	- String newS = new String("Hello"); //Creates the string in the heap itself instead of in the String Pool
	- String s = "Hello"; //Creates the string in the String Pool, can be reused
	- interning
		- .intern() method - checks if a String is already in the String Pool and if it exists returns a reference to it, otherwise creates that string and returns that reference
</details>

<details>
	<summary>StringBuilder/StringBuffer</summary>

	- mutable alternatives to Strings with methods that allow for manipulation
		- append, replace...
	- StringBuilder is not thread-safe - faster
	- StringBuffer is thread-safe - slower
</details>

#### 4 Pillars of OOP (A PIE)
<details>
	<summary>Abstraction</summary>
	
	- a way to hide complex implementation behind a simple interface
		- using methods without actually know how they're implememented
	- in Java, abstraction is used via the abstract and the interface keywords
		- "abstract" keyword - non access modifier
			- can be found in front of a class
				- [access mod] abstract class [ClassName] {}
				- means that the class cannot be instatiated
					- cannot create an object of the abstract class
					- like template that pushes a user to use more specific CONCRETE(non-abstract) child classes
				- abstract classes can be inherited
				- can have abstract methods
					- Concrete (non-abstract) classes cannot have abstract methods
						- ie: if an abstract method is present, the class has to be abstract
					- Abtract classes can have 0 or more abstract methods
			- can be found in front of a method
				- [accessMod] abstract [returnType] [methodName]([params]);
					- method without a body/implementation
						- method is left to be implemented later (ie: child classes/ interface implementing classes)
				- abstract methods cannot be private
			- cannot be found in front of a variable  
		- "interface" keyword
			- declares behavior(s) for classes to implement
				- contract like
				- a class can implement as many interfaces as desired
			- convention dictates that interface should end the name with "-able"
			- every method declared in an interface is going to be implicitely public abstract
				- void doSomething();
				- implicitely: public abstract void doSomething();
			- can have variables
				- variables must be public static final(cannot be changed)
					- basically a constant
					- implicit to interfaces
			- interfaces can extend one another
			- Types of interfaces
				- Marker interfaces
					- interfaces with no methods
					- used to provide information to the compiler and JVM
				- Functional interfaces
					- interfaces with 1 methods
					- can use lambda expressions
				- regular interfaces
					- any interface
			- In java 8 the "default" keyword was introduced
				- to provide default implementation to methods in interfaces in needed
</details>

<details>
	<summary>Polymorphism</summary>
	
	- refers to the ability of objects and methods to adapt their behaviors in different contexts
		- can occur at compile time or at runtime
	- method overloading
		- HAPPENS WITHIN THE SAME CLASS
		- creating multiple methods in the same class with the same method name
			 but with different number and/or type(s) of parameters
		- can change the return type as well
		- compile time/static polymorphism
	- method overriding
		- write a method in a subclass with the same method signature as in the parent class 
			but with a different implementation
		- ie: overriding the toString() method to be relevant to the subclass
		- can add @Override
		- runtime polymorphism
	- covariance return types
		- when Overriding a method, there is an option to change the return type
			- the new return type would have to be a child/subtype of the original type
	- upcasting/downcasting
		- upcasting refers to instantiating/assigin a child by declaring its parent's reference type
			- Task t = new BoringTask();
			- this restricts the available methods to the methods declared in the parent class
				however when a method is called, it calls the child's implementation
		- downcasting refers to instiating/ assigning an object by declaring its child class as a reference type
			- BoringTask bt = (BoringTask) new Task();
			- leads to issue, please avoid doing that
</details>

<details>
	<summary>Inheritance</summary>
	
	- subclasses/children classes adopting states and behaviors from a parent/super class
	- deriving classes allows for code reusability and more efficient development
		- DRY - Do not repeat yourself - principle
	- keyword "extends"
		- by default/implicitely every class extends the Object class
	- by default the first line of every constructor is a call the the parent's constructor (super())
		- super is a keyword that refers to the parent class
			- can access states and behaviors from the parent class dependending on the access modifer
		- this vs super
		- this - refers to the current instance
		- super - refers to the parent
	- Java does not support multiple inheritance
		- diamond problem
</details>

<details>
	<summary>Encapsulation</summary>
	
	- refers to the ability for an object to protect its states and its behaviors
	- can be achieved by defining accessibility of classmembers using access modifiers
		- setting variables to private
	- using setters and getters to retrieve/change the states of an object
</details>
<hr>

### WEEK 3

<details>
	<summary>Java Memory Management</summary>
	
	- Automatic memory management
	- Stack
		- Datastructure that follows a LastInFirstOut pattern (LIFO) 
		- stores primitives and reference variables
		- each thread gets its own stack
		- variables "pop off" the stack once the scope the method is over 
	- Heap
		- Object storage
			- almost anything that's not a primitive is an object
		- String pool
			- special place in the heap to store String literals for reusability
		- shared by all the threads
			- can lead to concurrency issues if not managed properly
		- managed by the garbage collector

	- Pass By Value 
		- means that passing a variable into a method uses the value itself rather than the variable passed in
			- passing a reference variable in a method passes the reference value of the location in the heap and as such changes made to the object are persisted
	- Garbage collection
		- frees up memory/resources once they are no longer referenced
		- Garbage Collector is a daemon thread
			- thread that runs in the background
			- removes objects without references in the heap
				- finalize() 
					- last method called upon an object before it is garbage collected
					- people don't really use this anymore
			- garbage collection cannot be forced, but it can be suggested
				- System.gc();
</details>

<details>
	<summary>Casting</summary>
	
	- converting a datatype to another (int to a long)
		- implicit: if the original datatype has a smaller size than the target datatype (ie: int to long)
		- explicit: if the original datatype has a larger size than the target datatype (ie: long to int)
			- int i = (int) [long];
</details>

<details>
	<summary>Wrapper classes</summary>
	
	- way to treat primitive datatypes as objects
		- Autoboxing: Automatic conversion of a primitive to its wrapper class
		- AutoUnboxing: Automatic conversion of a wrapper class into its primitive
</details>

<details>
	<summary>Collection (Interface)</summary>
	
	- is an interface that can be considered the "super" interface, or the parent interface of all collections
		- while it extends iterable, Collection declares methods availables in all collections
			- add();
			- remove();
			- contains();
			- size();
	- collections only work with objects
		- need to work with wrapper classes for primitives
	- Collection interface should not be confused with the Collections class
		- Collections WITH AN S is a class that provides utility methods to work with collections 
	- Extends iterable, what allows collections to be iterated over/ use an iterator
</details>

<details>
	<summary>List (Interface)</summary>
	
	- is an interface that extends Collection, that can hold duplicate values
	- Concrete implementations of List
		- ArrayList (C)
			- Dynamically sized array in which elements can be added and removed
			- ArrayLists leverage indexes which make them more efficient for retrieval operations
		- LinkedList (C)
			- Consists of a series of nodes which store data and a reference to the next node
			- LinkedList are better for insertion and deletion operations
</details>

<details>
	<summary>Set (Interface)</summary>
	
	- is an interface that extends Collection, does not hold duplicate values
	- Concrete implementations
		- HashSet
			- that cannot promise order (leverages hashcode)
			- leverages a HashMap in the background
		- TreeSet
			- ordered
				- by default follows "natural ordering"
					- can specify custom order using Comparator/Comparable
</details>

<details>
	<summary>Queue (Interface)</summary>
	
	- is an interface that extends Collection, generally it follows a FIFO order (First In First Out)
	- Concrete implementations
		- Priority Queues
			- Ordered by priority
		- Linked List
			- implements both List and Queue
	- Come in with special methods unique to queues
		- peek
		- poll
</details>

<details>
	<summary>Map (Interface)</summary>
	
	- is an interface that does NOT extend Collection, leverage a key value pair system
		- it does not extend iterable and there cannot be iterated the traditional way 
		- comes with its own set of methods
	- Concrete implementations
		- HashMap
			- hashcode: 
				- method that provides a hash value based on the fields of an object
					- objects that are equals should return the same hashcode
					- objects that are not equals may return the same hashcode
						- a good hashcode implementation should try to produce distinct integers for unequal objects
				- this value is stored internally to store data in a Map and to improve access operation
		- TreeMap
			- A map that is ordered 
</details>

<details>
	<summary>Comparable (Interface)</summary>
	
	- functional interface
		- compareTo method
	- implement in the model class itself
		- should return an integer that will be used to define an order
			- if integer is negative, is of lesser value
			- if integer is positive, is of greater value
			- if integer is 0, objects are of equal value
	- used to define a natural order
</details>

<details>
	<summary>Comparator (Interface)</summary>
	
	- functional interface
		- compare method
	- implement in a different class
		- Comparator for an object
	- used to define a custom order
</details>

<details>
	<summary>Multi-threading</summary>
	
	- refers to dealing with applications with concurrent processes
		- these sub-processes/ path of executions are referred to as threads
	- priority
		- defines the order in which threads can access a synchronized resource
			- resource that can only be accessed by one thread at a time
		- threads with higher priority get first dibs over other threads
			- can lead to concurrency issues
	- types of threads
		- User-defined threads
			- JVM ensures their execution to completion
				- ie: main method - main thread
		- Daemon threads
			- "optional"
			- lower in priority
			- run in the background
				- ie: gc
		- each thread gets their own stack but all threads share the same heap
</details>

<details>
	<summary>Creating and running threads</summary>
	
	- extends the Thread class
	- implements the Runnable interface
		- preferred way
	- override the run() method
		- to specify the behavior for a thread
	- instantiate a thread object and invoke the start() method on that thread
		- start() method creates a new thread and invoke the run() on it
		- otherwise if run() is called without start, it will just be executed on the main thread
</details>

<details>
	<summary>States of a thread</summary>
	
	- New
		- created a thread object, run() hasn't been called yet
	- Runnable
		- run() has been called and is executing
	- Blocked
		- waiting for another thread to be done accessing a resource on which a lock has been placed (synchronized keyword)
			- synchronized prevents more than one thread at a time to access a resource
	- Waiting
		- wait()
			- waiting for notify/notifyAll()
		- join()
			- stops the execution of a thread until another thread is done executing
	- Timed-waiting
		- sleep()
			- wait a certain amount of time
	- Terminated
		- everything has been executed
</details>

<details>
	<summary>Concurrency issues</summary>
	
	- starvation
		- lower priority threads not being able to access a resource because higher priority threads keep taking them
	- deadlock
		- occurs when the waiting process is still holding on to another resource that the first needs before it can finish.
</details>

<details>
	<summary>Lambda expressions</summary>
	
	- allows for a compact expression of instances of single method class
		- implements a functional interface without require a whole new class to do so
			- ie: Runnable, run method
	- syntax
		- [interfaceName] [objectName] = ()->[return value];
		- [interfaceName] [objectName] = ([params])->[return value];
		- [interfaceName] [objectName] = ([params])->{
			// code goes here
		  };
</details>

<details>
	<summary>Log4J2</summary>
	
	- Java Logging Framework, successor of Log4J
	- Logging consists in capturing and persisting information regarding the state of an application
		- making it available at a later time for analysis
	- Main components
		- Loggers
			- responsible for recording log events and forwarding them to the appropriate appender(s)
			- a root logger is provided by default - getRootLogger()
				- usually class specific loggers are preferred
		- Appenders
			- responsible for delivering log events to their destination targets
			- formatting of the event is delegated to a layout
		- Layouts
			- responsible for returning a byte array to be turned into a String using the appropriate layout
	- Logging levels
		- Trace
			- debug message, used to capture the flow through out the application
				- identify a specific part for debugging
		- Debug
			- debug message for a general debug event/ some information that might be helpful to debug
		- Info
			- identify general event
		- Warning
			- identify an event that might lead to an error
		- Error
			- identify error occuring during app, potentially recoverable
		- Fatal
			- identify a server error that prevents an app from continuing its execution
</details>
<hr>

### WEEK 4
<details>
	<summary>SQL Sublanguages</summary>
	
	- refer to the different types of operations to be performed on database entities
	- for the sake of this training, there will be 5 SQL sublanguages
		- DDL, DCL, DML, DQL, TCL
			- DCL - Data Control Language
				- Manage user permissions within a database
					- define power/access to tables/schema to users
				- GRANT and REVOKE
			- DDL - Data Definition Language
				- Statements that are used to construct/modify/delete  database/scheme/user/tables/properties (Database entities)
					- CREATE
					- ALTER
					- DROP
					- TRUNCATE
						- unlike DROP, just removes records from a database
</details>

<details>
	<summary>DML</summary>
	
	- Data Manipulation Language
	- statements to perform record level operations
		- to interact with the records stored in tables
	- CRUD 
		- Create/Read/Update/Delete
	- INSERT
		- create operation
		- insert into [table_name]([col1_name], [col2_name]...) values ([col1_value], [col2_value]...); 
			- mockaroo to generate mock-data
				- https://www.mockaroo.com/
	- UPDATE
		- update [table_name] set [col1_name] = [col1_value] where [condition]; 
			- if no where clause is specified, it will update every record in the table
	
	- DELETE
		- delete from [table_name] here [condition]; 
			- if no where clause is specified, it will delete every record in the table
</details>

<details>
	<summary>DQL</summary>
	
	- Data Query Language
		- "sub-sub" language/ part of DML
	- Statements used to retrieve information from tables
	- SELECT
		- main operation when querying data that can be filtered using additional clauses
		- Result set can be filtered using: (order matters)
			- where
			- group by
			- having 
			- order by
			- limit
</details>

<details>
	<summary>TCL</summary>
	
	- Transaction Control Language
		- Transaction
			- consists in combining multiple database operations into one unit of work
			- PostgreSQL treats every SQL statement as being executed within a transaction unless specified otherwise
		- Relevant statements
			- begin
				- can indicate the start of a transaction
					- if begin is not used, one can be implicitely provided
				- start of transaction block
					- statement(s) to be executed as a unit of work
					- between begin and commit
			- savepoint
				- saves the state of the database at that point within the transaction
			- rollback [savepoint]
				- reverts the state of a database to a previous commit or savepoint
			- commit
				- saves/persists the changes to a database

	- https://www.postgresql.org/docs/10/tutorial-transactions.html

	- Properties of a transaction - ACID
		- Atomicity
			- one unit, transaction should be completed as a whole or not at all
		- Consistency
			- database should be in a valid state before/after the transaction
				- valid in regards to db constraint
				- valid in regards to business logic
		- Isolation
			- one transaction should not interfere with another transaction
			- results of transactions executing concurrently should be the be the same as if they occured in sequence
		- Durability
			- Commits are final, changes should be persisted to the database
	
	- Transaction Isolation Levels
		- Read uncommited
			- able to read/interact with uncommited data
		- Read commited
			- only able to interact with commited data
		- Repeatable read
			- only see data commited before the transaction began
		- Serializable
			- strictest level of isolation
			- transactions have to occur sequentially
	- Read phenomema
		- Dirty read (can arise from read uncommited isolation level)
			- transaction is able to read data written in a concurrent uncommited transaction
		- Non repeatable read
			- a transaction re-reads data it has previously read and finds that the data has been modified by another transaction
		- Phantom read
			- transaction re-execute a query and returns a set of row to find out that the set has been modified by another transaction
</details>

<details>
	<summary>JDBC</summary>
	
	- Java Database Connectivity
		- establish a connection between a Java application and a database
			- dependency is database specific
	- Important JDBC classes/interfaces
		- DriverManager (C)
			- manage the JDBC driver
			- .getConnection()
				- provide a connection to the db
					- hardcoded (bad practice)
					- property file (prevent from pushing to GitHub using .gitignore)
					- environment variables
		- Connection (I)
			- represents the connection to the database
			- .setAutoCommit(boolean)
			- createStatement()/PreparedStatement()
			- commit()/rollback()
			- close()
		- Statement (I)
			- represents a SQL statement
			- .executeQuery(sql)
			- vulnerable to SQL injection
				- best used when no user input is required
		- PreparedStatement (I)
			- represents a SQL statement
				- that has been pre-processed and therefore prevents SQL injections
			- have to setFields for user inputs
		- ResultSet
			- result of a Query
	- SQL Injection
		- placing malicious code into a sql statement
			- ie: "select * from employees where = [userInput];
				- input could be "0; drop table employees;"
</details>

<details>
	<summary>Joins</summary>
	
	- inner join/join: return all the records that have matching values in both tables
	- left (outer) join: return all records from the left table and matched records from the right table
	- right (outer) join: return all records from the right table and matched records from the left table
	- full (outer) join: return all records when there is a match in either left or right table
	- self join: join a table with itself to combine related data from the same table

	- ie: https://www.postgresql.org/docs/10/tutorial-join.html
</details>

<details>
	<summary>Scalar and aggregate functions</summary>
	
	- scalar functions
		- used on a single input to retrieve a single output
		- length, upper, lower, round...
	- aggregate functions
		- used on a group of inputs to retrieve a single output
		- count, avg, sum, max, min
		- GROUP BY
			- used with aggregate functions
			- select [col1], [aggregateFunction]([col2]) from [table_name] group by [col1];
		- HAVING
			- used with aggregate function for more specificity
			- select [col1], [aggregateFunction]([col2]) from [table_name] group by [col1]
				having [col2] > [x];
</details>

<details>
	<summary>Set operations (union)</summary>
	
	- operations used to combine queries together
		- NOTE: different from joins which combine tables together
		- combine the rows of different result sets
	- union
		- combining select query results with no duplicates
	- union all
		- combining select query results with duplicates
			- faster because duplicates are not filtered out
	- intersect
		- returns only the common records from both data sets
	- except
		- returns a dataset where the second results are removed from the first
</details>

<details>
	<summary>Index</summary>
	
	- providing a more efficient way to retrieve records in a database 
		- an index is generated on fields set as primary key
		- BTree
			- leverages binary tree
			- better for comparison operations
		- Hash
			- leverages hashing
			- better for equality
	- makes update/insert/delete operation may take longer due to updating the index
</details>
<hr>

### WEEK 5

<details>
	<summary>JavaScript</summary>
	
	- Primitives
		- boolean
		- number
		- null
		- undefined
		- symbol -- we're not going to talk about symbol
		- string

	- Falsy values
		- null
		- underfined
		- 0
		- ''
		- false (the boolean)
		- NaN (not a number)

	- Hoisting
		- variables declared var will be hoisted to the top of their scope
			- top of global/function scope
			- functions get hoisted
		- used before they are declared
</details>

<details>
	<summary>AJAX</summary>
	
	- Asynchronous JS And XML
	- library that allows us to send HTTP requests and receive HTTP responses
		- allows for partial page reload
			- making async calls, code is still running while we wait for the HTTP response
	- 4 main Steps
		- create XMLHttpRequest object
		- setting a callback function to the ready state change of that object
		- parameterize / open the request object (set request information)
		- send the request
	- 5 ready states
		- 0: unsent - xhr object has beent created
		- 1: opened - .open() has been called
		- 2: headers received -- request has been sent, response headers have been received
		- 3: loading - body of the response is loading
		- 4: done - full response has been received and is ready for manipulation
	- onreadystate change callback function
		- checks that the ready state is 4 to make sure the the response was fully received
		- checks that the status code is 200 for success
		- define handling behavior for the response
</details>

<details>
	<summary>AWS S3</summary>
	
	- Simple Storage Service
		- Highly scalable
		- Highly durable and available
	- can programmatically access an s3 using the Amazon SDK
	- can host static webpages on S3
		- https://docs.aws.amazon.com/AmazonS3/latest/userguide/HostingWebsiteOnS3Setup.html
</details>
<hr>

### WEEK 6
<details>
	<summary>Java Bean</summary>
	
	- Special type of POJO
			- All java beans are POJOs but not POJOs are Java beans
		- should implement the Serializable interface
			- marker interface that provides information to the compiler that a class can be serialized
				- serialization: process of converting an object into a stream of byte to store that object(file, db)...
		- no-args constructor defined
		- all fields to be encapsulated
			- private
			- setters/getters
		- override Object class methods
			- toString()
			- hashcode()
			- equals()
</details>

<details>
	<summary>Javalin</summary>
	
	- lightweight framework for Java (and Kotlin) to handle HTTP requests and response
	- runs on an embedded webserver(Jetty)
		- Jetty: an open source web server and servlet container which allows a servlet application to run 
	- Built on top of Servlets
	- Set up
		- dependencies
			- Javalin
			- slf4j
			- (Object mapper, optional)
		- main method
		- Javalin app = Javalin.create()
			      = Javalin.create( config -> {})
		- app.start(portNum)
			- by default 8080
		- HTTP handlers
			- a functional interface that takes in a Context Object as a parameter, used to specify a behavior to handle a request at a particular endpoint
				- Context object is an abstraction of HttpServletRequest and HttpServletResponse
					- used to retrieve information from the request
						- url
						- headers
						- body
					- used to add information to the response
						- headers
						- body
						- status code
				- https://javalin.io/documentation#context
			- app.get("path/endpoint", Executable<Handler>)
			- app.get("path/endpoint", lambda)
			- app.get("path/endpoint", (ctx) -> { // handle request})
			- app.post("path/endpoint"...)

			.../employees 
				- Get
					- retrieve all employees
				- Post
					- add a new employee
					- include employee information in the request body
</details>

<details>
	<summary>JSON Marshalling/Object mapping</summary>
	
	- Converting Java Objects to JSON
		- mapping for data exchange
	- Jackson-databind
		- provides us with an object mapper to convert Java to JSON and vice versa
		- Jackson integrates very well with javalin
</details>

<details>
	<summary>URL - Uniform Resource Locator</summary>
	
	- Location for where a resource (on a server) is located
	- URL => protocol + domain name (host + port) + URI + parameters
	
	- URL: https://www.google.com/search?q=java+documentation&rlz=1C1GCEU_enUS924US924&oq=java+documen&aqs=chrome.0.0i433i512j69i57j0i512l5j69i60.4432j0j7&sourceid=chrome&ie=UTF-8
	
	- protocol - https://
	- domain name - www.google.com
	- URI - search
		- Uniform Resource Identifier
			- specifically determines a resource/service requested
	- query params denoted by ?, q, &, etc
		- ?param1=value1&param2=value2...
		- q=java+documentation
		- &rlz=1C1GCEU_enUS924US924
		- &oq=java+documen
		- &aqs=chrome.0.0i433i512j69i57j0i512l5j69i60.4432j0j7
		- &sourceid=chrome
		- &ie=UTF-8
	- path params embedded in url
		- resources/{path-param}/{something}
		- employees/{id}/reimbursements?param1=value1
</details>

<details>
	<summary>ORM</summary>
	
	- Object-Relational mapping
		- converting between incompatible types of objects (Java) and database tables
</details>

<details>
	<summary>Hibernate</summary>
	
	- ORM framework for Java
		- leverage annotations or xml files to sync java objects with a database
<hr>

	- Benefits
		- Abstraction of JDBC
			- uses JDBC under the hood
			- abstracts away SQL queries
				- abstracts sql dialects(ie: PostgreSQL, OracleSQL, MySQL...)
					- can reuse the same methods for different dialects
			- reduces boiler plate code 
		- Caching
			- can store query information in the server if reused
				- can reduce the number of calls to the database
		- leverages HQL
			- Hibernate Query Language
				- dialect specific to hibernate
			- if desired, native SQL can still be used (bad practice)
<hr>

	- Configuration (Class)
		- allowing to define information regarding your connection to your database/hibernate configuration to configure a session factory

		- hibernate.cfg.xml
			- config file for hibernate
				- root tag: hibernate-configuration
			- session factory tag
				- provide configuration for session factory
					- db info:
						- username/password
						- dialect - PostgreSQL
						- url
						- dialect...
					- hbm2ddl.auto
						- CREATE
							- drop all existing tables and objects with names provided in your application and recreate the schema
						- UPDATE
							- doesn't drop anything, but adds to the schema if needed
						- VALIDATE
							- makes sure that the schema is expected as defined in your application
						- NONE
							- hibernate doesn't do ddl
						- etc... 
		- Object mapping in Hibernate (xml or annotation based)
			- xml
				- create a file: EntityName.hbm.xml
					<hibernate-mapping>
						<class name="com.revature.models.ClassName" table="entities">
							<id name="entityId" column="entity_id"><generator class="increment"/></id>
							<property name="entityProperty" column="entity_property"></property>
						</class>
					</hibernate-mapping>	
				- in hibernate.cfg.xml
					- <mapping resource="EntityName.hbm.xml"></mapping>
			- annotation
				- <mapping class="com.revature.models.ClassName"></mapping>	
				- in the class, use JPA imported annotations to map a class
					- @Entity: must go at the top of the class to be mapped to a table
					- @Table: allows us to name a table to a different name than the class if desired
						- @Table(name="entities")
<hr>

	- SessionFactory (Interface)
		- Heavy resource
		- Singleton in charge of creating session object using a factory design pattern

	- Session (Interface)
		- represents database connection

	- Transaction (Interface)
		- manages ACID compliant transactions
		- retrieved from Session object
			- .beginTransaction()
				- commit();
				- rollback();

	- Query (Interface)
		- used to write more complex CRUD operations using HQL

	- Criteria (Interface)
		- used to programmatically write complicated SELECT statements
</details>

<details>
	<summary>JPA</summary>
	
	- Java Persitence API
		- interfaces meant for the mapping/managing of relational data with Java Objects
		- "concept" rather than an actual tool
			- describes which objects should be persisted and how
			- tools like Hibernate IMPLEMENT JPA
				- JPA != Hibernate
</details>

<details>
	<summary>DAO/CRUD methods (These are common interview questions, for more info checkout the documentation)</summary>
	
	- .get() vs .load()
		- used to retrieve records from the database
		- get:
			- eager fetching
			- return null when getting an id not in the db
		- load
			- lazily fetches an object
			- throw an exception if it doesn't exist in the database
	- .update() vs merge()
		- update:
			- void return
			- if trying to updat an object with no id in db, throws an exception
			- if a persistent object in the same session has the same id, throws an exception
		- merge:
			- returns the merged object
			- if a persistent object in the same session has the same id, merges with the persistent object
</details>
<hr>

### WEEK 7

#### Hibernate continued
<details>
	<summary>Object states in Hibernate (very popular interview question)</summary>
	
	- transient
		- an object is not associated with the database
			- new Object();
	- persisted
		- object associated with a session and matches what is found in the database
			- use a method to persist an object
	- detached
		- object persisted is represented in the database but is no longer associated with a session
			- session.close()
</details>

<details>
	<summary>Native queries</summary>
	
	- writing plain SQL, NativeQuery<T>
	- not best practice
		- tight coupling with a specific SQL dialect
</details>

<details>
	<summary>HQL</summary>
	
	- Hibernate Query Language
	- more OO way to query data compared to SQL
		- not dialect dependent
	- paired with Query<T>
</details>

<details>
	<summary>Named Queries</summary>
	
	- define a query to used in the model/entiry
	- could be used in a project when the architecture is not separated by layers, would be easy to find
</details>

<details>
	<summary>Criteria API</summary>
	
	- Java-based way to programmatically query data in a more OO way
		- DQL
</details>

<details>
	<summary>Caching</summary>
	
	- "storing for quicker access"
	- stores the result of a query from the dabatase to save time
		- "buffer memory" that lies between the app and the database
	- Types of caching
		- L1 - Level 1
			- session level
			- automatic and mandatory
				- if multiple updates are made onto an object within a session, hibernate will delay the actual update as long as possible in order to 				reduce the number of statement made.
			- closing the session causes that information to be lost
		- L2 - Level 2
			- SessionFactory level
				- lasts for the lifetime of an application
			- optional
				- needs to leverage 3rd party for session factory level caching
					- ie: ehcache
</details>
<hr>

### WEEK 8
<details>
	<summary>TypeScript</summary>

	- types
		- any
		- void
		- never
		- enums
		- tuple
</details>

<details>
	<summary>Angular</summary>
	
	- DI - Dependency Injection 
			- design pattern
			- relying on a framework to provide instances of dependencies rather than creating them ourselves
				- in this case, dependencies refer to objects that our code needs to run properly
			- Abstract away implementation details

	- Modules
		- groups of related components
		- at least one module
			- by default AppModule, which is the root module
				- convention, can be renamed if desired
		- @NgModule
			- decorator for modules
				- defines 4 properties
					- declarations - to declare components associated with the module
					- imports - to import additional modules
					- providers - define services associates with the module
					- bootstrap - define the root component for the module
	- Routing 
		- allows us to swap components dynamically based on the url-path
		- routing module
			- contains predefined routes
				- maps component to url paths
		- define paths to navigate to using routerLink attribute
			- ie: routerLink = ''
			- IMPORTANT:
				- do not use href attribute, or it will reload the page.
		- <router-outlet></router-outlet>
			- where the component is going to be injected

	- Directives
		- classes that add behaviors to elements in Angular application
		- angular has certain built-in directives to be leveraged
		- Component
			- html tag that refers to a component
			- <app-someComponent></app-someComponent>
		- Structural
			- directives used to interact with the DOM
				- create elements, display elements...
			- *ngIf, *ngFor...
		- Attribute
			- directives used to change the looks/behaviors of DOM elements/ components
				- can generate custom directives
					- @Directive decorator
			- ngStyle, ngClass, ngModel

	- Pipes
		- used to transform/display data in the view
		- don't actually change the data itself, only the way it's displayed
		- ie: uppercase/lowercase
			- date formatting
			- currency
		- used in conjunction with String Interpolation {{value | pipeName}}
		- custom pipes
			- ng g pipe pipename
				- @Pipe decorator which has the name of the pipe
				- implement transform()
					- specifies the pipe behavior
</details>

<details>
	<summary>Making HTTP calls in Angular</summary>
	
	- import HttpClientModule in the app.module
		- gives access to HttpClient
	- HttpClient allows us to make HttpRequests
	- returns an Observable
		- stream of data returned from an async http call
		- follow a publisher/subscriber model/design pattern
			- any entity that is subscrived to the the observable can retrieve the information
			- lazy, which means that the http call is only made when the observable is subscribed
	- .pipe - allows us to return an Observable
	- .map - allows us to "map" a JSON object to an interface (Pokemon)
	- https://angular.io/guide/http
</details>

<details>
	<summary>@Input/@Output</summary>
	
	- decorator in order to pass information between a parent and a child components
	- EventEmitter in order to emit custom asynchronously between our components
	- https://angular.io/guide/inputs-outputs
</details>

<details>
	<summary>SDLC</summary>
	
	- Software Development Life Cycle
	- Process of planning, creating, testing and displaying an information system
	- General steps/ phases:
		- Requirement phase
			- evaluate the current system and identify deficiencies
			- meetings with client/ share holders... eliciting requirements
				- done by Business Analysts(BAs)
		- Analysis phase
			- Requirements are going be to defined
				- particular defiencies in the system and a proposal is made
				- Senior Dev/Architect and BAs
		- Design phase
			- Proposed system is designed and product features are planned out
			- no coding
		- Development phase
			- Software is actually built
			- code is written
		- Testing phase
			- Software is tested to ensure desired functionality
			- testing team
		- Deployment/maintenance phase(s)
			- product is delivered to the customer
			- maintenance is kept up
</details>

<details>
	<summary>Waterfall model</summary>
	
	- traditional model, linear/sequential cycle
	- each phase must be completed before the next phase begins/ no overlapping of the phases
		- inflexible, aka, no going back up
	- Advantages
		- well fleshed out requirements/documented
		- well defined timeline, stable product definition
		- most successful with small scale projects
	- Disadvantages
		- Cannot accomodate well for stakeholder feedback
			- inflexible
		- no coding until way later in the lifecycle
		- poor model for long-ongoing project
</details>

<details>
	<summary>Agile</summary>
	
	- Approach to SDLC based on iterative and incremental development with a focus on customer satisfaction and rapid delivery of software product
		- breaking down the product into small incremental builds, provided in iterations, each iteration involving cross functional teams working simultaneously
	- a philosophy/mindset
		- https://agilemanifesto.org/
		- Individuals and interactions over processes and tools
		- Working software over comprehensive documentation
		- Customer collaboration over contract negotiation
		- Responding to change over following a plan
</details>

<details>
	<summary>Scrum</summary>
	
	- Agile framework/ implementation of Agile
	- Artifacts(documents)
		- Product Backlog
			- List of everything that is known to be needed in the product/ list of requirements /features
			- Managed by the product owner
		- Sprint Backlog
			- Set of product backlog items selected for a sprint
	- Scrum team
		- usually 5-9 members
		- Product Owner
			- responsible for the product resulting from the dev team
			- resonsible for managing the product backlog
		- Development Team
			- devs/QA 
		- Scrum master
			- Facilitator to the scrum dev team
			- clarifies questions, organize activities...
	- Ceremonies (meetings) 
		- https://www.myagilepartner.com/blog/wp-content/uploads/2018/10/sprint-scrum-1-e1538431497335.jpg
		- Sprint Planning
			- meeting to plan the work to done during a sprint
			- collaboration of the whole Scrum team
				- assign features/ story pointing(assign story points to user stories)
			- ~ 2 hours per week of the sprint
		- Daily Scrum(Daily standups) 
			- Daily meeting for the dev team
			- to check on the progress
				- what did you do yesterday?
				- what did you do today?
					- includes struggles
				- Any blockers?
					- blocker: not being able to do work due to someone else's
			- ~15 minutes
		- Sprint Review
			- Demo of the product resulting from the sprint, "working software"
			- Scrum team and stake holders collaborate on inspecting what was done and update the product backlog as required
				- maybe rework a feature
		- Sprint Retrospective
			- Scrum team reviews how the sprint went, and create a plan for improvement for the next sprint
	- Burndown chart
		- graph displaying the progress over the course of a sprint
		- Story point remaining vs time
</details>
<hr>

### WEEK 9

<details>
	<summary>Spring</summary>
	
	- Framework that leverages Inversion of Control (IoC) and Dependency Injection (DI) in order to accelerate software development	
	- in general, "Spring" refers to the Spring Framework
	- Spring modules
		- different "features" within the Spring framework, usually address a specific concern within an application
			- https://docs.spring.io/spring-framework/docs/5.0.0.M5/spring-framework-reference/html/overview.html
			- SpringCore (IoC Container)
			- SpringBeans
			- SpringContext
			- SpringWebMVC
			- SpringAOP
			- SpringORM
	- Spring projects
		- address different industry concerns(ie: Security, Microservice Architecture, Rapid development)
			- https://spring.io/projects
			- SpringSecurity
			- SpringCloud
			- SpringBoot
			- SpringData
	- IoC container
		- Spring container
		- responsible for instantiating our dependencies and providing them to our code
	- Spring Bean
		- Any Java entity that is managed by the IoC container
		- their dependencies are also managed and provided by Spring
	- Dependency Injection (DP)
		- "dependency"
			- object/entity needed for our code to work properly
		- "injection"
			- framework provides the instances of these objects for us

</details>

<details>
	<summary>IoC Container</summary>
	
	- "inversion of control" - Spring manages the lifecycle of Spring Beans
	- Once the beans and the dependencies have been specified, the IoC container manages them
		- creates the bean(we don't have to use the 'new' keyword to retrieve an instance)
		- handles dependency injection(if our object needs another Spring Bean, the IoC container will inject it for us)
	- represented by the BeanFactory(BF) and the ApplicationContext(AC)
		- leverage the Factory design pattern to create beans
		- BF is the older version of the IoC container
			- lazy instantiation
		- AC is the newer version of the IoC container
			- has all of the functionality of the BF and more
			- eager instantiation
	- Some important AC implementations
		- ClassPathXMLApplicationContext
		- AnnotationConfigApplicationContext
		- WebApplicationContext
	- Bean life cycle
		- managed by the IoC container
			- steps for the initialization/destruction of a bean
		- https://howtodoinjava.com/wp-content/uploads/Spring-bean-life-cycle.png
</details>

<details>
	<summary>Dependency injection</summary>
	
	- IoC container finds the appropriate bean requested as a dependency and injects it
	- 4 different types of DI, Spring only supports 3:
		- field injection - dependency is placed/injected directly into the field/property itself
		- setter injection - dependency is placed/injected via the setter
		- constructor injection - dependency is placed/injected using a constructor with fields
			- also used in Angular!
			- best practice
		- interface injection - not supported by Spring
</details>

<details>
	<summary>Bean definition in XML</summary>
	
	- root tag of the xml file should be "beans"
	- beans are mapped using the "bean" tag
		- example:
			- <bean name="department" class="com.revature.models.Department"></bean>
	- add a static value to a bean:
		- <property name="id" value="12"></property>
	- map to other beans
		- <property name="department" ref="department"></property>
</details>

<details>
	<summary>Bean wiring</summary>
	
	- refers to establishing dependencies between Spring beans
	- either XML or annotation based
		- @Autowired 
			- used to tell Spring that an object is a dependency
			- Dependency Injection type is defined based on where it's found
				- ie: on top of Constructor defines Constructor Injection
			- byName/Type
			- Automagically
				- let Spring figure it out
</details>

<details>
	<summary>Scopes of a bean</summary>
	
	- Singleton: only one of this bean is created, all request for this bean gets the same one
	- Prototype: everytime this bean is requested, all requests get a new one
	- Request: web-based, new bean is created per HTTP Request
	- Session: web-based, new bean is created per HTTP Session
	- Global: web-based, new bean is created per Global HTTP Session
</details>

<details>
	<summary>Stereotype Annotations</summary>
	
	- refers to annotations used for beans that the IoC should manage
	- 4 main types
		- @Component: standard, managed bean. Other annotations include this annotation implicitely
		- @Service: denotes a service
		- @Controller: denotes a controller, can also be used in conjunction to SpringWeb to handle HTTP requests/responses
		- @Repository: denotes a repository/dao, bean to retrieve data from a database can be used with SpringData
</details>

<details>
	<summary>@Bean</summary>

	- Annotation that goes over a METHOD to specify that the returned object will be managed by Spring
		- good for object that have a class that we didn't write
</details>

<details>
	<summary>Spring Boot</summary>

	- Spring Project
		- built upon different Spring modules within the Spring Framework
	- makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
	- takes an opinionated view of the Spring platform and third-party libraries. 
		- Requires need minimal Spring configuration.
	- Can create Spring boot application via the Spring initializr
		- https://start.spring.io/
	- imbedded Tomcat server
		- previously had to package Servlet Applications into a .war(web archive) to be deployed on a web server(ie: Tomcat)
			- if using legacy spring applications, would have to deploy it manually
		- now Tomcat is embedder, can just "run" the application
			- .jar packaging
	- "Convention over configuration"
		- reduces the number of configuration by assuming common conventions
			- ie: web server runs on port 8080
		- can provide custom configuration in application.yml/.properties
			- no more xml configuration
			- ie: providing db credentials/overriding convention
	- Set up
		- class with a main method
			- needs to be in the parent package/base package of the application
				- otherwise component scan may not find relevant beans
		- @SpringBootApplication
			- abstration of 3 annotations
				- @Configuration
				- @EnableAutoConfiguration
				- @ComponentScan
 		- in the main method SpringApplication.run([application-name].class, args);
	- DevTools
		- set of tools to ease application development
			- automatic server restart
			- live reloading of the ApplicationContext
			- ... more
	- Actuator
		- information about the application using certain endpoints
			- metrics(traffic.. state of app)
			- bean info
			- health checks
		- production-ready features of the application
</details>

<details>
	<summary>Spring Web MVC</summary>

	- Spring Module
		- Spring Web
	- Allows us to handle HTTP requests for Spring applications
	- abstracted away
		- leverages a FrontController design pattern
			- DispatcherServlet
		- only need to provide implementation of controller methods and mapping
			- xml configuration is handled for you
	- Controllers
		- have methods used to handle requests for a particular path/http method
		- can return
			- data (ie: JSON) 
			- views/static resources
	- Flow of a request
		- client makes a request
		- request is received by Tomcat
			- converts HTTP request to HttpServletRequest and generate HttpServletResponse
		- checks the web.xml
			- xml file used to map Servlets to endpoints
				- ie: /reimbursements go to ReimbursementServlet
			- only has ServletDispatcher(FC)
				- ie: /* meaning all requests are routed to this one servlet
		- routes to the ServletDispatcher
		- checks the HandlerMapper
			- contains information about the different controllers (ie: endpoints)
		- sent to relevant controller/view resolver if a static webpage is requested
 
	- traditionally, have to configure ServletDispatcher, ViewResolver as beans to be managed by the AC
		- SpringBoot abstracts that away from us using conventions
</details>

<details>
	<summary>MVC Annotations</summary>

	MVC Annotations
	- @Controller
		- stereotype annotation to denote a controller class to be managed by AC
		- can be leveraged alongside @RequestMapping to specify a common path for all controller methods
		- used to handle HTTP Requests
	- @RestController
		- implicitely adds @ResponseBody to all methods
			- indicates for a response body to be sent back instead of a view
			- by passes the ViewResolver
	- @RequestMapping(Mapping, HttpMethod)
		- used to specify an HTTP verb and a url mapping for a method to handle
		- by default, methods go through the ViewResolver unless @ResponseBody is specified
	- @GetMapping/@PostMapping...
		- specifying the HTTP verb for a method(@RequestMapping(for method x))
		- ways to retrieve information from a request
			- path-params(/users/{id})
				- @PathVariable
					- used to retrieve a variable from the url 
			- query-params(/users?role=[value])
				- @RequestParam
					- used to retrieve query params
			- request body
				- @RequestBody
					- used to map a JSON object to a JavaObject implicitely to be used in the method
</details>

<details>
	<summary>ResponseEntity</summary>

	- Class to provide more granular control over return HTTP response
		- ie: specify a status code
</details>

<details>
	<summary>GlobalException Handling</summary>

	- allows to handle all exception occuring server side and returning relevant status codes within a single a class
	- annotate class with @ControllerAdvice
	- annotate methods with @ExceptionHandler(exceptionName.class)
	- specify status code using @ResponseStatus([status], reason)
</details>

<details>
	<summary>RestTemplate</summary>

	- Spring's traditional way to make HTTP calls from your server
		- WebClient is more recent and async
	- why make http call from backend?
		- some APIs require API keys 
			- making front end might expose key to end user
</details>

<details>
	<summary>Spring Data JPA</summary>

	- Spring Data is a Spring project used to address data persistence
		- Spring Data JPA
			- "a module" within that project provides standard implementations for common DAO methods
				- no longer needs to implement methods
			- Built upon Spring ORM (a module of the Spring Framework) which is built upon Hibernate, which is built upon JDBC
		- Spring Data Hierarchy
			- Repository (I)
			- CrudRepository (I)
			- PagingAndSortingRepository(I)
			- JpaRepository (I)
		- Custom methods
			- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
		- use @Query annotation if trying to do custom behavior not provided by Spring Data
	- Setup
		- map models using JPA annotations
			- @Entity
			- @Id
			- @OneToMany...
		- create an interface
			- @Repository
			- extends JpaRepository<[EntityToBeManaged], [id]>
		- provide database credentials in Application.yml
</details>

<details>
	<summary>Transaction Management</summary>

	- not specific to Spring Data JPA
	- @Transactional
		- indicate that a method should be handled in the context of a Spring handled transaction
		- properties to be overriden
			- isolation
				- specify the isolation level
			- rollbackFor
				- specify when to rollback the transaction
					- ie: when an exception is thrown
			- propagation
				- how nested transactions are handled
	- @Transactional(propagation=Propagation.[value])
		- REQUIRED
			- executes in the current transaction or creates one if one doesn't exist
			- default value (?)
		- NESTED
			- executes in the current transaction, separated by a savepoint
		- REQUIRES_NEW
			- creates a new transaction
				- commit/rollback independently from others
		- MANDATORY
			- must execute in an existing transaction otherwise throws an exception
		- NEVER
			- must execute non-transactionally otherwise thows an exception if a transaction is open
		- SUPPORTED
			- will execute in the scope of a transaction if there is one, otherwise runs non-transactionally
		- NOT_SUPPORTED
			- will execute non transactionally no matter what
</details>

<details>
	<summary>Validating incoming http requests</summary>

	- bean validation
		- allows for the validation of the data received by our controllers
		- can annotate a bean's properties with constraints
			- @Length
			- @Min/Max
			- @Positive/Negative
			- @Pattern(regexp="")
		- use in conjunction with @valid to annotate the @RequestBody parameter to be validated
</details>

<details>
	<summary>Lombok</summary>

	- Code generation tool
		- annotate your class to generate code
			- @Setter
			- @Getter
			- ... 
			- @Data
				- includes setter/getter/no-args constructor/to string/ hashcode equal
	- Setting up lombok with your IDE
		- navigate to ~/.m2/repository/org/projectlombok/lombok/[lombok-version]
		- run: java -jar lombok-[version].jar
		- using installer, navigate to your ide location on your device
		- click install/update
		- restart ide
</details>

<details>
	<summary>Swagger UI</summary>

	- API documentation tool
		- integrates well with SpringBoot (before 2.6.0)
			- version matters
	- setup in pom.xml:

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>

	- setup in driver file:
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
</details>

<details>
	<summary>Set up h2 database</summary>

	- import h2 dependency
	- example application.yml
	spring:
	datasource: 
		url: jdbc:h2:mem:mydb
		username: user
		password: pass
	jpa:
		database-platform: org.hibernate.dialect.H2Dialect
		defer-datasource-initialization: true
		hibernate:
		ddl-auto: create
	h2:
		console:
		enabled: true
		
	- can access h2 console in browser
		- like dbeaver for in memory db but in browser instead
		- localhost:[port]/h2-console
			- provide url, username, password
</details>

<details>
	<summary>Spring profiles</summary>

	- Allows use to use different versions of beans in diffenrent environment
		- ie:
			- dev - for development, testing, struggling, crying
				- may just connect to an in memory database as a datasource
			- prod - for end users
				- actually connect to a production RDS
	- Setup

		- example application.yml file (this sets the profile to whatever is defined by maven):
			spring:
				profiles:
					active:
					- "@profileActive@"

		- have application-dev.yml
			- set dev environment
				- ie: information to set up h2
					- with data.sql file to insert records on start up

		- have application-prod.yml
			- set prod environment
				- ie: info about prod RDS

		- in pom.xml:
			- define the prod and dev profiles and set 'dev' as default

		- example pom.xml (relevant part shown):
			<profiles>
				<profile>
					<id>dev</id>
					<activation>
						<activeByDefault>true</activeByDefault>
					</activation>
					<properties>
						<build.profile.id>dev</build.profile.id>
						<profileActive>dev</profileActive>
					</properties>
				</profile>
				<profile>
					<id>prod</id>
					<properties>
						<build.profile.id>prod</build.profile.id>
						<profileActive>prod</profileActive>
					</properties>
				</profile>
			</profiles>
</details>

<details>
	<summary>Changing from dev to prod</summary>

	If using spring profiles, while packaging we can specify which environment to use for our artifact to be deployed with the "-Pprod" flag:

	mvn package -Dmaven.test.skip -Pprod
</details>

<details>
	<summary>Exposing Headers to an Angular application</summary>

	- use @CrossOrigin(exposedHeaders="[name of Header]")
</details>

<details>
	<summary>DTO (data transfer object)</summary>

	- objects without sensitive data to be transfered between webservices/to the front end
</details>

<details>
	<summary>Spring AOP</summary>

	- Spring module, part of the Spring Framework
	- Aspect Oriented Programming
		- used to handle Cross Cutting Concerns(CCC) == Aspect
			- Any logic not related to the business logic of the application
			- Global concerns to be kept in mind
				- ie:
					- logging
					- security
					- validation
			- Can be handled universally in order to decouple from the business logic
				- separation of concern
	- AspectJ
		- annotation based framework to work with Aspects
		- would have to enable aop in xml file if not using SpringBoot
	- Terminology
		- aspect
			- a class annotated with @Aspect to address a specific CCC
		- advice
			- an entity that modifies the code
				- code to be injected elsewhere
			- Typically a method with the aspect class
		- joinpoint
			- any code that can be modified by an advice/that can be advised
			- in the context of Spring, refers to any method in a Spring bean
		- pointcut
			- expression to target specific joinpoints
			- @Pointcut()
	- types of advices - indicates when the advice should be executed in relation to the execution of the method advised
		- @Before: before the method executes
		- @After: after the method executes
		- @AfterReturning: after successful method execution
		- @AfterThrowing: after an exception/error is thrown
		- @Around:
			- the most powerful type of advice
			- happens before the method execution and can control if and when a method should be executed
	- AOP
		- leverages AOP proxies to implement the advices in the methods's execution
			- ie: the advice are injected into the method, the proxy's methods has original method + advice and that method gets executed instead
</details>
<hr>

### WEEK 10
<details>
	<summary>Manual deployment</summary>
	
	- set up an EC2 and expose endpoints
		- set up memory and processing power
	- ssh -i [path]/[to]/[pemfile] ec2-user@[ip-address of ec2]
	
	- sudo yum update -y
	
	- sudo yum install java-1.8.0-openjdk-devel -y
	- sudo yum install git -y
	-- install maven

	- git clone [repository].git
	
	- cd into repository
	
	- mvn package
		- generates an artifact (.jar file)
	
	- java -jar [artifact-name].jar
</details>

#### Docker
<details>
	<summary>Overview</summary>
	
	- Tool revolving around the idea of containerization
		- used to develop and deploy applications using containers
	- supported on linux
		- requires Toolbox to be run on Windows
	- follows a client-server architecture
		- docker cli/daemon		
</details>

<details>
	<summary>Container</summary>
	
	- standard unit of software
	- Abstraction at the app layer that packages code and dependencies together
		- multiple containers can be ran on the same Docker engine and share the same host OS with one another
			- each container runs as an isolated process (namespaces)
			- lightweight therefore more scalable
				- runs on the host OS while a VM usually requires its own OS
				- provides a more efficient use of resources
</details>

<details>
	<summary>Terminology</summary>
	
	- Docker Client
	- CommandLine tool to interact with the Docker Daemon
	- Docker Daemon
		- background service running on top of the OS to manage/build/run Docker containers
	- Dockerfile
		- simple text file that contains a list of commands to create a docker image
			- note: a dockerfile does not have a file extension
	- Docker Image
		- template/blueprint of an application to create containers
		- include everything needed to run an application, code, runtime environment, dependencies...
	- Docker Container
		- running process, isolated from the host and from one another
		- each container will interact with its own private filesystem provided by the docker image
	- Docker Volume
		- preferred way for persisting and sharing data between Docker containers
	- Docker Hub
		- online registry and version control system for docker images
			- docker pull
			- docker push
</details>

<details>
	<summary>Dockerfile</summary>
	
	- FROM [base-image]
		- specifies an image to be built up/ usually going to be the runtime environment for an application
		- at the top of the dockerfile
	- COPY [host filesystem] [container filesystem]
		- used to copy files into the container's filesystem
			- ie: artifact to be ran
	- ENTRYPOINT
		- can be used to specify the entry point of the application
	- ADD [url]
		- add a file to be retrieved online
	- ENV
		- used to specify environment variables
	- CMD/RUN
		- used to run commands in the containers
			- sudo yum update...
	- EXPOSE
		- specifies which port to expose for the container
</details>

<details>
	<summary>Workflow</summary>
	
	- Dockerfile -> docker build -t [image-name] . (to specify where) -> Docker Image -> docker run -e [name-of-env]=[value-of-env] -p [HostPort]:[ContainerPort] -d (run in detached mode, ie: still be able to use the console) [image-name] -> Docker Container
</details>

<details>
	<summary>Basic commands</summary>
	
	- docker 
		- displays a list of available commands
	- docker images
		- displays available images
	- docker ps
		- displays running containers
	- docker build
	- docker run
	- docker stop
		- stop a container
</details>

<details>
	<summary>DevOps</summary>

	- set of practices that combines software development with IT operations
	- goal is to create "high quality code" more efficiently
		- often associated with Agile
	- building, testing, deploying, maintaining the software
	- DevOps pipelines
		- set of automated processes and tools that allow developers and operations to collaborate in building and deploying code to a production environment
</details>

<details>
	<summary>CI</summary>

	Continuous Integration
	- practice of constantly merging source code, often to a centralized location/repository
		- meant to prevent large error from accumulating
		- to make sure that people are working with the latest code base 
</details>

<details>
	<summary>CD</summary>

	Continuous Delivery
	- Having the application in a constant state to be deployed "at the push of a button"
		- but not deploying yet
</details>

<details>
	<summary>CD</summary>

	Continuous Deployment
	- once code is integrated and passes the test, sent to production environment
		- live environment
</details>

<details>
	<summary>Maven Lifecycle</summary>

	- clean - removing old artifacts, deletes the target folder
	- default
		- broken down in different phases
			- validate
			- compile
			- test
			- package <- generates an artifact(.jar)
			- verify
			- install
	- site
</details>

<details>
	<summary>Jenkins</summary>

	- Open-sourced automation server/ build automation tool
		- allows us to automate CI/CD/CD processes
	- Plugins allow Jenkins to add additional functionality
		- ie: maven, gradle, git...
	- Jenkins Job
		- project managed by Jenkins, set of repeatable steps to be automated by Jenkins
			- ie: pulling code for Github, packaging, creating image ...
			- build trigger
				- defines when the job should run/be executed
					- can do manually
					- set up webhook using GitHub
						- allows Github to notify our Jenkins server once the main branch has been updated
			- Build status
				- green: success
				- yellow: unstable
				- red: failure
			- weather forecast:
				- how often build failed lately
	- Jenkinsfile
		- text file that contains the definition of a Jenkins pipeline 
			- added to git repository
			- set of repeatable steps to be automated by Jenkins
</details>

<details>
	<summary>REST</summary>

	Archetectural constraints:
		Uniform interface
		Stateless
		Layered system
		Client–server
		Cacheable
		Code on demand (optional)

	Characteristics:
		The explicit use of HTTP to facilitate client-server communication
		The use of HTTP status codes to indicate the success or failure of request processing
		The uniform naming convention used to define the application's endpoints
		The server that processes requests does so in a stateless manner
		The use of any one of a variety of data interchange formats (JSON, XML, etc.)
</details>

<details>
	<summary>Service-Oriented Architecture (SOA)</summary>
	SOA is a style of software design where services are provided to the other components by application components, through a communication protocol over a network. The basic principles of service-oriented architecture are independent of vendors, products and technologies. A service is a discrete unit of functionality that can be accessed remotely and acted upon and updated independently, such as retrieving a credit card statement online.

	Properties:
		Services logically represents a business activity with a specified outcome
		Loosely-coupled; individual services are self-contained and not dependent upon other services
		Abstraction; a black box for its consumers
		It may consist of other underlying services.

	Priorities:
		Business value over technical strategy
		Strategic goals over project-specific benefits
		Intrinsic interoperability over custom integration
		Shared services over specific-purpose implementations
		Flexibility over optimization
		Evolutionary refinement over pursuit of initial perfection
</details>

<details>
	<summary>SOAP</summary>

	SOAP, or Simple Object Access Protocol, is a protocol designed to exchange information in the form of Web Services. It is primarily based on XML documents exchanged over HTTP, but it's possible to transmit messages through other mediums like email and JMS.

	SOAP web services are generally based on a a Web Services Description Language or WSDL, which is an XML contract that defines all the data and services offered by a given web service. The client and the server both use this contract as a basis for exchanging information and making remote procedural calls.	

	Characteristics:
		The server that processes requests does so in a stateless manner
		The explicit use of XML as the sole data interchange format
		Communication between the client and server commonly uses HTTP, though other protocols are permitted (FTP, SMTP, etc.)
		The XML messages between the client and server comply with the SOAP specification for Envelope structure
		The use of XML fault blocks (SOAP specification) to communicate request processing failures
</details>

<details>
	<summary>Sonar cloud setup</summary>

	- Create new organization/analyze new project
		- (might need admin approval from me)
	- Select repository
	- set up
	- Select GitHub Actions for Analysis method
		- follow instructions
			- GitHub Secret
			- .github/workflows/build.yml
				- select maven
					- in the build.yml file, update the branch name from master to the branch you'd like to analyze
					- on:
					    push:
					      branches:
						- [branch-name]
</details>

<details>
	<summary>Jenkins Docker Pipeline</summary>

	https://github.com/101121-VA-Java/notes/blob/main/week%2010/jenkins-docker-pipeline.txt
</details>
<hr>

### WEEK 11

<details>
	<summary>Microservice Architecture (MSA)</summary>

	- breaking down monolithic applications into smaller independent services
		- promoting decoupling
	- Monolith vs MSA
		- Monolith
			- System simplicity
			- More centralized control
			- Less Network Traffic
			- ACID transactions
		- MSA
			- More efficient resource allocation and scalability
				- scale a specific aspect of the application rather than the whole
			- modularity
				- decoupling
				- easy to upgrade/ replace
				- no longer tied to a particular tech stack
					- if all services are Restful, tech stack no longer matters
			- databases
				- MSA might have dbs for each of their services
					- multiple databases might need to be updated by a single request
					- lack of transactional operation (ACID)
						- Basic Availability, Softstate, eventual Consitency
							- BASE
			- Higher fault tolerance
				- if one instance goes down, does not break the whole application
			- Complex architecture
				- a lot more overhead
			- Chatty interfaces
				- a lot of interservice communication over a network
</details>

<details>
	<summary>Spring Cloud</summary>

	- Spring project, designed to address "issues" related to MSA
		- provides implementation for design patterns solving these issues
			- Service Discovery/Registration
			- API Gateway
			- Circuit Breaking
			- Load Balancing
			- Central Config
</details>

<details>
	<summary>API Gateway/ Gateway Service</summary>

	- acts as a "gateway" to route and filter all incoming requests
		- b/c all requests go through the gateway, ideal location to apply auth filters...
	- routing
		- sending the incoming requests to their relevant services based on the url, ie: 
			- some-url.com/quiz -> route to quiz-service
			- some-url.com/flashcard -> route to flashcard-service
		- specified in property file
	- implementations
		- Zuul - from the netflix stack
		- Spring Cloud Gateway - abstraction of Zuul
</details>

<details>
	<summary>Service Discovery</summary>

	- services communicate with one another constantly, one the challenges is to keep track of the location of those services as they are instantiated and taken down
	- Service Discovery allows for the service to locate one another using the service registry
		- list of available services and their location
		- keeps track of whether or not service registered are up and running
			- leverages a heartbeat ( sends a ping and expects ping back )
		- services within our application register to this registry
	- Implementation
		- Eureka - from Netflix
		- Consul
			- provide secure service to service communication for us
			- accessible via download or docker
				- docker run -d --name consul -p 8500:8500 consul
			- by default runs on port 8500
			- services can register to consul via the @EnableDiscoveryClient annotation aboce the main class of their application
</details>

<details>
	<summary>Load Balancing</summary>

	- spliting the requests between available instances of the services
		- Client-side load balancer (Ribbon)
			- in this case client-side means the service making the request
		- Server-side load balancer (API gateway)
</details>

<details>
	<summary>Feign Client</summary>

	- Declarative web client, makes it easier to make requests between services
	- setup
		- import openfeign dependency
		- @EnableFeignClients(above the main method class)
		- create interface with @FeignClient(name="service-name")
		- list the methods to be used with the relevant mappings
</details>

<details>
	<summary>Circuit Breaking</summary>

	- Providing methods for failing gracefully in case a service is unavailable
	- Circuit Breaker design pattern
		- Closed - works as intended
		- Open - service is unavailable, use fallback
		- Half-closed - testing requests to see if a service back up
	- Implementation
		- Resilience4j
			- imported dependency
			- define a fallback method/behavior
			- @CircuitBreaker annotation above method making request to another service
</details>

<details>
	<summary>Centralized Configuration</summary>

	- Configuration Server to store all of our services' config
		- keep track of all of the configurations in a repository (ie: GitHub)
			- easy to maintain and reuse	
	- Spring Cloud Config
</details>

<details>
	<summary>Kafka</summary>

	- Messaging service/ messaging queue
	- Design patterns:
		- Publisher/Subscriber
		- Producer/Consumer
	- when service is unavailable, messages sent to that service can be preserved in a queue to be consumed once the service is back up
	- for eventual consistency, when a record is created makes sure that all relevant services also have that record
	- Broker
		- log of messaging
		- maintain topics
			- subset of data
				- records
					- data added to the queue to be consumed
					- written into topics, ordered with an id
	- not a 1-to-1 relationship
		- can have multiple producers/consumers for the same queue
</details>

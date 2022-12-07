# PROJECT DESCRIPTION
AVL tree is a self-balancing Binary Search Tree (BST) where the difference
between heights of left and right subtrees cannot be more than one for all
nodes. In this project, I have developed and tested a small AVL Tree (i.e.
the entire tree resides in main memory). The data is given in the form (key)
with no duplicates, and implement an AVL Tree to store the key
values. The implementation should support the following operations:
1. Initialize (): create a new AVL tree
2. Insert (key)
3. Delete (key)
4. Search (key): returns the key if present in the tree else NULL
5. Search (key1, key2): returns keys that are in the range key1 ≤key ≤key2

# RUNNING THE PROJECT
### INSTALL AND RUN 
```
> git clone https://github.com/PrasannPradeepPatil/AVLTREE.git
> make
> java avltree input_file.tx
```


### INPUT FILE FORMAT (input_file.txt)
```
The first line in the input file Initialize(m) means creating an AVL Tree. Each
of the remaining lines specifies an AVL tree operation. The following is an ex-
ample of an input file:
Initialize()  
Insert(21)  
Insert(108)  
Insert(5)  
Insert(1897)  
Insert(4325)  
Delete(108)  
Search(1897)  
Insert(102)  
Insert(65)  
Delete(102)  
Delete(21)  
Insert(106)  
Insert(23)  
Search(23,99)  
Insert(32)  
Insert(220)  
Search(33)  
Search(21)  
Delete(4325)  
Search(32)  
You can use integer as the type of the key.  
```

### OUTPUT FILE FORMAT (output_file.txt)
```
1897
23,65
NULL
NULL
32

```


# CODE EXPLANATION
### Class Node  
```
This class represents each node in the AVL tree and each node has the following members:
value,height,left to represent left subtree, right to represent right subtree
```

### Class Avl
```
This class represents the class which is our actual Avl tree of which we create an object in main
function

Class Helper:
This is the subclass which has all te helper functions for the AVL tree  

private int getHeight(Node node):
returns the height of a particular node

private int getBalanceFator(Node node):
Returns the balance factor of node by subtracting height of left and right subtrees

private int previousNode(Node node):
For the node moves to extreme rightmost node

private int roteRight(Node node):
Perform LL rotation on given node

private int rotateRight(Node node):
Perform RR rotation on given node

private int balanceTree(Node node):
Balance the tree to mantain the balance factor using appropriate rotation techniques

public void initialise():
INitialises the AVL Tree

private Node insert(Node node , int value)
Insert a node with given value in AVL tree. If also rebalances the tree

public void insert(int value)
Insert the value in the AVL tree and rebalance the tree

pubic Node delete(Node node,int value)
Delete a node with given value in AVL tree. If also rebalances the tree

pubic void delete(Node node,int value)
Delete the value in AVL tree. If also rebalances the tree

public void preOrderTraversal(Node node)
Preforms preorder traversal starting from the given node

public int search(int value)
Searches value in the tree and returns if present

public List<Node> searh(int value1 , value2)
Returns a list of nodes with values lying between value1 and value2
```



### Class PerformOperation
```
This class is used to perform input and iutput operations

public String parseInput(String input)
Parses the input and extracts the values to arguements

public List<Sting> performInput(String[] args)
Takes arguments from cmd as input file and performs operation based on input files input_file.txt
and returns list of output

public void performOutput(List<Sting> output)
Returns the output to output_file.txt
```

### Class avlTree
```
Class obtaining the java main function
```
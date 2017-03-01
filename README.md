# MyMaze
Algorithms Analysis and  Data Structures Project 5


- This Java project generates a random maze puzzle which can be seen in newspapers.
- This project uses disjoint sets where every cell of the maze is a disjoint set on its own. 
- A random cell is picked and checked with its neighbors, if they do not lie in the same disjoint cell(i.e. there is a wall between the cells or they aren't connected via any other cell), then the wall is broken between them and they are then put in the same disjoint set.
- This process continues until all the cells are in the same disjoint set.
- Thus, in the end, all the cells are connected to each other directly or indirectly which increases the complexity of the maze.
- The output is also shown as a GUI output using Java Swing.

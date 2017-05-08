# Recursive-serializer
Serializes and deserializes graph of names.
Each node from a graph can be of type NodeA NodeB NodeC. 
Each node can store information differently.
Type of storing is choosed by query of settings method. Ex :
 Settings 1 2 3 - means that NodeA will store adjuscent nodes in an array;
 nodeB will store nodes in a linked list
 nodeC will store nodes in a tree set.

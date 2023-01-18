# Trie Data Structure

A trie is an efficient information retrieval data structure. Using a trie, search complexities can be brought to optimal limit (key length). If we store keys in a binary search tree, a well balanced binary search tree will need time proportional to m * log n, where m is maximum string length and n is number of keys in the tree. Using a trie, we can search the key in O(m) time. However penalty is on the trie storage requirements.

Every node of a trie consists of multiple branches. Each branch represents a possible character of keys. We need to mark the last node of every key as leaf node. A trie node field value will be used to distinguish the node as leaf node.

As an example, a words dictionary text file is attached, you can replace it with any dictionary you like :)

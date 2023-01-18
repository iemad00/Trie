
class TrieNode{

    TrieNode[] child;
    boolean isWord;
	
    TrieNode(){
        this.child = new TrieNode[26];
        this.isWord = false;       
    }
}
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Trie {
	private TrieNode root;
	private File file;
    
	public Trie() {
		root = new TrieNode();
        try {
            file = new File("Dictionary.txt");
        	Scanner fileRead = new Scanner(file);
        }
    	catch(FileNotFoundException c) {
    		System.out.println("Dictionary not found!");
    		c.printStackTrace();
    	}
	}
	
	public Trie(char[] letters) {
		root = new TrieNode();

		try {
            file = new File("Dictionary.txt");
        	Scanner fileRead = new Scanner(file);
    		Arrays.sort(letters);		
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
		        String dict_word = reader.nextLine();
		        char[] dict_letters = dict_word.toCharArray();
		        Arrays.sort(dict_letters);
				ArrayList<Character> list1 = new ArrayList<Character>();
				ArrayList<Character> list2 = new ArrayList<Character>();
				for(int i = 0; i< letters.length;i++) 
					list1.add(letters[i]);
				
				for (int i = 0; i < dict_letters.length; i++) 
					list2.add(dict_letters[i]); // add chars of the word from dictionary into ArrayList
				
        		for (int i = 0; i < dict_letters.length; i++)
					if(list1.contains(dict_letters[i])) {
						list1.remove(list1.indexOf(dict_letters[i]));
						list2.remove(list2.indexOf(dict_letters[i]));
					}
        		
        		if(list2.size() == 0) {
            		insert(dict_word);
        		}

		      }
		      reader.close();
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	public void clear() {
		for(int i = 0; i< root.child.length;i++) 
			root.child[i] = null; // make every pointer null
	}
	
	public boolean isEmpty() {
		for(int i = 0; i< root.child.length;i++) 
			if(root.child[i] != null) // if any child points to something
				return false; // then the Trie is not empty
		return true; // reaching here means the Trie is empty
	}
	
	public int size() {
		if(isEmpty())
			return 0;
		TrieNode trav = root;
		int size = 0;
        Queue<TrieNode> queue = new Queue<TrieNode>();
        if (trav != null) {
             queue.enqueue(trav);
             while (!queue.isEmpty()) {
                 trav = queue.dequeue();
                 if(isValidPath(trav) && trav!=root)                	 
                	 size++;
                 for (int i = 0; i < trav.child.length; i++)
					if(trav.child[i] != null)
						queue.enqueue(trav.child[i]);
					
             }
        }
        return size;		
	}
	
	
	public boolean isPrefix(String p) {
		TrieNode trav = root;
		p = p.toUpperCase();
		char[] letters = p.toCharArray();
		for(char letter: letters) {
			int index = (int)(letter - 'A');
			if(trav.child[index] == null)
				return false;
			else
				trav = trav.child[index];
		}
		return true;
	}


	
    public String[] allWordsPrefix(String p) {       
        TrieNode trav = root;    
        p = p.toUpperCase();
        if (!isPrefix(p)) 
        	return new String[0];
                  
        ArrayList<String> list = new ArrayList<>();
        Stack<TrieNode> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();
            
        for (int i = 0; i < p.length(); ++i) // to reach the node of p
        	trav = trav.child[p.charAt(i) - 'A'];
            
         s1.push(trav);
         s2.push(p);

         while (!s1.isEmpty() && !s2.isEmpty()) {
        	 trav = s1.pop();
             p = s2.pop();

             if (trav.isWord)
            	 list.add(p);
                
             int cnt = 0;
             for (TrieNode child: trav.child) {
                 if (child != null) {
                     s1.push(child);
                     s2.push(p + ((char) ('A' + cnt)));
                 }
                 cnt++;
             }
         }
         
         //convert the arrayList into array to return it
         String[] wordsPrefixArray = new String[list.size()];
         for(int i = 0 ; i < list.size();i++) 
        	 wordsPrefixArray[i] = list.get(i);    
         return wordsPrefixArray;
    }
    

	

	
	public boolean contains(String s) {
		TrieNode trav = root;
		s = s.toUpperCase();				
		for(int i = 0 ; i < s.length();i++) {
			if(trav.child[(int)(s.charAt(i))  - 'A'] == null) 
				return false;		
			else 
				trav = trav.child[(int)(s.charAt(i)) - 'A'];			
		}
		if(trav.isWord)
			return true;
		return false;
	}
	

	
	public void insert(String s) {
		TrieNode trav = root;
		for(char c: s.toCharArray()) {
			int index = (int)(c) - 'A'; // to make A's index 0 and Z's index 25.
			if(trav.child[index]  == null)
				trav.child[index] = new TrieNode();
			trav = trav.child[index];
		}
		if(trav.isWord == true)
			System.out.println(s+ " is already in the trie");
		else {
			System.out.printf("Done: The word (%s) inserted\n",s);
			trav.isWord = true;
			
		}
	}
	
	private boolean isValidPath(TrieNode node) {
        Queue<TrieNode> queue = new Queue<TrieNode>();
        if(node!= null) {
        	queue.enqueue(node);
    		while(!queue.isEmpty()) {
    			TrieNode trav = queue.dequeue();
    			if(trav.isWord)
    				return true;
                for (int i = 0; i < trav.child.length; i++)
					if(trav.child[i] != null)
						queue.enqueue(trav.child[i]);
    		}
        }
		return false;
	}
	
	    
	public void delete(String s) {
		TrieNode trav = root;
		s = s.toUpperCase();
		for(char c: s.toCharArray()) {
			int index = (int)(c) - 'A'; 
			if(trav.child[index] == null){
				System.out.println("WARNING: The word '"+s+"' doesn't exist!");
				return;
				}
			trav = trav.child[index];
		}
		if(trav.isWord) {
			trav.isWord = false;
			System.out.println("Done: The word " + s + " has be deleted");
		}
		else
			System.out.println("WARNING: The word '"+s+"' doesn't exist!");
		
	}
 	   
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String s) {
        return search(s, root, 0);
    }
    
    public boolean search (String word, TrieNode node, int idx)
    {
        if (idx == word.length())
            return node.isWord;
        
        if (word.charAt(idx) != '.')
        {
            if ( (node.child[word.charAt(idx) - 'A'] != null) && (search(word, node.child[word.charAt(idx) - 'A'], idx+1)) )
                return true;
            
            return false;
        }
        else // if '.'
        {
            for (TrieNode n: node.child)
            {
                if (n != null && search(word, n, idx+1))
                    return true;
            }
            return false;
        }
    }
        
    
}

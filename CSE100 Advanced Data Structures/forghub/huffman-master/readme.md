/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Q&A
 */
 
1.	Suppose we are using a method of encryption that does as much as possible 
to hide any patterns, including character frequencies. Do you believe there
is any advantage (in terms of resulting file size) to compressing a file before
encrypting it, as opposed to compressing it after encrypting it? In one sentence,
say why or why not in the form of a hypothesis.

    It should be better to compress before encrypting, as encrypting usually
    results in more unique characters, which leads to a bigger huffman trie.

2.	Test your hypothesis on the command line using warandpeace.txt, 
your compression program, and the command 
gpg --batch -c -z0 --passphrase <ANY PASSPHRASE YOU WANT> <FILENAME> . 
The -z0 flag is very important because it prevents GPG from doing its own 
compression. You can read more about GPG at https://www.gnupg.org/. 
You may use the reference compression program if there is a problem with yours.

	What file size results from compressing before encrypting with gpg?
	
	1,870,825 bytes
	
.	What file size results from compressing after encrypting with gpg?

	3,289,150 bytes
	
.	Do these results agree with your hypothesis?

	Yes, it makes sense.

3.	Suppose instead of GPG we encrypted by XORing each byte in the file against 
a single shared one byte key. This is effectively a byte substitution cypher. 
Would this compress better, worse, or the same as a file encrypted with GPG? 
In one sentence, why?

    It would compress better, as the number of unique characters would be the
    same as the original file.

4.	Would it compress better, worse, or the same as the original unencrypted 
file? In one sentence, why?

    It would compress the same, as the number of unique characters would be the
    same as the original file.

5.	Would the Shannon entropy be higher, lower, or the same as the original 
unencrypted file? In one sentence, why?

    It would be the same, as each byte exclusively XORs to its own unique byte,
    so every byte has the same frequency.
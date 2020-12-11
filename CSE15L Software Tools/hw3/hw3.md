
# Homework 3 (Due October 23, 11:59pm)

Name: Tracker Wonderdog  
PID: AXXXXXXXX  
Account: cs15xzz  

_Don't forget to change the file header!_

#### In this homework assignment, you will assume the role as TA for CSE 15L, who's in the middle of preparing the next homework for your students.

1.  You have all the questions written in a text file named `hw3.md`. You’re
confident that it's somewhere inside your `~/Documents/` directory, but you
can't seem to remember which subdirectory it's in. Assuming that you are
currently inside `~/Documents/`, write a command to `find` the path to
`hw3.md`. **(1 point)**

        find .-iname hw3.md

2.  Using this command, you have successfully located your `hw3.md` file at
`~/Documents/CSE15L/homework/hw3/hw3.md`. You want to do a quick sanity check
to make sure that `hw3.md` is indeed ready to be released. What is a command
that allows you to scroll through the content of a file without opening it in
an editor or touching your mouse/touchpad? **(1 point)**

        cat hw3.md

3.  Everything looks good. Now, you need to find a way to transfer that file to
the ieng6 server so that students can have access. You are in
`~/Documents/CSE15L/homework/hw3/` (where `hw3.md` is located) on your
personal laptop, and your cs15x account is `cs15x0`. What is the command to
copy `hw3.md` to your home directory on `ieng6.ucsd.edu`? **(1 point)**

        scp -r ./hw3/cs15x0@ieng6.ucsd.edu:~/

4.  You copied `hw3.md` into the public directory, and announced on Piazza
that Homework 3 is available. An hour later, students responded that they are
having "permission denied" issues. "Not this again," you sighed to yourself as
you navigated to the public `hw3.md` to find out what's causing the problem.
What is a command that shows you all permissions associated with ONLY the file
named `hw3.md`? **(1 point)**

        ls -l hw3.md
5.  The command above shows the permission string for `hw3.md` to be
`-rwxr----x`. What is the corresponding octal code for this permission string?
**(1 point)**

        741

6.  So this is why students can't start early, let alone start often -- they
are not given "read" permission to `hw3.md`, which they need in order to copy
it to their own directory so that they can start writing their answers. You
need to grant students "read" permissions, but at the same time, you will want
all of the other permissions to remain unchanged. What is the command that will
give "other" permission to read `hw3.md` without modifying anything else?
**(1 point)**

        chmod 747 hw3.md

7.  When students turn in their homework, an automatic script is run that
archives the entire `hw3` directory and sends it to your account for grading.
For a student whose username is cs15xzz, knowing that their assignment is
archived with the following command from ~/homework directory:
`tar -czf cs15xzz.tar hw3`. What is a command that will un-archive the file to
retrieve the hw3 directory? **(1 point)**
 
        gunzip -r cs15xzz.tar

## Turnin Procedure:
Be sure your `hw3.md` file is saved on your cs15xzz account in the directory
`~/homeworks/hw3`. When logged into your cs15xzz account, enter the following
commands:
```
$ cd ~/homeworks/hw3
$ turnin
```
Follow the Autograder prompts, using your UC San Diego email login credentials,
and you should be good to go! Make sure you get the confirmation email from
Autograder. Additionally, you can log in to Autograder to verify your submission.

## Feedback Time:
Feel free to leave any comment regarding this week's lectures, lab, and this
homework assignment. What did you find most interesting? Are there any
frustrations? What can we do better?

# Homework 5 (Git)

Name: Christopher Yeh	 
PID: A15720503 
Account: cs15xzz  

For all the questions below, assume that NO ALIASES have been configured
for Git. 

1. After taking CSE 15L, you landed yourself on an internship at Pied-Piper's
core algorithm team. Your job as a tester is to ensure that the algorithm is
correct. The first thing you'll need to do in order to start is to get access
to the code base. Since it's the first day of your work, you will need to tell
Git about  yourself before you can start contributing. What are two Git
commands that will set your name and email globally for your account?
**(1 point)**

        git config --global user.name Chris
		git config --global user.email myemail@ucsd.edu

2. If the source code for Pied-Piper's Middle-Out algorithm is located at  
`https://git.piedpiper.com/algorithm/middle_out.git`, what is a Git command
that will obtain a local copy of the full repository? **(1 point)**

        git clone https://git.piedpiper.com/algorithm/middle_out.git

3. After you've navigated to the project directory, what is a command that will
show you the history of all commits to the repository, assuming no aliases have
be configured for Git? **(1 point)**

        git log --all

4. Turns out you do have some personalized preferences how you use Git. What is
the file you can edit in order to set global configurations for Git to your
likings? **(1 point)**

        .gitconfig

5. To start on your own testing project, you want to make sure that the changes
you make will not affect others, and therefore it is good practice to be
working on a new branch altogether. What is a command (or commands) in Git that
will allow you to switch to a new branch named 'testing'? **(1 point)**

        git checkout -b testing

6. The tests are written in Java, and therefore will generate many `.class`
files upon compilation. In order to keep the repository clean of files you
don't wish to keep under version control, you want to tell Git not to include
any `.class` files into your repository. 

Which file tells Git the file patterns such that Git will not monitor any 
changes to those files?
**(1 point)**

        Makefile

7. Assuming that the said file is empty, what modifications do you need to make
in order for Git to ignore any '.class' file? **(1 point)**

		task:
			@rm ./middle_out/*.class
		OR command:
        git rm -r --cached *.class

8. You've written your very first test named 'ShouldSortByD2F.java', what are
the Git commands that will reflect your changes in the `testing` branch
in the REMOTE repository named 'origin'? **(1 points)**
        
		git add ShouldSortByD2F.java
		git commit -m "First Test"
		git push origin testing

9. Satisfied with what you did on the first day, you turned off your computer
and went back home. Meanwhile, Gary, another member on your team, pushed some
changes to the 'core' branch in the remote repository, which include new
classes to be tested. 

What is a single Git command that will keep your current branch up-to-date with 
the changes made to the 'core' branch on the remote repository? 
**(1 point)**
		
		git merge core testing
        # OR IF TRYING STRICTLY PULL CHANGES
		git pull origin core

10. The previous command is effectively the same as sequentially executing
which two Git commands? **(1 points)**

        git checkout test
		git merge core

11. We made some changes to fix a bug for `ShouldSortByD2F.java` and we now want 
    to commit them. Hence, we enter:

    ```
    git add ShouldSortByD2F.java
    git commit -m “fixed a bug with ShouldSortByD2F.java”
    ```

    Uh oh! We now realize that the fix was not a fix after all! It actually
    broke the program. We should have tested it before committing.
    However, you also know there is a git command to UNDO this commit. 
    
    What is a single git command to undo the commit above? **(1 point)**

        git revert HEAD

12. You have now decided to work on your own new project. You are currently in 
    your home directory, and you type

    ```
    mkdir my_new_project
    cd my_new_project
    ```
    You decide you want to use git for version control on your project, what is 
    the command to initialize your current directory into a new git repository?

        git init

13. You decide you want to host your repository on Github. You create a new 
    repository on Github to house your local repository. Github gives you a remote 
    URL of `https://github.com/Gary/my_cool_project.git`. 
    
    What is a command to link your local repository with your new remote 
    repository? Name this remote repository `my_new_remote`.

        git remote add my_new_remote https://github.com/Gary/my_cool_project.git
        

14. You realize the name `my_new_remote` is a strange name for a remote repo, 
    and you decide you want to rename it to `origin`. 
    
    What is a single command to rename your remote from "my_new_remote" to 
    `origin`?
        
        git remote set-url origin https://github.com/Gary/my_cool_project.git

15. You have created a `README.md` for your project and decide you want to add 
    it to your remote repository, first we must add the file to the staging area. 

    What is a command to add the file `README.md` to the staging area?
        
        git add README.md

16. Excellent! You decide you are ready to make your first commit, what is a 
    single command to make a commit with the commit message of "added a readme"?

        git commit -m "added a readme"


17. Lastly, you are satisfied with your changes and you decide you want to push 
    to the remote repository. Recall the name of our remote was origin. 
    The branch we want to push to is the master branch. 
    
    What is one command to push your commit to the master branch of your 
    remote repository named `origin`?

        git push origin master


18. We now want to tag the current commit as `final` so that we can refer to it
    easily in the future. 

    What is one command that will set the tag for the current commit as `final`?
    
        git tag final


19. What is one command that will list all the available tags for the current
    git repository?

        git tag


20. Which directory stores all the data necessary for git to keep track of your
    project and its history?
    
    A) .gitignore  
    B) .git  
    C) .vimrc  
    D) .gitlogs  
    E) .gitrc  

        B

## Turnin Procedure:
Be sure your `hw5.md` file is saved on your cs15xzz account in the directory
`~/homeworks/hw5`. When logged into your cs15xzz account, enter the following
commands:
```
$ cd ~/homework/hw5
$ turnin
```
Follow the Autograder prompts, using your UC San Diego email login credentials,
and you should be good to go! Make sure you get the confirmation email from
Autograder. Additionally, you can log in to Autograder to verify your submission.

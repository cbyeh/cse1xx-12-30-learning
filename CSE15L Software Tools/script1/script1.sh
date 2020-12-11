#!/bin/bash
#
# CSE 15L - Fall 2018
# Scripting Project 1 (Due: November 23, 11:59pm)
#
# Name: Christopher Yeh 
# PID:   A15720503
# Login: cs15xii
#
# Welcome to your first shell scripting practice assignment. Here, you will
# put what you learned about bash scripts from recent labs into practice.
#
# The first assignment involves knowledge regarding positional parameters,
# loops, and conditions. Use Lab 6 as your reference whenever you run into any
# problems, and feel free to ask on Piazza if you get confused at any point!
#
# In this assignment, you are trying to test whether a hypothetical homework
# solution is implemented correctly. You suspect that your solution has some
# non-determinstic behavior, which makes it difficult to see whether your
# solution is correct. Therefore, you wish to stress-test your program. You'll
# do this by running your solution against the reference solution side by side
# outputting the number of times the results differ.
#
# Here's an example of how you should call your script:
#
# $ ./script1.sh my_solution ref_solution num_tests
#
# Notice that your script takes in 3 command line arguments: the path to your
# solution, the path to a reference solution, and the number of tests you want
# to run on your solution. Both solution files are found in the same
# directory as the script. You can execute them using `./solution_file`.
#
# The output of the script should be a single number, representing the number
# of times your output is different than the solution output.
#
# When writing your script, you may assume in your script that the arguments
# are always passed in correctly, i.e. the paths to both files are always valid
# and the third argument is always a positive integer value.
#
# Below are a couple test cases you should try out:
#
# $ ./script1.sh my_solution ref_solution 100
#       Here, you'd expect the a random number from [0, 100]
# $ ./script1.sh bad_solution ref_solution 100
#       Here, you'd expect the number to be 100 (output is always incorrect!)
# $ ./script1.sh ref_solution ref_solution 100
#       Here, you'd expect the number to be 0 (output is always correct!)
#
# That's it. Write your solution below and good luck!

count=0
for i in $( eval echo {1..$3} ); do
  var1="$(./$1)"
  var2="$(./$2)"
  #compare=`diff $var1 $var2`
  if [ "$var1" != "$var2" ]; then
    count=$((count+1))
  fi
done
echo $count

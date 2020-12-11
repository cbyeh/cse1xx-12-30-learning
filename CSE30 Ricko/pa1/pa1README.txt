DESCRIPTION
-------------------------------------------------------------------------------
    PA1 is a program that draws an atomic pinwheel as ascii text. 
        There are up to three paramteters that may be used.
        Invalid inputs are rejected.


HOW TO COMPILE
-------------------------------------------------------------------------------
    Use the command: `make pa1E`


HOW TO RUN
-------------------------------------------------------------------------------
    Use the command: `./pa1 (even int in bounds) (upward char) (downward char)



NORMAL OUTPUT
-------------------------------------------------------------------------------
    Normal output is printed to screen.
    Example of normal output:

$ ./pa1 8 # ^

   @-------@
  @@@-----@@@
 @@@@@---@@@@@
@@@@@@@-@@@@@@@
-------@-------
 -----@@@-----
  ---@@@@@---
   -@@@@@@@-


ABNORMAL OUTPUT
-------------------------------------------------------------------------------
    Abnormal output is printed stderr.
    Example of abnormal output:

$ ./pa1
 Usage: ./pa1 size [upward_triangle_char [downward_triangle_char]]
  size: equal to the number of rows in the Radioactive Pinwheel
    -- must be an even integer in the bounds [4, 50]
  upward_triangle_char: used for drawing the upward triangles
    -- must be a single character within the ASCII bounds [32, 126]
    -- must be different than downward triangle char
    -- enter nothing to use default character (@)
  downward_triangle_char: used for drawing the downward triangles
    -- must be a single character within the ASCII bounds [32, 126]
    -- must be different than upward triangle char
    -- enter nothing to use default character (-)

$ ./pa1 7770
 Error: Radioactive Pinwheel size must be within the bounds [4, 50]


HOW I TESTED
-------------------------------------------------------------------------------
I used mostly vdb, looking at code line by line to debug.
Usually, I did not find any bugs in most files created.


QUESTIONS
-------------------------------------------------------------------------------
1.  Line 12 has a space in front of unified
    Line 41 has a block space in sum3V3.s
    Line 54 there is an empty line in sum3v1.s

2.  *x = 3;

3.  5

4.  dd, dn

5.  yy, p

6.  :set number, :set nonumber

7.  x, gg, G

8.  10j, 10k

9.  git diff HEAD

10. print nptr

11. p/x endptr

12. print size, p $r0

13. print *endptr

14. print endptr

15. p/d errno

16. Using only the allowed resources! Also, never sharing code.
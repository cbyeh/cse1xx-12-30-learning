P: 8 4 2 1 0
D: 11 : 1

TX:

P8 = ^{D...};
P4 = ^{D...};
P2 = ^{D...};
P1 = ^{D...};
P0 = ^{P8,P4,P2,P1,D[11:1]};  P0 at TX  

channel:

Q4 = !P4


RX:

P8,Q4,P2,P1,P0, D[11:1];


S8 = P8
S4 = !P4    -- know we have bad P4
S2 = P2
S1 = P1

S0 = ^{P8,Q4,P2,P1,D[11:1]};
P0 =  {P8,P4,P2,P1,D[11:1]};

S0 = !P0   -- one error

1) {P8, P4, P2, P1} == {S8, S4, S2, S1} & P0 == S0 --  no error	          (16'b00000DDD_DDDDDDDD);
2) {P8, P4, P2, P1} != {S8, S4, S2, S1} & P0 == S0 --  two error          (16'b10000xxx_xxxxxxxx);  
3) {P8, P4, P2, P1} == {S8, S4, S2, S1} & P0 != S0 --  bad P0  (ignore)	  (16'b01000DDD_DDDDDDDD);
4) {P8, P4, P2, P1} != {S8, S4, S2, S1} & P0 != S0 --  one error (fix it) (16'b01000EEE_EEEEEEEE);
 
 EEEEEEEEEEE = repaired version of DDDDDDDDDDD




Program 3:  Where's Waldo?

pattern: 5 bits long, buried in an 8-bit byte     PPPPP000   

string: 32 bytes long = 256 bits

		S[  7  6  5  4  3  2  1  0]
		S[ 15 14 13	12 11 10 09	08]
		S[ 23 22 21 20 19 18 17 16]
		    ...
 
 3 event counters

P = 11111

Prog 3.1 How many bytes contain at least 1 (overlapping) copy of the message?

          1 1 1 1 1 1 1 1		   +1
		  0 0 0 0 0 0 0 0		   +0
          0 1 1 1 1 1 0 0		   +1
		  0 1 1 1 1 1 1 0		   +1
                                             score = 3	  / 	4
Total score = total number of bytes containing pattern


          0 1 1 1 1 1 1 0          
		  1 1 1 1 1        

		  1 0 0 0 0            != 0
		  
          0 1 1 1 1 1 1 0          
  		    1 1 1 1 1        

			0 0 0 0 0         = 0	      +1



Prog 3.2) How may occurrences total, contained within byte boudaries?


          1 1 1 1 1 1 1 1		   +4
		  0 0 0 0 0 0 0 0		   +0
          0 1 1 1 1 1 0 0		   +1
		  0 1 1 1 1 1 1 0		   +2
									    +7 / 16



    1 0 1 0 1 0 1 0		  10101
    0 1 0 1 0 1 0 1
    1 0 1 0 1 0 1 0
    0 1 0 1 0 1 0 1
 

Prog 3.3) How many total occurrences including across byte boundaries?


		S[  7  6  5  4  3  2  1  0]
		S[ 15 14 13	12 11 10 09	08]
		S[ 23 22 21 20 19 18 17 16]


    1 0 1 0 1 0 1 0		                    10101
    0 1 0 1 0 1 0 1
    1 0 1 0 1 0 1 0

    0 1 0 1 0 1 0 1

    0 1 0 1 0 1 0 1
    0 1 0 1 0 1 0 1
    0 1 0 1 0 1 0 1
    0 1 0 1 0 1 0 1


                                 11001


     1 1 0 0 1 1 0 0		  1       		1
	 1 1 0 0 1 1 0 0		  2				1
	 1 1 1 0 0 1 1 0		  1				1
	 0 1 0 0 0 0 0 0		  0				0


      0 0 0 0 1 1 0 0		  1       		1
	  0 0 0 0 1 1 0 0		  2				1
	  0 1 1 0 1 1 1 0		  1				1
	  0 0 0 0 0 1 0 0		  0				0









		S[ 23 22 21 20 19 18 17 16]
		S[ 15 14 13	12 11 10 09	08]
		S[  7  6  5  4  3  2  1  0]

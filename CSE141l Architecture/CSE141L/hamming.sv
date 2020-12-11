0   0   0   0   0   d11 d10 d09
d08	d07 d06 d05 d04	b03	d02	d01

Program 1
d11 d10 d09	d08	d07 d06 d05 p08
d04 d03 d02 p04 d01 p02 p01 p00

Program 2
d11 d10 d09	d08	d07 d06 d05 s08
d04 d03 d02 s04 d01 s02 s01 s00

s8 = ^d[11:5]  
s4 = (^d[11:8])^(^d[4:2]); 
s2 = d[11]^d[10]^d[7]^d[6]^d[4]^d[3]^d[1];
s1 = d[11]^d[ 9]^d[7]^d[5]^d[4]^d[2]^d[1];
s0 = ^d[11:0]^p[8]^p[4]^p[2]^p[1]

{s8,s4,s2,s1} ^ {p8,p4,p1,p1} 

		   0111

module program_counter(
  input clk,
//  input jump_abs,
  input jump_rel,
  input reset,
  input        [15:0]target,   // from LUT 
  output logic [15:0] PC);

  always @(posedge clk) begin
  	if(reset)
	  PC <= 0;
//    else if(jump_abs)
//	  PC <= target;
	else if(jump_rel)
	  PC <= PC + target; 	 // 16'hffff  -1  
    else
	  PC <= PC + 1; 
  end  

endmodule


 // LUT 

module LUT (
  input       [ 1:0] addr,
  output logic[15:0] data);

  logic[15:0] core[4];

  always_comb case(addr)
    00: data = 16'hff00;
	01: data = 16'h8000;
	02: data = 16'h0020;
	03:	data = 16'h0100;
  endcase


endmodule




c = a + b;

add $c $a $b

b = a + b;

add $b $a $b

add $b $b+1 $b

acc = b + acc

add $r0 $rx $r0






^vec[7:0] 

v7 v6 v5 v4 v3 v2 v1 v0
0  0   0  0 v7 v6 v5 v4


			v7^v3 v6^v2 v5^v1 v4^v0
						v7^v3 v6^v2
// CSE140L  Fall 2019
// generate all possible parity combinations
// for Hamming 11:16 error correction coding
module lab4_tb();

logic[11:1] d1_in;              // original messages
logic       p0, p8, p4, p2, p1; // Hamming block parity bits
logic[15:0] d1_out;             // orig messages w/ parity inserted
integer f1;

initial begin
  f1 = $fopen("hamming_demo.txt");
  for(int i=0; i<2048; i++) begin
	d1_in = i; 
    p8 = ^d1_in[11:5];
    p4 = (^d1_in[11:8])^(^d1_in[4:2]); 
    p2 = d1_in[11]^d1_in[10]^d1_in[7]^d1_in[6]^d1_in[4]^d1_in[3]^d1_in[1];
    p1 = d1_in[11]^d1_in[ 9]^d1_in[7]^d1_in[5]^d1_in[4]^d1_in[2]^d1_in[1];
    p0 = ^d1_in^p8^p4^p2^p1;  // overall parity (0th bit)
    d1_out = {{d1_in[11:5],p8,d1_in[4:2],p4,d1_in[1],p2,p1,p0}};
	$fdisplay(f1,"in = %b, out = %b",d1_in,d1_out);
    #10ns;
  end
  $fclose(f1);
end
endmodule
										   
// input
//  0 0 0 0 0 b11 b10 b9 
// b8 b7 b6 b5 b4 b3 b2 b1 

// output
//  d1_in[11:5],p8,d1_in[4:2],p4,d1_in[1],p2,p1,p0;
//	b11 b10 b9 b8 b7 b6 b5 p8
//	b4  b3 b2 p4 b1 p2 p1 p0


// ^w[3:0]
//  w[3]^w[2]^w[1]^w[0]


//  w[3:0] ^ y[3:0]
//  XOR bitwise
//  z[3:0] = w[3:0] ^ y[3:0]
//  z[3] = w[3] ^ y[3]
//  z[2] = w[2] ^ y[2]
//  ...
//  z[o] = w[0] ^ y[0]

//  w[3]   w[2]    w[1]         w[0]
//    0     0      w[3]         w[2]
// bitwise xor
//  w[3]  w[2]  w[3]^w[1]   w[2]^w[0]

//  w[3] w[2]   w[3]^w[1]   w[2]^w[0]
//    0  w[3]    w[2]       w[3]^w[1]   
// bitwise xor

//  w[3] w[3]^w[2]  ^321	^w[3:0]
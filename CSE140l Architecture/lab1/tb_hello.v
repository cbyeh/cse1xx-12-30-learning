//
// tb_hello
// Lab1 test bench
// CSE140L Spring 2019
//
// Starter code
//   Bryan Chin
//   Lih-Feng Tsaur
//
// Author: Christopher Yeh
//

`timescale 1ns/1ns
   
//
// Lab1 testbench
// runs some basic tests on Lab1_hello
//
module tb_hello;

   wire L1_andOut;
	wire L1_orOut;
	wire L1_nandOut;
	wire L1_norOut;
	wire L1_notOut_a;
   reg  tb_a;
   reg  tb_b;
   reg  tb_c;
   
	// 000 -> 001 -> 010 -> 011 -> 100 -> 101 -> 110 -> 111
   initial begin
      tb_a <= 0;
      tb_b <= 0;
      tb_c <= 0;
      #20;		
      tb_a <= 0;
      tb_b <= 0;
      tb_c <= 1;
      #20;		
      tb_a <= 0;
      tb_b <= 1;
      tb_c <= 0;
      #20;		
      tb_a <= 0;
      tb_b <= 1;
      tb_c <= 1;
      #20;		
      tb_a <= 1;
      tb_b <= 0;
      tb_c <= 0;
      #20;		
      tb_a <= 1;
      tb_b <= 0;
      tb_c <= 1;
      #20;		
      tb_a <= 1;
      tb_b <= 1;
      tb_c <= 0;
      #20;		
      tb_a <= 1;
      tb_b <= 1;
      tb_c <= 1;
      #20;	
      $finish;
   end

   initial begin
      $display("Hello There Christopher Yeh");
   end

   initial begin
		#160; // Added so GoodBye is after Hello
      $display("GoodBye!!!");
   end
   
   // Our boolean operations from Lab1_hello.v
   Lab1_hello Lab1_hello(.tb_a(tb_a), .tb_b(tb_b), .tb_c(tb_c), .L1_andOut(L1_andOut), .L1_orOut(L1_orOut), .L1_nandOut(L1_nandOut), .L1_norOut(L1_norOut), .L1_notOut_a(L1_notOut_a));

endmodule
      
      
      

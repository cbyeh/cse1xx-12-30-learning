// --------------------------------------------------------------------
// >>>>>>>>>>>>>>>>>>>>>>>>> COPYRIGHT NOTICE <<<<<<<<<<<<<<<<<<<<<<<<<
// --------------------------------------------------------------------
// Copyright (c) 2019 by UCSD CSE 140L
// --------------------------------------------------------------------
//
// Permission:
//
//   This code for use in UCSD CSE 140L.
//   It is synthesisable for Lattice iCEstick 40HX.  
//
// Disclaimer:
//
//   This Verilog source code is intended as a design reference
//   which illustrates how these types of functions can be implemented.
//   It is the user's responsibility to verify their design for
//   consistency and functionality through the use of formal
//   verification methods.  
//
// -------------------------------------------------------------------- //           
//                     Lih-Feng Tsaur
//                     Bryan Chin
//                     UCSD CSE Department
//                     9500 Gilman Dr, La Jolla, CA 92093
//                     U.S.A
//
// --------------------------------------------------------------------
// -----------------------------------
//
// reset generator
//    
// when esc key or space is detected, generates a reset signal for 16 cycles
//
//
//
module resetGen(
         output reg  rst, // global reset		 
		 input 	     bu_rx_data_rdy, // data from uart rdy
		 input [7:0] bu_rx_data,     // data from uart
		 input       tb_sim_rst,     // simulation reset
		 input 	     clk
		 );
   
   
   reg [5-1:0] 		 reset_count;
   wire 		     escKey = bu_rx_data_rdy & (bu_rx_data == 8'h1b);
   
   wire [5-1:0] reset_count_next;
   defparam uu0.N = 5;
   N_bit_counter uu0(
    .result (reset_count_next[5-1:0])     // Output
   ,.r1 (reset_count[5-1:0])              // input
   ,.up (1'b1)
   );
   
   always @(posedge clk) begin
      rst <= ~reset_count[4];
      reset_count <= (tb_sim_rst | escKey)? 5'b00000 :
	             (reset_count[4])? reset_count: reset_count_next;
   end // always @ (posedge clk_in)
endmodule


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
//
//
// delay 16 clks
//
//
module delay16clks(
		  output reg 	   rdy,
		  input wire       go,
		  input wire 	   rst,
		  input wire 	   clk);
   parameter WIDTH = 8;
   reg  [WIDTH-1:0] count;
   wire [WIDTH-1:0] count_next;   
   defparam uu0.N = WIDTH;
   N_bit_counter uu0(
   .result (count_next[WIDTH-1:0])       , // Output
   .r1 (count[WIDTH-1:0])                  , // input
   .up (1'b1)
   );
   
   
   always @(posedge clk) begin
    if (rst)
	   count <= {WIDTH{1'b0}};
    else begin
	if (go)
	   count <= count_next;
	else if(|count)
	   count <= count_next;
    else 
       count <= {WIDTH{1'b0}};
    end
    rdy <= &count;
   end
   
endmodule // dispString

      
	
      

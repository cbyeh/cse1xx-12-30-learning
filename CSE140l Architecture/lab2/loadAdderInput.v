
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
// load adder inputs
//
//
module loadAdderInput(
		  input wire 	rst,             // reset signal (active high)
		  input wire 	clk,             // global clock
		  input 	    data_in_rdy,     // data from the uart ready
		  input  [7:0] 	data_in,         // data from the uart
		  output [7:0]	data_out1,
		  output [7:0]	data_out2,
		  output [7:0]	data_out3,
		  output [7:0]	data_out4
		  );

    reg  [3:0] shif_reg;
	
    reg  [3:0] data_reg;
    regrce #(8) get_input0 (data_out1, data_in, data_in_rdy & shif_reg[0], rst, clk);
    regrce #(8) get_input1 (data_out2, data_in, data_in_rdy & shif_reg[1], rst, clk);
    regrce #(8) get_input2 (data_out3, data_in, data_in_rdy & shif_reg[2], rst, clk);
    regrce #(8) get_input3 (data_out4, data_in, data_in_rdy & shif_reg[3], rst, clk);

   
    always @(posedge clk) begin
        if (rst)
	        shif_reg <= 4'b0001;
        else begin
	    if (data_in_rdy)
	       shif_reg <= {shif_reg[2:0], 1'b0};
	    else if(|shif_reg)
	       shif_reg <= shif_reg;
        else 
           shif_reg <= 4'b0001;
        end

    end
   
endmodule // dispString

      
	
      

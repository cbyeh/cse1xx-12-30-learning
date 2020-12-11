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
//                     UCSD CSE Department
//                     9500 Gilman Dr, La Jolla, CA 92093
//                     U.S.A
//
// --------------------------------------------------------------------

module Lab2_140L (
		  input wire 	    rst,             // reset signal (active high)
		  input wire 	    clk,             // global clock
		  input wire        oneSecStrb,  	 // not used in Lab-2    	  
		  input 	        bu_rx_data_rdy,  // data from the uart ready
		  input [7:0] 	    bu_rx_data,      // data from the uart
		  output wire 	    L3_tx_data_rdy,  // data rdy to display
		  output wire [7:0] L3_tx_data,      // data to display
		  output [4:0] 	    L3_led,          //5 LED control - 1: on, 0, off
		  output reg [6:0] 	L3_segment1,     //not used in Lab-2
		  output reg [6:0] 	L3_segment2,     //not used in Lab-2
		  output reg [6:0] 	L3_segment3,     //not used in Lab-2
		  output reg [6:0] 	L3_segment4,     //not used in Lab-2

		  output [3:0] 	    di_Mtens,        //not used in Lab-2
		  output [3:0] 	    di_Mones,        //not used in Lab-2
		  output [3:0] 	    di_Stens,        //not used in Lab-2
		  output wire [3:0] di_Sones,        //not used in Lab-2
		  output [3:0] 	    di_AMtens,       //not used in Lab-2
		  output [3:0] 	    di_AMones,       //not used in Lab-2
		  output [3:0] 	    di_AStens,       //not used in Lab-2
		  output [3:0] 	    di_ASones        //not used in Lab-2
		  );
    
	//get 4 letters from PC terminal SW 
	//  getting x+y=   or getting x-y=
    wire [7:0] x, y, op, eq;
    loadAdderInput lai0(
		  .rst (rst),                   // reset signal (active high)
		  .clk (clk),                   // global clock
		  .data_in_rdy(bu_rx_data_rdy), // data from the uart ready
		  .data_in(bu_rx_data),         // data from the uart
		  .data_out1(x),
		  .data_out2(op),
		  .data_out3(y),
		  .data_out4(eq)
		  );

    //checking and validating inputs
	wire is_add = ~|(op ^ "+");
    wire is_sub = ~|(op ^ "-");
    wire is_eq  = ~|(eq ^ "=");
    wire is_x_bcd, is_y_bcd;
    wire input_is_valid = is_eq & (is_add | is_sub) & is_x_bcd & is_y_bcd;
	//(a) Implementing isBCD module (5% of points)
    isBCD isBCD1 (.isBCD(is_x_bcd), .char(x));
	isBCD isBCD2 (.isBCD(is_y_bcd), .char(y));   
	

    // convert bcd to number
    wire [3:0] sum;
    wire       co;
    wire [3:0] xx, yy;
    //(b) converting input x in BCD format to 4-bit number (5% of points)
							   
	BCD2Num xx0 ( .bcd(x), .num(xx));			  
	BCD2Num yy0 ( .bcd(y), .num(yy));	

    //(c) Implementing s = x+y  (75% of points)
	//(d) Implementing s = x-y  (10% of points)
    // adding or substrating
    Adder add0 (
	    .sum(sum), // sum
	    .co(co),  // carry
	    .x(xx),
	    .y(yy),
        .is_sub(is_sub)
	    );
		
    //generate a strobe every 16 cycles of clk
    wire delayStrb;
    delay16clks  delay(
		  .rdy(delayStrb)
		  ,.go(bu_rx_data_rdy)
		  ,.rst(rst)
		  , .clk(clk));
    

	//display the result to terminal SW on computer
    wire [7:0] b4, b5, adder_o_in_bcd;
    Num2BCD num2bcd1 (.bcd(adder_o_in_bcd), .num(sum));		
    assign b4 = (input_is_valid) ? (co? (is_add ? "1": "F"): adder_o_in_bcd) : 8'h20;						
    assign b5 = (input_is_valid) ? (co? adder_o_in_bcd:8'h20) : 8'h20;		  

    dispString dspStr (
		        .rdy(L3_tx_data_rdy)
              , .dOut(L3_tx_data)
		      , .b0(x) 
		      , .b1(op)
		      , .b2(y)
		      , .b3(eq)
		      , .b4(b4) 
		      , .b5(b5)
		      , .b6(8'h0d)
		      , .b7(8'h0d)
		      , .go(delayStrb)//oneSecStrb)	
		      , .rst(rst)
		      , .clk(clk)
		      );
			  
	//(e) connecting co and sum to LED (5% of points)		  
    assign L3_led = 5'b10101;  //just a sample code
																		  
endmodule // Lab2_140L


// replace the code
module Adder (
	     output wire [3:0] sum, // sum
	     output wire       co,  // carry
	     input  wire [3:0]  x,
	     input  wire [3:0]  y,
         input  wire       is_sub
	     );
	// assign co = 0;
	wire w1, w2, w3, w4;
	wire s1, s2, s3, s4;
	assign s1 = is_sub ^ y[0];
	assign s2 = is_sub ^ y[1];
	assign s3 = is_sub ^ y[2];
	assign s4 = is_sub ^ y[3];
	assign co = is_sub ^ w4;
	FA fa0(.s(sum[0]), .co(w1), .a(x[0]), .b(s1), .cin(is_sub), .is_sub(is_sub));
	FA fa1(.s(sum[1]), .co(w2), .a(x[1]), .b(s2), .cin(w1), .is_sub(is_sub));
	FA fa2(.s(sum[2]), .co(w3), .a(x[2]), .b(s3), .cin(w2), .is_sub(is_sub));
	FA fa3(.s(sum[3]), .co(w4), .a(x[3]), .b(s4), .cin(w3), .is_sub(is_sub));
	
    // assign {co, sum} = x + y;
endmodule


// replace the code
module FA(
	      output wire  s,
	      output wire co,
	      input  wire a,
          input  wire b,
          input  wire cin,
          input  wire is_sub
	      );
		  // always @(*) begin
	assign s = a ^ b ^ cin;
    assign co = (a & b)| (a & cin) | (b & cin);

endmodule




//implementing code
module BCD2Num(
    input [7:0]  bcd,
    output [3:0] num
    );
	wire isNum = ~|(bcd ^ "0") | ~|(bcd ^ "1") | ~|(bcd ^ "2") | ~|(bcd ^ "3") | ~|(bcd ^ "4") | ~|(bcd ^ "5") | ~|(bcd ^ "6") | ~|(bcd ^ "7") | ~|(bcd ^ "8") | ~|(bcd ^ "9");
	wire isA = ~|(bcd | 8'b0010_0000) ^ "a";
	wire isB = ~|(bcd | 8'b0010_0000) ^ "b";
	wire isC = ~|(bcd | 8'b0010_0000) ^ "c";
	wire isD = ~|(bcd | 8'b0010_0000) ^ "d";
	wire isE = ~|(bcd | 8'b0010_0000) ^ "e";
	wire isF = ~|(bcd | 8'b0010_0000) ^ "f";
	assign num = (isNum) ? {bcd[3:0]} : 
				((isA) ? 4'b1010 :
				((isB) ? 4'b1011 :
				((isC) ? 4'b1100 :
				((isD) ? 4'b1101 :
				((isE) ? 4'b1110 :
				4'b1111)))));
	// assign num = {bcd[3:0]};

endmodule

//implementing code
module Num2BCD(
       output[7:0] bcd,
       input [3:0] num
	   );
	// If 7th bit is 0, it's a #, same last 4
	// If letter, add one, last 4
	// wire isNum = ~|(char[7] ^ 0);
	wire isNum = ~|(num ^ "0") | ~|(num ^ "1") | ~|(num ^ "2") | ~|(num ^ "3") | ~|(num ^ "4") | ~|(num ^ "5") | ~|(num ^ "6") | ~|(num ^ "7") | ~|(num ^ "8") | ~|(num ^ "9");
	wire isA = ~|(num ^ 4'b1010);
	wire isB = ~|(num ^ 4'b1011);
	wire isC = ~|(num ^ 4'b1100);
	wire isD = ~|(num ^ 4'b1101);
	wire isE = ~|(num ^ 4'b1110);
	wire isF = ~|(num ^ 4'b1111);
	/*assign bcd = (isNum) ? {4'h3, num} : 
				((isA) ? 8'b1000001 :
				((isB) ? 8'b1000010 :
				((isC) ? 8'b1000011 :
				((isD) ? 8'b1000100 :
				((isE) ? 8'b1000101 :
				8'b1000110))))); */
	assign bcd = {4'h3, num};
	  

endmodule

//implementing code 
module isBCD (
       output isBCD,
       input [7:0] char
	  );
	assign isBCD = ~|(char ^ "0") | ~|(char ^ "1") | ~|(char ^ "2") | ~|(char ^ "3") | ~|(char ^ "4") | ~|(char ^ "5") | ~|(char ^ "6") | ~|(char ^ "7") | ~|(char ^ "8") | ~|(char ^ "9") | ~|(char ^ "a") | ~|(char ^ "b") | ~|(char ^ "c") | ~|(char ^ "d") | ~|(char ^ "e") | ~|(char ^ "f") | ~|(char ^ "A") | ~|(char ^ "B") | ~|(char ^ "C") | ~|(char ^ "D") | ~|(char ^ "E") | ~|(char ^ "F");
endmodule


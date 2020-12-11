
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
// uncomment this define if you want to target hardware
// otherwise, this file will be configured for the simulator
//
`define HW

//
// Revision History : 0.0
//

`ifdef HW

module inpin(
  input clk,
  input pin,
  output rd);

  SB_IO #(.PIN_TYPE(6'b0000_00)) _io (
        .PACKAGE_PIN(pin),
        .INPUT_CLK(clk),
        .D_IN_0(rd));
endmodule
`endif



// -----------------------------------
//
// top level for lab2
//
module latticehx1k (
		   output 	sd,
 	     
		   input 	clk_in,
		   input wire 	from_pc,
		   output wire 	to_ir,
		   output wire 	o_serial_data,
		   output [4:0] led
				
				
`ifndef HW
		   // for software only
		   ,output [6:0] L3_segment1
		   ,output [6:0] L3_segment2
		   ,output [6:0] L3_segment3
		   ,output [6:0] L3_segment4

		   ,input 	tb_sim_rst
		   ,input [7:0]	tb_rx_data     // alarm status data
		   ,input 	tb_rx_data_rdy // alarm status data rdy
		   ,output [7:0] L3_tx_data
		   ,output L3_tx_data_rdy

		   //
		   // raw outputs (for debug purposes)
		   // use these if you don't have the segment encoder
		   // working yet.
		   //
		   ,output [3:0] di_Mtens
		   ,output [3:0] di_Mones
		   ,output [3:0] di_Stens
		   ,output [3:0] di_Sones
		   ,output [3:0] di_AMtens
		   ,output [3:0] di_AMones
		   ,output [3:0] di_AStens
		   ,output [3:0] di_ASones
`endif

	);
`ifdef HW
   wire [6:0] 	    L3_segment1;
   wire [6:0] 	    L3_segment2;
   wire [6:0] 	    L3_segment3;
   wire [6:0] 	    L3_segment4;
`endif

   wire 			clk;
   wire 			rst;
 			
`ifdef HW
   wire tb_sim_rst = 1'b0;
   
   // we are not using the IR interface for this lab
   //
   assign to_ir = 1'b0;
   assign sd = 1'b0;

   wire PLLOUTGLOBAL;
   latticehx1k_pll latticehx1k_pll_inst(.REFERENCECLK(clk_in),
                                     .PLLOUTCORE(clk),
                                     .PLLOUTGLOBAL(PLLOUTGLOBAL),
                                     .RESET(1'b1));
									 
`else // !`ifdef HW
   fake_pll uut (
                         .REFERENCECLK(clk_in),
                         .PLLOUTCORE(clk),
		         .PLLOUTGLOBAL(),
                         .RESETB(1'b1),
                         .BYPASS(1'b0)
                        );
   
`endif

   wire [7:0] 	    bu_rx_data;         // data from uart to dev
   wire  	    bu_rx_data_rdy;     // data from uart to dev is valid
   wire [7:0] 	    vbuf_tx_data;         // data from vbuf to host
   wire 	    vbuf_tx_data_rdy;     // data from vbuf to host ready 
   wire             bu_tx_busy;
   
   
   


`ifdef HW

   wire 	    uart_RXD;
   
   inpin _rcxd(.clk(clk), .pin(from_pc), .rd(uart_RXD));

   // with 12 MHz clock, 115600 baud, 8, N, 1
   buart buart (
		.clk (clk),
		.resetq(1'b1),
		.rx(uart_RXD),
		.tx(o_serial_data),
		.rd(1'b1),                // read strobe
		.wr(vbuf_tx_data_rdy),	  // write strobe 
		.valid(bu_rx_data_rdy),   // rx has valid data
		.busy (bu_tx_busy),
		.tx_data(vbuf_tx_data),
		.rx_data(bu_rx_data));

`else // !`ifdef HW
   fake_buart buart (
		.clk (clk),
		.resetq(tb_sim_rst),
		.rx(from_pc),
		.tx(o_serial_data),
		.rd(1'b1),                // read strobe
		.wr(1'b0),                // write strobe 
		.valid(bu_rx_data_rdy),   // rx has valid data
        .busy(bu_tx_busy),        // uart is busy transmitting
		.tx_data(8'b0),
		.rx_data(bu_rx_data),
  	        .fa_data(),
		.fa_valid(),
		.to_dev_data(tb_rx_data),
		.to_dev_data_valid(tb_rx_data_rdy));
`endif


   //
   // reset generation
   //
   resetGen resetGen(
		     .rst(rst),
		     .bu_rx_data_rdy(bu_rx_data_rdy),
		     .bu_rx_data(bu_rx_data),
		     .tb_sim_rst(tb_sim_rst),
		     .clk(clk)
		     );
   

   wire [4:0] 	    L3_led;
   
   assign led[4:0] = {1'b0, L3_led[4:0]};
   

   wire 	    L3_tx_data_rdy;   // alarm data to print
   wire [7:0] 	    L3_tx_data;
 	    
   
   //
   // instantiate assignment 3 DUT
   //
   Lab2_140L Lab_UT(
		    .rst   (rst),                            
		    .clk   (clk),
		    .oneSecStrb(1'b0),
		    .bu_rx_data_rdy (bu_rx_data_rdy),
		    .bu_rx_data (bu_rx_data),
		    .L3_tx_data_rdy (L3_tx_data_rdy),
		    .L3_tx_data (L3_tx_data),
		    .L3_led   (L3_led[4:0]),      
		    .L3_segment1 (L3_segment1),
		    .L3_segment2 (L3_segment2),
		    .L3_segment3 (L3_segment3),
		    .L3_segment4 (L3_segment4),

		  .di_Mtens(),
		  .di_Mones(),
		  .di_Stens(),
		  .di_Sones(),
		  .di_AMtens(),
		  .di_AMones(),
		  .di_AStens(),
		  .di_ASones()

		    );
			


			
`ifdef HW

   
   //
   // video buffer
   //
   defparam uu2.CLKFREQ = 12000000;
   defparam uu2.BAUD    = 115200;
   vbuf uu2(
	 .reset (rst),
	 .vram_clk (clk),

	 .data_in(L3_tx_data),
	 .data_in_rdy(L3_tx_data_rdy),
	 //.data_in_rdy(1'b0),

	 .use_7_segment_code (1'b1),  
	 .segment1 (7'b0000000),
	 .segment2 (7'b0000000),
	 .segment3 (7'b0000000),
	 .segment4 (7'b0000000),
	 .dot_on   (1'b0),
	 .alarm_on (1'b0),
	 
     .enable_pulling_mode(1'b0),  //1: calling module pull data, data_sink_busy becomes pull_strobe
     .data_sink_busy(bu_tx_busy),       //0: vbuf push data to calling module, data_sink_buy is busy signal 
     .cont_write_en(1'b1),
	 
	 .data_out(vbuf_tx_data[7:0]),
	 .data_out_rdy(vbuf_tx_data_rdy)
	 );
`endif


endmodule // latticehx1k

`ifdef HW
//
// PLL
//
module latticehx1k_pll(REFERENCECLK,
                       PLLOUTCORE,
                       PLLOUTGLOBAL,
                       RESET);

input wire REFERENCECLK;
input wire RESET;    /* To initialize the simulation properly, the RESET signal (Active Low) must be asserted at the beginning of the simulation */ 
output wire PLLOUTCORE;
output wire PLLOUTGLOBAL;

SB_PLL40_CORE latticehx1k_pll_inst(.REFERENCECLK(REFERENCECLK),
                                   .PLLOUTCORE(PLLOUTCORE),
                                   .PLLOUTGLOBAL(PLLOUTGLOBAL),
                                   .EXTFEEDBACK(),
                                   .DYNAMICDELAY(),
                                   .RESETB(RESET),
                                   .BYPASS(1'b0),
                                   .LATCHINPUTVALUE(),
                                   .LOCK(),
                                   .SDI(),
                                   .SDO(),
                                   .SCLK()); 

//\\ Fin=12 Mhz, Fout=12 Mhz;
defparam latticehx1k_pll_inst.DIVR = 4'b0000;
defparam latticehx1k_pll_inst.DIVF = 7'b0111111;
defparam latticehx1k_pll_inst.DIVQ = 3'b0110;
defparam latticehx1k_pll_inst.FILTER_RANGE = 3'b001;
defparam latticehx1k_pll_inst.FEEDBACK_PATH = "SIMPLE";
defparam latticehx1k_pll_inst.DELAY_ADJUSTMENT_MODE_FEEDBACK = "FIXED";
defparam latticehx1k_pll_inst.FDA_FEEDBACK = 4'b0000;
defparam latticehx1k_pll_inst.DELAY_ADJUSTMENT_MODE_RELATIVE = "FIXED";
defparam latticehx1k_pll_inst.FDA_RELATIVE = 4'b0000;
defparam latticehx1k_pll_inst.SHIFTREG_DIV_MODE = 2'b00;
defparam latticehx1k_pll_inst.PLLOUT_SELECT = "GENCLK";
defparam latticehx1k_pll_inst.ENABLE_ICEGATE = 1'b0;

endmodule

`endif

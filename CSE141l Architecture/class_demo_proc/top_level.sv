module top_level(
  input  clk, reset, req, 
  output logic done);

  wire[11:0] target, prog_ctr;
  wire  RegWrite;
  wire[7:0] datA,datB,muxB, immed;
  logic sc;
// fetch subassembly
  PC  p1(.reset  (reset),
         .clk    (clk)  ,
		 .jump_en(),
		 .target (target),
		 .prog_ctr(prog_ctr));

  PC_LUT pl1(.addr(),
             .target(target));   

  instr_ROM i1(.prog_ctr(prog_ctr),
               .mach_code());

  Control(.instr(),
  .RegDst  (), 
  .Branch  (), 
  .MemRead (),
  .MemWrite(), 
  .ALUSrc  (), 
  .RegWrite(RegWrite),     
  .MemtoReg(),
  .ALUOp());

  reg_file r1(.dat_in(),	   // loads, most ops
              .clk(clk),
              .wr_en(RegWrite),
              .rd_addrA(),
              .rd_addrB(),
              .wr_addr(),
              .datA_out(datA),
              .datB_out(datB)); 

  assign muxB = ALUscr? immed : datB;

  alu alu1(.alu_cmd(),
         .inA    (datA),
		 .inB    (muxB),
		 .sc_i   (sc),   // output from sc register
		 .rslt   (),
		 .sc_o   (sc_o));  // input to sc register

  dat_mem d1(.dat_in(),
             .clk(clk),
			 .wr_en(MemWrite), // stores
			 .addr(),
             .dat_out());

// free running
  always_ff @(posedge clk)
    if(sc_clr)
	  sc <= 'b0;
    else if(sc_en)
      sc <= sc_o;
//	else
//	  sc <= sc;

endmodule
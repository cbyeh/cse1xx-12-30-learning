module Control(
  input [5:0] instr,
  output logic RegDst, Branch, MemRead,
     MemtoReg, MemWrite, ALUSrc, RegWrite,
  output logic[4:0] ALUOp);

always_comb begin
// defaults
RegDst 	  = 'b0;   // 1: not in place  just leave 0
Branch 	  = 'b0;   // 1: branch
MemRead	  =	'b1;   // 1: load, but just leave 1 all the time   
MemWrite  =	'b0;   // 1: store
ALUSrc 	  =	'b0;   // 1: immediate
RegWrite  =	'b1;   // 0: store
MemtoReg  =	'b0;   // 1: load
ALUOp	  =  5'b11000;	  // y = a+0;
case(instr)    // override defaults with exceptions
  6'b000000:  MemWrite = 'b1;  //store 
  6'b000001:  ALUOp    = 'b00000;  // add:  y = a+b
  6'b000010:  begin


              end
endcase

end






endmodule
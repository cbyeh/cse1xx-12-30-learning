// program counter
// absolute jumps
module PC2 (
  input reset,
        clk,
		jump_en,                   // jump enable
  input       [11:0] target,
  output logic[11:0] prog_ctr
);

  always_ff @(posedge clk)
    if(reset)
	  prog_ctr <= '0;			 // prog_ctr <= 0;
	else if(jump)
	  prog_ctr <= target;
	else
	  prog_ctr <= prog_ctr + 'b1;

endmodule
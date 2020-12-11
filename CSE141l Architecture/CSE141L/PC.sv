// program counter
// relative jumps
module PC #(parameter D=12)(
  input reset,
        clk,
		jump_en,                   // jump enable
  input       [D-1:0] target,
  output logic[D-1:0] prog_ctr
);

  always_ff @(posedge clk)
    if(reset)
	  prog_ctr <= '0;
	else if(jump_en)
	  prog_ctr <= prog_ctr + target;
	else
	  prog_ctr <= prog_ctr + 'b1;

endmodule
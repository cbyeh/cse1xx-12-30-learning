module dat_mem (
  input[7:0] dat_in,
  input      clk,
  input      wr_en,
  input[7:0] addr,
  output logic[7:0] dat_out);

  logic[7:0] core[256];    // 2-dim array  8 wide  256 deep

// reads are combinational
  assign dat_out = core[addr];

// writes are sequential (clocked) -- stores only
  always_ff @(posedge clk)
    if(wr_en)						// stores only
      core[addr] <= dat_in; 

endmodule
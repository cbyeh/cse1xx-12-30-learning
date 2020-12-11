// empty dummy processor for CSE141L test bench
module prog(  
  input        clk,
               reset,	   // 
		       req,		   // from test bench: "start program"
  output logic ack);	   // to test bench: "done"

logic[15:0] ct;            // dummy cycle counter
// memory connections -- dummied in for now
logic  [ 7:0] MemAdr,
              DatIn;
wire   [ 7:0] DatOut;
logic         ReadEn  = 1;
logic         WriteEn = 0;

dm dm1(.*);                // instantiate data memory

// the following sequence makes sure the test bench
//  stops; in practice, you will want to tie your ack
//  flags to the completion of each program
always @(posedge clk) begin
  if(reset) begin
    ct  <= 0;
	ack <= 0;
  end
  else if(req) begin
	ct  <= 0;
	ack <= 0;
  end
  else begin
    if(ct<255) 
      ct <= ct+1;
    else
      ack <= 1;
  end
end


endmodule
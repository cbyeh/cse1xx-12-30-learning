// generic state machine paradigm
// 3-bit up/down counter   limited to 0 to 7 inclusive
module StateMachine(
  input     Reset_i,
            Clock_i,
		    Up_i,
		    Down_i,
  output logic[2:0] Status_o);
										  //reg	   assign 
// internal variables
  logic[2:0] NextState,
             PresentState;

// sequential logic
  always_ff @(posedge Clock_i) begin
    if(Reset_i)
	  PresentState <= '0; //3'b000;
	else 
	  PresentState <= NextState;
  end

// combinational logic
  always_comb begin 
    case(PresentState)
      0: if(Up_i) NextState = 1;
		   else if(Down_i) NextState = 0;
		   else NextState = 0;
	  7: if(Up_i) NextState = 7;
		   else if(Down_i) NextState = 6;
		   else NextState = 7;
	  default: if(Up_i) NextState = PresentState+1;
	           else if(Down_i) NextState = PresentState-1;
	           else NextState = PresentState;    
    endcase
  end

  assign Status_o = PresentState; 

endmodule
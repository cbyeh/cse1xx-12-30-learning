// CSE141L
// making and avoiding implied latches
// incomplete case statement; what happens when sel = 1x?
always_comb case(sel)
  2'b00: output = 5;
  2'b01: output = 10;
endcase

// resolve with default:
always_comb case(sel)
  2'b00: output = 5;
  2'b01: output = 10;
  default: output = 0; 
endcase

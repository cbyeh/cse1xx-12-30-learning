// shift operator
module shift(
  input[7:0] a,b,
  input[1:0] op,
  output logic[7:0] r);

// op=0:  left rotating shift       rot(a<<b)
// op=1:  mask (bitwise AND)

  case(op)
    0: r = ((a<<b) | (a>>(8-b)));      //     abcdefgh
								      //	  defgh000
								      //	  00000abc
    1: r = a & 8'b1111_1000;          //      left shift

  endcase


endmodule
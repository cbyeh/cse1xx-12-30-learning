// combinational -- no clock
module alu(
  input[2:0] alu_cmd,    // ALU instructions
  input[7:0] inA, inB,
  input      sc_i,       // shift_carry in
  output logic[7:0] rslt,
  output logic sc_o      // shift_carry out
);

always_comb begin 
  rslt = 'b0;            
  sc_o = 'b0;    
  case(alu_cmd)
    3'b000: // add 2 8-bit unsigned; automatically extends to 9
      {sc_o,rslt} = ina + inb + sc_i;
	3'b001: // left_shift
	  {sc_0,rslt} = {ina, sc_i};
      begin
		rslt[7:1] = ina[6:0];
		rslt[0]   = sc_i;
		sc_o      = ina[7];
      end
    3'b010: // right shift
	  {rslt,sc_o} = {sc_i,ina};

/*
    3'b000: {sc_o,rslt} = {inA,sc_i}; // left_shift of inA
    3'b001: {sc_o,rslt} = {1'b0,inA^inB};	  // clear sc_o; bitwise XOR
    3'b010: {sc_o,rslt} = {1'b0,inA&inB};	  // clear sc_o; bitwise mask


       inA = 10101010_1  sc_i = 1
            
 sc_o =1    1_01010101				 a	 1100_1100 1100_0000
									 b	 0100_0010 0100_0010
									   			 1 0000_0010
									   1 0000_1111


									   1 0000_0000 0000_0000
       11110000_11001010     


    (1)11100001_10010100          
*/

  endcase
end
   
endmodule
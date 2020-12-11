// dummy processor for CSE141L test bench
// Spring 2020
module prog(
  input        clk,
               reset,
		       req,
  output logic ack);
// program 1 variables
logic  [11:1] d_in1[15];
logic  [15:0] d_in_par1[15];
// program 2 variables		 
logic  [15:0] d_in2[15];                          // uncorrected data w/ parity
logic  [15:0] d_in_corr2[15];		              // corrected data w/ parity
logic  [11:1] d_out2[15];				          // uncorrected data
logic         p0[15],p8[15], p4[15], p2[15], p1[15];	  // received parity
logic         s0[15],s8[15], s4[15], s2[15], s1[15];     // reconstructed parity
logic  [ 3:0] err[15];					          // disparity
logic  [11:1] d_out_corr2[15];                    // corrected data
// program 3 variables
logic  [  3:0] pat3;
logic  [  7:0] mat_str3[32];
logic  [255:0] str3;
logic  [  7:0] ctb3, 
               cts3,
			   cto3,
			   ctp3;
// common variables
logic  [15:0] ct;                                 // dummy cycle counter
// memory connections -- dummied in for now
logic  [ 7:0] MemAdr,
              DatIn;
wire   [ 7:0] DatOut;
logic         ReadEn  = 1;
logic         WriteEn = 0;

dm dm1(.*);			                   // instantiate data memory

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

// program 1
always @(negedge req) begin
  for(int i=0; i<15; i++) begin
// pattern: d[11:5] p8 d[4:2] p4 d[1] p2 p1
    d_in1[i] = {dm1.core[2*i+1],dm1.core[2*i]};
    d_in_par1[i][15:13] = dm1.core[2*i+1][2:0];   // d11:d9
    d_in_par1[i][12:09] = dm1.core[2*i  ][7:4];	  //  d8:d5
    d_in_par1[i][07:05] = dm1.core[2*i  ][3:1];	  //  d4:d2
    d_in_par1[i][    3] = dm1.core[2*i  ][  0];   //     d1
    d_in_par1[i][ 8]    = ^d_in1[i][11:5];   
    d_in_par1[i][ 4]    = (^d_in1[i][11:8])^(^d_in1[i][4:2]);
    d_in_par1[i][ 2]    = d_in1[i][11]^d_in1[i][10]^d_in1[i][7]^d_in1[i][6]^d_in1[i][4]^d_in1[i][3]^d_in1[i][1];
    d_in_par1[i][ 1]	= d_in1[i][11]^d_in1[i][ 9]^d_in1[i][7]^d_in1[i][5]^d_in1[i][4]^d_in1[i][2]^d_in1[i][1];
    d_in_par1[i][ 0]    = ^d_in_par1[i][15:1];
    dm1.core[31+2*i] = {d_in_par1[i][15:8]}; 
	dm1.core[30+2*i] = {d_in_par1[i][ 7:0]};
  end  
end

// program 2
always @(negedge req) begin
  for(int i=0; i<15; i++) begin
    d_in2[i] = {dm1.core[65+2*i],dm1.core[64+2*i]};
	d_out2[i][11:5] = d_in2[i][15:9];
	p8[i]           = d_in2[i][   8];
	d_out2[i][ 4:2] = d_in2[i][ 7:5];
	p4[i]           = d_in2[i][   4];
	d_out2[i][   1] = d_in2[i][   3];
	{p2[i],p1[i]}   = d_in2[i][ 2:1];
	p0[i]           = d_in2[i][   0];
// reconstruct parity according to received data
    s8[i] = ^d_out2[i][11:5];
    s4[i] = (^d_out2[i][11:8])^(^d_out2[i][4:2]); 
    s2[i] = d_out2[i][11]^d_out2[i][10]^d_out2[i][7]^d_out2[i][6]^d_out2[i][4]^d_out2[i][3]^d_out2[i][1];
    s1[i] = d_out2[i][11]^d_out2[i][ 9]^d_out2[i][7]^d_out2[i][5]^d_out2[i][4]^d_out2[i][2]^d_out2[i][1];
    s0[i] = ^(d_in2[i]);
// find where reconstructed parity != received parity
	err[i] = {s8[i]^p8[i],s4[i]^p4[i],s2[i]^p2[i],s1[i]^p1[i]};              
// the binary number "err" will point to the bad bit, if any
    for(int k=0; k<16; k++)
      if(err[i]==k) d_in_corr2[i][k] = !d_in2[i][k];  
      else       d_in_corr2[i][k] =  d_in2[i][k];
// select the 11 corrected data bits from the 15 corrected data w/ parity
    d_out_corr2[i][11:5] = d_in_corr2[i][15:9];
    d_out_corr2[i][ 4:2] = d_in_corr2[i][ 7:5];
    d_out_corr2[i][   1] = d_in_corr2[i][   3];
    dm1.core[95+2*i]     = {5'd0,d_out_corr2[i][11:9]};
	dm1.core[94+2*i]     = d_out_corr2[i][8:1];
    if(!s0[i])
      dm1.core[95+2*i]     = {5'b10000,d_out_corr2[i][11:9]};
  end
end

// program 3
always @(negedge req) begin
  pat3 = dm1.core[160];
  for(int i=0; i<32; i++)
    mat_str3[i] = dm1.core[128+i];
  str3 = 0;
  ctb3 = 0;
  ctp3 = 0;
  cto3 = 0;
  cts3 = 0;
  for(int i=0; i<32; i++) 
    str3 = (str3<<8) + mat_str3[i]; 
  for(int i=0; i<252; i++) begin
    if(str3[255:251]==pat3)
	  cts3++;
	str3 = str3<<1;
  end
  for(int j=0; j<32; j++) begin
    ctp3 = 0;
    for(int k=0; k<4; k++) begin
	  if(pat3==mat_str3[j][4:0]) begin
	    ctb3++;
		ctp3 = 1;
	  end
	  mat_str3[j] = mat_str3[j]>>1;
	end
    cto3 = cto3 + ctp3;
  end
  dm1.core[192] = ctb3;
  dm1.core[193] = cts3;
  dm1.core[194] = cto3;
end

endmodule
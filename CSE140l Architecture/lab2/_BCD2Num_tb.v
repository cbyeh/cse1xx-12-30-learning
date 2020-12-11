module BCD2Num_tb;
reg [7:0]  bcd;
wire [3:0] x;

BCD2Num uut (.bcd(bcd), .num(x));

initial begin
#10 bcd="F";
#10 bcd="D";
#10 bcd="9";
#10 bcd="A";
#10 bcd="E";
#10 bcd="B";
#10 bcd="1";
#10 bcd="2";
#10 bcd="3";
#10 $stop;
end

initial begin
 $monitor(" time=%0t   bcd=%d   num=%x   \n", $time, bcd, x);
end
endmodule

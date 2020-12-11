module Num2BCD_tb;
reg [3:0]  num;
wire [7:0] bcd;

Num2BCD uut (.num(num), .bcd(bcd));

initial begin
#10 num=4'hf;
#10 num=4'he;
#10 num=4'hd;
#10 num=4'ha;
#10 num=4'h3;
#10 num=4'h7;
#10 num=4'h4;
#10 num=4'h1;
#10 num=4'h0;
#10 num=4'h5;
#10 $stop;
end

initial begin
 $monitor(" time=%0t   num=%x  bcd=%d   \n", $time, num, bcd);
end
endmodule

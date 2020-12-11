module isBCD_tb;
reg [7:0] char;
wire  isBCD;

isBCD uut (.char(char), .isBCD(isBCD));

initial begin
#10 char="F";
#10 char="X";
#10 char="3";
#10 char="J";
#10 char="A";
#10 char="C";
#10 char="9";
#10 char="0";
#10 char="1";
#10 char="P";
#10 $stop;
end

initial begin
 $monitor(" time=%0t   char=%d  isBCD=%d   \n", $time, char, isBCD);
end
endmodule

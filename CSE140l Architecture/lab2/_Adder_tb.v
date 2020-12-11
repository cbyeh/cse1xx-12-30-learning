module Adder_tb;
reg [3:0] a;
reg [3:0] b;
reg is_sub;
wire [3:0] sum;
wire co;
Adder uut (  .x(a), .y(b), .is_sub(is_sub), .sum(sum), 
                        .co(co)  );
initial begin
#10 a=4'b0000;b=4'b0000;is_sub=1'b0;
#10 a=4'b0001;b=4'b0001;is_sub=1'b0;
#10 a=4'b0011;b=4'b0010;is_sub=1'b0;
#10 a=4'b0101;b=4'b0010;is_sub=1'b1;
#10 a=4'b1111;b=4'b0100;is_sub=1'b1;
#10 a=4'b0001;b=4'b0010;is_sub=1'b0;
#10 a=4'b0110;b=4'b0101;is_sub=1'b0;
#10 a=4'b0100;b=4'b0111;is_sub=1'b1;
#10 $stop;
end

initial begin
 $monitor(" time=%0t   a=%d   b=%d   is_sub=%d   sum=%d   cout=%d\n", $time, a, b, is_sub, sum, co);
end
endmodule

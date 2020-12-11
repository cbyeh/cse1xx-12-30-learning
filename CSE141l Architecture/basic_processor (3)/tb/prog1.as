ld.. b0..b11

// Set p8 to r5
eor r0, b11, b10
eor r1, r0, b9
eor r2, r1, b8
eor r3, r2, b7
eor r4, r3, b6
eor r5, r4, b5

// Set p4 to r6
eor r0, b11, b10
eor r1, r0, b9
eor r2, r1, b8
eor r3, r2, b4
eor r4, r3, b3
eor r6, r4, b2

// Set p2 to r7
eor r0, b11, b10
eor r1, r0, b7
eor r2, r1, b6
eor r3, r2, b4
eor r4, r3, b3
eor r7, r4, b1

// Set p1 to r8
eor r0, b11, b9
eor r1, r0, b7
eor r2, r1, b5
eor r3, r2, b4
eor r4, r3, b2
eor r8, r4, b1

// Set p0 to r9
eor r0, b11, b10
eor r1, r0, b9
eor r2, r1, b8
eor r3, r2, b7
eor r4, r3, b6
eor r5, r4, b5
eor r0, r5, b4
eor r1, r0, b3
eor r2, r1, b2
eor r3, r2, b1
eor r4, r3, r5
eor r0, r4, r6
eor r1, r0, r7
eor r9, r1, r8

// Str  mem[30]
ld r0, mem
add r0, r0, 30
str r0, b4
add r0, r0, 1
str r0, b3
add r0, r0, 1
str r0, b2
add r0, r0, 1
str r0, r6
add r0, r0, 1
str r0, b1
add r0, r0, 1
str r0, r7
add r0, r0, 1
str r0, r8
add r0, r0, 1
str r0, r9

// Str mem[31]
ld r0, mem
add r0, r0, 31
str r0, b11
add r0, r0, 1
str r0, b10
add r0, r0, 1
str r0, b9
add r0, r0, 1
str r0, b8
add r0, r0, 1
str r0, b7
add r0, r0, 1
str r0, b6
add r0, r0, 1
str r0, b5
add r0, r0, 1
str r0, r5

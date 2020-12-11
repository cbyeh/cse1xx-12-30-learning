My hasBlock() is taken from java's String class's hashCode() method principles, modified to represent a String.
	We start by creating a String made of the timestamp, index, and previous hash
	We proceed to create an array of char with that String
	We then multiply 0 by (String's length) times by prime # 31 added by each character of the String's ASCII code.


My proofOfWork() Calculates the least common multiple of previous proofOfWork + 1 and 13
	As the blocks in our chains grow, the proofOfWork becomes significantly harder to compute
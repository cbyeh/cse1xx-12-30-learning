# Homework 4 (Makefile Assignment)
#
# Name:  Tracker Wonderdog
# PID:   AXXXXXXXX
# Login: cs15xzz
#
# Your impressive work at your internship has won you a return offer at the
# Galactic Empire. You were but the student; now you are the master! You are
# responsible for all Imperial Vehicles's software reliability. The vehicle
# classes include:
#
#     *   ATAT.java
#     *   ATST.java
#     *   DeathStar.java
#     *   ImperialShuttle.java
#     *   StarDestroyer.java
#     *   TIEBomber.java
#     *   TIEFighter.java
#
# For each of these classes, there is a Tester class that contains all unit
# tests for the corrsponding vehicle named as `(Vehicle)Tester.java`, where
# (Vehicle) can be replaced by any of the vehicle class name.  Write a
# Makefile that does the following:
#
#     1.  When type in `make (Vehicle).class`, the corresponding vehicle class
#         is built, if and only if its Java source file has been updated since
#         the last time it was built.
#
#     2.  When type in `make` or `make vehicles`, each vehicle class will be
#         built (ATAT.class, ATST.class, etc.) if and only if the corresponding
#         Java source file has been updated since the last time that class was
#         built.
#
#     3.  When type in `make (Vehicle)Tester.class`, the corresponding tester
#         class is built, if and only if (Vehicle).java or (Vehicle)Tester.java
#         has been updated since the last time the tester was built.
#
#     4.  When type in `make tests`, all tester classes will be built
#         (ATATTester.class, ATSTTester.class, etc.), if and only if the
#         corresponding Java source file for that tester or the tested vehicle
#         has been updated since the last time the tester was built.
#
#     5.  When type in `make clean`, all class files will be removed.
#
# Hints:
#
# You are encouraged to use variables to organize your code and increase
# readability. And feel free to create dummy classes for testing purposes.
#==============================================================================
#
	
default: vehicles

.SUFFIXES: .java .class
.java.class:
	javac $<

VEHICLE = ATAT ATST DeathStar ImperialShuttle StarDestroyer TIEBomber TIEFighter

vehicles: $(VEHICLE).java
	javac $(VEHICLE).java

$(VEHICLE).class: $(VEHICLE).java
	# javac $(VEHICLE).java
	
$(VEHICLE)Tester.class: $(VEHICLE).java 
	javac $(VEHICLE)Tester.java

tests: $(VEHICLE).java
	javac $(VEHICLE)Tester.java

clean:
	rm -f *.class

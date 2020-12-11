# Homework 4 (Makefile Assignment)

Name: Tracker Wonderdog  
PID: AXXXXXXXX  
Account: cs15xzz  

1. Congratulations, you have been offered an internship position by the
Galactic Empire! You will be working with the Death Star team as a test
engineer, making sure that the Empire's proudest piece of machinery is fully
operational so that the Emperor does not become the laughingstock of those
Rebel Scum. Knowing that the `DeathStar` class has 4 constructors and 84 public
methods, in general, how many units are there to be tested? **(1 point)**

        88

2. One of the methods you are testing is for the Death Star to make hologram
calls. The method will take in a contact number as parameter, and returns the
`ConnectionStream` object if the call is connected and `null` if the call never
was picked up. Complete the following test: **(1 point)**

    ```
    public void testMakeHologramCall() {
    
        // construct a new Death Star
        DeathStar ds = new DeathStar();

        // call Darth Vader
        String contact = "1 (202) 358-0001";

        // this method should return null since Darth Vader never picks up calls
        ConnectionStream stream = ds.makeHologramCall(contact);

        // make assertion and fill in the blank (in the *BRACKETS* below)
        _________________________

    }

    ```
    What goes in the underlined space?

        assertEquals(null, stream);

3. Another method, `jumpToLightSpeed`, takes in a coordinate as the parameter
for the destination, and throws a `HyperDriveOfflineException` if the
hyperdrive appears to be offline.
    ```
    public void testJumpToLightSpeed(Coordinate destination) {

        // construct a new Death Star
        DeathStar ds = new DeathStar();

        // turn off hyperdrive
        ds.hyperDrive.off();

        // destination: Planet Alderaan
        Coordinate alderaan = LocationUtil.getCoordinate("Alderaan");

        // trying to jump to light speed should throw an exception
        try {
            ds.jumpToLightSpeed(alderaan);

            // FIXME: POSITION A

        } catch (HyperDriveOfflineException e) {
            System.err.println(e);

            // FIXME: POSITION B

        } finally {
            // FIXME: POSITION C
        }
    }
    ```
    Choose the best place(s) to add the `fail()` statement where it says FIXME
    (i.e. 1, 2, or 3): **(1 point)**

        1, POSITION A

4. Now you're looking to save some time when coordinating the next raid on a
Rebel base. To do this, you want to create a Makefile to automatically update
the Galactic Fleet. In general, what is the default target inside a Makefile?
**(1 point)**

        the first target entry (or make, or default)

5. Assuming that you want the Makefile to recompile the test only if
`DeathStar.java` or `DeathStarTester.java` have been updated since the last
time it was compiled. what goes to the dependency list below? **(1 point)**

    ```
    DeathStarTester.class: __________________________
        javac DeathStarTester.java
    ```
    What goes in the underlined space above?

        DeathStar.class

6. Explain what the following lines do in a Makefile. **(1 point)**

    ```
    .SUFFIXES: .java .class  
    .java.class:  
        javac $<
    ```

        It is saying .SUFFIXES is dependent on .java and .class file types.
		$< means the first prerequisite (.java) i.e. it will compile the source code.

7. Makefile Practice: See `Makefile` for details. **(5 points)**

## Turnin Procedure:
Be sure your `hw3.md` file is saved on your cs15xzz account in the directory
`~/homeworks/hw3`. When logged into your cs15xzz account, enter the following
commands:
```
$ cd ~/homework/hw4
$ turnin
```
Follow the Autograder prompts, using your UC San Diego email login credentials,
and you should be good to go! Make sure you get the confirmation email from
Autograder. Additionally, you can log in to Autograder to verify your submission.

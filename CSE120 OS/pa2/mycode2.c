/* mycode2.c: your portion of the kernel
 *
 *   	Below are functions that are called by other parts of the kernel. 
 * 	Your ability to modify the kernel is via these functions.  You may
 *  	modify the bodies of these functions (and add code outside of them)
 *  	in any way you wish (however, you cannot change their interfaces).  
 */

#include "aux.h"
#include "sys.h"
#include "mycode2.h"

#define TIMERINTERVAL 1	// in ticks (tick = 10 msec)
#define L 100000
#define MAXSIXTYFOURBITINT (unsigned long long int) -1

// static int count = 0; // For testing TODO: remove

/* 	A sample process table. You may change this any way you wish. 
 */
static struct {
	int valid;		// is this entry valid: 1 = yes, 0 = no
	int pid;		// process ID (as provided by kernel)
} proctab[MAXPROCS];

/* FIFO
 */
static struct {
  int queue[MAXPROCS];
  int head;
  int tail;
} Queue;
  
/* LIFO
 */
static struct {
  int stack[MAXPROCS];
  int top;
} Stack;

/* RR
 */
static struct {
  int curr;
} Robin;

/* PROPORTIONAL
 */
static struct {
  unsigned long long int pass; // Pass value, 64-bit to avoid Overflow
  int stride; // Stride value
  int cpu; // If it requested CPU, how much % it is. 0 if it hasn't

  // int num; // For testing TODO: remove

} proptab[MAXPROCS];

static int cpuUsed; // 0-100% CPU used, will be between 1 and 100
static int numZeroProcs; // Number of processes that can't/won't request cpu 

/* Proportional helper for changing pass values on new requests,
 * or when another process starts or ends.
 * Also allocate the remaining CPU to the other processes here,
 * to avoid starvation.
 */
void recalibrate()
{
  int i;
  int min;

  // Calibrate pass values of running processes by decrementing all by lowest
  min = MAXSIXTYFOURBITINT;
  for (i = 0; i < MAXPROCS; i++) { // Find actual min
    if (proctab[i].valid && proptab[i].pass < min) {
      min = proptab[i].pass;
    }
  }
  if (min > 0) {
    min--;
  }
  for (i = 0; i < MAXPROCS; i++) {
    if (proctab[i].valid && proptab[i].pass != MAXSIXTYFOURBITINT) {
      proptab[i].pass -= min;
    }
  }

  // DPrintf("\n ZeroProcs: %d \n", numZeroProcs);
  // DPrintf("\n cpuUsed: %d \n", cpuUsed);

  // Finally allocate remaining CPU
  if (cpuUsed == 100) { // If all CPU is used, don't allocate
    for (i = 0; i < MAXPROCS; i++) {
      if (proctab[i].valid && proptab[i].cpu == 0) {
        proptab[i].pass = MAXSIXTYFOURBITINT; // Set to max
      }
    }
    return;
  } else { // Reset their pass values
    for (i = 0; i < MAXPROCS; i++) {
      if (proctab[i].valid && proptab[i].cpu == 0) {
        if (proptab[i].pass == MAXSIXTYFOURBITINT) {
          proptab[i].pass = 0;
        }
      }
    }
  }
  int zeroStride;
  int cpuUnused;
  if (numZeroProcs > 0) {
    cpuUnused = 100 - cpuUsed;
    if (cpuUnused < numZeroProcs) { // If available cpu must be fractioned
      zeroStride = L * numZeroProcs / cpuUnused;
    } else {
      zeroStride = L / (cpuUnused / numZeroProcs); // Else fraction them evenly
    }
    for (i = 0; i < MAXPROCS; i++) {
      if (proctab[i].valid && proptab[i].cpu == 0) {
        proptab[i].stride = zeroStride;
      }
    }
  }
}


/* 	InitSched() is called when the kernel starts up. First, set the
 * 	scheduling policy (see sys.h). Make sure you follow the rules
 *   	below on where and how to set it.  Next, initialize all your data
 * 	structures (such as the process table).  Finally, set the timer
 *  	to interrupt after a specified number of ticks. 
 */
void InitSched()
{
	int i;

	/* First, set the scheduling policy. You should only set it
	 * from within this conditional statement. While you are working
	 * on this assignment, GetSchedPolicy() will return NOSCHEDPOLICY. 
	 * Thus, the condition will be true and you may set the scheduling
	 * policy to whatever you choose (i.e., you may replace ARBITRARY).  
	 * After the assignment is over, during the testing phase, we will
	 * have GetSchedPolicy() return the policy we wish to test (and
	 * the policy WILL NOT CHANGE during the entirety of a test).  Thus
	 * the condition will be false and SetSchedPolicy(p) will not be
	 * called, thus leaving the policy to whatever we chose to test
	 * (and so it is important that you NOT put any critical code in
	 * the body of the conditional statement, as it will not execute when
	 * we test your program). 
	 */
	if (GetSchedPolicy() == NOSCHEDPOLICY) {	// leave as is
							// no other code here
		SetSchedPolicy(PROPORTIONAL);		// set policy here
							// no other code here
	}
		
	/* Initialize all your data structures here */
	for (i = 0; i < MAXPROCS; i++) {
		proctab[i].valid = 0;
	}

  switch(GetSchedPolicy()) {

  case FIFO:
    for (i = 0; i < MAXPROCS; i++) {
      Queue.queue[i] = 0;
    }
    Queue.head = 0;
    Queue.tail = 0;
    break;

  case LIFO:
    for (i = 0; i < MAXPROCS; i++) {
      Stack.stack[i] = 0;
    }
    Stack.top = 0;
    break;

  case ROUNDROBIN:
    Robin.curr = 0;
    break;

  case PROPORTIONAL:
    cpuUsed = 0;
    numZeroProcs = 0;
    break;

  }

	/* Set the timer last */
	SetTimer(TIMERINTERVAL);
}


/*  	StartingProc(p) is called by the kernel when the process
 * 	identified by PID p is starting. This allows you to record the
 * 	arrival of a new process in the process table, and allocate any
 * 	resources (if necessary). Returns 1 if successful, 0 otherwise. 
 */
int StartingProc(int p) 		
	// p: process that is starting
{
	int i;

	for (i = 0; i < MAXPROCS; i++) {
		if (!proctab[i].valid) {
			proctab[i].valid = 1;
			proctab[i].pid = p;
      // DPrintf("\nStarting %d\n", p);
      
      switch(GetSchedPolicy()) {

      case FIFO:
        Queue.queue[Queue.tail] = i; // Set to one of possible.
        Queue.tail = (Queue.tail + 1) % MAXPROCS;
        break;

      case LIFO:
        Stack.stack[Stack.top] = i;
        Stack.top = (Stack.top + 1) % MAXPROCS;
        DoSched();
        break;

      case PROPORTIONAL:
        proptab[i].pass = 0;
        proptab[i].stride = 0;
        proptab[i].cpu = 0;
        numZeroProcs++;
        recalibrate();
        break;

      default:
        break; // Not needed for Round Robin

      }

			return(1);
		}
	}
	DPrintf("Error in StartingProc: no free table entries\n");
	return(0);
}
			

/*   	EndingProc(p) is called by the kernel when the process
 * 	identified by PID p is ending.  This allows you to update the
 *  	process table accordingly, and deallocate any resources (if
 *  	necessary).  Returns 1 if successful, 0 otherwise. 
 */
int EndingProc(int p)
	// p: process that is ending
{
	int i;

	for (i = 0; i < MAXPROCS; i++) {
		if (proctab[i].valid && proctab[i].pid == p) {
      // DPrintf("\nEnding %d\n", p);
			proctab[i].valid = 0;
      
      switch(GetSchedPolicy()) {
    
      case FIFO:
        Queue.head = (Queue.head + 1) % MAXPROCS;
        break;

      case LIFO:
        Stack.top = (Stack.top - 1) % MAXPROCS;
        break;

      case PROPORTIONAL:
        proptab[i].pass = 0;
        proptab[i].stride = 0;
        if (proptab[i].cpu == 0) {
          numZeroProcs--;
        } else {
          cpuUsed -= proptab[i].cpu;
        }
        proptab[i].cpu = 0;
        recalibrate();
        break;

      default:
        break; // update RR not needed

      } 

			return(1);
    }
  }
	DPrintf("Error in EndingProc: can't find process %d\n", p);
	return(0);
}


/* 	SchedProc() is called by kernel when it needs a decision for
 * 	which process to run next. It will call the kernel function
 * 	GetSchedPolicy() which will return the current scheduling policy
 *   	which was previously set via SetSchedPolicy(policy). SchedProc()
 * 	should return a process PID, or 0 if there are no processes to run. 
 */
int SchedProc()
{
	int i;
  int pid;
  unsigned long long int min;

	switch(GetSchedPolicy()) {

	case ARBITRARY:

		for (i = 0; i < MAXPROCS; i++) {
			if (proctab[i].valid) {
				return(proctab[i].pid);
			}
		}
		break;

	case FIFO:
    pid = Queue.queue[Queue.head];
    if (proctab[pid].valid) {
      return(proctab[pid].pid);
    }
		break;

	case LIFO:
    pid = Stack.stack[(Stack.top - 1) % MAXPROCS];
    if (proctab[pid].valid) {
      return(proctab[pid].pid);
    }
		break;

	case ROUNDROBIN:
		pid = Robin.curr;
    for (i = 0; i < MAXPROCS; i++) {
      pid = (pid + 1) % MAXPROCS;
      if (proctab[pid].valid) {
        Robin.curr = pid;
        return(proctab[pid].pid);
      }
    }
		break;

	case PROPORTIONAL:
    pid = 0; // Initialize first to avoid segfault
    min = MAXSIXTYFOURBITINT;
    for (i = 0; i < MAXPROCS; i++) { // Find actual min
		  if (proctab[i].valid && proptab[i].pass < min) {
        pid = i;
        min = proptab[i].pass;
      }
    }
    if (proctab[pid].valid) { // Return process with min pass
      proptab[pid].pass += proptab[pid].stride;
      // For testing TODO: remove
      /* proptab[pid].num++; // TODO:
      count++; // TODO;
      if (count >= 100) {
        DPrintf("\n 100 quantums reached");
        for (i = 0; i < MAXPROCS; i++) {
          if (proctab[i].valid) {
            DPrintf("\nPID: %d, count: %d\n", proctab[i].pid, proptab[i].num); 
            proptab[i].num = 0;
          }
        }
        count = 0;
      } // TODO: remove */
      return(proctab[pid].pid);
    }
		break;

	}

	return(0);
}


/*  	HandleTimerIntr() is called by the kernel whenever a timer
 *  	interrupt occurs.  Timer interrupts should occur on a fixed
 * 	periodic basis.
 */
void HandleTimerIntr()
{
	SetTimer(TIMERINTERVAL);

	switch(GetSchedPolicy()) {	// is policy preemptive?

	case ROUNDROBIN:		// ROUNDROBIN is preemptive
	case PROPORTIONAL:		// PROPORTIONAL is preemptive

		DoSched();		// make scheduling decision
		break;

	default:			// if non-preemptive, do nothing
		break;
	}

}

/* 	MyRequestCPUrate(p, n) is called by the kernel whenever a process
 * 	identified by PID p calls RequestCPUrate(n).  This is a request for
 *   	n% of CPU time, i.e., requesting a CPU whose speed is effectively
 * 	n% of the actual CPU speed. Roughly n out of every 100 quantums
 *  	should be allocated to the calling process. n must be at least
 *  	0 and must be less than or equal to 100. MyRequestCPUrate(p, n)
 * 	should return 0 if successful, i.e., if such a request can be
 * 	satisfied, otherwise it should return -1, i.e., error (including
 * 	if n < 0 or n > 100). If MyRequestCPUrate(p, n) fails, it should
 *   	have no effect on the scheduling of this or any other process,
 * 	i.e., AS IF IT WERE NEVER CALLED.
 */
int MyRequestCPUrate(int p, int n)
	// p: process whose rate to change
	// n: percent of CPU time
{
  int i;

  if (n > 100 || n < 0) {
    return(-1);
  }
  for (i = 0; i < MAXPROCS; i++) {
    if (proctab[i].pid == p) {
      if (n == 0) {
        if (proptab[i].cpu > 0) { // Check if requested before
          cpuUsed -= proptab[i].cpu;
          numZeroProcs++;
          recalibrate();
          return(0);
        }
        return(0); // Else do nothing
      }
      if (proptab[i].cpu > 0) { // If it has been requested before
        if (cpuUsed + n - proptab[i].cpu <= 100) {
          cpuUsed += n - proptab[i].cpu;
          proptab[i].cpu = n;
          proptab[i].stride = L / n;
          recalibrate();
          return(0);
        }
        return(-1); // Else we can't accomadate it. Nothing happens
      }
      if (cpuUsed + n <=  100) { // Else hasn't been requested before
        cpuUsed += n;
        proptab[i].cpu = n;
        proptab[i].stride = L / n;
        numZeroProcs--;
        recalibrate();
        return(0);
      }
      return(-1); // Else we can't accomadate it. Nothing happens
    }
  }
  return(-1); // Process not found;
}

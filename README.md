*tr4j* is a light-weighted log tool to trace detailed execution in java.

# Features:

1. To log java execution with indent.

2. To log java execution with file name and line number.

3. It is upon slf4j and easy to deploy.

4. Minimum execution cost in deploy mode.

5. Can output log to DebugView tool if on windows.

# How to use:

```java
	@Test
	public void testTrace() {
		TR.enter();// use TR.enter() at the entrance of a method.
		try {
			TR.debug(aMethod());
		} catch (Exception e) {
			TR.warn("a warn message");
			TR.error("an error message");
			TR.warn(e);
			//TR.error(e);
		}
		TR.exit();// us TR.exit() at the exit of a method.
	}

	private int aMethod() throws Exception {
		TR.enter();
		TR.debug("Here I am.");
		TR.debug("A number is %d", 1);
		TR.debug();
		TR.info("some info");
		boolean condition = true; // false;
		if (condition) {
			TR.exit();// use TR.exit() at every exit.
			throw new Exception("Something is wrong.");
		}
		return TR.exit(1);// use TR.exit(value) with return value
	}

```

# Description
1. enter() / exit() is at level TRACE. It's lower than DEBUG. It can be turned off alone.

2. If a method exit without TR.exit(). It still runs without problem. But there will be a log like "TR log indent mismatch".

3. Do not use expression in log, it will slow down the performance.<br/>
	Bad sample<br/>
	TR.debug("Processing trade with id: " + id + " symbol: " + symbol);<br/>
	Good sample<br/>
	TR.debug("Processing trade with id: %d symbol: %s", id, symbol);

8. debug mode and deploy mode<br/>
	debug mode: import neo.log.tr.TR;<br/>
	deploy mode: import neo.log.notr.TR;<br/>

9. Defect and solution<br/>
	The DebugView tool needs a Filter keyword to filter logs from other application. It will also filter out stacktrace in an exception.<br/>
	A fallback solution is to add another appender. Check the stacktrace in a log file if an exception happens.

10. It depends on slf4j.

	private static final Logger logger = LoggerFactory.getLogger(CATEGORY);

11. DO NOT log confidential. Use TR.fixMe() if you have to.

	TR.fixMe("Confidential！");


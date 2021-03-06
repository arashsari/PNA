package au.edu.adelaide.pna.system;

import au.edu.adelaide.kahn.pn.Process;
import au.edu.adelaide.kahn.pn.InputPort;
import au.edu.adelaide.kahn.pn.OutputPort;
import java.util.Map;
import java.io.Serializable;

/**
 * A dataflow process execution strategy.  This strategy
 * will fire to the capacity of the channels.
 *
 * @author Darren Webb
 * @version $Id: DataFlowStrategy.java,v 1.2 2005-08-02 11:54:55 cvsproject Exp $
 */
public class DataFlowStrategy
	implements ProcessThreadStrategy, Serializable
{
  public static final String NAME = "Dataflow Strategy";
  public static final String DESCRIPTION = "Dataflow (or eager) firing policy.  "
                                           + "This strategy will fire to the "
                                           + "capacity of the output channels.";

	private boolean pause = false;
	private boolean paused = true;
	private boolean stop = false;
	private boolean stopped = false;

  public String getName()
  {
    return NAME;
  }

  public String getDescription()
  {
    return DESCRIPTION;
  }

	public boolean isPaused()
	{
		return paused;
	}

	public boolean isStopped()
	{
		return stopped;
	}

	public boolean isFiring()
	{
		return !paused && !stopped;
	}

	public void preFire(Process process)
	{
		synchronized(this)
		{
			paused = false;
			process.preFire();
		}
	}

	public boolean canFire(Process process,Map data)
	{
		synchronized(this)
		{
			return (!pause && !stop && process.isFireable(data));
		}
	}

	public void fire(Process process,Map data)
	{
		process.fire(data);
	}

	public void postFire(Process process)
	{
		synchronized(this)
		{
			if (pause)
				paused = true;
			if (stop)
				stopped = true;
			this.notifyAll();
		}
	}

	public void put(OutputPort port,Object token)
	{
		port.put(token);
	}

	public Object get(InputPort port)
	{
		return port.get();
	}

	public void pause(Process process)
	{
		synchronized(this)
		{
			if (paused)
				return;
			if (stopped)
				throw new IllegalStateException();

			pause = true;
			while (!paused && !stopped)
				try{wait();}catch (Exception e) {}
		}
	}

	public void resume(Process process)
	{
		synchronized(this)
		{
			if (stopped)
				throw new IllegalStateException();
			if (!paused)
				return;

			pause = false;
		}
	}

	public void stop(Process process)
	{
		synchronized(this)
		{
			if (paused)
				stopped = true;

			stop = true;
			while(!stopped)
				try{wait();}catch (Exception e) {}
		}
	}

	public String toString()
	{
		return "Dataflow";
	}
}

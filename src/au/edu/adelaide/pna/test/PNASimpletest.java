package au.edu.adelaide.pna.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import au.edu.adelaide.kahn.pn.Builder;
import au.edu.adelaide.kahn.pn.Factory;
import au.edu.adelaide.kahn.pn.Network;
import au.edu.adelaide.kahn.pn.Process;
import au.edu.adelaide.pna.processes.TokenPrinter;
import au.edu.adelaide.pna.processes.TokenProducer;
import au.edu.adelaide.pna.system.factory.PNACompFactory;
import au.edu.adelaide.pna.system.factory.PNAKahnFactory;
import au.edu.adelaide.pna.system.factory.PNAMOPFactory;

public class PNASimpletest {
	public static void main(String[] args) {
		/* setup the metalevel and customizations */
		/*
		 * decide which factory do you need? 
		 * 1- Just running kahn process netwrok
		 * 2-running kahn process netwrok with metaObjectProtocol
		 * 3-running kahn process netwrok with computation notation in
		 * MetaObject Protocol
		 */
//		 Factory factory = PNAKahnFactory.newInstance();
		 Factory factory = PNAMOPFactory.newInstance();
//	 	 Factory factory = PNACompFactory.newInstance();

		// create a network
		Network network = factory.newNetwork(factory);
		Builder builder = factory.newBuilder(network);

		// create the processes
		// Integer Generator
		Process producer = factory.newProcess(network,"au.edu.adelaide.pna.processes.TokenProducer");
		HashMap producerProperties = new HashMap();
		producerProperties.put("Initial Value", "1");
		producerProperties.put("Upper Bound", "5");
		producer.updateProperties(producerProperties);
		builder.add(producer);

		// print network result
		Process consumer = factory.newProcess(network,"au.edu.adelaide.pna.processes.TokenPrinter");
		builder.add(consumer);

		// connect the producer and consumder - this would be done via the builder
//		builder.connect(producer, 0,consumer,0);
		builder.connect(producer, producer.getOutputPort(0), consumer,
				consumer.getInputPort(0));
		// ---------------------------------
		// start computation
		builder.trigger();
		try {
			Thread.sleep(1100);
			System.out.println("Exiting");
			System.exit(0);
		} catch (Exception e) {
		}
	}
}

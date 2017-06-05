package com.itemshop.utilities.lotto;

import java.util.ArrayList;
import java.util.Random;

/**
 * @param <T>
 */
public class Lotto<T> {
	/** The list of participants. */
	private ArrayList<Participant<T>> participants = new ArrayList<Participant<T>>();
	/** The RNG to use when drawing winners. */
	private Random rng = new Random();
	
	/**
	 * Create a new instance of the Lotto class.
	 */
	public Lotto() {}
	
	/**
	 * Create a new instance of the Lotto class.
	 * @param random
	 */
	public Lotto(Random random) { this.rng = random; }
	
	/**
	 * Create a new instance of the Lotto class.
	 * @param seed
	 */
	public Lotto(long seed) { rng.setSeed(seed); }
	
	/**
	 * Add a participant with a single ticket.
	 * @param participant
	 * @returns this
	 */
	public Lotto<T> add(T participant) { 
		participants.add(new Participant<T>(participant)); 
		return this;
	}
	
	/**
	 * Add a participant with a number of tickets.
	 * @param participant
	 * @param tickets
	 * @returns this
	 */
	public Lotto<T> add(T participant, int tickets) { 
		participants.add(new Participant<T>(participant, tickets)); 
		return this;
	}
	
	/**
	 * Draw a winner.
	 * @return winner
	 */
	public T draw() {
		// Create a pot of all tickets.
		ArrayList<T> tickets = new ArrayList<T>();
		for(Participant<T> participant : participants) {
			for(int ticketIndex = 0; ticketIndex < participant.getTickets(); ticketIndex++) {
				tickets.add(participant.getParticipant());
			}
		}
		// Check to make sure we even have any tickets.
		if(tickets.isEmpty()) {
			throw new NoTicketsRuntimeException();
		}
		// Pick a winner!
		return tickets.get(this.rng.nextInt(tickets.size()));
	}
}

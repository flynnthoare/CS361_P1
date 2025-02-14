package fa.dfa;

import fa.State;

import java.util.*;

public class DFA implements DFAInterface {
    // the states store isFinal, so contains F
    private LinkedHashSet<DFAState> states;
    // sigma includes the total alphabet
    private LinkedHashSet<Character> sigma;
    // transitionTable maps from a state name to the list of state names that occur depending on the transition
    private Map<String, Map<Character, String>> transitionTable;

    // Store start state
    private DFAState start;


    /**
     * Constructor for a new DFA.
     */
    public DFA() {
        states = new LinkedHashSet<>();
        sigma = new LinkedHashSet<>();
        transitionTable = new HashMap<>();

        start = null;
    }

    /** 
     * Adds a new state to the DFA.
     * @param name The name of the state to add.
     * @return true if the state was added successfully, false if it already exists.
     */
    @Override
    public boolean addState(String name) {
        // Cannot have the name of an existing state
        if (getState(name) != null)
            return false;

        // Create a new state with this name
        DFAState state = new DFAState(name);
        states.add(state);
        return true;
    }

    /**
     * Marks a state as a final (accepting) state.
     * @param name The name of the state to set as final.
     * @return true if the state was successfully set as final, false if the state does not exist.
     */
    @Override
    public boolean setFinal(String name) {
        DFAState state = getState(name);
        // Cannot set a nonexistent state to final
        if (state == null)
            return false;

        // Set the state to final
        state.makeFinal();
        return true;
    }

    /**
     * Sets the start state of the DFA.
     * @param name The name of the state to set as the start state.
     * @return true if successfully set, false if the state does not exist.
     */
    @Override
    public boolean setStart(String name) {
        DFAState state = getState(name);
        // Cannot set a nonexistent state to start
        if (state == null)
            return false;

        // Ensure no other states are marked as isStart
        for (DFAState other : states)
            other.setStart(false);

        // Set the start state to this state
        state.setStart(true);
        start = state;
        return true;
    }

    /**
     * Adds a character to the DFA's alphabet.
     * @param symbol The character to add to the alphabet.
     */
    @Override
    public void addSigma(char symbol) {
        // Add the symbol to sigma
        sigma.add(symbol);
    }

    /**
     * Determines whether the DFA accepts a given input string.
     * @param s The input string.
     * @return true if the DFA accepts the string, false otherwise.
     */
    @Override
    public boolean accepts(String s) {
        // This tests if the string is accepted by the machine
        
        if (start == null) {
            return false;
        }

        // start at start state
        String currentState = start.getName();

        // loop through string
        for (char c : s.toCharArray()) {
            // extra check just in case the DFA isn't set up correctly
            if (!transitionTable.containsKey(currentState)) {
                return false;
            }
            // get next state from key
            String nextState = transitionTable.get(currentState).get(c);

            // check if transition exists
            if (nextState == null) {
                return false;
            }
            //set next state to current and continue looping through string
            currentState = nextState;
        }

        // return true if ending state is a final state and false if not
        return getState(currentState).isFinal();
    }

    /**
     * Retrieves the DFA's alphabet
     * @return A set containing all characters in the DFA's alphabet.
     */
    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    /**
     * Retrieves a state by its name.
     * @param name The name of the state to retrieve.
     * @return The DFAState object corresponding to the given name, or null if not found.
     */
    @Override
    public DFAState getState(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name))
                return state;
        }

        return null;
    }

    /**
     * Checks if a state is a final (accepting) state.
     * @param name The name of the state to check.
     * @return true if the state is final, false otherwise.
     */
    @Override
    public boolean isFinal(String name) {
        DFAState state = getState(name);
        if (state.isFinal()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if a state is the start state.
     * @param name The name of the state to check.
     * @return true if the state is the start state, false otherwise.
     */
    @Override
    public boolean isStart(String name) {
        DFAState state = getState(name);
        if (state.isStart()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
	 * Adds the transition to the DFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param toState is the label of the state where the transition ends
	 * @param onSymb is the symbol from the DFA's alphabet.
	 * @return true if successful and false if one of the states don't exist or the symbol in not in the alphabet
	 */
    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        if (getState(fromState) == null || getState(toState) == null || !sigma.contains(onSymb)) {
            return false;
        }

        // add a new hashmap to transition table if fromState entry doesn't already exist
        transitionTable.putIfAbsent(fromState, new HashMap<>());
        // add symbol to toState transition in inned hashmap for current fromState
        transitionTable.get(fromState).put(onSymb, toState);
        
        return true;
    }

    
    /**
	 * Creates a deep copy of this DFA
	 * which transitions labels are
	 * swapped between symbols symb1
	 * and symb2.
	 * @return a copy of this DFA
	 */
    @Override
    public DFA swap(char symb1, char symb2) {
        DFA swappedDFA = new DFA();
        
        // copy alphabet
        for (char c : this.sigma) {
            swappedDFA.addSigma(c);
        }

        //Copy States
        for (DFAState state : this.states) {
            swappedDFA.addState(state.getName());

            if (state.isFinal()) {
                swappedDFA.setFinal(state.getName());
            }
            if (state.isStart()) {
                swappedDFA.setStart(state.getName());
            }
        }

        // copy transitions with symbol swaps
        for (Map.Entry<String, Map<Character, String>> transition : this.transitionTable.entrySet()) {
            
            // get current fromState and map of transitions associated with it
            String fromState = transition.getKey();
            Map<Character, String> transitions= transition.getValue();

            // create new transition map for swap
            HashMap<Character, String> swappedTransitions = new HashMap<>();

            for (Map.Entry<Character, String> transitionEntry : transitions.entrySet()) {
                // extract symbol and toState for the current transition we are swapping
                char symbol = transitionEntry.getKey();
                String toState = transitionEntry.getValue();

                // swap out symbols
                char newSymbol = (symbol == symb1) ? symb2 : (symbol == symb2) ? symb1: symbol;

                // add the transition with new symbol and same toState
                swappedTransitions.put(newSymbol, toState);
            }

            // add the swappedTransitions inner HashMap to the outer HashMap for each outter entry
            swappedDFA.transitionTable.put(fromState, swappedTransitions);
        }

        return swappedDFA;
    }

    /**
	 * Construct the textual representation of the DFA, for example
	 * A simple two state DFA
	 * Q = { a b }
	 * Sigma = { 0 1 }
	 * delta =
	 *		0	1	
	 *	a	a	b	
	 *	b	a	b	
	 * q0 = a
	 * F = { b }
	 * 
	 * The order of the states and the alphabet is the order
	 * in which they were instantiated in the DFA.
	 * @return String representation of the DFA
	 */
    @Override
    public String toString() {
        // Build the Q set
        String qText = " Q = { ";
        for (DFAState state : states) {
            String name = state.getName();
            qText += name + " ";
        }
        qText += "}\n";

        // Build the Sigma set and delta transition table first row
        String sigmaText = "Sigma = { ";
        String deltaText = "delta =\n\t\t";
        for (char c : sigma) {
            sigmaText += c + " ";
            deltaText += c + "\t";
        }
        sigmaText += "}\n";
        deltaText += "\n";

        // Build the rest of the transition table
        // Loop through all states (This set is ordered)
        for (DFAState state : states) {
            String key = state.getName();
            deltaText += "\t" + key;

            // Loop through all transitions (This set is also ordered)
            for (char transition : sigma) {
                String stateName = transitionTable.get(key).get(transition);
                deltaText += "\t" + stateName;
            }

            deltaText += "\n";
        }

        // Build q0
        String q0Text = "q0 = " + start.getName() + "\n";

        // Build the F set
        String fText = "F = { ";
        for (DFAState state : states) {
            String name = state.getName();
            if (state.isFinal())
                fText += name + " ";
        }
        fText += "}";

        // Return all values concatenated
        return qText + sigmaText + deltaText + q0Text + fText;
    }
}

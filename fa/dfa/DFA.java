package fa.dfa;

import fa.State;

import java.util.*;

public class DFA implements DFAInterface {
    // the states store isFinal, so contains F
    private Set<DFAState> states = new HashSet<>();
    // sigma includes the total alphabet
    private Set<Character> sigma = new HashSet<>();
    // transitionTable maps from a state name to the list of state names that occur depending on the transition
    private Map<String, String> transitionTable = new HashMap<>();

    // Store current state and start state
    private DFAState start;
    private DFAState current;

    // NICK
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

    @Override
    public boolean setStart(String name) {
        DFAState state = getState(name);
        // Cannot set a nonexistent state to start
        if (state == null)
            return false;

        // Ensure no other states are marked as isStart
        for (DFAState other : states)
            other.setStart(false);

        // Set the start state and current state to this state
        state.setStart(true);
        start = state;
        current = state;
        return true;
    }

    @Override
    public void addSigma(char symbol) {
        // Add the symbol to sigma
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // This tests if the string is accepted by the machine
        // TODO: decide whether to do a recursive function or loop through the string
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    // FLYNN
    @Override
    public DFAState getState(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name))
                return state;
        }

        return null;
    }

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

    //Might need to add additional checks to see if a transition already exists
    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        if (getState(fromState) == null || getState(toState) == null || !sigma.contains(onSymb)) {
            return false;
        }

        String key = fromState + "," + onSymb;
        transitionTable.put(key, toState);
        
        
        return true;
    }

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
        for (Map.Entry<String, String> transition :this.transitionTable.entrySet()) {
            String key = transition.getKey();
            String toState = transition.getValue();

            String[] keyParts = key.split(",");
            String fromState = keyParts[0];
            char symbol = keyParts[1].charAt(0);

            char newSymbol = (symbol == symb1) ? symb2 : (symbol == symb2) ? symb1: symbol;

            swappedDFA.addTransition(fromState, toState, newSymbol);
        }

        return swappedDFA;

        
    }
}

package fa.dfa;

import fa.State;

import java.util.*;

public class DFA implements DFAInterface {
    // the states store isFinal, so contains F
    private Set<DFAState> states = new HashSet<>();
    // sigmas includes the total alphabet
    private Set<Character> sigma = new HashSet<>();
    // transitionTable maps from a state name to the list of state names that occur depending on the transition
    private Map<String, ArrayList<String>> transitionTable = new HashMap<>();

    // Store current state and start state
    private DFAState start;
    private DFAState current;

    // NICK
    @Override
    public boolean addState(String name) {
        // This adds a state to the DFA with a name
        // TODO: create a list to store the states
        return false;
    }

    @Override
    public boolean setFinal(String name) {
        // This finds the given state and sets it to final
        // TODO: update DFAState class to have an isFinal flag
        return false;
    }

    @Override
    public boolean setStart(String name) {
        // This finds the given state and sets it to start
        // TODO: update start state, also setting current state by default
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        // This adds the symbol to the alphabet
        // TODO: create a character list to store the alphabet
    }

    @Override
    public boolean accepts(String s) {
        // This tests if the string is accepted by the machine
        // TODO: decide whether to do a recursive function or loop through the string
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        // This returns the alphabet
        return sigma;
    }

    // FLYNN
    @Override
    public State getState(String name) {
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return false;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }
}

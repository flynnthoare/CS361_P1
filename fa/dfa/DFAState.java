package fa.dfa;

import fa.State;

/**
 * This class represents a deterministic finite automata (DFA) state.
 * This class has a name and some information determining if it is
 * a start or final state. It is used by the DFA class to represent
 * each state in the machine.
 *
 * @author Nick Bortz & Flynn Hoare
 * @since 2025-02-13
 */
public class DFAState extends State {
    private boolean isFinal;
    private boolean isStart;

    /**
     * Constructor for a new DFA state with the given name.
     * @param name The name of the state.
     */
    public DFAState(String name) {
        super(name);
    }

    /**
     * Marks this state as a final (accepting) state.
     */
    public void makeFinal() {
        isFinal = true;
    }

    /**
     * Checks if this state is a final (accepting) state.
     * @return true if the state is final, false otherwise.
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Sets whether this state is the start state of the DFA.
     * @param isStart true if this state is the start state, false otherwise.
     */
    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    /**
     * Checks if this state is the start state of the DFA.
     * @return true if this state is the start state, false otherwise.
     */
    public boolean isStart() {
        return isStart;
    }
}

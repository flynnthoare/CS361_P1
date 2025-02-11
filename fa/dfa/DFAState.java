package fa.dfa;

import fa.State;

public class DFAState extends State {
    // TODO: Setup the flags and name for this state with a child constructor
    private boolean isFinal;

    public DFAState(String name) {
        super(name);
    }

    public void makeFinal() {
        isFinal = true;
    }
}

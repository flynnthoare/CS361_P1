package fa.dfa;

import fa.State;

public class DFAState extends State {
    // TODO: Setup the flags and name for this state with a child constructor
    private boolean isFinal;
    private boolean isStart;

    public DFAState(String name) {
        super(name);
    }

    public void makeFinal() {
        isFinal = true;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isStart() {
        return isStart;
    }
}

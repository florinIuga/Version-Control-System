package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public final class LogOperation extends VcsOperation  {

    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    // prints the commits and the id for the current branch
    @Override
    public int execute(Vcs vcs) {
    	String lastCommitMessage = vcs.getCurrentBranch().getLastCommit().getMessage();
        for (Commit c : vcs.getCurrentBranch().getCommits()) {
            vcs.getOutputWriter().write("Commit id: " + c.getId() + "\n");
            if (!c.getMessage().equals(lastCommitMessage)) {
                vcs.getOutputWriter().write("Message: " + c.getMessage() + "\n\n");
            } else {
            	vcs.getOutputWriter().write("Message: " + c.getMessage() + "\n");
            }

        }
        return ErrorCodeManager.OK;
     }
}

package vcs;
import utils.Staging;
import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public final class RollbackOperation extends VcsOperation  {

    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {

    	ArrayList<Commit> commits = vcs.getCurrentBranch().getCommits();
        // set the current snapshot to the last commit snapshot
    	vcs.setCurrentSnapshot(commits.get(commits.size() - 1).getCommitSnapshot());
        Staging.clearStaging();

       return ErrorCodeManager.OK;
    }
}

package vcs;

import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;
import utils.Staging;

public final class CheckoutOperation extends VcsOperation  {

    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // check if the staging is empty
        if (!Staging.isEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }
        // case 1: when operation looks like vcs checkout branchName

        if (!operationArgs.get(1).equals("-c")) {
            String branchName = operationArgs.get(1);
            if(!vcs.existsBranch(branchName)) {
               return ErrorCodeManager.VCS_BAD_CMD_CODE;
             }

            vcs.setCurrentBranch(branchName);
            // case 2: when operation looks like vcs checkout -c commitId
          } else {
            ArrayList<Commit> currentBranchCommits = vcs.getCurrentBranch().getCommits();
            // check for Id error
            int commitID = Integer.parseInt(operationArgs.get(2));
            boolean found = false;
            for (Commit c : currentBranchCommits) {
                if (c.getId() == commitID) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            }
            // set the current snapshot according to the desired commit id
            for (Commit c : currentBranchCommits) {
                if (c.getId() == commitID) {
                    vcs.setCurrentSnapshot(c.getCommitSnapshot());
                    break;
                 }
            }
            // delete the rest of the commits
            ArrayList<Commit> commitsToDelete = new ArrayList<>();
            for (Commit c : currentBranchCommits) {
                if (c.getId() != commitID) {
                    commitsToDelete.add(c);
                 }
            }
            vcs.getCurrentBranch().getCommits().removeAll(commitsToDelete);
            vcs.getAllCommits().removeAll(commitsToDelete);
        }

        return ErrorCodeManager.OK;
    }

}

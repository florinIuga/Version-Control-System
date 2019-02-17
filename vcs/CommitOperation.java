package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.IDGenerator;
import utils.OperationType;
import utils.Staging;

public final class CommitOperation extends VcsOperation  {

    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
     public int execute(Vcs vcs) {

        if (Staging.isEmpty()) {
           return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        // create the actual message to print
        String commitMessage = "";
        for  (int i = 2; i < operationArgs.size(); ++i) {
               commitMessage = commitMessage.concat(operationArgs.get(i));
               if (operationArgs.get(i).equals("files.")) {
                  break;
               }
                commitMessage = commitMessage.concat(" ");
        }
        // create a new commit with the message created above
        Commit com = new Commit(vcs.getActiveSnapshot(),
        		IDGenerator.generateCommitID(), commitMessage);
        // add the commit to the current branch
        vcs.getCurrentBranch().addCommit(com);
        Staging.clearStaging();
        return ErrorCodeManager.OK;
     }

}

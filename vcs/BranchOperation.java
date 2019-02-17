package vcs;
import utils.ErrorCodeManager;
import java.util.ArrayList;

import utils.OperationType;

public final class BranchOperation extends VcsOperation {

    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
           super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
       // if branch name already exists, return error
       if (vcs.existsBranch(operationArgs.get(1))) {
    	   return ErrorCodeManager.VCS_BAD_CMD_CODE;
       }
       // else, create the new branch with the specific name
       Branch newBranch = new Branch(operationArgs.get(1));
       // add it to the list of branches
       vcs.addBranchInVcs(newBranch);

       return ErrorCodeManager.OK;
    }

}

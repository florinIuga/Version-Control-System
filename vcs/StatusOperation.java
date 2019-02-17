package vcs;
import utils.Staging;
import utils.StagingFormat;
import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public final class StatusOperation extends VcsOperation  {

    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
         super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // get the current branch
        Branch currentBranch = vcs.getCurrentBranch();
        vcs.getOutputWriter().write("On branch: " + currentBranch.getName() + "\n");
        vcs.getOutputWriter().write("Staged changes:\n");
        // get the commands from staging
        ArrayList<StagingFormat> commandsInStagin = Staging.getCommandsInStaging();
        // print the specific action, file name and the text added to file (for writeToFile)
        for (StagingFormat sF : commandsInStagin) {
            if (!sF.getAction().equals("Added ")) {
                vcs.getOutputWriter().write("\t" + sF.getAction() + sF.getFileName() + "\n");
            } else {
                vcs.getOutputWriter().write("\t" + sF.getAction() + "\"" +
                sF.getTextAddedToFile() + "\"" 
                + " to file " + sF.getFileName() + "\n");
            }
        }
        return ErrorCodeManager.OK;
    }

}

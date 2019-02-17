package vcs;
import utils.IDGenerator;
import java.util.ArrayList;
import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private Branch currentBranch;
    private ArrayList<Branch> listOfBranches;
    private ArrayList<Commit> allCommits;
    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        allCommits = new ArrayList<>();
        listOfBranches = new ArrayList<>();
        Branch branchMaster = new Branch("master");
        Commit c1 = new Commit(this.activeSnapshot, IDGenerator.generateCommitID(), "First commit");
        allCommits.add(c1);
        branchMaster.addCommit(c1);
        currentBranch = branchMaster;
        listOfBranches.add(branchMaster);


        //TODO other initialisations
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
       return vcsOperation.execute(this);
    }

    public Branch getCurrentBranch() {
        return this.currentBranch;
    }

    public FileSystemSnapshot getActiveSnapshot() {
       return this.activeSnapshot;
    }

    public OutputWriter getOutputWriter() {
      return this.outputWriter;
    }

    public void addBranchInVcs(Branch b) {
      listOfBranches.add(b);
    }

    public ArrayList<Branch> getListOfBranches() {
      return this.listOfBranches;
    }

    public void setCurrentBranch(String bName) {
       for (Branch b : listOfBranches) {
            if (b.getName().equals(bName)) {
                this.currentBranch = b;
                break;
             }
        }
    }

    public boolean existsBranch(String branchName) {
        int found = 0;
        for (Branch br : listOfBranches) {
            if (br.getName().equals(branchName)) {
                found = 1;
                break;
             }
        }
        if (found == 1) {
            return true;
        }
       return false;
    }

    public void setCurrentSnapshot(FileSystemSnapshot snap) {
        this.activeSnapshot = snap;
    }

    public void addToAllCommits(Commit c) {
        allCommits.add(c);
    }

    public ArrayList<Commit> getAllCommits() {
        return this.allCommits;
    }
    //TODO methods through which vcs operations interact with this
}

package vcs;

import filesystem.FileSystemSnapshot;

public class Commit {
    // each commit has a message, and id and a snapshot
    private String message;
    private int id;
    private FileSystemSnapshot commitSnapshot;

    public Commit(FileSystemSnapshot fileSystem, int id, String message) {

        commitSnapshot = fileSystem.cloneFileSystem();
        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
	}

    public int getId() {
        return this.id;
    }

    public FileSystemSnapshot getCommitSnapshot() { 
        return this.commitSnapshot;
    }
}

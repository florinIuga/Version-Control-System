package vcs;

import java.util.ArrayList;

public final class Branch {
    // each Branch has a name and a list of commits
    private String name;
    private ArrayList<Commit> commits = new ArrayList<>();

    public Branch(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void addCommit(Commit c) {
        commits.add(c);
    }

    public ArrayList<Commit> getCommits() {
	    return this.commits;
    }

    public Commit getLastCommit() {
        return commits.get(commits.size() - 1);
    }
}

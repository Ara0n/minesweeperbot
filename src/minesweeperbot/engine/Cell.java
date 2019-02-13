package minesweeperbot.engine;

protected class Cell {
    private boolean revealed;
    private boolean mine;
    private boolean flagged;
    private int proximity;

    protected Cell() {
        //initialize all values at 0
    }

    protected void toggleFlagged() {
        if (revealed) throw new IllegalStateException("cell is revealed !");
        this.flagged = !this.flagged;
    }

    protected void reveal() {
        if (revealed) throw new IllegalStateException("cell is already revealed");
        this.revealed = true;
    }

    protected void setMine(boolean mine) {
        this.mine = mine;
    }

    protected void setProximity(int proximity) {
        if (mine) throw new IllegalStateException("cell is a mine");
        this.proximity = proximity;
    }

    public boolean isFlagged() {
        return this.flagged;
    }

    public boolean isMine() {
        return this.mine;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public int getProximity() {
        return this.proximity;
    }
}

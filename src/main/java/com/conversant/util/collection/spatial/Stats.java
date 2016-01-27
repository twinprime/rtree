package com.conversant.util.collection.spatial;

import java.io.PrintStream;

/**
 * Created by jcovert on 5/20/15.
 */
public class Stats {

    private RTree.Split type;
    private int maxFill;
    private int minFill;

    private int maxDepth = 0;
    private int branchCount = 0;
    private int leafCount = 0;
    private int entryCount = 0;
    private int[] entriesAtDepth = new int[10000];
    private int[] branchesAtDepth = new int[10000];
    private int[] leavesAtDepth = new int[10000];

    public void print(PrintStream out) {
        out.println("[" + type + "] m=" + minFill + " M=" + maxFill);
        out.println("   Branches (" + branchCount + " total)");
        out.print("      ");
        for (int i = 0; i <= maxDepth; i++) {
            out.print(i + ": " + branchesAtDepth[i] + "  ");
        }
        out.println("\n   Leaves (" + leafCount + " total)");
        out.print("      ");
        for (int i = 0; i <= maxDepth; i++) {
            out.print(i + ": " + leavesAtDepth[i] + "  ");
        }
        out.println("\n   Entries (" + entryCount + " total)");
        out.print("      ");
        for (int i = 0; i <= maxDepth; i++) {
            out.print(i + ": " + entriesAtDepth[i] + "  ");
        }
        out.printf("\n   Leaf Fill Percentage: %.2f%%\n", getLeafFillPercentage());
        out.printf("   Entries per Leaf: %.2f\n", getEntriesPerLeaf());
        out.println("   Max Depth: " + maxDepth);
        out.println();
    }

    public float getEntriesPerLeaf() {
        return ((entryCount * 1.0f) / leafCount);
    }

    public float getLeafFillPercentage() {
        return (getEntriesPerLeaf() * 100) / maxFill;
    }

    public RTree.Split getType() {
        return type;
    }

    public void setType(RTree.Split type) {
        this.type = type;
    }

    public void setMaxFill(int maxFill) {
        this.maxFill = maxFill;
    }

    public void setMinFill(int minFill) {
        this.minFill = minFill;
    }

    public int getBranchCount() {
        return branchCount;
    }

    public int getLeafCount() {
        return leafCount;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void countEntriesAtDepth(int entries, int depth) {
        entryCount += entries;
        entriesAtDepth[depth] += entries;
    }

    public void countLeafAtDepth(int depth) {
        leafCount++;
        leavesAtDepth[depth]++;
    }

    public void countBranchAtDepth(int depth) {
        branchCount++;
        branchesAtDepth[depth]++;
    }
}
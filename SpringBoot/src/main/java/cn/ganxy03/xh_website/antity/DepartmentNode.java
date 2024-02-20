package cn.ganxy03.xh_website.antity;

import java.util.List;

public class DepartmentNode {
    private String label;
    private int priority;
    private int nums;
    private List<DepartmentNode> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public List<DepartmentNode> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentNode> children) {
        this.children = children;
    }

    // 可选的toString方法，用于简化输出到控制台的调试信息
    @Override
    public String toString() {
        return "DepartmentNode{" +
                "label='" + label + '\'' +
                ", priority=" + priority +
                ", nums=" + nums +
                ", children=" + children +
                '}';
    }
}

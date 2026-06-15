package model;

public class Group {

    private int groupId;
    private String groupName;
    private int createdBy;
    private String createdAt;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(
            int groupId) {

        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(
            String groupName) {

        this.groupName = groupName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(
            int createdBy) {

        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            String createdAt) {

        this.createdAt = createdAt;
    }
}
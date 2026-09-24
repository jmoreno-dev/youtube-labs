package com.josemorenodevs.recordsvsclasses.basic;

public record UserRecord(
        Long id,
        String name,
        String email
)implements Comparable<UserRecord> {
    public String displayName() {
        return id + " - " + name;
    }
    @Override
    public int compareTo(UserRecord other) {
        return this.id.compareTo(other.id);
    }
}


package Library;

import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int memberId;
    private final String name;

    public Member(int memberId, String name) {
        if (memberId <= 0) {
            throw new IllegalArgumentException(
                "Member ID must be a positive number."
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Member name is required."
            );
        }

        this.memberId = memberId;
        this.name = name.trim();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void displayDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
    }
}
